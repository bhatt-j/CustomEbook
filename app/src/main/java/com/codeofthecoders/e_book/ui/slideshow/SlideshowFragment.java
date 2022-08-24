package com.codeofthecoders.e_book.ui.slideshow;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.codeofthecoders.e_book.Adapter.ReadBookAdapter;
import com.codeofthecoders.e_book.Constants;
import com.codeofthecoders.e_book.Model.ReadBook;
import com.codeofthecoders.e_book.PurchaseBookActivity;
import com.codeofthecoders.e_book.R;
import com.codeofthecoders.e_book.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlideshowFragment extends Fragment {

    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;

    private List<ReadBook> readBookList;
    private RecyclerView.Adapter adapter;
    String uname = String.valueOf(SharedPrefManager.getInstance(getContext()).getUsername());

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            //switch_theme.setChecked(true);
            getActivity().setTheme(R.style.darktheme);
        } else {
            getActivity().setTheme(R.style.AppTheme);
        }
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        mList = root.findViewById(R.id.main_list_bookmark);
        readBookList = new ArrayList<>();
        adapter = new ReadBookAdapter(getContext(),readBookList,"view");

        linearLayoutManager = new LinearLayoutManager(getContext(),linearLayoutManager.HORIZONTAL,false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false);
        mList.setLayoutManager(gridLayoutManager);
        mList.setAdapter(adapter);
        getData();


        return root;
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_FETCHBOOKMARK, new Response.Listener<String>(){


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
                        Toast.makeText(getContext(),"no Data!!",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "File Not Found!" + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("u_name",uname);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
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

        adapter = new ReadBookAdapter(getContext(),readBookList,"book");
        mList.setAdapter(adapter);
    }
}