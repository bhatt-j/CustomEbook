package com.codeofthecoders.e_book;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.codeofthecoders.e_book.Adapter.CategoryBookAdapter;
import com.codeofthecoders.e_book.Adapter.ReadBookAdapter;
import com.codeofthecoders.e_book.Model.ReadBook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryBookListActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    String cat_id, cat_name, cat_image;


    private RecyclerView mList;
    private LinearLayoutManager linearLayoutManager;
    private List<ReadBook> readBookList;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorybook);

        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);


        mList = findViewById(R.id.main_ctegory_book);
        readBookList = new ArrayList<>();
        adapter = new CategoryBookAdapter(getApplicationContext(),readBookList);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3, LinearLayoutManager.VERTICAL, false);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(gridLayoutManager);
        mList.setAdapter(adapter);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            cat_id = bundle.getString("cat_id");
            cat_name = bundle.getString("cat_name");
            getSupportActionBar().setTitle(cat_name);
            books_by_category();
        }
    }

    private void books_by_category() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Constants.URL_FETCHCATEGORYVISEBOOK, new Response.Listener<String>(){


            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if (success.equals("1")) {
                        progressDialog.dismiss();
                        JSON_PARSE_DATA_AFTER_WEBCALL(jsonArray);
                    }else{
                        Toast.makeText(getApplicationContext(),"no Data!!",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "File Not Found!" + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("cat_name",cat_name);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            ReadBook GetDataAdapter2 = new ReadBook();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);


                GetDataAdapter2.setImg_url(json.getString("book_img"));
                GetDataAdapter2.setName(json.getString("book_name"));

            }
            catch (JSONException e)
            {

                e.printStackTrace();
            }

            readBookList.add(GetDataAdapter2);

        }

        adapter = new CategoryBookAdapter(getApplicationContext(),readBookList);
        mList.setAdapter(adapter);
    }
}
