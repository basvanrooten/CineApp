package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.domain_layer.TicketPrint;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.squareup.picasso.Picasso;

public class MyTicketsDetailActivity extends AppCompatActivity {
    private final static String TAG = "MyTicketsDetailActivity";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"onCreate() called.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets_detail);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Hoofdtitel veranderen
        getSupportActionBar().setTitle(R.string.my_ticket_detail_title);

        // Intent met data van film vanuit MyTicketsActivity
        Intent intent = getIntent();
        TicketPrint clickedTicket = (TicketPrint) intent.getSerializableExtra(MyTicketsActivity.CLICKED_TICKET);

        ImageView QRCode = (ImageView) findViewById(R.id.ticketQRCodeBig);

//      String QRUrl = clickedTicket.getId() + "";    // placeholder voor QR-code
        String QRUrl = "https://cdn.crunchify.com/wp-content/uploads/2013/01/CrunchifyQR-Tutorial.png";    // placeholder voor QR-code
        Picasso.with(getApplicationContext()).load(QRUrl).fit().centerInside().into(QRCode);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(QRUrl, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            QRCode.setImageBitmap(bitmap);
        } catch (Exception e)   {
            e.printStackTrace();
        }

        TextView movieTitle = (TextView) findViewById(R.id.ticketDetailMovieTitle);
        movieTitle.setText(clickedTicket.getMovie());
        TextView movieDate = (TextView) findViewById(R.id.ticketDetailDate);
        String movieDateText = "" + this.getString(R.string.my_ticket_detail_date) + clickedTicket.getDate();
        movieDate.setText(movieDateText);
        TextView movieTime = (TextView) findViewById(R.id.ticketDetailTime);
        String movieTimeText = "" + this.getString(R.string.my_ticket_detail_time) + clickedTicket.getTime();
        movieTime.setText(movieTimeText);

        TextView movieTheater = (TextView) findViewById(R.id.ticketDetailTheater);
        movieTheater.setText(this.getString(R.string.my_ticket_detail_theater) + clickedTicket.getTheater());
        TextView movieSeat = (TextView) findViewById(R.id.ticketDetailSeat);
        movieSeat.setText(this.getString(R.string.my_ticket_detail_seat) + clickedTicket.getSeat());
        TextView movieType = (TextView) findViewById(R.id.ticketDetailType);

        String category = "";

            if(clickedTicket.getPaymentCategory().equals("Adult")){
                category = "" + this.getString(R.string.adult);
            } else if (clickedTicket.getPaymentCategory().equals("Child")){
                category = "" + this.getString(R.string.child);
            } else if (clickedTicket.getPaymentCategory().equals("Student")){
                category = "" + this.getString(R.string.student);
            } else if (clickedTicket.getPaymentCategory().equals("Senior")){
                category = "" + this.getString(R.string.senior);
            }

        movieType.setText(this.getString(R.string.my_ticket_detail_type) + category);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_my_tickets) {
            Intent intent = new Intent(this,MyTicketsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
