package com.example.nytimesdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nytimesdemo.R;
import com.example.nytimesdemo.data.model.NewsData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder> {

    private List<NewsData> newsList;
   private Context context;
    public NewsListAdapter(Context context, List<NewsData> newsList) {
        this.newsList = newsList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_recyclerview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NewsData newsData = newsList.get(position);
        holder.title.setText(newsData.getTitle());
        holder.subHeading.setText(newsData.getSubHeading());
        holder.publishedDate.setText(newsData.getPublishedDate());
        Picasso.with(context).load(newsData.getImgUrl()).into(holder.icon);
    }

    @Override
    public int getItemCount() {
            return newsList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, subHeading, publishedDate;
        private ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.text_title);
            subHeading = (TextView) view.findViewById(R.id.text_subheading);
            publishedDate = (TextView) view.findViewById(R.id.text_date);
            icon =(ImageView)view.findViewById(R.id.img_icon);
        }
    }

    public void updateReceiptsList(List<NewsData> newsList) {
        this.newsList = newsList;
//        if(this.newsList!=null) {
//            this.newsList.clear();
//        }
//         this.newsList.addAll(newsList);
        this.notifyDataSetChanged();
    }
}
