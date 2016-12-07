package com.example.sorina.login.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sorina.login.R;
import com.example.sorina.login.model.Book;

public class BookDetailsActivity extends AppCompatActivity {

    private int id;
    private Book book = new Book();
    private boolean favorite;
    private MenuItem addFavorites;
    private MenuItem removeFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Books Details");
        setContentView(R.layout.activity_book_details);

        Intent myIntent	= getIntent();
        id = myIntent.getIntExtra("id", 0);

        BooksListActivity.bookController.addAsViewedBook(id);
        book = BooksListActivity.bookController.getBook(id);
        favorite = book.getFavorite();

        ((TextView)findViewById( R.id.bookTitleDetails )).setText( book.getTitle() );
        ((TextView)findViewById( R.id.bookAuthorDetails )).setText( book.getAuthor() );
        ((TextView)findViewById( R.id.bookDescriptionDetails )).setText( book.getDescription() );
        ((ImageView)findViewById( R.id.bookImageDetails )).setImageResource( book.getImage() );
        ((TextView)findViewById( R.id.bookViewsDetails )).setText( "Details page viewed: " + book.getViewed() + " times");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.icon);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.back);
    }

    public	boolean	onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.book_details_menu_favorites,	menu);
        addFavorites = menu.findItem(R.id.add_favorites);
        removeFavorites = menu.findItem(R.id.remove_favorites);

        if (BooksListActivity.bookController.getBook(id).getFavorite()) {
            removeFavorites.setVisible(true);
            addFavorites.setVisible(false);
        }
        else {
            removeFavorites.setVisible(false);
            addFavorites.setVisible(true);
        }
        return	true;
    }

    public	boolean	onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.remove_favorites) {
            BooksListActivity.bookController.removeFavoriteBook(book.getId());
            Toast toast = Toast.makeText(this, "   Removed from favorites   ", Toast.LENGTH_SHORT );
            View view = toast.getView();
            view.setBackgroundResource(R.drawable.toastinputborder);
            toast.setView(view);
            toast.show();
            favorite = false;
        }
        else if (id == R.id.add_favorites) {
            BooksListActivity.bookController.addFavoriteBook(book.getId());
            Toast toast = Toast.makeText(this, "   Added to favorites   ", Toast.LENGTH_SHORT );
            View view = toast.getView();
            view.setBackgroundResource(R.drawable.toastinputborder);
            toast.setView(view);
            toast.show();
            favorite = true;
        } else if (id == android.R.id.home) {
            onBackPressed();
        }
        invalidateOptionsMenu();
        return super.onOptionsItemSelected(item);
    }

    public	void onBackPressed() {
        startActivity(new Intent(BookDetailsActivity.this, BooksListActivity.class));
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (favorite) {
            removeFavorites.setVisible(true);
            addFavorites.setVisible(false);
        }
        else {
            removeFavorites.setVisible(false);
            addFavorites.setVisible(true);
        }
        return true;
    }
}
