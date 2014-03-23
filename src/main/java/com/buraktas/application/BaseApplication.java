package com.buraktas.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.buraktas.resource.DefaultBookResource;

@ApplicationPath("")
public class BaseApplication extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public BaseApplication() {

        singletons.add(new DefaultBookResource());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
