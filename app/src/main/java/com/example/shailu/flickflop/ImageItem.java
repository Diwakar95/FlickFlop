package com.example.shailu.flickflop;

import android.graphics.Bitmap;

/**
 * Created by shailu on 10/6/16.
 */

public class ImageItem {

    int position;
    String owner;
    Bitmap photo;
    String id;
    String secret;
    String server;
    String farm;
    String imageUrl;
    String title;
    String ownerName;
    
    
//
//    public ImageItem(String id, String owner, String secret, String server, String farm) {
//        super();
//
//        this.id=id;
//        this.owner=owner;
//        this.secret=secret;
//        this.server=server;
//        this.farm = farm;
//
//
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }


    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }



    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


}
