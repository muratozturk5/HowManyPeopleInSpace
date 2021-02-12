package com.devearth.peopleinspace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by Belal on 9/5/2017.
 */

public class TennisAdapter extends ArrayAdapter<TennisModel> {

    //the hero list that will be displayed
    private List<TennisModel> heroList;

    //the context object
    private Context mCtx;

    //here we are getting the herolist and context
    //so while creating the object of this adapter class we need to give herolist and context
    public TennisAdapter(List<TennisModel> heroList, Context mCtx) {
        super(mCtx, R.layout.lv_players, heroList);
        this.heroList = heroList;
        this.mCtx = mCtx;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.lv_players, null, true);





        //getting text views
        TextView name = listViewItem.findViewById(R.id.name);
        TextView task = listViewItem.findViewById(R.id.task);
        TextView day = listViewItem.findViewById(R.id.day);
        ImageView flag = listViewItem.findViewById(R.id.flag);
        ImageView imageView = listViewItem.findViewById(R.id.imageview);


        //Getting the hero for the specified position
        TennisModel hero = heroList.get(position);

        //setting hero values to textviews
        name.setText(hero.getName());
        task.setText(hero.getTitle());











        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String inputString1 = hero.getLaunchdate();
        String inputString2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        try {
            Date date1 = myFormat.parse(inputString1);
            Date date2 = myFormat.parse(inputString2);
            long diff = date2.getTime() - date1.getTime();
            System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));

            long daycount =TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);


            day.setText(String.valueOf(daycount));

        } catch (ParseException e) {
            e.printStackTrace();
        }

int weight = Integer.valueOf(hero.getBiophotowidth()) ;
int height =Integer.valueOf(hero.getBiophotoheight());

        Picasso
                .get()
                .load(hero.getBiophoto())
                .resize(weight, height)
                .noFade()
                .error(R.drawable.icon)
                .into(imageView);


        Picasso
                .get()
                .load(hero.getCountryflag())
                .noFade()
                .error(R.drawable.icon)
                .into(flag);

        //returning the listitem
        return listViewItem;
    }
}