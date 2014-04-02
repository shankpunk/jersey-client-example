package com.buraktas;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.buraktas.entity.BookEntity;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class DefaultBookResourceManualTest {

    private static final String POST_INPUT_1 = "{\"title\" : \"LOTR\",\"author\" : \"Tolkien\",\"price\" : 12.99}";
    private static final String POST_INPUT_2 = "{\"title\" : \"TheSoulforge\",\"author\" : \"MargaretWeis\",\"price\" : 15.99}";
    private static final String POST_INPUT_3 = "{\"title\" : \"Exile\",\"author\" : \"Salvadore\",\"price\" : 15.99}";

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
        clearBookList();
    }

    @Test
    public void testCreateSuccess() throws Exception {

        WebResource webResourcePost = client.resource("http://localhost:8080/jersey-rest-client/services/book-service/create");
        ClientResponse response = webResourcePost.accept("application/json").type("application/json").post(ClientResponse.class, POST_INPUT_1);
        BookEntity responseEntity = response.getEntity(BookEntity.class);

        assertEquals(200, response.getStatus());
        assertEquals("LOTR", responseEntity.getTitle());
        assertEquals("Tolkien", responseEntity.getAuthor());
        assertEquals(12.99, responseEntity.getPrice(), 0.0001);
    }

    @Test
    public void testCreateFromPath() throws Exception {

        WebResource webResourcePost = client.resource("http://localhost:8080/jersey-rest-client/services/book-service/create")
                                            .path("/title/TheSoulforge")
                                            .path("/author/MargaretWeis")
                                            .path("/price/12.99");

        ClientResponse response = webResourcePost.accept("application/json").post(ClientResponse.class);

        BookEntity responseEntity = response.getEntity(BookEntity.class);

        assertEquals(200, response.getStatus());
        assertEquals("TheSoulforge", responseEntity.getTitle());
        assertEquals("MargaretWeis", responseEntity.getAuthor());
        assertEquals(12.99, responseEntity.getPrice(), 0.0001);
    }

    @Test
    public void testFindSuccess() throws Exception {

        WebResource webResourcePost = client.resource("http://localhost:8080/jersey-rest-client/services/book-service/create");
        ClientResponse response = webResourcePost.accept("application/json").type("application/json").post(ClientResponse.class, POST_INPUT_1);
        assertEquals(200, response.getStatus());

        response = webResourcePost.accept("application/json").type("application/json").post(ClientResponse.class, POST_INPUT_2);
        assertEquals(200, response.getStatus());

        response = webResourcePost.accept("application/json").type("application/json").post(ClientResponse.class, POST_INPUT_3);
        assertEquals(200, response.getStatus());

        WebResource webResourceGet = client.resource("http://localhost:8080/jersey-rest-client/services/book-service/find").queryParam("id", "2");
        response = webResourceGet.accept("application/json").get(ClientResponse.class);

        BookEntity responseEntity = response.getEntity(BookEntity.class);

        assertEquals(200, response.getStatus());
        assertEquals("Exile", responseEntity.getTitle());
        assertEquals("Salvadore", responseEntity.getAuthor());
        assertEquals(15.99, responseEntity.getPrice(), 0.0001);
    }

    @Test
    public void testFindAll() throws Exception {

        WebResource webResourcePost = client.resource("http://localhost:8080/jersey-rest-client/services/book-service/create");
        ClientResponse response = webResourcePost.accept("application/json").type("application/json").post(ClientResponse.class, POST_INPUT_1);
        assertEquals(200, response.getStatus());

        response = webResourcePost.accept("application/json").type("application/json").post(ClientResponse.class, POST_INPUT_2);
        assertEquals(200, response.getStatus());

        response = webResourcePost.accept("application/json").type("application/json").post(ClientResponse.class, POST_INPUT_3);
        assertEquals(200, response.getStatus());

        WebResource webResourceGet = client.resource("http://localhost:8080/jersey-rest-client/services/book-service/list");
        Map<Integer, BookEntity> list = webResourceGet.accept("application/json").get(new GenericType<Map<Integer, BookEntity>>() {
        });

        assertEquals(3, list.size());
    }

    @Test
    public void testUpdateSuccess() throws Exception {

        WebResource webResourcePost = client.resource("http://localhost:8080/jersey-rest-client/services/book-service/create");
        ClientResponse response = webResourcePost.accept("application/json").type("application/json").post(ClientResponse.class, POST_INPUT_1);
        assertEquals(200, response.getStatus());

        response = webResourcePost.accept("application/json").type("application/json").post(ClientResponse.class, POST_INPUT_2);
        assertEquals(200, response.getStatus());

        response = webResourcePost.accept("application/json").type("application/json").post(ClientResponse.class, POST_INPUT_3);
        assertEquals(200, response.getStatus());

        WebResource webResourcePut = client.resource("http://localhost:8080/jersey-rest-client/services/book-service/update")
                                           .path("/id/2")
                                           .path("/title/HarryPotter")
                                           .path("/author/Rowling")
                                           .path("/price/7.88");

        response = webResourcePut.accept("application/json").put(ClientResponse.class);

        BookEntity responseEntity = response.getEntity(BookEntity.class);

        assertEquals(200, response.getStatus());
        assertEquals("HarryPotter", responseEntity.getTitle());
        assertEquals("Rowling", responseEntity.getAuthor());
        assertEquals(7.88, responseEntity.getPrice(), 0.000001);
    }

    @Test
    public void testUpdateFail() throws Exception {

        WebResource webResourcePut = client.resource("http://localhost:8080/jersey-rest-client/services/book-service/update")
                                           .path("/id/2")
                                           .path("/title/HarryPotter")
                                           .path("/author/Rowling")
                                           .path("/price/7.88");

        ClientResponse response = webResourcePut.accept("application/json").put(ClientResponse.class);

        assertEquals(204, response.getStatus());
    }

    @Test
    public void testDeleteSuccess() throws Exception {

        WebResource webResourcePost = client.resource("http://localhost:8080/jersey-rest-client/services/book-service/create");
        ClientResponse response = webResourcePost.accept("application/json").type("application/json").post(ClientResponse.class, POST_INPUT_1);
        assertEquals(200, response.getStatus());

        response = webResourcePost.accept("application/json").type("application/json").post(ClientResponse.class, POST_INPUT_2);
        assertEquals(200, response.getStatus());

        response = webResourcePost.accept("application/json").type("application/json").post(ClientResponse.class, POST_INPUT_3);
        assertEquals(200, response.getStatus());

        WebResource webResourceDelete = client.resource("http://localhost:8080/jersey-rest-client/services/book-service/delete").path("/id/1");
        response = webResourceDelete.accept("application/json").delete(ClientResponse.class);

        BookEntity responseEntity = response.getEntity(BookEntity.class);

        assertEquals(200, response.getStatus());
        assertEquals("TheSoulforge", responseEntity.getTitle());
        assertEquals("MargaretWeis", responseEntity.getAuthor());
        assertEquals(15.99, responseEntity.getPrice(), 0.000001);
    }

    @Test
    public void testDeleteFail() throws Exception {

        WebResource webResourceDelete = client.resource("http://localhost:8080/jersey-rest-client/services/book-service/delete").path("/id/2");
        ClientResponse response = webResourceDelete.accept("application/json").delete(ClientResponse.class);

        assertEquals(204, response.getStatus());
    }

    @Test
    public void testDeleteAll() throws Exception {

        WebResource webResourcePost = client.resource("http://localhost:8080/jersey-rest-client/services/book-service/deleteAll");
        ClientResponse response = webResourcePost.delete(ClientResponse.class);
        assertEquals(204, response.getStatus());
    }

    private void clearBookList() {

        WebResource webResourcePost = client.resource("http://localhost:8080/jersey-rest-client/services/book-service/deleteAll");
        webResourcePost.delete(ClientResponse.class);
    }

}
