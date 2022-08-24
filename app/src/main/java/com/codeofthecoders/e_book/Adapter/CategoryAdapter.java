package com.codeofthecoders.e_book.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.codeofthecoders.e_book.CategoryBookListActivity;
import com.codeofthecoders.e_book.Model.Category;
import com.codeofthecoders.e_book.R;

import java.util.List;
import java.util.Random;

public class CategoryAdapter extends  RecyclerView.Adapter<CategoryAdapter.ViewHolder> {


    private Context context;
    private List<Category> list;
    String from;

    public CategoryAdapter(Context context, List<Category> list,String from) {
        this.context = context;
        this.list = list;
        this.from = from;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        View itemView;
        if (from.equalsIgnoreCase("Home")) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.category_item, parent, false);
            return new ViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.category_item2, parent, false);
            return new ViewHolder(itemView);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final  int position) {
        final Category category = list.get(position);
        holder.title.setText(category.getCat_name());
        holder.id.setText("" + list.get(position).getCat_name().charAt(0));



        if (position == 0) {
            holder.iv_thumb.setBackgroundColor(context.getResources().getColor(R.color.cat_1));
        } else if (position == 1) {
            holder.iv_thumb.setBackgroundColor(context.getResources().getColor(R.color.cat_2));
        } else if (position == 2) {
            holder.iv_thumb.setBackgroundColor(context.getResources().getColor(R.color.cat_3));
        } else if (position == 3) {
            holder.iv_thumb.setBackgroundColor(context.getResources().getColor(R.color.cat_4));
        } else if (position == 4) {
            holder.iv_thumb.setBackgroundColor(context.getResources().getColor(R.color.cat_5));
        } else {
            holder.iv_thumb.setBackgroundColor(getRandomColor());
        }

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CategoryBookListActivity.class);
                intent.putExtra("cat_id", list.get(position).getId());
                intent.putExtra("cat_name", list.get(position).getCat_name());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CategoryBookListActivity.class);
                intent.putExtra("cat_id", list.get(position).getId());
                intent.putExtra("cat_name", list.get(position).getCat_name());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.iv_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CategoryBookListActivity.class);
                intent.putExtra("cat_id", list.get(position).getId());
                intent.putExtra("cat_name", list.get(position).getCat_name());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });




    }
    public int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public void Call(){

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title,id;
        public ImageView iv_thumb;

        public ViewHolder(View v) {
            super(v);
            id = itemView.findViewById(R.id.txt_tag);
            title = itemView.findViewById(R.id.txt_bookname);
            iv_thumb = itemView.findViewById(R.id.iv_thumb);


        }
    }
}
