package com.saurabhjn76.userapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh on 28/5/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.SalonViewHolder> {

    ArrayList<Salon> salons;
    private Context context;
    public static class SalonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView salonName;
        TextView salonDistance;
        TextView salonRating;
        ImageView salonPhoto;
        RatingBar rb;
        TextView salonAddress;

        SalonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            salonName = (TextView)itemView.findViewById(R.id.salon_name);
            salonDistance = (TextView)itemView.findViewById(R.id.salon_distance);
            salonPhoto = (ImageView)itemView.findViewById(R.id.salon_photo);
           // rb=(RatingBar) itemView.findViewById(R.id.rating);
          //  salonRating=(TextView)itemView.findViewById(R.id.ratingTextView);
            salonAddress=(TextView)itemView.findViewById(R.id.AddressTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                    salonPhoto.buildDrawingCache();
                    Bitmap bitmap = salonPhoto.getDrawingCache();
                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                    intent.putExtra("byteArray", bs.toByteArray());
                    intent.putExtra("salon_name",salonName.getText());
                    intent.putExtra("salon_distance",salonDistance.getText());
                    v.getContext().startActivity(intent);
                    Toast.makeText(v.getContext(), "os version is: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });

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
        personViewHolder.salonDistance.setText(salons.get(i).getDistance() + " Km");
        switch (i % 3) {
            case 0:
                personViewHolder.salonPhoto.setImageResource(R.drawable.hair_inside_salon);
                break;
            case 1:
                personViewHolder.salonPhoto.setImageResource(R.drawable.salon_4_full);
                break;
            case 2:
                personViewHolder.salonPhoto.setImageResource(R.drawable.slider_newton_highlands);
                break;
        }
        // personViewHolder.rb.setRating((float)(salons.get(i).getRating())/1f);
        //personViewHolder.salonRating.setText((float) Math.round(salons.get(i).getRating()*10d)/10d + "/5");
        personViewHolder.salonAddress.setText(salons.get(i).getAddressLine1() + " " + salons.get(i).getAddressLine2());

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
