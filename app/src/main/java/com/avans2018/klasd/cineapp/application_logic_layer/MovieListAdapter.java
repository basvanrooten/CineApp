package com.avans2018.klasd.cineapp.application_logic_layer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.domain_layer.Movie;
import com.avans2018.klasd.cineapp.util_layer.StringLimiter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//Adapter voor RecyclerView in MainActivity
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private Context mContext;
    private ArrayList<Movie> movieList;
    private OnItemClickListener listener;
    private final static String TAG = "MovieAdapter";

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MovieListAdapter(Context mContext, ArrayList<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG,"onCreateViewHolder() called.");
        View v = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_row, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Log.i(TAG,"onBindViewHolder() called.");

        Movie movie = movieList.get(position);

        holder.movieTitle.setText(StringLimiter.limit(movie.getName(),35));
        holder.movieDescription.setText(movie.getInfo());
        
        String imageUrl = movie.getImageUrl();
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.movieImage);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView movieImage;
        private TextView movieTitle;
        private TextView movieDescription;

        private MovieViewHolder(View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.listFilmImg);
            movieTitle = itemView.findViewById(R.id.listFilmTitle);
            movieDescription = itemView.findViewById(R.id.ListFilmDescription);

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
