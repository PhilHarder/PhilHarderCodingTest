package org.the_harders.philhardercodingtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by harde on 7/27/2017.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookVH> {

    private List<Book> books;

    public BookAdapter(List<Book> books){
        this.books = books;
    }

    public class BookVH extends RecyclerView.ViewHolder {

        TextView bTitle, bAuthor;
        ImageView bImage;

        public BookVH(View bookView){
            super(bookView);
            bTitle = (TextView) bookView.findViewById(R.id.titleView);
            bAuthor = (TextView) bookView.findViewById(R.id.authorView);
            bImage = (ImageView) bookView.findViewById(R.id.imageView);
        }

    }

    @Override
    public BookVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_view, parent, false);
        return new BookVH(v);
    }

    @Override
    public void onBindViewHolder(BookVH holder, int position) {
        Book thisBook = books.get(position);

        holder.bTitle.setText(thisBook.getTitle());
        holder.bAuthor.setText(thisBook.getAuthor());
        Picasso.with(holder.bImage.getContext())
                .load(thisBook.getImageURL())
                .resize(120,120)
                .into(holder.bImage);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

}
