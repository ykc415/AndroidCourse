package com.sunghyun.andriod.layoutbasic01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button gridButton, layoutCodeButton, linearButton, dynamicGridButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutCodeButton = (Button) findViewById(R.id.layout_button);
        gridButton = (Button) findViewById(R.id.grid_button);
        linearButton = (Button) findViewById(R.id.linear_button);
        dynamicGridButton = (Button) findViewById(R.id.DGbutton);

        layoutCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LayoutCode.class);
                startActivity(intent);
            }
        });

        gridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Grid.class);
                startActivity(intent);
            }
        });

        linearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Linear.class);
                startActivity(intent);
            }
        });

        dynamicGridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DynamicGrid.class);
                startActivity(intent);
            }
        });
    }
}
