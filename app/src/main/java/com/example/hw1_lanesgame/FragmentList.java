package com.example.hw1_lanesgame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.textview.MaterialTextView;

public class FragmentList extends Fragment {

    private MaterialTextView list_LBL_title;
    private ListView fragmentList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        findViews(view);
        return view;
    }


    private void findViews(View view) {
        list_LBL_title = view.findViewById(R.id.list_LBL_title);
        fragmentList = view.findViewById(R.id.fragmentList);
    }

}
