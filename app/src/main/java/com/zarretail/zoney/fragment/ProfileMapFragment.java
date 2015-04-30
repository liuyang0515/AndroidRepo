package com.zarretail.zoney.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zarretail.zoney.R;

/**
 * Created by David on 4/7/2015.
 */
public class ProfileMapFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_myprofile_map, container, false);
    }
}
