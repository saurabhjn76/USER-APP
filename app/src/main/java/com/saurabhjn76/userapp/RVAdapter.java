package com.saurabhjn76.userapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh on 28/5/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.SalonViewHolder> {

    ArrayList<Salon> salons;

    public static class SalonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView salonName;
        TextView salonDistance;
        ImageView salonPhoto;

        SalonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            salonName = (TextView)itemView.findViewById(R.id.salon_name);
            salonDistance = (TextView)itemView.findViewById(R.id.salon_distance);
            salonPhoto = (ImageView)itemView.findViewById(R.id.salon_photo);

        }
    }



    RVAdapter(){
        this.salons=new ArrayList<>();

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public SalonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        SalonViewHolder svh = new SalonViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(SalonViewHolder personViewHolder, int i) {
        personViewHolder.salonName.setText(salons.get(i).getSalonName());
        personViewHolder.salonDistance.setText(salons.get(i).getDistance()+"");
        switch(i%3) {
            case 0:  personViewHolder.salonPhoto.setImageResource(R.drawable.hair_inside_salon);
                        break;
            case 1: personViewHolder.salonPhoto.setImageResource(R.drawable.salon_4_full);
                        break;
            case 2: personViewHolder.salonPhoto.setImageResource(R.drawable.slider_newton_highlands);
                        break;
        }
    }
    @Override
    public int getItemCount() {
        return salons.size();
    }

    public void add(Salon s)
    {
        salons.add(s);
        notifyItemInserted(0);
    }
}
