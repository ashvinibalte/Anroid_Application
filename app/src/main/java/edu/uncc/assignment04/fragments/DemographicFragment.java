package edu.uncc.assignment04.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import edu.uncc.assignment04.Profile;
import edu.uncc.assignment04.R;
import edu.uncc.assignment04.databinding.FragmentDemographicBinding;


public class DemographicFragment extends Fragment {
    public static final String TAG= "demo";
    // for education selection
    private String selectedEdu;
    public void setSelectedEdu(String selectedEdu) {
        this.selectedEdu = selectedEdu;
    }
    //marrrige state selection
    private String selectedMaritial;
    public void setSelectedMaritial(String selectedMaritial) {
        this.selectedMaritial = selectedMaritial;
    }
    // for living ststus selection
    private String selectLivSta;

    public void setSelectLivSta(String selectLivSta) {
        this.selectLivSta = selectLivSta;
    }
    // for income
    private String selectIncome;
    public void setSelectIncome(String selectIncome) {
        this.selectIncome = selectIncome;
    }

    FragmentDemographicBinding binding;
    TextView textViewEducation;
    public DemographicFragment() {
        // Required empty public constructor
    }



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        if (getArguments() != null) {
            Profile profile = (Profile) args.getSerializable("PROFILE");
            if (profile != null) {
                // You have the Profile object here, you can use it as needed
                String name = profile.getName();
                String email = profile.getEmail();
                String role = profile.getRole();
                Log.d(TAG, "Received Data in Demographic: " + profile.toString());

                // Alternatively, log individual attributes
                Log.d(TAG, "Name: " + name);
                Log.d(TAG, "Email: " + email);
                Log.d(TAG, "Role: " + role);

            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView:DemographicFragment ");
        // Inflate the layout for this fragment
        binding =FragmentDemographicBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated:DemographicFragment ");
        Log.d(TAG, "onViewCreated: onViewCreated "+selectedEdu);
        if (selectedEdu == null){
            binding.textViewEducation.setText("N/A");

        }else {
            binding.textViewEducation.setText(selectedEdu);

        }

        if(selectedMaritial == null){
            binding.textViewMaritalStatus.setText("N/A");
        }else {
            binding.textViewMaritalStatus.setText(selectedMaritial);
        }
        if(selectLivSta ==null)
        {
            binding.textViewLivingStatus.setText("N/A");
        }else {
            binding.textViewLivingStatus.setText(selectLivSta);
        }
        if(selectIncome == null)
        {
            binding.textViewIncomeStatus.setText("N/A");
        }else {
            binding.textViewIncomeStatus.setText(selectIncome);
        }
        binding.buttonSelectEducation.findViewById(R.id.buttonSelectEducation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dListener.gotoEducation();

                }

        });
        binding.buttonSelectMarital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dListener.gotoMaritalSelectFragment();
            }
        });
        binding.buttonSelectLiving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dListener.gotoLivingSatusFragment();
            }
        });
        binding.buttonSelectIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dListener.gotoIncomeFragment();
            }
        });

        binding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedEdu == null || selectedEdu.isEmpty() ||
                        selectedMaritial == null || selectedMaritial.isEmpty() ||
                        selectLivSta == null || selectLivSta.isEmpty() ||
                        selectIncome == null || selectIncome.isEmpty()) {
                    Toast.makeText(getContext(), "Please Make Selection for All above.", Toast.LENGTH_LONG).show();
                    return;
                }
                Bundle args = getArguments(); // Retrieve the arguments bundle
                if (args != null && args.containsKey("PROFILE")) {
                    Profile profile = (Profile) args.getSerializable("PROFILE");

                    if (profile != null) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("profile", profile);
                        bundle.putString("education", selectedEdu);
                        bundle.putString("maritalStatus", selectedMaritial);
                        bundle.putString("livingStatus", selectLivSta);
                        bundle.putString("income", selectIncome);

                        dListener.onUpdateProfileData(bundle); //
                    } else {
                        Log.e(TAG, "Profile object is null");
                    }
                } else {
                    Log.e(TAG, "Arguments Bundle is Null or PROFILE key is missing");
                }
            }
        });
    }


    DemographicListerner dListener;
    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        dListener =(DemographicListerner) context;

    }


    public interface DemographicListerner{
        void gotoEducation();
        void gotoMaritalSelectFragment();
        void gotoLivingSatusFragment();
        void gotoIncomeFragment();

        void onUpdateProfileData(Bundle bundle);

    }
}