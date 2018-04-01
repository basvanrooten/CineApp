package com.avans2018.klasd.cineapp.application_logic_layer;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avans2018.klasd.cineapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Tom on 1-4-2018.
 */

public class DatePagerAdapter extends PagerAdapter{
    private ArrayList<Date> dateList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public DatePagerAdapter(Context context, ArrayList<Date> dateList){
        this.context = context;
        this.dateList = dateList;
    }

    @Override
    public int getCount() {
        return dateList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.date_pager,container,false);
        TextView textView = (TextView) itemView.findViewById(R.id.datePagerContentDate);

        Date date = dateList.get(position);
        DateFormat df = new SimpleDateFormat("EEEE d MMMM");
        String dateAsString = df.format(date);

        String textToAdd = dateAsString;
        textView.setText(textToAdd);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

}
