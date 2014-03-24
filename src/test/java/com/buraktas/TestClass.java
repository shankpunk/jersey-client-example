package com.buraktas;

import org.junit.Test;

import com.buraktas.entity.BookEntity;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class TestClass {

    @Test
    public void testName() throws Exception {

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);
        WebResource webResource = client.resource("http://localhost:8080/rest-examples/customer-service/list");

        BookEntity entity = webResource.accept("application/json").get(BookEntity.class);

        System.out.println("HELLO WORLD TEST");

    }
}
