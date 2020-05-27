package com.claykab.photoApp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.claykab.photoApp.adapters.PictureAdapter;
import com.claykab.photoApp.databinding.FragmentHomeBinding;
import com.claykab.photoApp.model.PictureResponse;
import com.claykab.photoApp.utils.API_KEY;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private PictureAdapter pictureAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel= new ViewModelProvider(this).get(HomeViewModel.class);

        binding=FragmentHomeBinding.inflate(inflater, container, false);

        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),3);



        binding.recyclerviewPictures.setHasFixedSize(true);
        binding.recyclerviewPictures.setLayoutManager(gridLayoutManager);
        loadPictures();





        return binding.getRoot();
    }

    private void loadPictures() {



        homeViewModel.getPictures().observe(getViewLifecycleOwner(), pictureResponse -> {

            try {
                if(!pictureResponse.getHitsList().isEmpty()){

                       binding.shimmerFrameLayout.stopShimmer();
                        binding.shimmerFrameLayout.setVisibility(View.GONE);
                        pictureAdapter= new PictureAdapter(getContext(), pictureResponse.getHitsList(),"homeAction");
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

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.shimmerFrameLayout.startShimmer();

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
        binding=null;

    }
}
