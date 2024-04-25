package edu.uncc.assignment04.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import edu.uncc.assignment04.Profile;
import edu.uncc.assignment04.R;
import edu.uncc.assignment04.databinding.FragmentIdentificationBinding;
import edu.uncc.assignment04.databinding.FragmentMainBinding;


public class IdentificationFragment extends Fragment {
    public static final String TAG= "demo";


    public IdentificationFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    FragmentIdentificationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentIdentificationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        binding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.editTextName.getText().toString();
                String email = binding.editTextEmail.getText().toString();
                int selectedRadioButtonId = binding.radioGroup.getCheckedRadioButtonId();
                String role ="";
                if (selectedRadioButtonId == R.id.radioButtonStudent) {
                    role = "Student";
                } else if (selectedRadioButtonId == R.id.radioButtonEmployee) {
                    role = "Employee";
                } else if (selectedRadioButtonId == R.id.radioButtonOther) {
                    role = "Other";
                }
                try {
                    Log.d(TAG, "Name: " + name);
                    Log.d(TAG, "Email: " + email);
                    Log.d(TAG, "Role: " + role);
                    if(name.isEmpty()){
                        Toast.makeText(getActivity(),"Enter valid name and Email", Toast.LENGTH_SHORT).show();
                    }else if(email.isEmpty()){
                        Toast.makeText(getActivity(),"Enter Email", Toast.LENGTH_SHORT).show();
                    }else if(role.isEmpty()){
                        Toast.makeText(getActivity(),"Select Role", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Profile profile = new Profile(name, email, role);
                        identificationListerner.gotoDemographic(profile);

                    }
                }catch(Exception ex) {
                    Toast.makeText(getActivity(),"Enter above Details", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    identificationListerner identificationListerner;
    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
       identificationListerner = (identificationListerner) context;
    }
    public interface identificationListerner{
        void sendProfile(Profile profile);
        void gotoDemographic(Profile profile);
    }
}