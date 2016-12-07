package com.example.sorina.login.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.sorina.login.R;

public class AddNewBookActivity extends AppCompatActivity {

    private BooksListActivity booksListActivity = new BooksListActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add new book");
        setContentView(R.layout.activity_add_new_book);
        final Button saveBook = (Button) findViewById(R.id.save_book);
        saveBook.setOnClickListener(handleClick);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.icon);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.back);
    }

    private View.OnClickListener handleClick = new View.OnClickListener(){
        public void onClick(View arg0) {
            EditText titleText = (EditText) findViewById(R.id.add_title);
            EditText authorText = (EditText) findViewById(R.id.add_author);
            EditText descriptionText = (EditText) findViewById(R.id.add_description);
            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.img_radio_group);
            int selectedIdRadioButton = radioGroup.getCheckedRadioButtonId();

            RadioButton radioButtonSelected = (RadioButton) findViewById(selectedIdRadioButton);

            int image;

            switch(radioButtonSelected.getId()){
                case R.id.r_img2 :
                    image =  R.mipmap.book2;
                    break;
                case R.id.r_img3 :
                    image =  R.mipmap.book3;
                    break;
                case R.id.r_img4 :
                    image =  R.mipmap.book4;
                    break;
                default:
                    image =  R.mipmap.book1;
                    break;
            }

            System.out.println(titleText.getText().toString() + " " + authorText.getText().toString() + " " + descriptionText.getText().toString() + " "  + image);
            BooksListActivity.bookController.addBook(titleText.getText().toString(), authorText.getText().toString(), descriptionText.getText().toString(), image);
            startActivity(new Intent(AddNewBookActivity.this, BooksListActivity.class));
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public	void onBackPressed() {
        startActivity(new Intent(AddNewBookActivity.this, BooksListActivity.class));
    }
}
