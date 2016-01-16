package com.example.harjot.game_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button anti, normal, clear;
    static String str="normal";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anti = (Button) findViewById(R.id.anti);
        normal = (Button) findViewById(R.id.normal);
        clear= (Button) findViewById(R.id.clr);
        normal.setText("SELECTED");
        onClickListener1();
        onClickListener2();
        onClickListener3();
    }
    public void onClickListener1(){
        anti.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        str="anti";
                        anti.setText("SELECTED");
                        normal.setText("");
                    }
                }
        );
    }
    public void onClickListener2(){
        normal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        str="normal";
                        anti.setText("");
                        normal.setText("SELECTED");
                    }
                }
        );
    }
    public void onClickListener3(){
        clear.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CanvasView.circles.clear();
                    }
                }
        );
    }
}
