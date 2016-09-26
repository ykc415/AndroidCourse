package com.sunghyun.andriod.layoutbasic01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;

public class DynamicGrid extends AppCompatActivity {

    GridLayout grid;
    int num = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_grid);

        Button button = (Button) findViewById(R.id.makebutton);
        grid = (GridLayout) findViewById(R.id.gridView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button newButton = new Button(DynamicGrid.this);

                newButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        grid.removeView(view);
                    }
                });
                newButton.setText(String.valueOf(num++));
                grid.addView(newButton);
            }
        });
    }


}
