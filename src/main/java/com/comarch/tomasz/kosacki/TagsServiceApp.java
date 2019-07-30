package com.comarch.tomasz.kosacki;

import com.comarch.tomasz.kosacki.authentication.AuthUser;
import com.comarch.tomasz.kosacki.authentication.TagAppAuthenticator;
import com.comarch.tomasz.kosacki.authentication.TagAppAuthorizer;
import com.comarch.tomasz.kosacki.configuration.TagServiceConfiguration;
import com.comarch.tomasz.kosacki.healthcheck.TagServiceHeathCheck;
import com.comarch.tomasz.kosacki.mapper.TagMapper;
import com.comarch.tomasz.kosacki.resources.TagResources;
import com.comarch.tomasz.kosacki.service.TagService;
import com.comarch.tomasz.kosacki.serviceException.AppExceptionMapper;
import com.comarch.tomasz.kosacki.tagDB.TagDb;
import com.comarch.tomasz.kosacki.tagEntity.TagEntity;
import com.mongodb.MongoClient;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class TagsServiceApp extends Application<TagServiceConfiguration> {

    public static void main(String[] args) throws Exception {

        new TagsServiceApp().run(args);
    }

    @Override
    public void run(TagServiceConfiguration configuration, Environment environment) {

        final Morphia morphia = new Morphia();
        morphia.map(TagEntity.class);
        MongoClient mongoClient = new MongoClient();
        final Datastore datastore = morphia.createDatastore(mongoClient, configuration.getDbname());
        datastore.ensureIndexes();

        TagDb tagDb =new TagDb(datastore);
        TagMapper mapper = new TagMapper();
        TagService tagService = new TagService(tagDb, mapper);
        final TagResources tagResources = new TagResources(tagService);

        environment.jersey().register(tagResources);
        environment.jersey().register(new AppExceptionMapper());
        environment.healthChecks().register("ServiceHealthCheck", new TagServiceHeathCheck(mongoClient));

        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<AuthUser>()
                .setAuthenticator(new TagAppAuthenticator(configuration))
                .setAuthorizer(new TagAppAuthorizer())
                .setRealm("BASIC-AUTH-REALM")
                .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(AuthUser.class));
    }
}
