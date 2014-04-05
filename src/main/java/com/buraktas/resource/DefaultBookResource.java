package com.buraktas.resource;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.buraktas.entity.BookEntity;

public class DefaultBookResource implements BookResource {

    private Map<Integer, BookEntity> list      = new HashMap<Integer, BookEntity>();
    private AtomicInteger            idCounter = new AtomicInteger();

    public DefaultBookResource() {

        BookEntity bookEntity1 = new BookEntity();
        bookEntity1.setId(idCounter.getAndIncrement());
        bookEntity1.setAuthor("MargaretWeis");
        bookEntity1.setTitle("TheSoulforge");
        bookEntity1.setPrice(7.99);

        BookEntity bookEntity2 = new BookEntity();
        bookEntity2.setId(idCounter.getAndIncrement());
        bookEntity2.setAuthor("Salvatore");
        bookEntity2.setTitle("Exile");
        bookEntity2.setPrice(9.99);

        list.put(bookEntity1.getId(), bookEntity1);
        list.put(bookEntity2.getId(), bookEntity2);
    }

    @Override
    public Map<Integer, BookEntity> getBookList() {

        return list;
    }

    @Override
    public BookEntity findBook(int id) {

        return list.get(id);
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
    public BookEntity updateBook(int id, String title, String author, double price) {

        if (list.containsKey(id)) {

            BookEntity updateEntity = list.get(id);

            updateEntity.setTitle(title);
            updateEntity.setAuthor(author);
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

    @Override
    public void deleteAll() {

        list.clear();
        idCounter.set(0);
    }

}
