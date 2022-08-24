package com.codeofthecoders.e_book.Model;

public class Category {

    String id;
    String cat_name;

    public Category(){

    }

    public Category(String id, String cat_name) {
        this.id = id;
        this.cat_name = cat_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }
}
