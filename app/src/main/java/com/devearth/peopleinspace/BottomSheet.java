package com.devearth.peopleinspace;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * Created by Sumeet Jain on 28-06-2019.
 */

public class BottomSheet extends BottomSheetDialogFragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_bottom_sheet, container,false);

        ImageView asflag =(ImageView) v.findViewById(R.id.asimage);

        asflag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "sa", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}