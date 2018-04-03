package com.avans2018.klasd.cineapp.application_logic_layer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.domain_layer.seating.CenterSeat;
import com.avans2018.klasd.cineapp.domain_layer.seating.EdgeSeat;
import com.avans2018.klasd.cineapp.domain_layer.seating.SelectionSeat;

import java.util.List;

/**
 * Created by Tom on 31-3-2018.
 */

public class SeatingAdapter extends SelectableAdapter<RecyclerView.ViewHolder> {
    private OnSeatSelected mOnSeatSelected;

    private static class EdgeViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSeat;
        ImageView imgSeatBooked;
        private final ImageView imgSeatSelected;


        public EdgeViewHolder(View itemView) {
            super(itemView);
            imgSeat = (ImageView) itemView.findViewById(R.id.img_seat);
            imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);
            imgSeatBooked = (ImageView) itemView.findViewById(R.id.img_seat_booked);

        }

    }

    private static class CenterViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSeat;
        ImageView imgSeatBooked;
        private final ImageView imgSeatSelected;

        public CenterViewHolder(View itemView) {
            super(itemView);
            imgSeat = (ImageView) itemView.findViewById(R.id.img_seat);
            imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);
            imgSeatBooked = (ImageView) itemView.findViewById(R.id.img_seat_booked);

        }

    }

    private static class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }

    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<SelectionSeat> mItems;

    public SeatingAdapter(Context context, List<SelectionSeat> items) {
        mOnSeatSelected = (OnSeatSelected) context;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mItems = items;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SelectionSeat.TYPE_CENTER) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new CenterViewHolder(itemView);
        } else if (viewType == SelectionSeat.TYPE_EDGE) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new EdgeViewHolder(itemView);
        } else {
            View itemView = new View(mContext);
            return new EmptyViewHolder(itemView);
        }
    }

            @Override
            public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
                int type = mItems.get(position).getType();
                if (type == SelectionSeat.TYPE_CENTER) {
                    final CenterSeat item = (CenterSeat) mItems.get(position);
                    CenterViewHolder holder = (CenterViewHolder) viewHolder;
                    if(item.getSeat().isTaken()){
                        // Dit moet worden veranderd in een gereserveerde stoel
                        holder.imgSeatBooked.setVisibility(View.VISIBLE);
                        return;
                    }


            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    toggleSelection(position);

                    mOnSeatSelected.onSeatSelected(getSelectedItemCount());
                }
            });


            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);

        } else if (type == SelectionSeat.TYPE_EDGE) {
            final EdgeSeat item = (EdgeSeat) mItems.get(position);
            EdgeViewHolder holder = (EdgeViewHolder) viewHolder;
            if(item.getSeat().isTaken()){
                // Dit moet worden veranderd in een gereserveerde stoel
                holder.imgSeatBooked.setVisibility(View.VISIBLE);
                return;
            }


            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    toggleSelection(position);
                    mOnSeatSelected.onSeatSelected(getSelectedItemCount());


                }
            });

            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);

        }
    }

}
