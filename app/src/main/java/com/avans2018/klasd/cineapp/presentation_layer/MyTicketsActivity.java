package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.application_logic_layer.MyTicketsListAdapter;
import com.avans2018.klasd.cineapp.application_logic_layer.OnItemClickListener;
import com.avans2018.klasd.cineapp.data_access_layer.TicketStorageDB;
import com.avans2018.klasd.cineapp.domain_layer.TicketPrint;
import com.avans2018.klasd.cineapp.util_layer.StringLimiter;

import java.util.ArrayList;

public class MyTicketsActivity extends AppCompatActivity implements OnItemClickListener {
    private final static String TAG = "MyTicketActivity";
    final static String CLICKED_TICKET = "clickedTicket";

    private RecyclerView recyclerView;
    private ArrayList<TicketPrint> ticketPrintList = new ArrayList<>();
    private MyTicketsListAdapter adapter = new MyTicketsListAdapter(MyTicketsActivity.this, ticketPrintList);
    private TicketStorageDB ticketStorage = new TicketStorageDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets);

        // Hoofdtitel veranderen
        getSupportActionBar().setTitle(StringLimiter.limit(getResources().getString(R.string.my_tickets_title), 25));

        recyclerView = (RecyclerView) findViewById(R.id.myTicketsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyTicketsActivity.this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(MyTicketsActivity.this);

        // dummy data
//        ticketPrintList.add(new TicketPrint(1,"22-05-2019","14:40","Theater 1","24A","Pulp Fiction","Student"));
//        ticketPrintList.add(new TicketPrint(2,"22-05-2019","14:40","Theater 1","24B","Pulp Fiction","Child"));
//        ticketPrintList.add(new TicketPrint(3,"22-05-2019","14:40","Theater 1","24C","Pulp Fiction","Child"));
//        ticketPrintList.add(new TicketPrint(4,"22-05-2019","14:40","Theater 1","24D","Pulp Fiction","Child"));
//        ticketPrintList.add(new TicketPrint(5,"22-05-2019","14:40","Theater 1","24E","Pulp Fiction","Child"));
//        ticketPrintList.add(new TicketPrint(6,"22-05-2019","14:40","Theater 1","24F","Pulp Fiction","Child"));

        ticketPrintList.addAll(ticketStorage.getAllTicketPrints());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        // Klik logica voor meegeven film en opstarten DetailActivity
        Log.i(TAG, "onItemClick() called.");
        Intent detailIntent = new Intent(this, MyTicketsDetailActivity.class);
        TicketPrint clickedTicket = ticketPrintList.get(position);
        detailIntent.putExtra(CLICKED_TICKET, clickedTicket);
        startActivity(detailIntent);
        Log.i(TAG, "Starting MyTicketsDetailActivity...");
    }
}
