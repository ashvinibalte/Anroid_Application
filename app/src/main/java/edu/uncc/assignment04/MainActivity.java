package edu.uncc.assignment04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import edu.uncc.assignment04.fragments.DemographicFragment;
import edu.uncc.assignment04.fragments.IdentificationFragment;
import edu.uncc.assignment04.fragments.MainFragment;
import edu.uncc.assignment04.fragments.ProfileFragment;
import edu.uncc.assignment04.fragments.SelectEducationFragment;
import edu.uncc.assignment04.fragments.SelectIncomeFragment;
import edu.uncc.assignment04.fragments.SelectLivingStatusFragment;
import edu.uncc.assignment04.fragments.SelectMaritalStatusFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.MainListener, IdentificationFragment.identificationListerner, DemographicFragment.DemographicListerner, SelectEducationFragment.SelectEducationListener, SelectMaritalStatusFragment.maritialSelectionListener, SelectLivingStatusFragment.livingStatusSelectListener, SelectIncomeFragment.icomeSelectListener{
    // implements the interface
    public static final String TAG="demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainerView, new MainFragment())
                .commit();
        Log.d(TAG, "MainActivity: onCreate: ");
    }
    protected void onResume() {

        super.onResume();
        Log.d(TAG, "MainActivity: onResume: ");
    }
    public void gotoIdentification(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, new IdentificationFragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void sendProfile(Profile profile) {
        Toast.makeText(this, "Received profile: " + profile.toString(), Toast.LENGTH_SHORT).show();
        /*getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, ProfileFragment.newInstance(profile))
                .addToBackStack()
                .commit();*/


    }
    public void sendEducation(String education){
        Log.d(TAG, "sendEducation: "+education);
    }

    @Override
    public void gotoDemographic(Profile profile) {
        Log.d(TAG, "Received Profile: " + profile.toString());
        Bundle bundle = new Bundle();
        bundle.putSerializable("PROFILE",profile);
        DemographicFragment demoFrag =new DemographicFragment();
        demoFrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, demoFrag, "Demographic-Fragment")
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void gotoEducation() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, new SelectEducationFragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void gotoMaritalSelectFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, new SelectMaritalStatusFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoLivingSatusFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, new SelectLivingStatusFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoIncomeFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, new SelectIncomeFragment())
                .addToBackStack(null)
                .commit();
        
    }


    @Override
    public void onUpdateProfileData(Bundle bundle) {
        ProfileFragment profileFragment = new ProfileFragment();
        profileFragment.setArguments(bundle); // Pass the bundle to the fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, profileFragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void gotoBackDemographicFragment() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onEducationSelected(String education) {
        Log.d(TAG, "OnEducation Selected: " + education);
        // find the fragment that we would like to send the education level to
        //send the data to that fragment
        //pop the back stack.
        DemographicFragment demographicFragment = (DemographicFragment) getSupportFragmentManager().findFragmentByTag("Demographic-Fragment");

        if(demographicFragment!=null){
            demographicFragment.setSelectedEdu(education);

            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void cancelMaritalSelection() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendMaritialSelection(String Status) {
        Log.d(TAG, "sendMaritialSelection: "+Status);
        DemographicFragment marital = (DemographicFragment) getSupportFragmentManager().findFragmentByTag("Demographic-Fragment");
        if(marital !=null)
        {
            marital.setSelectedMaritial(Status);
            getSupportFragmentManager().popBackStack();
        }

    }

    @Override
    public void cancelLivingStatus() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendLivingStatus(String val) {
        Log.d(TAG, "sendLivingStatus: "+val);
        DemographicFragment living = (DemographicFragment) getSupportFragmentManager().findFragmentByTag("Demographic-Fragment");
        if(living !=null)
        {
            living.setSelectLivSta(val);
            getSupportFragmentManager().popBackStack();
        }

    }

    @Override
    public void cancelIncome() {
        getSupportFragmentManager().popBackStack();

    }

    @Override
    public void sendIncome(String income) {
        Log.d(TAG, "sendIncome: "+income);
        DemographicFragment inSelect = (DemographicFragment) getSupportFragmentManager().findFragmentByTag("Demographic-Fragment");
        if (income !=null)
        {
            inSelect.setSelectIncome(income);
            getSupportFragmentManager().popBackStack();
        }

    }
}