package com.claykab.photoApp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.claykab.photoApp.adapters.PictureAdapter;
import com.claykab.photoApp.databinding.FragmentHomeBinding;
import com.claykab.photoApp.model.PictureResponse;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private PictureAdapter pictureAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        binding=FragmentHomeBinding.inflate(inflater, container, false);

        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),2);


        binding.recyclerviewPictures.setHasFixedSize(true);
        binding.recyclerviewPictures.setLayoutManager(gridLayoutManager);
        loadPictures();





        return binding.getRoot();
    }

    private void loadPictures() {
        homeViewModel.getPictures().observe(getViewLifecycleOwner(), new Observer<PictureResponse>() {
            @Override
            public void onChanged(PictureResponse pictureResponse) {
                try {
                    Toast.makeText(getContext(), "Number of hits: "+pictureResponse.getTotalHits(),Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Error: "+e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }

                try {
                    if(!pictureResponse.getHitsList().isEmpty()){

                            binding.progressBarPictures.setVisibility(View.GONE);
                            pictureAdapter= new PictureAdapter(getContext(), pictureResponse.getHitsList());
                            binding.recyclerviewPictures.setAdapter(pictureAdapter);



                    }else {
                        Toast.makeText(getContext(),"Error: ",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(getContext(), "Error: "+e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });



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
