package com.example.wickerlabs.d_pay03;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        // Re using the arrayList created to adapter
    ArrayList<String> arrayList;
    Context context;
    ImageView thumbnaildots;
    PositionCallback positionCallback;


    public RecyclerAdapter(ArrayList<String> arrayList, Context context, PositionCallback callback) {
        this.arrayList= arrayList;
        this.context = context;
        this.positionCallback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_child, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // setting text to card from the arrayList string position
        holder.info.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        // setting expandable size of cards acco0rding to Array list created
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView info;
        public ViewHolder(final View itemView) {
            super(itemView);
            info = (TextView) itemView.findViewById(R.id.info_text);

                // Action when a card is clicked so as it Pop ups a dialog view
            itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ImageView view = new ImageView(context);
                            view.setImageDrawable(context.getResources().getDrawable(R.drawable.qrcode));
                            new AlertDialog.Builder(context).setView(view).show();

                            positionCallback.onItemClickPosition(getLayoutPosition());

                            }
                        });
                // Action when card dots are clicked to popup a delete menu
            thumbnaildots= (ImageView) itemView.findViewById(R.id.thumbnaildots);
            thumbnaildots.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PopupMenu popupMenu = new PopupMenu(context, itemView);
                            popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());

                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                public boolean onMenuItemClick(MenuItem item) {
                                        // function to manually delete the card in Kuponi Tab
                                    KuponiTab.arrayList.remove(getPosition());
                                    KuponiTab.recyclerAdapter.notifyDataSetChanged();

                                    Toast.makeText(context, "Kuponi deleted " + item.getTitle(),
                                            Toast.LENGTH_SHORT).show();

                                    return true;
                                }
                            });
                            popupMenu.show();
                        }
                    }
            );


        }

    }

}
