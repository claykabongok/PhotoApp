package com.claykab.photoApp.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.claykab.photoApp.R;
import com.claykab.photoApp.databinding.FragmentCategoryBinding;
import com.claykab.photoApp.model.hits;

public class CategoryFragment extends Fragment {

    private CategoryViewModel categoryViewModel;
    private FragmentCategoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoryViewModel =
                ViewModelProviders.of(this).get(CategoryViewModel.class);
        binding= FragmentCategoryBinding.inflate(inflater, container, false);

        View root = inflater.inflate(R.layout.fragment_category, container, false);

        binding.cvCatFashion.setOnClickListener(v -> {

            Bundle bundle= new Bundle();
            String category="fashion";
            bundle.putString("category",category);
           Navigation.findNavController(v).navigate(R.id.action_navigation_category_to_categoryViewFragment,bundle);

        });
        binding.cvCatNature.setOnClickListener(v -> {
            Bundle bundle= new Bundle();
            String category="nature";
            bundle.putString("category",category);
            Navigation.findNavController(v).navigate(R.id.action_navigation_category_to_categoryViewFragment,bundle);

        });
        binding.cvCatScience.setOnClickListener(v -> {
            Bundle bundle= new Bundle();
            String category="science";
            bundle.putString("category",category);
            Navigation.findNavController(v).navigate(R.id.action_navigation_category_to_categoryViewFragment,bundle);

        });
        binding.cvCatPeople.setOnClickListener(v -> {
            Bundle bundle= new Bundle();
            String category="people";
            bundle.putString("category",category);
            Navigation.findNavController(v).navigate(R.id.action_navigation_category_to_categoryViewFragment,bundle);

        });
        binding.cvCatTravel.setOnClickListener(v -> {
            Bundle bundle= new Bundle();
            String category="travel";
            bundle.putString("category",category);
            Navigation.findNavController(v).navigate(R.id.action_navigation_category_to_categoryViewFragment,bundle);

        });
        binding.cvCatBuildings.setOnClickListener(v -> {
            Bundle bundle= new Bundle();
            String category="buildings";
            bundle.putString("category",category);
            Navigation.findNavController(v).navigate(R.id.action_navigation_category_to_categoryViewFragment,bundle);

        });


        binding.cvCatPlaces.setOnClickListener(v ->{
            Bundle bundle= new Bundle();
            String category="places";
            bundle.putString("category",category);
            Navigation.findNavController(v).navigate(R.id.action_navigation_category_to_categoryViewFragment,bundle);

        });


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
