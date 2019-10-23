package com.google.ar.sceneform.samples.hellosceneform;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //TODO - descobrir qual é o setContentView correto pro menu (ou criar um) setContentView(R.layout.main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.time1:
                startActivity(new Intent(this, SceneformActivity.class));
                return true;
            case R.id.time2:
                //startActivity(new Intent(this, SceneformActivity2.class));
                return true;

                //-TODO -  gambiarra abaixo se nada der certo
           /* case R.id.time2:
                startActivity(new Intent(this, SceneformActivity3.class));
                return true;
            case R.id.time3:
                startActivity(new Intent(this, SceneformActivity4.class));
                return true;
            case R.id.time4:
                startActivity(new Intent(this, SceneformActivity5.class));
                return true;
            case R.id.time5:
                startActivity(new Intent(this, SceneformActivity6.class));
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
