package com.codeofthecoders.e_book;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.codeofthecoders.e_book.Adapter.AuthorAdapter;
import com.codeofthecoders.e_book.Adapter.ReadBookAdapter;
import com.codeofthecoders.e_book.Model.Author;
import com.codeofthecoders.e_book.Model.ReadBook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllAuthorActivity extends AppCompatActivity {

    private RecyclerView mListAuthor;
    private LinearLayoutManager linearLayoutManagerAuthor;
    private List<Author> authorList ;
    private RecyclerView.Adapter adapterAuthor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allauthor);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Author");

        mListAuthor = findViewById(R.id.rv_author);
        authorList = new ArrayList<>();
        adapterAuthor = new AuthorAdapter(getApplicationContext(),authorList,"view");

        linearLayoutManagerAuthor = new LinearLayoutManager(getApplicationContext(),linearLayoutManagerAuthor.HORIZONTAL,false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3, LinearLayoutManager.VERTICAL, false);
        mListAuthor.setLayoutManager(gridLayoutManager);
        mListAuthor.setAdapter(adapterAuthor);


        getData();
        
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constants.URL_FETCHAUTHOR, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Author author= new Author();
                        author.setA_img(jsonObject.getString("img"));
                        author.setName(jsonObject.getString("name"));


                        authorList.add(author);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapterAuthor.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
    }
}
