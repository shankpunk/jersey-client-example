package com.buraktas;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.buraktas.entity.BookEntity;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class TestClass {

    private static final String POST_INPUT = "{\"title\" : \"LOTR\",\"author\" : \"J.K.Rowling\",\"price\" : 12.99}";
    private static Client       client;

    @Before
    public void setUp() {

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        client = Client.create(clientConfig);
    }

    @After
    public void close() {

        client.destroy();
    }

    @Test
    public void testCreateSuccess() throws Exception {

        WebResource webResourcePost = client.resource("http://localhost:8080/jersey-rest-client/services/book-service/create");
        ClientResponse response = webResourcePost.accept("application/json").type("application/json").post(ClientResponse.class, POST_INPUT);
        BookEntity responseEntity = response.getEntity(BookEntity.class);

        assertEquals(200, response.getStatus());
        assertEquals("LOTR", responseEntity.getTitle());
        assertEquals("J.K.Rowling", responseEntity.getAuthor());
        assertEquals(12.99, responseEntity.getPrice(), 0.0001);

    }
}
