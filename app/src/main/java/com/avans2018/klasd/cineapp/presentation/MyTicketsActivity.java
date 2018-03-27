package com.avans2018.klasd.cineapp.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.application_logic.MovieListAdapter;
import com.avans2018.klasd.cineapp.application_logic.MyTicketsAdapter;
import com.avans2018.klasd.cineapp.application_logic.OnItemClickListener;
import com.avans2018.klasd.cineapp.domain.Movie;
import com.avans2018.klasd.cineapp.domain.Ticket;

import java.util.ArrayList;

public class MyTicketsActivity extends AppCompatActivity implements OnItemClickListener {
    private final static String TAG = "MyTicketActivity";
    private RecyclerView recyclerView;
    private ArrayList<Ticket> ticketList = new ArrayList<>();
    private MyTicketsAdapter adapter = new MyTicketsAdapter(MyTicketsActivity.this,ticketList);
    final static String CLICKED_TICKET = "clickedTicket";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyTicketsActivity.this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(MyTicketsActivity.this);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        // Klik logica voor meegeven film en opstarten DetailActivity
        Log.i(TAG, "onItemClick() called.");
        Intent detailIntent = new Intent(this, DetailActivity.class);
        Ticket clickedTicket = ticketList.get(position);
        detailIntent.putExtra(CLICKED_TICKET, clickedTicket);
        startActivity(detailIntent);
        Log.i(TAG, "Starting MyTicketsDetailActivity...");
    }
}
