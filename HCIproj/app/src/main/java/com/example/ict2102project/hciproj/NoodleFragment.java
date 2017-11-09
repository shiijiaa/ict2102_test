package com.example.ict2102project.hciproj;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class NoodleFragment extends Fragment {

    public NoodleFragment() {
        // Required empty public constructor
    }

    public static NoodleFragment newInstance() {
        NoodleFragment fragment = new NoodleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_noodle, container, false);
    }

}
