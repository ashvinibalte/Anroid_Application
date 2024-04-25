package edu.uncc.assignment04.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import edu.uncc.assignment04.R;
import edu.uncc.assignment04.databinding.FragmentSelectEducationBinding;
import edu.uncc.assignment04.databinding.FragmentSelectMaritalStatusBinding;


public class SelectMaritalStatusFragment extends Fragment {
    FragmentSelectMaritalStatusBinding binding;

    public SelectMaritalStatusFragment() {
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
        binding=FragmentSelectMaritalStatusBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msListener.cancelMaritalSelection();
            }
        });

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int checkedId = binding.radioGroup.getCheckedRadioButtonId();
               if(checkedId == -1){
                   Toast.makeText(getActivity(),"make selcetion please", Toast.LENGTH_SHORT).show();
               }else{
                   RadioButton radioButton = binding.radioGroup.findViewById(checkedId);
                   String selectedStatus = radioButton.getText().toString();
                   msListener.sendMaritialSelection(selectedStatus);
               }

            }
        });
    }

    maritialSelectionListener msListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        msListener= (maritialSelectionListener) context;
    }

    public interface maritialSelectionListener{
        void cancelMaritalSelection();
        void sendMaritialSelection(String Status);
    }
}