package com.claykab.photoApp.ui.imageDetails;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.claykab.photoApp.R;
import com.claykab.photoApp.databinding.FragmentImageDetailsBinding;
import com.claykab.photoApp.utils.API_KEY;
import com.claykab.photoApp.utils.NetworkState;
import com.google.android.material.snackbar.Snackbar;

import java.text.NumberFormat;


public class ImageDetailsFragment extends Fragment {
    private static final int PERMISION_REQUEST_CODE = 2000;
    private FragmentImageDetailsBinding binding;
    private ImageDetailsViewModel imageDetailsViewModel;
    private String pictureUrl="";

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
        imageDetailsViewModel=new  ViewModelProvider(this).get(ImageDetailsViewModel.class);


        //verifies if the device is connected before making a request
        boolean isConnected= NetworkState.isDeviceConnected(getContext());
        if(isConnected) {
            loadSelectedImage();
        }
        else {
            displayNoConnectivityMessage();
        }



        binding.ibDownloadPicture.setOnClickListener(v -> {

            try {
                if (ContextCompat.checkSelfPermission(
                        getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED) {
                    // You can use the API that requires the permission.
                      downloadPictureSelected(pictureUrl);
                }
               else {
                    // You can directly ask for the permission.

                    String[] permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE};

                    requestPermissions(permissions,PERMISION_REQUEST_CODE);


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return binding.getRoot();

    }


    private void downloadPictureSelected(String pictureUrl) {

        //verify if the url is set

        if(pictureUrl != null){
            Toast.makeText(getContext(), "Download in progress", Toast.LENGTH_LONG).show();

            DownloadManager.Request requestDM= new DownloadManager.Request(Uri.parse(pictureUrl));
            requestDM.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
            requestDM.setTitle("Download in Progress");
            requestDM.setTitle("Downloading selected picture");
            requestDM.allowScanningByMediaScanner();
            requestDM.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            requestDM.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"PhotoApp/"+System.currentTimeMillis()+".jpg");
            DownloadManager downloadManager=(DownloadManager)getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
            downloadManager.enqueue(requestDM);
        }
    }

    private void displayMessagePermission() {
        try {

            Snackbar.make(getActivity().findViewById(R.id.nav_host_fragment), "Permission to access to your external storage required to download files", Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", v -> {

                    }).show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSelectedImage() {

        long pictureId=0;
       String key= API_KEY.API_KEY;
//
        try {
            ImageDetailsFragmentArgs imageDetailsFragmentArgs= ImageDetailsFragmentArgs.fromBundle(getArguments());
            pictureId=imageDetailsFragmentArgs.getPictureId();


        } catch (Exception e) {
            e.printStackTrace();
        }

        imageDetailsViewModel.getPicturesById(key,pictureId).observe(getViewLifecycleOwner(), pictureDetailsResponse -> {

            try {
                 if(!pictureDetailsResponse.getHitsList().isEmpty()){
                     Glide.with(getContext()).load(pictureDetailsResponse.getHitsList().get(0).getLargeImageURL())
                            .placeholder(R.drawable.ic_image_black_24dp).into(binding.ivSelectedPicture);

                     long value = pictureDetailsResponse.getHitsList().get(0).getComments();
                     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                         binding.tvCommentValue.setText(NumberFormat.getInstance().format(pictureDetailsResponse.getHitsList().get(0).getComments()));
                         binding.tvFavoriteValue.setText(NumberFormat.getInstance().format(pictureDetailsResponse.getHitsList().get(0).getFavorites()));
                         binding.tvLikesValue.setText(NumberFormat.getInstance().format(pictureDetailsResponse.getHitsList().get(0).getLikes()));
                         binding.tvViewsValue.setText(NumberFormat.getInstance().format(pictureDetailsResponse.getHitsList().get(0).getViews()));
                         binding.tvDownloadValue.setText(NumberFormat.getInstance().format(pictureDetailsResponse.getHitsList().get(0).getDownloads()));
                     }else{

                         binding.tvCommentValue.setText(String.valueOf(pictureDetailsResponse.getHitsList().get(0).getComments()));
                         binding.tvFavoriteValue.setText(String.valueOf(pictureDetailsResponse.getHitsList().get(0).getFavorites()));
                         binding.tvLikesValue.setText(String.valueOf(pictureDetailsResponse.getHitsList().get(0).getLikes()));
                         binding.tvViewsValue.setText(String.valueOf(pictureDetailsResponse.getHitsList().get(0).getViews()));
                         binding.tvDownloadValue.setText(String.valueOf(pictureDetailsResponse.getHitsList().get(0).getDownloads()));

                     }
                     binding.tvImageTagsValue.setText(pictureDetailsResponse.getHitsList().get(0).getTags());
                     Glide.with(getContext()).load(pictureDetailsResponse.getHitsList().get(0).getUserImageURL())
                             .placeholder(R.drawable.ic_person_theme_color_24dp).into(binding.ivImageAuthor);
                     binding.tvImageAuthorNameValue.setText(String.valueOf(pictureDetailsResponse.getHitsList().get(0).getUser()));
                     pictureUrl=pictureDetailsResponse.getHitsList().get(0).getLargeImageURL();



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

    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link PackageManager#PERMISSION_GRANTED}
     *                     or {@link PackageManager#PERMISSION_DENIED}. Never null.
     * @see #requestPermissions(String[], int)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISION_REQUEST_CODE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                    downloadPictureSelected(pictureUrl);
                }  else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                    displayMessagePermission();

                }
                return;
        }
    }

    //Notifies the user when the device is offline
    private void displayNoConnectivityMessage() {
        try {

            Snackbar.make(getActivity().findViewById(R.id.nav_host_fragment), "Device offline, please connect to a Wifi or cellular network.", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Retry", v -> {
                       loadSelectedImage();
                    }).show();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
