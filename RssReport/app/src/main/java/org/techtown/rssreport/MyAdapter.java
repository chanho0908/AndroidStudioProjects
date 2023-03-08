package org.techtown.rssreport;

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

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    static ArrayList<Item> items;
    static Context contex;

    public MyAdapter(ArrayList<Item> items, Context contex) {
        this.items = items;
        this.contex = contex;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recycler_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Item item){
        items.add(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvDesc, tvDate;
        ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvDate = itemView.findViewById(R.id.tv_date);
            iv = itemView.findViewById(R.id.iv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String link = items.get(getLayoutPosition()).getLink();
                    Intent intent = new Intent(contex, ItemActivity.class);
                    intent.putExtra("link", link);
                    contex.startActivity(intent);
                }
            });
        }

        public void setItem(Item item){
            tvTitle.setText(item.getTitle());
            tvDesc.setText(item.getDesc());
            tvDate.setText(item.getDate());

            if(item.getImgUrl() == null){
                iv.setVisibility(View.GONE);
            }else{
                iv.setVisibility(View.VISIBLE);
                //네트워크에 있는 이미지를 보여주려면
                //별도의 Thread가 필요한데 이를 편하게
                //해주는 Library사용(Glide library)

                Glide.with(itemView).load(item.getImgUrl()).into(iv);
            }
        }
    }
}
