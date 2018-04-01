package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.application_logic_layer.MyTicketsListAdapter;
import com.avans2018.klasd.cineapp.application_logic_layer.OnItemClickListener;
import com.avans2018.klasd.cineapp.data_access_layer.TicketStorageDB;
import com.avans2018.klasd.cineapp.domain_layer.Ticket;
import com.avans2018.klasd.cineapp.domain_layer.TicketPrint;
import com.avans2018.klasd.cineapp.util_layer.StringLimiter;

import java.util.ArrayList;

public class MyTicketsActivity extends AppCompatActivity implements OnItemClickListener {
    private final static String TAG = "MyTicketActivity";
    final static String CLICKED_TICKET = "clickedTicket";
    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private ArrayList<TicketPrint> ticketPrintList = new ArrayList<>();
    private MyTicketsListAdapter adapter = new MyTicketsListAdapter(MyTicketsActivity.this, ticketPrintList);
    private TicketStorageDB ticketStorage = new TicketStorageDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
//        ticketStorage = (TicketStorageDB) intent.getSerializableExtra(ConfirmationActivity.DATABASE);

        // Hoofdtitel veranderen
        getSupportActionBar().setTitle(R.string.my_tickets_title);

        recyclerView = (RecyclerView) findViewById(R.id.myTicketsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyTicketsActivity.this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(MyTicketsActivity.this);

        ticketPrintList.addAll(ticketStorage.getAllTicketPrints());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        // Klik logica voor meegeven film en opstarten MyTicketsDetailActivity
        Log.i(TAG, "onItemClick() called.");
        Intent detailIntent = new Intent(this, MyTicketsDetailActivity.class);
        TicketPrint clickedTicket = ticketPrintList.get(position);
        detailIntent.putExtra(CLICKED_TICKET, clickedTicket);
        startActivity(detailIntent);
        Log.i(TAG, "Starting MyTicketsDetailActivity...");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp(){
        startActivity(new Intent(MyTicketsActivity.this, MainActivity.class));
        finish();
        return true;
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(MyTicketsActivity.this, MainActivity.class));
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_my_tickets) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
