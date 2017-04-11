package com.example.wickerlabs.d_pay03;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class KuponiTab extends Fragment {

    RecyclerView recyclerView;
        // declare ArrayList and Recycler viewer Static so as they can be accessed any where inside the project
    static ArrayList<String> arrayList;
    static RecyclerAdapter recyclerAdapter;
    MyDBHandler dbHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.activity_kuponi_tab, container, false);


        recyclerView= (RecyclerView)v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        dbHandler= new MyDBHandler(getActivity(),null,null,1);


            // creating a GridLayout for the cards (this, 2) gives 2 column format
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

            // Array implementation for card contents in it
        arrayList= new ArrayList<>();
            // initialy i set it to have no data coz data will be added after purchasing kuponi
                // sample data can be added as arratList.add("weka String");
            // this method should be inside ArrayList Creation

      /*  SharedPreferences sharedEzy= getActivity().getSharedPreferences("EzyCards", Context.MODE_PRIVATE);
        if(sharedEzy.contains("EzyPesa Cards")) {
            for (int y = 0; y < MalipoTab.nauli; y++) {
                arrayList.add("Ezy pesa");
                recyclerAdapter.notifyDataSetChanged();
            }
        }

        SharedPreferences sharedTigo = getActivity().getSharedPreferences("TigoCards", Context.MODE_PRIVATE);
        if (sharedTigo.contains("TigoPesa Cards")) {
            for (int y = 0; y < MalipoTab.nauli; y++) {
                arrayList.add("Tigo pesa");
                recyclerAdapter.notifyDataSetChanged();
            }
        }

        SharedPreferences sharedVoda = getActivity().getSharedPreferences("VodaCards", Context.MODE_PRIVATE);
        if (sharedVoda.contains("Mpesa Cards")) {
            for (int y = 0; y < MalipoTab.nauli; y++) {
                arrayList.add("M-pesa");
                recyclerAdapter.notifyDataSetChanged();
            }
        }

        SharedPreferences sharedAirTel = getActivity().getSharedPreferences("AirTelCards", Context.MODE_PRIVATE);
        if (sharedAirTel.contains("AirTel Cards")) {
            for (int y = 0; y < MalipoTab.nauli; y++) {
                arrayList.add("AirTel Money");
                recyclerAdapter.notifyDataSetChanged();
            }
        }  */

        // prints Database srtings saved when cards were created but its not stable method
        if(dbHandler!=null) {

            for (int a = 0; a <= arrayList.size(); a++) {
                dbHandler.getCardsName();
                arrayList.add(dbHandler.databseToString());
            }
        }else{
            System.out.println("No Db");
        }

        recyclerAdapter = new RecyclerAdapter(arrayList, getContext(), new PositionCallback() {
            @Override
            public void onItemClickPosition(int position) {
                Log.d("Position Callback","Item no "+String.valueOf(position));
            }
        });

        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();

        return v;
    }
}
