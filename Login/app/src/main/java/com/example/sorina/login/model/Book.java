package com.example.sorina.login.model;

public class Book {

    private int id;
    private String title;
    private String author;
    private String description;
    private int image;
    private int viewed;
    private boolean favorite;

    public Book(){}

    public Book(int id, String title, String author, String description, int image, int viewed, boolean favorite){
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.image = image;
        this.viewed = viewed;
        this.favorite = favorite;
    }

    public int getId(){
        return this.id;
    }

    public int getImage(){
        return this.image;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getDescription(){
        return this.description;
    }

    public int getViewed() { return this.viewed; }

    public void setViewed(int viewed) { this.viewed = viewed ; }

    public boolean getFavorite() { return this.favorite; }

    public void setFavorite(boolean favorite) { this.favorite = favorite ; }

}
