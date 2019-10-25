package com.google.ar.sceneform.samples.hellosceneform;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MenuActivity extends AppCompatActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButton = findViewById(R.id.time1);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.time1:
                Intent intentNF = new Intent(this, SceneformActivity.class);
                startActivity(intentNF);

                break;

            case R.id.time2:
                //Intent intentNF = new Intent(this, SceneformActivity2.class);
                //startActivity(intentNF);
                break;

        }
    }
}



