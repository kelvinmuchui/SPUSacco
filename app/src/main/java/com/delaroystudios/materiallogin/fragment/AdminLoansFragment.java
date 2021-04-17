package com.delaroystudios.materiallogin.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delaroystudios.materiallogin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminLoansFragment extends Fragment {


    public AdminLoansFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_loans, container, false);
    }

}
