package com.codeofthecoders.e_book;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity {

    TextView txt_already_signup,forget,txt_login;
    EditText et_email,et_password;
    private String str_email,str_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        if (SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()){
            finish();
            startActivity(new Intent(getApplicationContext(),MainDashBoard.class));
            return;
        }


        txt_already_signup = findViewById(R.id.txt_already_signup);
        forget = findViewById(R.id.forget);
        txt_login = findViewById(R.id.txt_login);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);

        txt_already_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });


        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    LoginUser();
                }
            }
        });
    }

    public void forgetpassword(View view){

        AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.custom_dialog,null);

        final EditText txt_input = (EditText)mView.findViewById(R.id.et_email);

        Button btn_k = (Button)mView.findViewById(R.id.btn_ok);



        alert.setView(mView);

        btn_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String str_email = txt_input.getText().toString().trim();
                if(txt_input.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,"Plz check mail",Toast.LENGTH_LONG).show();
                }else{
                    final ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "", "Please wait...", true);

                    StringRequest request = new StringRequest(Request.Method.POST, Constants.URL_FORGOT, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            dialog.dismiss();
                            txt_input.setText("");

                            Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(i);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            dialog.dismiss();
                            Toast.makeText(LoginActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("email",str_email);
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(request);

                }
            }
        });
        AlertDialog dialog  = alert.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }


    public void LoginUser(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");

        progressDialog.show();
        getData();

        StringRequest request = new StringRequest(Request.Method.POST,
                Constants.URL_Login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();
                        ClearData();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String name = object.getString("name").trim();
                                    String email = object.getString("email").trim();
                                    String id = object.getString("id").trim();
                                    String phno = object.getString("contact").trim();

                                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(
                                            object.getInt("id"),
                                            object.getString("name"),
                                            object.getString("email"),
                                            object.getString("contact"));


                                    Toast.makeText(getApplicationContext(),"Loggin Success",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, MainDashBoard.class);
                                    intent.putExtra("name", name);
                                    intent.putExtra("email", email);
                                    intent.putExtra("id", id);
                                    startActivity(intent);
                                    finish();

                                }

                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Login invalid",Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Login Error!" + e.toString(), Toast.LENGTH_LONG).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("email", str_email);
                params.put("pass", str_password);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }


    public void getData(){
        str_email = et_email.getText().toString().trim();
        str_password=et_password.getText().toString().trim();
    }

    public boolean validation() {

        if (et_email.getText().toString().isEmpty() || !isEmailValid(et_email.getText().toString())) {
            et_email.setError("Enter Valid Email");
            return false;
        }
        if (et_password.getText().toString().isEmpty()) {
            et_password.setError("Enter Valid password");
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


    private void ClearData(){
        et_email.setText("");
        et_password.setText("");


    }
}