package org.the_harders.philhardercodingtest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by harde on 7/27/2017.
 */

public interface RetrofitInterface {
    @GET("books.json")
    Call<List<Book>> getAllBooks();
}
