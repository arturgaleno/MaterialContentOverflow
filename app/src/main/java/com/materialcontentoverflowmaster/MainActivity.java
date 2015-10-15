package com.materialcontentoverflowmaster;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.materialcontentoverflow.MaterialContentOverflow;


public class MainActivity extends FragmentActivity {

    private MaterialContentOverflow contentOverflow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentOverflow = (MaterialContentOverflow) findViewById(R.id.contentOverflow);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        contentOverflow.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        contentOverflow.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
}
