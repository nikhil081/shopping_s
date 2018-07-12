package com.example.nikma.shopping_s;

public class model {
    private String name;
    private String desc;
    private String photourl;
    private Integer price;

    public model(String name, String photourl,String desc,Integer price) {
        this.name = name;
        this.photourl = photourl;
        this.desc = desc;
        this.price = price;
    }
    public model(){

    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }
}
