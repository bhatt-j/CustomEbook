package com.codeofthecoders.e_book.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codeofthecoders.e_book.Model.Author;
import com.codeofthecoders.e_book.OpenBookAuthorActivty;
import com.codeofthecoders.e_book.R;

import java.util.List;

public class AuthorAdapter extends  RecyclerView.Adapter<AuthorAdapter.ViewHolder>  {

    RequestOptions option ;
    private Context context;
    private List<Author> list;
    String from;

    public AuthorAdapter(Context context, List<Author> list, String from) {

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
                    .inflate(R.layout.author_item, parent, false);
            return new ViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.author_item, parent, false);
            return new ViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Author author = list.get(position);
        holder.Bookname.setText(author.getName());
        Glide.with(context).load(author.getA_img()).apply(option).into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OpenBookAuthorActivty.class);
                intent.putExtra("a_name", list.get(position).getName());
                intent.putExtra("a_id", list.get(position).getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);



            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView Bookname,bookprice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_thumb);
            Bookname  = itemView.findViewById(R.id.txt_bookname);


        }
    }
}
