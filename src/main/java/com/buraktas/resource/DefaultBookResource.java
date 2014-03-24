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
    public BookEntity updateBook(int id, String title, String author, double price) {

        if (list.containsKey(id)) {

            BookEntity updateEntity = list.get(id);

            updateEntity.setTitle(title);
            updateEntity.setTitle(author);
            updateEntity.setPrice(price);

            return updateEntity;
        } else {

            return null;
        }
    }

    @Override
    public BookEntity deleteBook(int id) {

        if (list.containsKey(id)) {

            BookEntity deleteEntity = list.get(id);
            list.remove(id);

            return deleteEntity;
        } else {

            return null;
        }
    }

}
