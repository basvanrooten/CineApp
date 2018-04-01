package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.avans2018.klasd.cineapp.R;
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

// Activity voor detailscherm bij onItemClick in MainActivity
public class DetailActivity extends AppCompatActivity implements OnItemClickListener, GetMovieSchedulesListener {
    private final static String TAG = "DetailActivity";
    private Context mContext;
    private ArrayList<MovieSchedule> scheduleList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ScheduleListAdapter adapter = new ScheduleListAdapter(DetailActivity.this, scheduleList);
    final static String CLICKED_SCHEDULE = "clickedSchedule";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate() called.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);



        // Intent met data van film vanuit MainActivity
        Intent intent = getIntent();
        Movie clickedMovie = (Movie) intent.getSerializableExtra(MainActivity.CLICKED_MOVIE);
        Date currentDate = new Date();
        GetMovieSchedulesTask getMovieSchedulesTask = new GetMovieSchedulesTask(this, clickedMovie, currentDate);
        getMovieSchedulesTask.execute();

        // Hoofdtitel veranderen
        getSupportActionBar().setTitle(clickedMovie.getName());

        ImageView detailImageView = (ImageView) findViewById(R.id.detailFilmImg);
        TextView detailActivityIMDB = (TextView) findViewById(R.id.detailFilmIMDB);
        detailActivityIMDB.setText("   " + clickedMovie.getRating() + "/10");
        TextView detailActivityPlaytime = (TextView) findViewById(R.id.detailFilmPlaytime);
        detailActivityPlaytime.setText("       " + clickedMovie.getDuration() + "m");
        TextView detailActivityFilmAge = (TextView) findViewById(R.id.detailFilmAge);
        if(clickedMovie.isAdultOnly()){
            detailActivityFilmAge.setText("18+");
        } else {
            detailActivityFilmAge.setText(R.string.detail_all_ages);
        }
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
    }

    @Override
    public void onItemClick(int position) {
        // Klik logica voor meegeven film en opstarten DetailActivity
        Log.i(TAG, "onItemClick() called.");
        Intent ticketSelectionIntent = new Intent(this, TicketSelectionActivity.class);
        MovieSchedule schedule = scheduleList.get(position);
        ticketSelectionIntent.putExtra(CLICKED_SCHEDULE, schedule);
        startActivity(ticketSelectionIntent);
        Log.i(TAG, "Starting TicketSelectionActivity...");
    }

    @Override
    public void onMovieSchedulesRecieved(ArrayList<MovieSchedule> movieSchedules) {
        for (int i = 0; i < movieSchedules.size(); i++) {
            scheduleList.add(movieSchedules.get(i));
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
