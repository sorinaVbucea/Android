package com.example.sorina.login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sorina.login.R;
import com.example.sorina.login.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends BaseAdapter {

    public List<Book> items = new ArrayList<Book>();
    private Context context;

    public BookAdapter(Context context, List<Book> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Book getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get a reference to the LayoutInflater service
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // check if we can reuse a previously defined cell which now is not visible anymore
        View myRow = (convertView == null) ? inflater.inflate(R.layout.book_item, parent, false) : convertView;
        // get the visual elements and update them with the information from the model

        ((TextView)myRow.findViewById( R.id.bookTitle )).setText( ((Book) getItem(position)).getTitle() );
        ((TextView)myRow.findViewById( R.id.bookAuthor )).setText( ((Book) getItem(position)).getAuthor() );
        ((TextView)myRow.findViewById( R.id.bookDescription )).setText( ((Book) getItem(position)).getDescription() );
        ((ImageView)myRow.findViewById( R.id.bookImage )).setImageResource(((Book) getItem(position)).getImage());

        return myRow;
    }
}
