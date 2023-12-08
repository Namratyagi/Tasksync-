package com.example.tasksyncapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public

class

Splash_activity

        extends

        AppCompatActivity

{

    @Override


    protected

    void

    onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delay the action until after the layout is inflated
        View decorView = getWindow().getDecorView();
        decorView.post(new Runnable() {
            @Override
            public void run() {
                getSupportActionBar(); // Hide the action bar after the delay

                final Intent i = new Intent(Splash_activity.this, MainActivity.class);
                new Handler().postDelayed(new Runnable() {
                    @Override


                    public

                    void

                    run()

                    {
                        startActivity(i);
                        finish();
                    }
                }, 2000);
            }
        });
    }
}