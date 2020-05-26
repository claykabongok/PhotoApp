package com.claykab.photoApp.ui.categoryview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.claykab.photoApp.adapters.PictureAdapter;
import com.claykab.photoApp.databinding.FragmentCategoryViewBinding;
import com.claykab.photoApp.model.PictureResponse;
import com.claykab.photoApp.utils.API_KEY;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryViewFragment extends Fragment {
    private FragmentCategoryViewBinding binding;
    private CategoryViewModel categoryViewModel;
    private PictureAdapter pictureAdapter;

    public CategoryViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentCategoryViewBinding.inflate(inflater, container, false);
         categoryViewModel= ViewModelProviders.of(this).get(CategoryViewModel.class);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),3);



        binding.recyclerviewPictures.setHasFixedSize(true);
        binding.recyclerviewPictures.setLayoutManager(gridLayoutManager);
        String categoryT="";

//

        loadPictures();

        return binding.getRoot();
    }


    private void loadPictures() {


        String category="";

//
        try {
            category=getArguments().getString("category");
            getActivity().setTitle(category);

        } catch (Exception e) {
            e.printStackTrace();
        }

        categoryViewModel.getPicturesCat(category).observe(getViewLifecycleOwner(), pictureResponse -> {
            try {
                if(!pictureResponse.getHitsList().isEmpty()){

                    binding.shimmerFrameLayout.stopShimmer();
                    binding.shimmerFrameLayout.setVisibility(View.GONE);
                    pictureAdapter= new PictureAdapter(getContext(), pictureResponse.getHitsList());
                    binding.recyclerviewPictures.setVisibility(View.VISIBLE);
                    binding.recyclerviewPictures.setAdapter(pictureAdapter);
                }else {
                    Toast.makeText(getContext(),"Error: ",Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }

        });






    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.shimmerFrameLayout.startShimmer();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}
