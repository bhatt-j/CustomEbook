package com.codeofthecoders.e_book;


import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
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
import android.app.AlertDialog;
import android.app.Dialog;

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

public class OpenBookActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    String book_name;
    TextView txt_title,txt_by_author,txt_price,txt_category,txt_category2,txt_bookmark,txt_descripation;
    ImageView iv_thumb;
    RelativeLayout buy_now;

    Uri uri;

    String Title,rate,author,category,category2,deac,img,BOOK_URL,Amount,Price;
    DownloadManager downloadManager;


    public static String GPAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    public static String payerName = null;
    public static String UpiId =null;
    public static String msgNote = null;
    public static String sendAmount=null;
    public static String status;
    String approvalRefNo;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openbookdetails);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        iv_thumb = findViewById(R.id.iv_thumb);
        txt_title = findViewById(R.id.txt_title);
        txt_by_author = findViewById(R.id.txt_by_author);
        txt_price = findViewById(R.id.txt_price);
        txt_category = findViewById(R.id.txt_category);
        txt_category2 = findViewById(R.id.txt_category2);
        txt_bookmark = findViewById(R.id.txt_bookmark);
        txt_descripation = findViewById(R.id.txt_descripation);
        buy_now = (RelativeLayout) findViewById(R.id.buy_now);



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
        StringRequest stringRequest = new StringRequest(Request.Method.POST,Constants.URL_FETCHOPENBOOK, new Response.Listener<String>(){
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

                                img = json.getString("book_img");
                                Title = json.getString("book_name");
                                BOOK_URL = json.getString("book_url");
                                deac = json.getString("book_desc");
                                category = json.getString("book_category");
                                category2 = json.getString("book_category2");
                                author = json.getString("by_name");



                                txt_title.setText(json.getString("book_name"));
                                Price = json.getString("book_rate");


                                if(Price.equals("0"))
                                {
                                    txt_price.setText("DownLoad");
                                    txt_category.setText(json.getString("book_category"));
                                    txt_category2.setText(json.getString("book_category2"));
                                    txt_by_author.setText(json.getString("by_name"));
                                    txt_descripation.setText(json.getString("book_desc"));
                                    buy_now.setVisibility(View.GONE);
                                    final String URL = json.getString("book_url");
                                    txt_price.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            downloadManager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                                            Uri uri=Uri.parse(URL);
                                            DownloadManager.Request request=new DownloadManager.Request(uri);
                                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                                            Long reference=downloadManager.enqueue(request);

                                        }
                                    });

                                }
                                else
                                    {
                                    txt_price.setText("₹ " + Price);
                                    txt_category.setText(json.getString("book_category"));
                                    txt_category2.setText(json.getString("book_category2"));
                                    txt_by_author.setText(json.getString("by_name"));
                                    txt_descripation.setText(json.getString("book_desc"));
                                    buy_now.setVisibility(View.VISIBLE);
                                    buy_now.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            show();
                                        }
                                    });
                                }
                                RequestOptions options = new RequestOptions();

                                Glide.with(getApplicationContext()).load(img).apply(options).into(iv_thumb);
                                txt_bookmark.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                            Addbookmark();
                                    }
                                });

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

    private void Addbookmark() {

        final String u_name = String.valueOf(SharedPrefManager.getInstance(OpenBookActivity.this).getUsername());
        Amount = String.valueOf(Price);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");


        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST,
                Constants.URL_ADDBOOKMARK, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                Toast.makeText(OpenBookActivity.this, response, Toast.LENGTH_SHORT).show();

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("u_name",u_name);
                params.put("Book_name",Title);
                params.put("Book_img",img);
                params.put("Book_category",category);
                params.put("Book_url",BOOK_URL);
                params.put("Book_desc",deac);
                params.put("Author",author);
                params.put("Price",Amount);
                params.put("Category2",category2);

                Log.d("err",params.toString());

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);


    }

    public void show(){

        Amount = String.valueOf(Price);

        final AlertDialog.Builder alert = new AlertDialog.Builder(OpenBookActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.payment_dialog,null);

        final EditText Note = (EditText)mView.findViewById(R.id.transaction_note);
        final EditText amt = (EditText)mView.findViewById(R.id.amount);
        final EditText uId = (EditText)mView.findViewById(R.id.upi_id);
        final EditText pname = (EditText)mView.findViewById(R.id.name);
        final TextView msg = mView.findViewById(R.id.msg);
        Button btn_k = (Button)mView.findViewById(R.id.btn_ok);

        alert.setView(mView);

        final AlertDialog dialog  = alert.create();


        amt.setText(Amount);
        uId.setText("jb.janvibhatt@okicici");
        pname.setText("E Book");
        Note.setText("E Book! Thank You For Payment!!");


        btn_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                payerName = pname.getText().toString();
                UpiId = uId.getText().toString();
                msgNote = Note.getText().toString();
                sendAmount = amt.getText().toString();



                if(!payerName.equals("") && !UpiId.equals("") && !msgNote.equals("") && !sendAmount.equals("")){

                    uri = getUpiPaymentUri(payerName, UpiId, msgNote, sendAmount);
                    payWithGpay("com.google.android.apps.nbu.paisa.user");

                }
                else {
                    Toast.makeText(getApplicationContext(),"Fill all above details and try again.", Toast.LENGTH_SHORT).show();


                }
            }
        });


        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
    private static Uri getUpiPaymentUri(String name, String upiId, String note, String amount){


        return  new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa",upiId)
                .appendQueryParameter("pn",name)
                .appendQueryParameter("tn",note)
                .appendQueryParameter("am",amount)
                .appendQueryParameter("cu","INR")
                .build();



    }

    private void payWithGpay(String packageName){
        if(packageName == "com.google.android.apps.nbu.paisa.user" ){

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setPackage(packageName);
            startActivityForResult(intent,0);

        }
        else{
            Toast.makeText(getApplicationContext(),"Google pay is not installed . Please istall and try again.", Toast.LENGTH_SHORT).show();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            status = data.getStringExtra("Status").toLowerCase();
            approvalRefNo = data.getStringExtra("txnRef");


        }
        if ((Activity.RESULT_OK == resultCode) && status.equals("success")) {
            Toast.makeText(getApplicationContext(), "Transaction successful. "+approvalRefNo, Toast.LENGTH_SHORT).show();


                addOrder();

                startActivity(new Intent(getApplicationContext(),MainDashBoard.class));

        }

        else{
            addOrder();
            Toast.makeText(getApplicationContext(), "Transaction cancelled or failed please try again.", Toast.LENGTH_SHORT).show();

        }

    }

    private void addOrder() {

    final String u_id = String.valueOf(SharedPrefManager.getInstance(OpenBookActivity.this).getUserId());
    final String u_name = String.valueOf(SharedPrefManager.getInstance(OpenBookActivity.this).getUsername());
    final String u_email = String.valueOf(SharedPrefManager.getInstance(OpenBookActivity.this).getUserEmail());
    Amount = String.valueOf(Price);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");


        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST,
                Constants.URL_ADDORDER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                Toast.makeText(OpenBookActivity.this, response, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),MainDashBoard.class);
                startActivity(i);
                finish();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("u_name",u_name);
                params.put("u_id",u_id);
                params.put("u_email",u_email);
                params.put("Book_name",Title);
                params.put("Book_img",img);
                params.put("Book_category",category);
                params.put("Book_url",BOOK_URL);
                params.put("Book_desc",deac);
                params.put("Total_pay",Amount);
                params.put("Author",author);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);




    }


    public static boolean isAppInstalled(Context context, String packageName){
        try{
            context.getPackageManager().getApplicationInfo(packageName,0);
            return true;
        }catch (PackageManager.NameNotFoundException e){
            return false;
        }
    }


}
