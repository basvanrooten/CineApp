package com.avans2018.klasd.cineapp.application_logic_layer;

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
import com.avans2018.klasd.cineapp.domain_layer.TicketPrint;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyTicketsListAdapter extends RecyclerView.Adapter<MyTicketsListAdapter.TicketViewHolder> {

    private Context mContext;
    private ArrayList<TicketPrint> ticketList;
    private OnItemClickListener listener;
    private final static String TAG = "MyTicketsListAdapter";

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MyTicketsListAdapter(Context mContext, ArrayList<TicketPrint> ticketList) {
        this.mContext = mContext;
        this.ticketList = ticketList;
    }

    @Override
    public MyTicketsListAdapter.TicketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG,"onCreateViewHolder() called.");
        View v = LayoutInflater.from(mContext).inflate(R.layout.mytickets_row, parent, false);
        return new MyTicketsListAdapter.TicketViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyTicketsListAdapter.TicketViewHolder holder, int position) {
        Log.i(TAG,"onBindViewHolder() called.");

        TicketPrint ticket = ticketList.get(position);


        // Onderstaande aanpassen aan Ticket class
        holder.ticketMovieTitle.setText(ticket.getMovie());
        holder.ticketDate.setText(ticket.getDate());
        holder.ticketTime.setText(""+ ticket.getTime());


        String QRUrl = "https://cdn.crunchify.com/wp-content/uploads/2013/01/CrunchifyQR-Tutorial.png";    // placeholder voor QR-code
        Picasso.with(mContext).load(QRUrl).fit().centerInside().into(holder.QRCode);

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
