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
import edu.uncc.assignment04.databinding.FragmentSelectLivingStatusBinding;


public class SelectLivingStatusFragment extends Fragment {
FragmentSelectLivingStatusBinding binding;

    public SelectLivingStatusFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectLivingStatusBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lsListener.cancelLivingStatus();
            }
        });
        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedID = binding.radioGroup.getCheckedRadioButtonId();
                if(checkedID == -1){
                    Toast.makeText(getActivity(), "make Selction please", Toast.LENGTH_SHORT).show();
                }else {
                    RadioButton radioButton =binding.radioGroup.findViewById(checkedID);
                    String selectedLS = radioButton.getText().toString();
                    lsListener.sendLivingStatus(selectedLS);
                }
            }
        });
    }

    livingStatusSelectListener lsListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        lsListener =(livingStatusSelectListener)context;
    }

    public interface livingStatusSelectListener{
        void cancelLivingStatus();
        void sendLivingStatus(String val);
    }
}