package com.db.am.bauhaus.project.services;


import net.thucydides.core.util.SystemEnvironmentVariables;

/**
 * Created by oluseunorebajo on 26/10/2017.
 */
public class Service {

    public static String constructUrl(String endpoint){
        return SystemEnvironmentVariables.createEnvironmentVariables().getProperty("api.uri") + endpoint;
    }
}
