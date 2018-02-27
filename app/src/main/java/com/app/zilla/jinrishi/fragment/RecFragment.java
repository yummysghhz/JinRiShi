package com.app.zilla.jinrishi.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.zilla.jinrishi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecFragment extends Fragment {


    public RecFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rec, container, false);
    }

}
