package com.buraktas.entity;

public class BookEntity {

    private int    id;
    private String title;
    private String author;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Id : " + this.id + '\n');
        stringBuilder.append("Title : " + this.title + '\n');
        stringBuilder.append("Author : " + this.author + '\n');
        stringBuilder.append("Price : " + this.price + '\n');

        return stringBuilder.toString();
    }
}
