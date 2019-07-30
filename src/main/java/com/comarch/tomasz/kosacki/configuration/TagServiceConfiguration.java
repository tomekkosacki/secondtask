package com.comarch.tomasz.kosacki.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class TagServiceConfiguration extends Configuration {

    @NotEmpty
    private String password;

    @NotEmpty
    private String dbname;

    @JsonProperty
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty
    public String getDbname() {
        return dbname;
    }

    @JsonProperty
    public void setDbname(String dbname) {
        this.dbname = dbname;
    }
}
