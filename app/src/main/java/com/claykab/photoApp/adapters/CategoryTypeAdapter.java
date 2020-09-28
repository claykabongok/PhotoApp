package com.claykab.photoApp.adapters;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.claykab.photoApp.R;
import com.claykab.photoApp.model.CategoryType;
import com.claykab.photoApp.ui.category.CategoryFragmentDirections;

import java.util.List;

public class CategoryTypeAdapter extends RecyclerView.Adapter<CategoryTypeAdapter.CategoryViewHolder> {
    private Context context;
    private List<CategoryType> categoryTypeList;

    public CategoryTypeAdapter(Context context, List<CategoryType> categoryTypeList) {
        this.context = context;
        this.categoryTypeList = categoryTypeList;
    }

    public Context getContext() {
        return context;
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_type_list,parent,false);
        return new CategoryViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull CategoryTypeAdapter.CategoryViewHolder holder, int position) {
        CategoryType categoryType=categoryTypeList.get(position);

        holder.tv_category_type_name.setText(categoryType.getCategory().toUpperCase());


        Glide.with(getContext()).load( categoryType.getCategoryImageUrl())
                .into(holder.iv_category);

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return categoryTypeList.size();
    }


    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_category_type_name ;
        ImageView iv_category;



        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_category_type_name=itemView.findViewById(R.id.tv_category_type_name);

            iv_category=itemView.findViewById(R.id.iv_categorytype);

        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            /**
             *        HomeFragmentDirections.ActionNavigationHomeToImageDetailsFragment action
             *                         = HomeFragmentDirections.actionNavigationHomeToImageDetailsFragment();
             *                 action.setPictureId(pictureId);
             *                 Navigation.findNavController(v).navigate(action);
             */
            CategoryType categoryType=categoryTypeList.get(getAdapterPosition());
            String category=categoryType.getCategory();
            CategoryFragmentDirections.ActionNavigationCategoryToCategoryViewFragment action=
                    CategoryFragmentDirections.actionNavigationCategoryToCategoryViewFragment();
            action.setCategory(category);
            Navigation.findNavController(v).navigate(action);


//            Bundle bundle= new Bundle();
//            String category=categoryType.getCategory();
//            bundle.putString("category",category);
//           Navigation.findNavController(v).navigate(R.id.action_navigation_category_to_categoryViewFragment,bundle);
//




        }
    }
}
