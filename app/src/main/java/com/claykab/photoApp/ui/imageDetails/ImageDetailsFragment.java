package com.claykab.photoApp.ui.imageDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.claykab.photoApp.R;
import com.claykab.photoApp.databinding.FragmentImageDetailsBinding;
import com.claykab.photoApp.utils.API_KEY;


public class ImageDetailsFragment extends Fragment {
    private FragmentImageDetailsBinding binding;
    private ImageDetailsViewModel imageDetailsViewModel;

    public ImageDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentImageDetailsBinding.inflate(inflater, container, false);
        try {
            getActivity().setTitle("Image Info");

        } catch (Exception e) {
            e.printStackTrace();
        }
        imageDetailsViewModel= ViewModelProviders.of(this).get(ImageDetailsViewModel.class);

        loadSelectedImage();
        return binding.getRoot();

    }

    private void loadSelectedImage() {
        long pictureId=0;
       String key= API_KEY.API_KEY;
//
        try {
            pictureId=getArguments().getLong("pictureId");
            Toast.makeText(getContext(), "Picture id: "+pictureId,Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        imageDetailsViewModel.getPicturesById(key,pictureId).observe(getViewLifecycleOwner(), pictureDetailsResponse -> {

            try {
                 if(!pictureDetailsResponse.getHitsList().isEmpty()){
                     Glide.with(getContext()).load(pictureDetailsResponse.getHitsList().get(0).getLargeImageURL())
                            .placeholder(R.drawable.ic_image_black_24dp).into(binding.ivSelectedPicture);

                }
            } catch (Exception e) {
                e.printStackTrace();

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
        binding= null;
    }
}
