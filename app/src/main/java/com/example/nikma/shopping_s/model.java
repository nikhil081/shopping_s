package com.example.nikma.shopping_s;

public class model {
    private String name;
    private String desc;
    private String photourl;

    public model(String name, String photourl,String desc) {
        this.name = name;
        this.photourl = photourl;
        this.desc = desc;
    }
    public model(){

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

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

}
