package com.codeofthecoders.e_book;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileActivity extends AppCompatActivity {


EditText txt_name,txt_password,txt_email,txt_Contact;
TextView txt_save;
String uname,id;
    private String str_name,str_hno,str_sno,str_landmark,str_pincode,str_email,str_phno,str_password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");

        txt_name = findViewById(R.id.txt_name);
        txt_password = findViewById(R.id.txt_password);
        txt_email = findViewById(R.id.txt_email);
        txt_Contact = findViewById(R.id.txt_Contact);
        txt_save = findViewById(R.id.txt_save);
        txt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    UpdateProfile();
                }
            }
        });

        uname = String.valueOf(SharedPrefManager.getInstance(ProfileActivity.this).getUsername());
        id = SharedPrefManager.getInstance(this).getUserId();

        fetchdata();

    }

    public boolean validation() {

        if (txt_name.getText().toString().isEmpty()) {
            txt_name.setError("Enter Name");
            return false;
        }
        if (txt_password.getText().toString().isEmpty()) {
            txt_password.setError("Enter password");
            return false;
        }

        if (txt_email.getText().toString().isEmpty() || !isEmailValid(txt_email.getText().toString())) {
            txt_email.setError("Enter Valid Email");
            return false;
        }
        if (txt_Contact.getText().toString().isEmpty() || !validatePhoneNumber(txt_Contact.getText().toString()) ) {
            txt_Contact.setError("Enter Valid mobile");
            return false;
        }


        return true;
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    private static boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
            //validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
            //return false if nothing matches the input
        else return false;

    }


    private void fetchdata() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Constants.URL_FETCHUSER, new Response.Listener<String>(){


            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if (success.equals("1")) {
                        progressDialog.dismiss();

                        for(int i=0;i<jsonArray.length();i++) {

                            JSONObject json = null;

                            try {
                                json = jsonArray.getJSONObject(i);

                                txt_name.setText(json.getString("u_name"));
                                txt_email.setText(json.getString("u_mail"));
                                txt_password.setText(json.getString("u_password"));
                                txt_Contact.setText(json.getString("u_con"));

                                str_name = txt_name.getText().toString().trim();
                                str_email = txt_email.getText().toString().trim();
                                str_phno= txt_Contact.getText().toString().trim();
                                str_phno= txt_password.getText().toString().trim();


                            } catch (JSONException e) {
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
                params.put("id",id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }




    public void UpdateProfile(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");


        progressDialog.show();

        str_name = txt_name.getText().toString().trim();
        str_email = txt_email.getText().toString().trim();
        str_phno= txt_Contact.getText().toString().trim();
        str_password= txt_password.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST,
                Constants.URL_UPDATEUSER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
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
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<String, String>();
                params.put("name",str_name);
                params.put("email",str_email);
                params.put("phno",str_phno);
                params.put("password",str_password);
                params.put("id",id);



                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }


}
