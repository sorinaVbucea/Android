package com.example.sorina.login.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sorina.login.adapter.BookAdapter;
import com.example.sorina.login.R;
import com.example.sorina.login.controller.BookController;
import com.example.sorina.login.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksListActivity extends AppCompatActivity {

    private List<Book> books = new ArrayList<Book>();
    private BookAdapter bookAdapter;
    public static BookController bookController = new BookController();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Books List");
        setContentView(R.layout.activity_books_list);

        books = bookController.getBooks();
        bookAdapter = new BookAdapter(getBaseContext(), books);
        bookAdapter.notifyDataSetChanged();

        listView = (ListView)(findViewById(R.id.listView));

        registerForContextMenu(listView);
        listView.setAdapter(bookAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BooksListActivity.this, BookDetailsActivity.class);
                intent.putExtra("id", bookAdapter.getItem(position).getId());
                startActivity(intent);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.icon);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.back);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // verificăm dacă meniul este creat pentru lista vizată
        if ( v.getId() == R.id.listView ) {
            // identificăm elementul selectat din listă
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(((TextView)(info.targetView.findViewById(R.id.bookTitle))).getText().toString());
            // încărcăm structura vizuală a meniului
            getMenuInflater().inflate(R.menu.list_menu, menu);
        }
    }

    public boolean onContextItemSelected(MenuItem item) {
        // accesarea informației atașate meniului contextual
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        // identificarea elementului selectat din meniu, folosind ID-urile predefinite
        if(item.getItemId() == R.id.addBook){
            startActivity(new Intent(BooksListActivity.this, AddNewBookActivity.class));
            bookAdapter.notifyDataSetChanged();
        }
        else if(item.getItemId() == R.id.removeBook){
            bookController.removeBook(((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position);
            bookAdapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }

    public	boolean	onCreateOptionsMenu(Menu menu) {
        //	Inflate the	menu;	this	adds	items	to	the	action	bar	if	it	is	present.
        getMenuInflater().inflate(R.menu.options_bar_menu,	menu);
        return	true;
    }

    public	boolean	onOptionsItemSelected(MenuItem	item) {
        //	Handle	action	bar	item	clicks	here.	The	action	bar	will
        //	automatically	handle	clicks	on	the	Home/Up	button,	so	long
        //	as	you	specify	a	parent	activity	in	AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.reset_list) {
            books = bookController.resetList();
            bookAdapter.notifyDataSetChanged();
            Toast toast = Toast.makeText(this, "   List has been reset!   ", Toast.LENGTH_SHORT );
            View view = toast.getView();
            view.setBackgroundResource(R.drawable.toastinputborder);
            toast.setView(view);
            toast.show();
            return true;
        } else if (id == R.id.clear_favorites) {
            bookController.removeFavorites();
            Toast toast = Toast.makeText(this, "   List of favorites books has been reset!   ", Toast.LENGTH_SHORT );
            View view = toast.getView();
            view.setBackgroundResource(R.drawable.toastinputborder);
            toast.setView(view);
            toast.show();
            return true;
        } else if (id == R.id.sign_out) {
            AlertDialog.Builder logoutConfirmation = new AlertDialog.Builder(this);
            logoutConfirmation
                    .setTitle("Confirmation")
                    .setMessage("Please confirm log out intention?")
                    .setPositiveButton("Confirm", (DialogInterface.OnClickListener) confirmSignOut)
                    .setNegativeButton("Cancel", null)
                    .show();
        } else if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    private DialogInterface.OnClickListener confirmSignOut = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            startActivity(new Intent(BooksListActivity.this, LoginActivity.class));
        }
    };

    public	void onBackPressed() {
        startActivity(new Intent(BooksListActivity.this, LoginActivity.class));
    }

}
