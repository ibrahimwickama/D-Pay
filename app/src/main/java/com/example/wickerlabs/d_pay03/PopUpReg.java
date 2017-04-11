package com.example.wickerlabs.d_pay03;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class PopUpReg extends AppCompatActivity {
    EditText newPin, rePin;
    ImageView reseter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            // make ActionBar disAppear
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popsup);

        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width= dm.widthPixels;
        int height= dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.5));
        getWindow().setGravity(Gravity.CENTER_VERTICAL);
            // method for making PoPuP Window be transparent background
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            // PadLock image for resetting password in Register activity
        reseter=(ImageView)findViewById(R.id.reset_icon);
        reseter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popupMenu = new PopupMenu(PopUpReg.this, v);
                        popupMenu.getMenuInflater().inflate(R.menu.passreset, popupMenu.getMenu());

                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            public boolean onMenuItemClick(MenuItem item) {
                                    // Use to delete all SharedPreference data created before resetting all saved data
                                SharedPreferences sharedPref = getSharedPreferences("PassVault", MODE_PRIVATE);
                                SharedPreferences.Editor editor= sharedPref.edit();
                                editor.clear().commit();

                                    // Codes for restarting the application immediately
                                Intent i = getBaseContext().getPackageManager()
                                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);

                                Toast.makeText(PopUpReg.this, "Pin has been resetted  ", Toast.LENGTH_SHORT).show();
                                return true;
                            }
                        });
                        popupMenu.show();
                    }
                }
        );
    }

    public void goClicked(View view){
        newPin= (EditText)findViewById(R.id.newPin);
        rePin=(EditText)findViewById(R.id.rePin);
        String pin1= newPin.getText().toString();
        String pin2=rePin.getText().toString();

        if(pin1.equals(pin2)){
                //Using SharedPreference as a local DB for saving passwords
            SharedPreferences sharedPref= getSharedPreferences("PassVault", MODE_PRIVATE);
            SharedPreferences.Editor editor= sharedPref.edit();
            editor.putString("password", pin1 );
            editor.apply();
        }else {
            Toast.makeText(PopUpReg.this, "Passwords don`t match", Toast.LENGTH_SHORT).show();
        }

        Intent i= new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
