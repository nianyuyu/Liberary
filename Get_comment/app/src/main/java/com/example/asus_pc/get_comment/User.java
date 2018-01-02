package com.example.asus_pc.get_comment;

/**
 * Created by asus-pc on 2015/11/30.
 */
public class User {
    private String name;
    private int imageId;

    public User(String name,int imageId){
        this.name=name;
        this.imageId=imageId;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}

