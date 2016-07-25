package com.example.kankoyan.summer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Vector;

public class Draw extends AppCompatActivity {
    DrawView drawView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        ArrayList<Integer> path_new = (ArrayList<Integer>)intent.getSerializableExtra("path");
        drawView = new DrawView(this,path_new);
        setContentView(drawView);

    }
}
