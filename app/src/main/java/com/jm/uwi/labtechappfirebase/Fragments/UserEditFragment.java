package com.jm.uwi.labtechappfirebase.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jm.uwi.labtechappfirebase.R;

public class UserEditFragment extends Fragment {

    private String mParam1;
    private String mParam2;

    public UserEditFragment() {
        // Required empty public constructor
    }

    public static UserEditFragment newInstance(String param1, String param2) {
        UserEditFragment fragment = new UserEditFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        return inflater.inflate(R.layout.fragment_user_edit, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
