package com.example.wickerlabs.d_pay03;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MalipoTab extends Fragment {

    EditText editText; TextView nauliOutPut;
    static int nauli;
    ImageButton imageButton,imageButton2,imageButton3,imageButton4;
    String full;
     static NotificationCompat.Builder notification;
    private static final int uniqueID= 45612;
    MyDBHandler dbHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.activity_malipo_tab, container, false);

        nauliOutPut=(TextView) v.findViewById(R.id.nauliOutPut);
        editText= (EditText) v.findViewById(R.id.editText2);

        dbHandler = new MyDBHandler(getActivity(), null, null, 1);

        editText.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        // Setting automatically updates the nauliOutPut value from entered No o tickets
                        nauliOutPut.setText(formatPrice(getNauli(s.toString())) +"/=");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                }
        );

        imageButton=(ImageButton)v.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Ezy Pesa", Toast.LENGTH_SHORT).show();
                            // Action for adding a card in Kuponi Tab regarding No of tickets purchased

                      /*  SharedPreferences sharedEzy =getActivity().getSharedPreferences("EzyCards", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor= sharedEzy.edit();
                        editor.putString("EzyPesa Cards", "");
                        editor.apply();

                        for(int x=0; x<nauli; x++){
                            KuponiTab.arrayList.add("Ezy pesa ");
                            KuponiTab.recyclerAdapter.notifyDataSetChanged(); }  */

                        for(int x=0; x<nauli; x++){
                            String detail= "Ezy  pesa "+x;
                            KuponiTab.arrayList.add(detail);
                            KuponiTab.recyclerAdapter.notifyDataSetChanged();

                            dbHandler.addCard(detail); }

                            // Making a Progress dialog for Requesting coupons after paying
                        final ProgressDialog dialog = new ProgressDialog(getContext());
                        dialog.setMessage("Requesting for Coupon...");
                        dialog.setIndeterminate(true);
                        dialog.setCancelable(true);
                        dialog.show();
                        // thread for making dialog delay for 3 sec
                        Handler h= new Handler();
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                    // brings You back to Kuponi Tab after purchase
                                MainActivity.tabHost.setCurrentTab(1);
                            }
                        }, 2000);

                        notificationClick();
                    }
                }
        );

        imageButton2=(ImageButton)v.findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(
               new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Tigo Pesa", Toast.LENGTH_SHORT).show();
                            // Action for adding a card in Kuponi Tab regarding No of tickets purchased

                     /*   SharedPreferences sharedTigo =getActivity().getSharedPreferences("TigoCards", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor= sharedTigo.edit();
                        editor.putString("TigoPesa Cards", "");
                        editor.apply();

                        for(int x=0; x<nauli; x++){
                        KuponiTab.arrayList.add("Tigo pesa");
                        KuponiTab.recyclerAdapter.notifyDataSetChanged(); }  */

                        for(int x=0; x<nauli; x++){
                            String detail= "Tigo pesa "+x;
                            KuponiTab.arrayList.add(detail);
                            KuponiTab.recyclerAdapter.notifyDataSetChanged();

                            dbHandler.addCard(detail); }

                            // Making a Progress dialog for Requesting coupons after paying
                        final ProgressDialog dialog = new ProgressDialog(getContext());

                        dialog.setMessage("Requesting for Coupon...");
                        dialog.setIndeterminate(true);
                        dialog.setCancelable(true);
                        dialog.show();
                        // thread for making dialog delay for 3 sec
                        Handler h= new Handler();
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                     // brings You back to Kuponi Tab after purchase
                                MainActivity.tabHost.setCurrentTab(1);
                            }
                        }, 2000);
                        notificationClick();

                    }
                }
        );

        imageButton3 = (ImageButton)v.findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "M-Pesa", Toast.LENGTH_SHORT).show();
                             // Action for adding a card in Kuponi Tab regarding No of tickets purchased

                      /*  SharedPreferences sharedVoda =getActivity().getSharedPreferences("VodaCards", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor= sharedVoda.edit();
                        editor.putString("Mpesa Cards", "");
                        editor.apply();

                        for(int x=0; x<nauli; x++){
                            KuponiTab.arrayList.add("M-pesa");
                            KuponiTab.recyclerAdapter.notifyDataSetChanged(); } */

                        for(int x=0; x<nauli; x++){
                            String detail= "M-pesa "+x;
                            KuponiTab.arrayList.add(detail);
                            KuponiTab.recyclerAdapter.notifyDataSetChanged();

                            dbHandler.addCard(detail); }

                            // Making a Progress dialog for Requesting coupons after paying
                        final ProgressDialog dialog = new ProgressDialog(getContext());
                        dialog.setMessage("Requesting for Coupon...");
                        dialog.setIndeterminate(true);
                        dialog.setCancelable(true);
                        dialog.show();
                        // thread for making dialog delay for 3 sec
                        Handler h= new Handler();
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                    // brings You back to Kuponi Tab after purchase
                                MainActivity.tabHost.setCurrentTab(1);
                            }
                        }, 2000);
                        notificationClick();
                    }
                }
        );

        imageButton4=(ImageButton)v.findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "AirTel Money", Toast.LENGTH_SHORT).show();
                            // Action for adding a card in Kuponi Tab regarding No of tickets purchased

                      /*  SharedPreferences shared =getActivity().getSharedPreferences("AirTelCards", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor= shared.edit();
                        editor.putString("AirTel Cards", "");
                        editor.apply();

                        for(int x=0; x<nauli; x++){
                            KuponiTab.arrayList.add("AirTel Money");
                            KuponiTab.recyclerAdapter.notifyDataSetChanged(); } */

                        for(int x=0; x<nauli; x++){
                            String detail= "AirTel Money "+x;
                            KuponiTab.arrayList.add(detail);
                            KuponiTab.recyclerAdapter.notifyDataSetChanged();

                            dbHandler.addCard(detail); }

                             // Making a Progress dialog for Requesting coupons after paying
                        final ProgressDialog dialog = new ProgressDialog(getContext());
                        dialog.setMessage("Requesting for Coupon...");
                        dialog.setIndeterminate(true);
                        dialog.setCancelable(true);

                        dialog.show();
                        // thread for making dialog delay for 3 sec
                        Handler h= new Handler();
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                    // brings You back to Kuponi Tab after purchase
                                MainActivity.tabHost.setCurrentTab(1);
                            }
                        }, 2000);
                        notificationClick();
                    }
                }
        );
            // Creating and declare of notification building
        notification= new NotificationCompat.Builder(getContext());
        notification.setAutoCancel(true);

        return v;
    }
        // method for calculating nauli from No of tickets wanted
    public int getNauli(String sequence){
        int total;
        total=0;
        try{
            nauli=Integer.parseInt(sequence);
            total= nauli*650;
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return total;
    }
        // method for putting 3 digit separator like 1,000
    private String formatPrice(int price){
        String format= "#,###";
        DecimalFormat decimalFormat= new DecimalFormat(format);
        return decimalFormat.format(price);
    }

    public void notificationClick(){

        // Builds Notication Contents will be displayed
        notification.setSmallIcon(R.drawable.ic_launcher);
        notification.setTicker("D-Pay new Coupon");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("New Coupon Added");
        notification.setContentText(" You have receive new Coupon for Tickets ");
        notification.setSound(RingtoneManager.getActualDefaultRingtoneUri(getContext(), RingtoneManager.TYPE_NOTIFICATION));
        Intent intent= new Intent(getActivity(),MainActivity.class);
            // pass a String Key to notification Intent action
            // so can be used onCreate() in MainActivity for setting a particular Tab
        intent.setAction("OpenTab1");

            // PendingIntent pendingIntent= PendingIntent.getActivity(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent= PendingIntent.getActivity(getActivity(), 0, intent, 0);

        notification.setContentIntent(pendingIntent);

        // Builds Notification and sends it to Notification Bar
        final NotificationManager nm= (NotificationManager) getActivity().getSystemService(android.content.Context.NOTIFICATION_SERVICE);

            // makes a delay thread for notification to appear after progress dialog
        Handler notfyDelay= new Handler();
        notfyDelay.postDelayed(new Runnable() {
            @Override
            public void run() {
                nm.notify(uniqueID, notification.build());
            }
        }, 3000);

    }
}


