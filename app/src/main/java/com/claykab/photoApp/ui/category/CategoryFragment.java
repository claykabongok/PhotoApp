package com.claykab.photoApp.ui.category;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.claykab.photoApp.R;
import com.claykab.photoApp.adapters.CategoryTypeAdapter;
import com.claykab.photoApp.databinding.FragmentCategoryBinding;
import com.claykab.photoApp.model.CategoryType;
import com.claykab.photoApp.utils.CategoryImageUrl;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {


    private FragmentCategoryBinding binding;
    private CategoryTypeAdapter categoryTypeAdapter;
    private List<CategoryType> categoryTypeList= new ArrayList<>();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding= FragmentCategoryBinding.inflate(inflater, container, false);



//        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        binding.recyclerViewCategoryType.setHasFixedSize(true);
        binding.recyclerViewCategoryType.setLayoutManager(linearLayoutManager);



        categoryTypeList.add(new CategoryType("fashion", CategoryImageUrl.FASHION_IMAGE_URL));
        categoryTypeList.add(new CategoryType("nature",CategoryImageUrl.NATURE_IMAGE_URL));
        categoryTypeList.add(new CategoryType("science",CategoryImageUrl.SCIENCE_IMAGE_URL));
        categoryTypeList.add(new CategoryType("people",CategoryImageUrl.PEOPLE_IMAGE_URL));
        categoryTypeList.add(new CategoryType("travel",CategoryImageUrl.TRAVEL_IMAGE_URL));
        categoryTypeList.add(new CategoryType("buildings",CategoryImageUrl.BUILDING_IMAGE_URL));
        categoryTypeList.add(new CategoryType("places",CategoryImageUrl.PLACES_IMAGE_URL));
        categoryTypeAdapter=new CategoryTypeAdapter(getContext(), categoryTypeList);


        binding.recyclerViewCategoryType.setVisibility(View.VISIBLE);
        binding.recyclerViewCategoryType.setAdapter(categoryTypeAdapter);



        return binding.getRoot();
    }

    /**
     * Called when the view previously created by {@link #onCreateView} has
     * been detached from the fragment.  The next time the fragment needs
     * to be displayed, a new view will be created.  This is called
     * after {@link #onStop()} and before {@link #onDestroy()}.  It is called
     * <em>regardless</em> of whether {@link #onCreateView} returned a
     * non-null view.  Internally it is called after the view's state has
     * been saved but before it has been removed from its parent.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding= null;
    }
}
