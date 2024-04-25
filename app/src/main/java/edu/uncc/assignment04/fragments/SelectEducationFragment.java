package edu.uncc.assignment04.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import edu.uncc.assignment04.R;
import edu.uncc.assignment04.databinding.FragmentSelectEducationBinding;


public class SelectEducationFragment extends Fragment {
    public static final String TAG="demo";
    FragmentSelectEducationBinding binding;



    public SelectEducationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSelectEducationBinding.inflate(inflater,container,false);
        return binding.getRoot();

     }
     public void onViewCreated(@NonNull View view,@NonNull Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        binding.buttonCancel.findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eduListener.gotoBackDemographicFragment();
            }
        });
        binding.buttonSubmit.findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = binding.radioGroup.getCheckedRadioButtonId();
                if(checkedId == -1){
                    Toast.makeText(getActivity(),"select Education level", Toast.LENGTH_SHORT).show();
                }else {
                    RadioButton radioButton = binding.radioGroup.findViewById(checkedId);
                    String education = radioButton.getText().toString();
                    eduListener.onEducationSelected(education);
                }
            }
        });
     }

    SelectEducationListener eduListener;

    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        eduListener = (SelectEducationListener) context;
    }
    public interface SelectEducationListener {
        void gotoBackDemographicFragment();
        void onEducationSelected(String education);


    }
}