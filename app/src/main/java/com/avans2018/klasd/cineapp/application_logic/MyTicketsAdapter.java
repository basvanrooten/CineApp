package com.avans2018.klasd.cineapp.application_logic;

// Gebruikt door MyTicketsActivity voor weergeven opgeslagen (aangekochte) tickets

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.domain.Movie;
import com.avans2018.klasd.cineapp.domain.Ticket;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyTicketsAdapter extends RecyclerView.Adapter<MyTicketsAdapter.TicketViewHolder> {

    private Context mContext;
    private ArrayList<Ticket> ticketList;
    private OnItemClickListener listener;
    private final static String TAG = "MyTicketsAdapter";

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MyTicketsAdapter(Context mContext, ArrayList<Movie> movieList) {
        this.mContext = mContext;
        this.ticketList = ticketList;
    }

    @Override
    public MyTicketsAdapter.TicketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG,"onCreateViewHolder() called.");
        View v = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_row, parent, false);
        return new MyTicketsAdapter.TicketViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyTicketsAdapter.TicketViewHolder holder, int position) {
        Log.i(TAG,"onBindViewHolder() called.");

        Ticket ticket = ticketList.get(position);


        // Onderstaande aanpassen aan Movie class
        holder.ticketMovieTitle.setText("PlaceholderTitle");    // placeholder
        holder.ticketDate.setText("PlaceholderDate");    // placeholder
        holder.ticketTime.setText("PlaceholderTime");    // placeholder

        String imageUrl = "https://d3fa68hw0m2vcc.cloudfront.net/e3a/91155935.jpeg";    // placeholder
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.QRCode);

    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder {

        private ImageView QRCode;
        private TextView ticketMovieTitle;
        private TextView ticketDate;
        private TextView ticketTime;


        private TicketViewHolder(View itemView) {
            super(itemView);
            QRCode = itemView.findViewById(R.id.myTicketQRCode);
            ticketMovieTitle = itemView.findViewById(R.id.myTicketTitle);
            ticketDate = itemView.findViewById(R.id.myTicketDate);
            ticketTime = itemView.findViewById(R.id.myTicketTime);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
