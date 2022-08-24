package com.codeofthecoders.e_book.Model;

public class ReadBook {


    String id, name, img_url, rate, category, subcategory, by_name,Desc;

    public ReadBook() {
    }

    public ReadBook(String id, String name, String img_url, String rate, String category, String subcategory, String by_name,String Desc) {
        this.id = id;
        this.name = name;
        this.img_url = img_url;
        this.rate = rate;
        this.category = category;
        this.subcategory = subcategory;
        this.by_name = by_name;
        this.Desc = Desc;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getBy_name() {
        return by_name;
    }

    public void setBy_name(String by_name) {
        this.by_name = by_name;
    }
}
