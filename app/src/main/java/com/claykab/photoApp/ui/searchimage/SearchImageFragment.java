package com.claykab.photoApp.ui.searchimage;


import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.claykab.photoApp.R;
import com.claykab.photoApp.databinding.FragmentSearchImageBinding;
import com.claykab.photoApp.utils.NetworkState;

public class SearchImageFragment extends Fragment {

    private SearchImageViewModel searchImageViewModel;
    private FragmentSearchImageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding =FragmentSearchImageBinding.inflate(inflater, container, false);

        searchImageViewModel =
                ViewModelProviders.of(this).get(SearchImageViewModel.class);

        setHasOptionsMenu(true);
        searchImageViewModel.getText().observe(getViewLifecycleOwner(), s -> binding.textNotifications.setText(s));
        return  binding.getRoot();
    }


    /**
     * Initialize the contents of the Fragment host's standard options menu.  You
     * should place your menu items in to <var>menu</var>.  For this method
     * to be called, you must have first called {@link #setHasOptionsMenu}.  See
     * { #onCreateOptionsMenu(Menu) Activity.onCreateOptionsMenu}
     * for more information.
     *
     * @param menu     The options menu in which you place your items.
     * @param inflater
     * @see #setHasOptionsMenu
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        try {
            inflater=getActivity().getMenuInflater();
            inflater.inflate(R.menu.menu_search_image,menu);
            MenuItem searchItem= menu.findItem(R.id.app_bar_search_image);
            SearchView searchView=(SearchView) searchItem.getActionView();
            searchView.setInputType(InputType.TYPE_CLASS_TEXT);
            searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

            boolean isConnected= NetworkState.isDeviceConnected(getContext());

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    Toast.makeText(getContext(),"Search submitted.", Toast.LENGTH_LONG).show();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
            super.onCreateOptionsMenu(menu, inflater);
        } catch (Exception e) {
            e.printStackTrace();

        }
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
