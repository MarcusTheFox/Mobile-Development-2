package ru.mirea.bublikov.shoppinglist.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.bublikov.shoppinglist.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvEmail = view.findViewById(R.id.tvUserEmail);

        ViewModelFactory factory = new ViewModelFactory(requireContext());
        viewModel = new ViewModelProvider(this, factory).get(ProfileViewModel.class);

        viewModel.email.observe(getViewLifecycleOwner(), email -> {
            tvEmail.setText(email);
        });
    }
}