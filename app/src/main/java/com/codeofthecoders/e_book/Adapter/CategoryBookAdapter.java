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
import com.codeofthecoders.e_book.Model.Category;
import com.codeofthecoders.e_book.Model.ReadBook;
import com.codeofthecoders.e_book.OpenBookActivity;
import com.codeofthecoders.e_book.R;

import java.util.List;


public class CategoryBookAdapter extends  RecyclerView.Adapter<CategoryBookAdapter.ViewHolder> {

    private Context context;
    private List<ReadBook> alist;
    RequestOptions option ;
    public CategoryBookAdapter(Context context, List<ReadBook> alist) {
        this.context = context;
        this.alist = alist;
        option = new RequestOptions();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categorywise_book, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ReadBook readBook = alist.get(position);

        holder.Bookname.setText(readBook.getName());
        Glide.with(context).load(readBook.getImg_url()).apply(option).into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OpenBookActivity.class);
                intent.putExtra("id",alist.get(position).getId());
                intent.putExtra("book_name", alist.get(position).getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
return  alist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public TextView Bookname,bookprice;

        public ViewHolder(View itemView) {

            super(itemView);
            img = itemView.findViewById(R.id.iv_thumb);

            Bookname  = itemView.findViewById(R.id.txt_bookname);


        }
    }
}
