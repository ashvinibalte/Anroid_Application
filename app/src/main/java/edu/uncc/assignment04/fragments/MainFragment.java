package edu.uncc.assignment04.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uncc.assignment04.R;
import edu.uncc.assignment04.databinding.FragmentMainBinding;


public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    FragmentMainBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
   public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        binding.buttonStart.findViewById(R.id.buttonStart).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mainListener.gotoIdentification();

            }
        });
   }
   MainListener mainListener;
   public void onAttach(@NonNull Context context) {

       super.onAttach(context);
       mainListener = (MainListener) context;
   }
   // define interface in the fragment
    public interface MainListener{
        void gotoIdentification();
   }
}