package com.codeofthecoders.e_book.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;


import com.android.volley.toolbox.Volley;
import com.codeofthecoders.e_book.Adapter.AuthorAdapter;
import com.codeofthecoders.e_book.Adapter.CategoryAdapter;
import com.codeofthecoders.e_book.Adapter.ReadBookAdapter;
import com.codeofthecoders.e_book.AllAuthorActivity;
import com.codeofthecoders.e_book.BookDetailsActivity;
import com.codeofthecoders.e_book.CategoryActivity;
import com.codeofthecoders.e_book.CheckActivity;
import com.codeofthecoders.e_book.Constants;
import com.codeofthecoders.e_book.FreeBookActivity;
import com.codeofthecoders.e_book.Model.Author;
import com.codeofthecoders.e_book.Model.Category;
import com.codeofthecoders.e_book.Model.ReadBook;
import com.codeofthecoders.e_book.NewArrivalBookActivity;
import com.codeofthecoders.e_book.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;


    private RecyclerView mList;
    private LinearLayoutManager linearLayoutManager;
    private List<Category> categoryList;
    private RecyclerView.Adapter adapter;



    private RecyclerView mListBook;
    private LinearLayoutManager linearLayoutManagerBook;
    private List<ReadBook> readBookList;
    private RecyclerView.Adapter adapterBook;


    private RecyclerView mListNewBook;
    private LinearLayoutManager linearLayoutManagerNewBook;
    private List<ReadBook> readBookNewList;
    private RecyclerView.Adapter adapterNewBook;


    private RecyclerView mListAuthor;
    private LinearLayoutManager linearLayoutManagerAuthor;
    private List<Author> authorList ;
    private RecyclerView.Adapter adapterAuthor;

    private RecyclerView mListFreeBook;
    private LinearLayoutManager linearLayoutManagerFreeBook;
    private List<ReadBook> FreeBookList;
    private RecyclerView.Adapter adapterFreeBook;




    TextView txt_viewall_category,txt_viewall_item,txt_viewall_new_arrival,txt_viewall_freebook,txt_viewall_author;
    ViewFlipper viewFlipper;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            //switch_theme.setChecked(true);
            getActivity().setTheme(R.style.darktheme);
        } else {
            getActivity().setTheme(R.style.AppTheme);
        }


        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        txt_viewall_category = root.findViewById(R.id.txt_viewall_category);
        txt_viewall_item = root.findViewById(R.id.txt_viewall_item);
        txt_viewall_new_arrival=root.findViewById(R.id.txt_viewall_new_arrival);
        txt_viewall_freebook=root.findViewById(R.id.txt_viewall_freebook);
        txt_viewall_author=root.findViewById(R.id.txt_viewall_author);


        int img[] ={R.drawable.slide1,R.drawable.slide2,R.drawable.slide3,R.drawable.slide4};

        viewFlipper = root.findViewById(R.id.v_flipper);


        for (int imge:img){
            fillperimg(imge);
        }

        txt_viewall_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CategoryActivity.class));
            }
        });
        txt_viewall_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BookDetailsActivity.class));
            }
        });
        txt_viewall_new_arrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NewArrivalBookActivity.class));
            }
        });
        txt_viewall_freebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FreeBookActivity.class));
            }
        });

        txt_viewall_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AllAuthorActivity.class));
            }
        });



        mList = root.findViewById(R.id.ry_category);
        categoryList = new ArrayList<>();
        adapter = new CategoryAdapter(getContext(),categoryList,"Home");
        mList.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext(),linearLayoutManager.HORIZONTAL,false);
        mList.setLayoutManager(linearLayoutManager);
        mList.setAdapter(adapter);
        getCategory();



        mListBook = root.findViewById(R.id.rv_feature_item);
        readBookList = new ArrayList<>();
        adapterBook = new ReadBookAdapter(getContext(),readBookList,"Home");
        mListBook.setHasFixedSize(true);
        linearLayoutManagerBook = new LinearLayoutManager(getContext(),linearLayoutManagerBook.HORIZONTAL,false);
        mListBook.setLayoutManager(linearLayoutManagerBook);
        mListBook.setAdapter(adapterBook);
        getReadBook();



        mListNewBook = root.findViewById(R.id.rv_newarrival);
        readBookNewList = new ArrayList<>();
        adapterNewBook = new ReadBookAdapter(getContext(),readBookNewList,"Home");
        mListNewBook.setHasFixedSize(true);
        linearLayoutManagerNewBook = new LinearLayoutManager(getContext(),linearLayoutManagerNewBook.HORIZONTAL,false);
        mListNewBook.setLayoutManager(linearLayoutManagerNewBook);
        mListNewBook.setAdapter(adapterNewBook);
        getReadNewBook();



        mListAuthor = root.findViewById(R.id.rv_author);
        authorList = new ArrayList<>();
        adapterAuthor = new AuthorAdapter(getContext(),authorList,"Home");
        mListAuthor.setHasFixedSize(true);
        linearLayoutManagerAuthor = new LinearLayoutManager(getContext(),linearLayoutManagerAuthor.HORIZONTAL,false);
        mListAuthor.setLayoutManager(linearLayoutManagerAuthor);
        mListAuthor.setAdapter(adapterAuthor);
        getAuthor();


        mListFreeBook = root.findViewById(R.id.rv_freebook);
        FreeBookList= new ArrayList<>();
        adapterFreeBook = new ReadBookAdapter(getContext(),FreeBookList,"Home");
        mListFreeBook.setHasFixedSize(true);
        linearLayoutManagerFreeBook = new LinearLayoutManager(getContext(),linearLayoutManagerFreeBook.HORIZONTAL,false);
        mListFreeBook.setLayoutManager(linearLayoutManagerFreeBook);
        mListFreeBook.setAdapter(adapterFreeBook);
        getFreeBook();





        return root;
    }

    public void fillperimg(int imgage){
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(imgage);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(getContext(),android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);

    }

    private void getCategory() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constants.URL_FETCHCATEGORY, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Category category = new Category();
                        category.setCat_name(jsonObject.getString("cat_name"));
                        category.setId(jsonObject.getString("id"));


                        categoryList.add(category);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void getReadBook() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constants.URL_FETCHREADBOOK, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        ReadBook readBook = new ReadBook();
                        readBook.setImg_url(jsonObject.getString("book_img"));
                        readBook.setName(jsonObject.getString("book_name"));
                        readBook.setRate(jsonObject.getString("book_rate"));

                        readBookList.add(readBook);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapterBook.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void getReadNewBook() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constants.URL_FETCHNEWBOOK, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        ReadBook readBook = new ReadBook();
                        readBook.setImg_url(jsonObject.getString("book_img"));
                        readBook.setName(jsonObject.getString("book_name"));
                        readBook.setRate(jsonObject.getString("book_rate"));

                        readBookNewList.add(readBook);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapterNewBook.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void getAuthor() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void getFreeBook() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constants.URL_FETCHFREEBOOK, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        ReadBook readBook = new ReadBook();
                        readBook.setImg_url(jsonObject.getString("book_img"));
                        readBook.setName(jsonObject.getString("book_name"));
                        readBook.setRate(jsonObject.getString("book_rate"));


                        FreeBookList.add(readBook);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapterFreeBook.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }



}