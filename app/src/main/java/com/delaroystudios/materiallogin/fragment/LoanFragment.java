package com.delaroystudios.materiallogin.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delaroystudios.materiallogin.PayLoanActivity;
import com.delaroystudios.materiallogin.RequestLoanActivity;


import com.delaroystudios.materiallogin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoanFragment extends Fragment {

    CardView getloan, payloan;


    public LoanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_loank, container, false);
        getloan = view.findViewById(R.id.getloan);
        payloan= view.findViewById(R.id.payloan);

        getloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getLoan = new Intent( getActivity(), RequestLoanActivity.class);
                startActivity(getLoan);
            }
        });
        payloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getLoan = new Intent( getActivity(), PayLoanActivity.class);
                startActivity(getLoan);
            }
        });

        return view;



    }

}
