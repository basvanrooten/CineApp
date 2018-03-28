package com.avans2018.klasd.cineapp.data_access_layer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.avans2018.klasd.cineapp.domain.Ticket;
import com.avans2018.klasd.cineapp.domain.TicketPrint;

import java.util.ArrayList;

/**
 * Created by Tom on 28-3-2018.
 */

public class TicketStorageDB extends SQLiteOpenHelper{
    private final static String TAG = "TicketStorageDB";

    private final static String DATABASE_NAME = "CineApp.db";
    private final static String TABLE_NAME = "Ticket";
    private final static int DATABASE_VERSION = 1;

    private final static String ID = "id";
    private final static String DATE = "date";
    private final static String TIME = "time";
    private final static String THEATER = "theater";
    private final static String SEAT = "seat";
    private final static String MOVIE = "movie";
    private final static String PAYMENTCATEGORY = "paymentCategory";


    public TicketStorageDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "onCreate() aangeroepen.");
        sqLiteDatabase.execSQL(

                "CREATE TABLE " +               TABLE_NAME +
                        " (" +
                        ID +                " INTEGER PRIMARY KEY," +
                        DATE +              " TEXT NOT NULL," +
                        TIME +              " TEXT NOT NULL, "+
                        THEATER +           " TEXT NOT NULL," +
                        SEAT +              " TEXT NOT NULL," +
                        MOVIE +             " TEXT NOT NULL," +
                        PAYMENTCATEGORY +   " TEXT NOT NULL);"



        );
        Log.i(TAG,"Database " + DATABASE_NAME + " created with table " + TABLE_NAME + ".");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i(TAG,"onUpgrade() called.");
        String query = "DROP TABLE " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query,null);
        this.onCreate(sqLiteDatabase);
    }


    public void addTicket(Ticket ticket){
        Log.i(TAG,"addTicket() called for ticket ID " + ticket.getId() + ".");

        // Waardes toevoegen aan tabel
        ContentValues toBeAdded = new ContentValues();
        toBeAdded.put(ID, ticket.getId());
        toBeAdded.put(DATE, ticket.getDate());
        toBeAdded.put(TIME, ticket.getTime());
        toBeAdded.put(THEATER, ticket.getSeat().returnTheaterForDB());
        toBeAdded.put(SEAT, ticket.getSeat().returnSeatNumberForDB());
        toBeAdded.put(MOVIE, ticket.getMovie().getName());
        toBeAdded.put(PAYMENTCATEGORY, ticket.getPaymentCategory().getPaymentMethodString());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, toBeAdded);
        Log.i(TAG,"Ticket added to DB.");
        db.close();
    }

    public ArrayList<TicketPrint> getAllTickets(){
        Log.i(TAG,"getAllPhotos() called.");
        ArrayList<TicketPrint> tickets = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            String theater = cursor.getString(cursor.getColumnIndex(THEATER));
            String date = cursor.getString(cursor.getColumnIndex(DATE));
            String time = cursor.getString(cursor.getColumnIndex(TIME));
            String seat = cursor.getString(cursor.getColumnIndex(SEAT));
            String movie = cursor.getString(cursor.getColumnIndex(MOVIE));
            String paymentCategory = cursor.getString(cursor.getColumnIndex(PAYMENTCATEGORY));

            TicketPrint ticket = new TicketPrint(id,date,time,theater,seat,movie,paymentCategory);
            Log.i(TAG,"Ticket ID " + ticket.getId() + " found in local SQLite storage.");
            tickets.add(ticket);

            cursor.moveToNext();
        }
        Log.i(TAG,"Total tickets found: " + tickets.size());

        return tickets;
    }


    // Hier nog methodes toevoegen voor ticketstuff


}
