package com.happyballoons.saket.algon.fragments;

/**
 * Created by Saket on 15-Apr-17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.happyballoons.saket.algon.R;


public class RedBlackTrees extends Fragment{

    public RedBlackTrees() {
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
        return inflater.inflate(R.layout.fragment_red_black_trees, container, false);
    }

}
