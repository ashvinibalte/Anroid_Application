package edu.uncc.assignment04.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import edu.uncc.assignment04.R;
import edu.uncc.assignment04.databinding.FragmentSelectIncomeBinding;


public class SelectIncomeFragment extends Fragment {
    FragmentSelectIncomeBinding binding;
    public SelectIncomeFragment() {
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
        binding =FragmentSelectIncomeBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iListener.cancelIncome();
            }
        });
        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int progress = binding.seekBar.getProgress();
                String incomeRange = getIncomeRange(progress);
                iListener.sendIncome(incomeRange);
            }
        });
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String incomeRange = getIncomeRange(progress);
                binding.textViewHouseHoldIncome.setText(incomeRange);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private String getIncomeRange(int progress)
    {switch (progress)
    {
        case 0:
            return "$0 to $10K";
        case 1:
            return "$10K to $20K";
        case 2:
            return "$20K to $30K";
        case 3:
            return "$30K to $40K";
        case 4:
            return "$40K to $50K";
        case 5:
            return "$50K to $60K";
        case 6:
            return "$60K to $70K";
        case 7:
            return "$70K to $80K";
        case 8:
            return "$80K to $90K";
        case 9:
            return "$90K to $100K";
        case 10:
            return "More than $100K";
        default:
            return "Unknown";
    }
    }

    icomeSelectListener iListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        iListener= (icomeSelectListener) context;
    }

    public interface icomeSelectListener{
        void  cancelIncome();
        void sendIncome(String income);
    }
}