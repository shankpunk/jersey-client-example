package com.buraktas.resource;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.buraktas.entity.BookEntity;

public class DefaultBookResource implements BookResource {

    private Map<Integer, BookEntity> list      = new HashMap<Integer, BookEntity>();
    private AtomicInteger            idCounter = new AtomicInteger();

    @Override
    public Map<Integer, BookEntity> getBookList() {

        return list;
    }

    @Override
    public BookEntity createBook(String title, String author, double price) {

        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor(author);
        bookEntity.setTitle(title);
        bookEntity.setPrice(price);
        bookEntity.setId(idCounter.getAndIncrement());

        list.put(bookEntity.getId(), bookEntity);

        return bookEntity;
    }

    @Override
    public BookEntity createBook(BookEntity bookEntity) {

        bookEntity.setId(idCounter.getAndIncrement());
        list.put(bookEntity.getId(), bookEntity);

        return bookEntity;
    }

    @Override
    public BookEntity findBook(int id) {

        return list.get(id);
    }

    @Override
    public void updateBook(int id, String name, String lastName, int age) {
        // TODO Auto-generated method stub

    }

}
