package com.example.serega.pocketnews.models;

import com.orm.SugarRecord;

public class News extends SugarRecord{

    private String title;
    private String photo;
    private String text;

    public News() {
    }

    public News(String title, String photo, String text) {
        this.title = title;
        this.photo = photo;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", photo='" + photo + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
