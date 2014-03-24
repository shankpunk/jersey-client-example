package com.buraktas.resource;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.buraktas.entity.BookEntity;

@Path("book-service")
public interface BookResource {

    @GET
    @Path("/list")
    @Produces({ MediaType.APPLICATION_JSON })
    public Map<Integer, BookEntity> getBookList();

    @GET
    @Path("/find")
    @Produces({ MediaType.APPLICATION_JSON })
    public BookEntity findBook(@QueryParam("id") int id);

    @POST
    @Path("/create/title/{title}/author/{author}/price/{price}")
    @Produces({ MediaType.APPLICATION_JSON })
    public BookEntity createBook(@PathParam("title") String title, @PathParam("author") String author, @PathParam("price") double price);

    @POST
    @Path("/create")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public BookEntity createBook(BookEntity bookEntity);

    @PUT
    @Path("/update")
    @Produces({ MediaType.APPLICATION_JSON })
    public BookEntity updateBook(@PathParam("id") int id,
                                 @PathParam("title") String title,
                                 @PathParam("author") String author,
                                 @PathParam("price") double price);

    @DELETE
    @Path("/delete")
    @Produces({ MediaType.APPLICATION_JSON })
    public BookEntity deleteBook(@QueryParam("id") int id);
}
