package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.domain_layer.Ticket;
import com.avans2018.klasd.cineapp.domain_layer.TicketPrint;
import com.avans2018.klasd.cineapp.util_layer.StringLimiter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.squareup.picasso.Picasso;

public class MyTicketsDetailActivity extends AppCompatActivity {
    private final static String TAG = "MyTicketsDetailActivity";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets_detail);

        // Hoofdtitel veranderen
        getSupportActionBar().setTitle(StringLimiter.limit(getResources().getString(R.string.my_ticket_detail_title), 25));

        // Intent met data van film vanuit MyTicketsActivity
        Intent intent = getIntent();
        TicketPrint clickedTicket = (TicketPrint) intent.getSerializableExtra(MyTicketsActivity.CLICKED_TICKET);

        ImageView QRCode = (ImageView) findViewById(R.id.ticketQRCodeBig);

        String QRUrl = clickedTicket.getId() + "";    // placeholder voor QR-code
        Picasso.with(mContext).load(QRUrl).fit().centerInside().into(QRCode);

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
        movieDate.setText("Date: " + clickedTicket.getDate());
        TextView movieTime = (TextView) findViewById(R.id.ticketDetailTime);
        movieTime.setText("Time: " + clickedTicket.getTime());

        TextView movieTheater = (TextView) findViewById(R.id.ticketDetailTheater);
        movieTheater.setText("Theater: " + clickedTicket.getTheater());
        TextView movieSeat = (TextView) findViewById(R.id.ticketDetailSeat);
        movieSeat.setText("Seat: " + clickedTicket.getSeat());
        TextView movieType = (TextView) findViewById(R.id.ticketDetailType);
        movieType.setText("Type: " + clickedTicket.getPaymentCategory());

    }
}
