package edu.uncc.assignment04.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uncc.assignment04.Profile;
import edu.uncc.assignment04.R;
import edu.uncc.assignment04.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM_PROFILE = "ARG_PARAM_PROFILE";


    public ProfileFragment() {
        // Required empty public constructor
    }
    //private String name;
    //private String email;
   // private String role;
    private  String education;
    private String maritalStatus;
    private String livingStatus;
    private String income;
    private Profile profile;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            profile = (Profile) getArguments().getSerializable("profile");
            education = getArguments().getString("education");
            maritalStatus = getArguments().getString("maritalStatus");
            livingStatus = getArguments().getString("livingStatus");
            income = getArguments().getString("income");
        }
    }

    FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding =FragmentProfileBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.textViewName.setText(profile.getName());
        binding.textViewEmail.setText(profile.getEmail());
        binding.textViewRole.setText(profile.getRole());

        binding.textViewEdu.setText(education);
        binding.textViewMaritalStatus.setText(maritalStatus);
        binding.textViewLivingStatus.setText(livingStatus);
        binding.textViewIncomeValue.setText(income);

    }
}