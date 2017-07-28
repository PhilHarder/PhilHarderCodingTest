package org.the_harders.philhardercodingtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycleView;
    String BASE_URL = "http://de-coding-test.s3.amazonaws.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void loadJSON (){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface request = retrofit.create(RetrofitInterface.class);

        Call<List<Book>> call = request.getAllBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                    List<Book> booklist = response.body();
                    for (Book b: booklist){
                        Log.d("RETROFIT", b.getTitle()+" "+b.getAuthor()+" "+b.getImageURL());
                    }
                    BookAdapter bookAdapter = new BookAdapter(booklist);
                    recycleView.setAdapter(bookAdapter);
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
               Log.d("RETROFIT",t.getMessage());
            }
        });
    }
}
