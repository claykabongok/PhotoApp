package com.claykab.photoApp.ui.categoryview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.claykab.photoApp.R;
import com.claykab.photoApp.adapters.PictureAdapter;
import com.claykab.photoApp.databinding.FragmentCategoryViewBinding;
import com.claykab.photoApp.utils.NetworkState;
import com.google.android.material.snackbar.Snackbar;


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



        categoryViewModel= new ViewModelProvider(this).get(CategoryViewModel.class);

        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),3);



        binding.recyclerviewPictures.setHasFixedSize(true);
        binding.recyclerviewPictures.setLayoutManager(gridLayoutManager);


        //verifies if the device is connected before making a request
        boolean isConnected= NetworkState.isDeviceConnected(getContext());
        if(isConnected) {
            loadPictures();
        }
        else {
            displayNoConnectivityMessage();
        }



        return binding.getRoot();
    }


    private void loadPictures() {


        String category="";


        try {


            CategoryViewFragmentArgs categoryViewFragmentArgs= CategoryViewFragmentArgs.fromBundle(getArguments());
            category=categoryViewFragmentArgs.getCategory();

            getActivity().setTitle(category);

        } catch (Exception e) {
            e.printStackTrace();
        }

        categoryViewModel.getPicturesCat(category).observe(getViewLifecycleOwner(), pictureResponse -> {
            try {
                if(!pictureResponse.getHitsList().isEmpty()){

                    binding.shimmerFrameLayout.stopShimmer();
                    binding.shimmerFrameLayout.setVisibility(View.GONE);
                    pictureAdapter= new PictureAdapter(getContext(), pictureResponse.getHitsList(),"categoryViewAction");
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

    //Notifies the user when the device is offline
    private void displayNoConnectivityMessage() {
        try {

            Snackbar.make(getActivity().findViewById(R.id.nav_host_fragment), "Device offline, please connect to a Wifi or cellular network.", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Retry", v -> {

                        loadPictures();

                    }).show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
