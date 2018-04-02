package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.application_logic_layer.DatePagerAdapter;
import com.avans2018.klasd.cineapp.application_logic_layer.MovieListAdapter;
import com.avans2018.klasd.cineapp.application_logic_layer.OnItemClickListener;
import com.avans2018.klasd.cineapp.application_logic_layer.ScheduleListAdapter;
import com.avans2018.klasd.cineapp.data_access_layer.movieschedule.GetMovieSchedulesListener;
import com.avans2018.klasd.cineapp.data_access_layer.movieschedule.GetMovieSchedulesTask;
import com.avans2018.klasd.cineapp.domain_layer.Movie;
import com.avans2018.klasd.cineapp.domain_layer.MovieSchedule;
import com.squareup.picasso.Picasso;
import com.avans2018.klasd.cineapp.util_layer.StringLimiter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

// Activity voor detailscherm bij onItemClick in MainActivity
public class DetailActivity extends AppCompatActivity implements OnItemClickListener, GetMovieSchedulesListener {
    private final static String TAG = "DetailActivity";
    private Context mContext;
    private ArrayList<MovieSchedule> scheduleList = new ArrayList<>();
    private ArrayList<Date> dateList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ViewPager datePager;
    private ScheduleListAdapter adapter = new ScheduleListAdapter(DetailActivity.this, scheduleList);
    private DatePagerAdapter datePagerAdapter = new DatePagerAdapter(this,dateList);;
    final static String CLICKED_SCHEDULE = "clickedSchedule";
    private Toolbar toolbar;
    private Button dateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate() called.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Intent met data van film vanuit MainActivity
        Intent intent = getIntent();
        final Movie clickedMovie = (Movie) intent.getSerializableExtra(MainActivity.CLICKED_MOVIE);
        Date currentDate = new Date();
        GetMovieSchedulesTask getMovieSchedulesTask = new GetMovieSchedulesTask(this, clickedMovie, currentDate);
        getMovieSchedulesTask.execute();

        // Hoofdtitel veranderen
        getSupportActionBar().setTitle(clickedMovie.getName());

        ImageView detailImageView = (ImageView) findViewById(R.id.detailFilmImg);
        TextView detailActivityIMDB = (TextView) findViewById(R.id.detailFilmIMDB);
        detailActivityIMDB.setText("   " + clickedMovie.getRating() + "/10");
        TextView detailActivityPlaytime = (TextView) findViewById(R.id.detailFilmPlaytime);
        if(Locale.getDefault().getDisplayLanguage().equals("Nederlands")){
            detailActivityPlaytime.setText("  " + clickedMovie.getDuration() + "m");
        } else {
            detailActivityPlaytime.setText("       " + clickedMovie.getDuration() + "m");
        }

        TextView detailActivityFilmAge = (TextView) findViewById(R.id.detailFilmAge);
        if(clickedMovie.isAdultOnly()){
            detailActivityFilmAge.setText("18+");
        } else {
            detailActivityFilmAge.setText(R.string.detail_all_ages);
        }

        TextView genreHeader = (TextView) findViewById(R.id.detailFilmGenreHeader);

        TextView genreContent = (TextView) findViewById(R.id.detailFilmGenreContent);

        String genre = clickedMovie.getGenre();
        String genreWithSpaces = genre.replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2");
        genreContent.setText(genreWithSpaces);

        TextView detailCommentHeader = (TextView) findViewById(R.id.detailFilmDescriptionHeader);
        TextView detailCommentContent = (TextView) findViewById(R.id.detailFilmDescriptionContent);
        detailCommentContent.setText(clickedMovie.getInfo());

        TextView detailFilmTimesHeader = (TextView) findViewById(R.id.detailFilmTimesHeader);

        // RecyclerView voor het weergeven van lijst van films
        recyclerView = (RecyclerView) findViewById(R.id.scheduleRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(DetailActivity.this);
        adapter.notifyDataSetChanged();


        // Picasso voor invullen ImageView
        String imageUrl = clickedMovie.getImageUrl();
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(detailImageView);

        // ViewPager voor datumselectie
        for (int i=0; i<30; i++){
            Date date = new Date();
            date.setTime((System.currentTimeMillis()) + (86400000 * i));
            dateList.add(date);
        }
        dateButton = (Button) findViewById(R.id.date_selection_button);
        datePager = (ViewPager) findViewById(R.id.datePager);
        datePager.setAdapter(datePagerAdapter);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetMovieSchedulesTask getMovieSchedulesTaskSpecified = new GetMovieSchedulesTask(DetailActivity.this, clickedMovie, dateList.get(datePager.getCurrentItem()));
                getMovieSchedulesTaskSpecified.execute();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        // Klik logica voor meegeven film en opstarten DetailActivity

        Log.i(TAG, "onItemClick() called.");
        MovieSchedule schedule = scheduleList.get(position);

        if(schedule.getDate().getTime() >= System.currentTimeMillis()){
            Intent ticketSelectionIntent = new Intent(this, TicketSelectionActivity.class);
            ticketSelectionIntent.putExtra(CLICKED_SCHEDULE, schedule);
            startActivity(ticketSelectionIntent);
            Log.i(TAG, "Starting TicketSelectionActivity...");
        } else {
            Log.i(TAG,"Clicked MovieSchedule invalid.");
            Toast.makeText(this, R.string.unavailable, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onMovieSchedulesRecieved(ArrayList<MovieSchedule> movieSchedules) {
        scheduleList.clear();
        for (int i = 0; i < movieSchedules.size(); i++) {
            dateList.add(movieSchedules.get(i).getDate());
            scheduleList.add(movieSchedules.get(i));
        }
        datePagerAdapter.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
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
