package com.claykab.photoApp.adapters;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.claykab.photoApp.R;
import com.claykab.photoApp.model.hits;

import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {

   private Context context;
   private List<hits> hitsList;

    public PictureAdapter(Context context, List<hits> hitsList) {
        this.context = context;
        this.hitsList = hitsList;
    }

    private Context getContext(){
        return context;
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pictures_grid,parent,false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder pictureViewHolder, int position) {


        hits hits = hitsList.get(position);

        /**
         * bind data to UI element
         */

        Glide.with(getContext()).load(hits.getWebformatURL())
                .placeholder(R.drawable.ic_image_gray_48dp).apply(new RequestOptions()
                .override(800, 600)).into(pictureViewHolder.iv_picture_grid);


    }

    @Override
    public int getItemCount() {
        return hitsList.size();
    }

    /**
     * add onclick event on the {@link RecyclerView}
     */
    class PictureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView iv_picture_grid;

        public PictureViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            iv_picture_grid= itemView.findViewById(R.id.iv_picture_grid);

        }

        @Override
        public void onClick(View v) {

            hits hits=hitsList.get(getAdapterPosition());
            Bundle bundle= new Bundle();
            long pictureId=hits.getId();
            bundle.putLong("pictureId",pictureId);
            //navigate to view picture details
            Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_imageDetailsFragment, bundle);


        }
    }

}
