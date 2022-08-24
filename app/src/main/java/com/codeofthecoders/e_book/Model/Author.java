package com.codeofthecoders.e_book.Model;

public class Author {

    String name,a_img,a_book,id;

    public Author() {
    }

    public Author(String id,String name, String a_img, String a_book) {
        this.name = name;
        this.id = id;
        this.a_img = a_img;
        this.a_book = a_book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getA_img() {
        return a_img;
    }

    public void setA_img(String a_img) {
        this.a_img = a_img;
    }

    public String getA_book() {
        return a_book;
    }

    public void setA_book(String a_book) {
        this.a_book = a_book;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
