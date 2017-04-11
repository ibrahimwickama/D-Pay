package com.example.wickerlabs.d_pay03;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Splash extends AppCompatActivity {

    ProgressBar progressBar;
    ImageView imageView, reseter;
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 4000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        imageView= (ImageView) findViewById(R.id.splashscreen);


        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                    // checking if in sharedPreferences contains 'password' file if yes then login if no Register
                SharedPreferences sharedPref= getSharedPreferences("PassVault", MODE_PRIVATE);
                if(sharedPref.contains("password")){
                    Intent a= new Intent(Splash.this, PopUpLogin.class);
                    startActivity(a);
                }else {
                    Intent a= new Intent(Splash.this, PopUpReg.class);
                    startActivity(a);
                }



                /* Create an Intent that will start the Menu-Activity. */
               /* Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();  */
            }
        }, SPLASH_DISPLAY_LENGTH);
            // handler for making progress Bar disappear after some time
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                //imageView.setVisibility(View.VISIBLE);
            }
        }, 2000);


    }
}