package com.danshima.noodleapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class KoreanFragment extends Fragment {
    public KoreanFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_korean, container, false);

        ListView list = view.findViewById(R.id.selected_noodleList);
        //Intent intent = new Intent(getActivity(), NoodleActivity.class);
        //getActivity().startActivity(intent);
        // Inflate the layout for this fragment
        return view;

    }
}
