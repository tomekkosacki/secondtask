package com.comarch.tomasz.kosacki;

import com.comarch.tomasz.kosacki.configuration.TagServiceConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class TagsServiceApp extends Application<TagServiceConfiguration> {

    public static void main(String[] args) throws Exception {

        new TagsServiceApp().run(args);
    }

    @Override
    public void run(TagServiceConfiguration configuration, Environment environment) throws Exception {

    }
}
