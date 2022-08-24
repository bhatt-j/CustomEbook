package com.codeofthecoders.e_book.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.codeofthecoders.e_book.LoginActivity;
import com.codeofthecoders.e_book.Model.ReadBook;
import com.codeofthecoders.e_book.OpenBookActivity;
import com.codeofthecoders.e_book.OpenBookAuthorActivty;
import com.codeofthecoders.e_book.OpenBookMarkActivity;
import com.codeofthecoders.e_book.OpenBookorderActivity;
import com.codeofthecoders.e_book.R;

import java.util.List;

public class ReadBookAdapter extends  RecyclerView.Adapter<ReadBookAdapter.ViewHolder>  {
    RequestOptions option ;
    private Context context;
    private List<ReadBook> list;
    String from;

    public ReadBookAdapter(Context context, List<ReadBook> list, String from) {
        this.context = context;
        this.list = list;
        this.from = from;
        option = new RequestOptions();

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView;
        if (from.equalsIgnoreCase("Home")) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.readbook_item, parent, false);
            return new ViewHolder(itemView);
        } else if(from.equalsIgnoreCase("pur")){
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.readbook_item3, parent, false);
            return new ViewHolder(itemView);
        }
        else if(from.equalsIgnoreCase("book")){
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.readbook_item3, parent, false);
            return new ViewHolder(itemView);
        }
        else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.readbook_item2, parent, false);
            return new ViewHolder(itemView);
       }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ReadBook readBook = list.get(position);
        holder.bookprice.setText("₹ "+readBook.getRate());
        holder.Bookname.setText(readBook.getName());
        Glide.with(context).load(readBook.getImg_url()).apply(option).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OpenBookActivity.class);
                intent.putExtra("id",list.get(position).getId());
                intent.putExtra("book_name", list.get(position).getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        if(from.equalsIgnoreCase("pur")){
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, OpenBookorderActivity.class);
                    intent.putExtra("id",list.get(position).getId());
                    intent.putExtra("book_name", list.get(position).getName());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });
        }
        if(from.equalsIgnoreCase("book")){
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, OpenBookMarkActivity.class);
                    intent.putExtra("id",list.get(position).getId());
                    intent.putExtra("book_name", list.get(position).getName());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView Bookname,bookprice;

        public ViewHolder(View itemView) {

            super(itemView);
            img = itemView.findViewById(R.id.iv_thumb);
            bookprice = itemView.findViewById(R.id.txt_book_price);
            Bookname  = itemView.findViewById(R.id.txt_bookname);


        }
    }
}
