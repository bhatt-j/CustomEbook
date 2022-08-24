package com.codeofthecoders.e_book;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OpenBookorderActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    String book_name;
    TextView txt_title,txt_by_author,txt_book,txt_category,txt_category2,txt_bookmark,txt_descripation;
    ImageView iv_thumb;
    RelativeLayout buy_now;



    String Title,rate,author,category,category2,deac,img,BOOK_URL,Amount,Price;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openorderbookdetails);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        iv_thumb = findViewById(R.id.iv_thumb);
        txt_title = findViewById(R.id.txt_title);
        txt_by_author = findViewById(R.id.txt_by_author);
        txt_book = findViewById(R.id.txt_book);
        txt_category = findViewById(R.id.txt_category);

        txt_bookmark = findViewById(R.id.txt_bookmark);
        txt_descripation = findViewById(R.id.txt_descripation);




        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            book_name = bundle.getString("book_name");
            getSupportActionBar().setTitle(book_name);
            books_by();
        }


    }
    private void books_by() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,Constants.URL_FETCHORDERBOOK, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if (success.equals("1")) {
                        progressDialog.dismiss();
                        for(int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject json = null;
                            try
                            {
                                json = jsonArray.getJSONObject(i);
                                txt_title.setText(json.getString("book_name"));
                                txt_category.setText(json.getString("book_category"));
                                txt_by_author.setText(json.getString("by_name"));
                                txt_descripation.setText(json.getString("book_desc"));
                                final String URL = json.getString("book_url");
                                img = json.getString("book_img");
                                txt_book.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        Intent intent = new Intent(OpenBookorderActivity.this,PdfActivity.class);

                                        intent.putExtra("book_url",URL);
                                        Log.d("TAG",URL);
                                        startActivity(intent);
                                       // Toast.makeText(getApplicationContext(),"View",Toast.LENGTH_LONG).show();

                                    }
                                });
                                RequestOptions options = new RequestOptions();
                                Glide.with(getApplicationContext()).load(img).apply(options).into(iv_thumb);

                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                            }

                        }
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
                params.put("book_name",book_name);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }




}
