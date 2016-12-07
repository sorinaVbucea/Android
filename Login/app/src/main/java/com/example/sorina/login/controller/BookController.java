package com.example.sorina.login.controller;

import com.example.sorina.login.R;
import com.example.sorina.login.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookController {

    private static List<Book> books = new ArrayList<Book>();

    static {
        books = getInitialDatabase();
    }

    private static List<Book> getInitialDatabase(){
        books.add(new Book(0, "The Catcher in the Rye", "J.D.Salinger", "Since his debut in 1951 as The Catcher in the Rye, " +
                "Holden Caulfield has been synonymous with \"cynical adolescent\".", R.mipmap.book1, 0, false));
        books.add(new Book(1, "Amintiri din Copilarie", "Ion Creanga", "Cartea ofera o relatare detaliata a copilariei lui Ion Creanga, " +
                "petrecuta in ceea ce era atunci Principatul Moldovei, cu amanunte privind peisajul.", R.mipmap.book2, 0, false));
        books.add(new Book(2, "Exilul si imparatia", "Albert Carnus", "Un univers in care granita dintre fictiune si realitate nu e vizibila, " +
                "ceea ce face cu atat mai atragatoare lectura.", R.mipmap.book3, 0, false));
        return books;
    }

    public boolean newAddedBook(){
        if (books.add(new Book(books.size(), "Iarna pe Ulita", "George Cosbuc", "Poezia lui George Cosbuc despre iarna de " +
                "afara. Surpinde tematica iernii...", R.mipmap.book4, 0, false)))
            return true;
        return false;
    }

    public boolean addBook(String title, String author, String description, int image){
        if (books.add(new Book(books.size(), title, author, description, image, 0, false)))
            return true;
        return false;
    }

    public void addAsViewedBook(int id){
        System.out.println("Book with id = " + id + " and title = " + books.get(id).getTitle() + " viewed = " + books.get(id).getViewed());
        books.get(id).setViewed(books.get(id).getViewed() + 1);
    }

    public boolean removeBook(int id){
        if (books.remove(books.get(id)))
            return true;
        return false;
    }

    public List<Book> resetList(){
        books.clear();
        books = getInitialDatabase();
        return books;
    }

    public void addFavoriteBook(int id){
        System.out.println("id = " + id);
        books.get(id).setFavorite(true);
    }

    public void removeFavoriteBook(int id){
        System.out.println("id = " + id);
        books.get(id).setFavorite(false);
    }

    public void removeFavorites(){
       for(int i = 0 ; i < books.size(); i++)
           books.get(i).setFavorite(false);
    }

    public List<Book> getBooks(){
        return this.books;
    }

    public Book getBook(int id){
        return books.get(id);
    }
}
