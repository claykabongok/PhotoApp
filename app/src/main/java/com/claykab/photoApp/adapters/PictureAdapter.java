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
import com.claykab.photoApp.ui.category.CategoryFragmentDirections;
import com.claykab.photoApp.ui.categoryview.CategoryViewFragmentDirections;
import com.claykab.photoApp.ui.home.HomeFragmentDirections;
import com.claykab.photoApp.ui.searchimage.SearchImageFragmentDirections;


import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {

   private Context context;
   private List<hits> hitsList;
   private String navigationAction;

    public PictureAdapter(Context context, List<hits> hitsList,String navigationAction) {
        this.context = context;
        this.hitsList = hitsList;
        this.navigationAction=navigationAction;
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
                .placeholder(R.drawable.loader).into(pictureViewHolder.iv_picture_grid);


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
            //use for android navigation component lib
            hits hits = hitsList.get(getAdapterPosition());
            long pictureId = hits.getId();
            Bundle bundle = new Bundle();
            bundle.putLong("pictureId", pictureId);




            if(navigationAction.equals("homeAction")) {
                //navigate to view picture details from home screen
//                Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_imageDetailsFragment, bundle);
                HomeFragmentDirections.ActionNavigationHomeToImageDetailsFragment action
                        = HomeFragmentDirections.actionNavigationHomeToImageDetailsFragment();
                action.setPictureId(pictureId);
                Navigation.findNavController(v).navigate(action);
            }else if(navigationAction.equals("categoryViewAction")) {
                CategoryViewFragmentDirections.ActionCategoryViewFragmentToImageDetailsFragment actionCategoryView
                        = CategoryViewFragmentDirections.actionCategoryViewFragmentToImageDetailsFragment();
                actionCategoryView.setPictureId(pictureId);
                Navigation.findNavController(v).navigate(actionCategoryView);
                //navigate to view picture details from category screen
//               Navigation.findNavController(v).navigate(R.id.action_categoryViewFragment_to_imageDetailsFragment, bundle);
            }else if(navigationAction.equals("searchAction")){
                //navigate  to view picture details from search screen
//                Navigation.findNavController(v).navigate(R.id.action_navigation_search_to_imageDetailsFragment, bundle);
                SearchImageFragmentDirections.ActionNavigationSearchToImageDetailsFragment actionSearch=
                        SearchImageFragmentDirections.actionNavigationSearchToImageDetailsFragment();
                actionSearch.setPictureId(pictureId);
                Navigation.findNavController(v).navigate(actionSearch);

            }


        }
    }

}
