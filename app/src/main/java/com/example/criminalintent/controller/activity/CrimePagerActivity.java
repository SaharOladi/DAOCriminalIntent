package com.example.criminalintent.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.criminalintent.R;
import com.example.criminalintent.ZoomOutPageTransform;
import com.example.criminalintent.controller.fragment.CrimeDetailFragment;
import com.example.criminalintent.model.Crime;
import com.example.criminalintent.repository.CrimeRepository;
import com.example.criminalintent.repository.IRepository;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {

    public static final String EXTRA_CRIME_ID = "com.example.criminalintent.crimeId";
    public static final String TAG = "CPA";
    private IRepository mRepository;
    private UUID mCrimeId;

    //private ViewPager2 mViewPagerCrimes;
    private ViewPager mViewPagerCrimes;

    private Button mButtonNext;
    private Button mButtonPrev;
    private Button mButtonFirst;
    private Button mButtonEnd;


    public static Intent newIntent(Context context, UUID crimeId) {
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mRepository = CrimeRepository.getInstance();
        mCrimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        findViews();
        setListener();
        initViews();
    }
    private void setListener(){

        mButtonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPagerCrimes.setCurrentItem(mViewPagerCrimes.getAdapter().getCount());
            }
        });

        mButtonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPagerCrimes.setCurrentItem(0);
            }
        });

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPagerCrimes.setCurrentItem(mViewPagerCrimes.getCurrentItem()+1);
            }
        });
        mButtonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPagerCrimes.setCurrentItem(mViewPagerCrimes.getCurrentItem()-1);
            }
        });

    }


    private void findViews() {
        mViewPagerCrimes = findViewById(R.id.view_pager_crimes);
        mButtonFirst = findViewById(R.id.button_first);
        mButtonNext = findViewById(R.id.button_next);
        mButtonPrev = findViewById(R.id.button_prev);
        mButtonEnd = findViewById(R.id.button_end);
    }

    private void initViews() {
        List<Crime> crimes = mRepository.getCrimes();
        CrimePagerAdapter adapter = new CrimePagerAdapter(getSupportFragmentManager(), crimes);
        mViewPagerCrimes.setAdapter(adapter);

        int currentIndex = mRepository.getPosition(mCrimeId);
        mViewPagerCrimes.setCurrentItem(currentIndex);

        mViewPagerCrimes.setPageTransformer(false, new ZoomOutPageTransform());
    }

    private class CrimePagerAdapter extends FragmentStatePagerAdapter {

        private List<Crime> mCrimes;

        public CrimePagerAdapter(@NonNull FragmentManager fm,List<Crime> crimes) {
            super(fm);
            mCrimes = crimes;
        }



        public List<Crime> getCrimes() {
            return mCrimes;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Log.d(TAG, "position: " + (position + 1));
//
            Crime crime = mCrimes.get(position);
            CrimeDetailFragment crimeDetailFragment =
                    CrimeDetailFragment.newInstance(crime.getId());

            return crimeDetailFragment;
        }

        @Override
        public int getCount() {
            return mCrimes.size();
        }

        public void setCrimes(List<Crime> crimes) {
            mCrimes = crimes;
        }
//
//        public CrimePagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Crime> crimes) {
//            super(fragmentActivity);
//            mCrimes = crimes;
//        }
//
//        @NonNull
//        @Override
//        public Fragment createFragment(int position) {
//            Log.d(TAG, "position: " + (position + 1));
//
//            Crime crime = mCrimes.get(position);
//            CrimeDetailFragment crimeDetailFragment =
//                    CrimeDetailFragment.newInstance(crime.getId());
//
//            return crimeDetailFragment;
//        }
//
//        @Override
//        public int getItemCount() {
//            return mCrimes.size();
//        }
    }
}