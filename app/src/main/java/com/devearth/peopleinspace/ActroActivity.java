package com.devearth.peopleinspace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.load.Key;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.view.View.GONE;

public class ActroActivity extends AppCompatActivity {
    private static final String JSON_URL = "https://www.howmanypeopleareinspacerightnow.com/peopleinspace.json";

    JsonArrayRequest RequestOfJSonArray;
    //listview object
    ListView listView;

    //the hero list where we will store all the hero objects after parsing json
    List<TennisModel> heroList;

    List<String> name = new ArrayList();
    List<String> biophoto = new ArrayList();
    List<String> country = new ArrayList();
    List<String> countryflag = new ArrayList();
    List<String> launchdate = new ArrayList();
    List<String> careerdays = new ArrayList();
    List<String> title = new ArrayList();
    List<String> location = new ArrayList();
    List<String> bio = new ArrayList();
    List<String> biolink = new ArrayList();
    List<String> twitter = new ArrayList();
     View mViewBg;

    RequestQueue requestQueue;


    LottieAnimationView  lottieAnimationView;


    TextView asname ;
    TextView ascountry ;
    TextView aslocation;
    TextView asbio;
    TextView asdate ;
    TextView t_link ;
    ImageView asimage ;
    ImageView asflag ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actro);

        overridePendingTransition(R.anim.bottom_to_original_layout, R.anim.bottom_to_original_layout);


        listView = (ListView) findViewById(R.id.lv);



         asname =findViewById(R.id.asname);
         ascountry =findViewById(R.id.ascountry);
         aslocation =findViewById(R.id.aslocation);
         asdate =findViewById(R.id.asdate);
         asimage =findViewById(R.id.asimage);
         asflag =findViewById(R.id.asflag);
         asbio =findViewById(R.id.bio);
        t_link =findViewById(R.id.t_link);



lottieAnimationView = findViewById(R.id.moon);
      //  lottieAnimationView.bringToFront();


        asbio.setMovementMethod(new ScrollingMovementMethod());




        heroList = new ArrayList<>();

        loadHeroList();




    }





















    private void loadHeroList() {
        //getting the progressbar


        //making the progressbar visible


        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion



                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            Log.v("kisiler :", obj.toString());
                            //we have the array named hero inside the object
                            //so here we are getting that json array





                            JSONArray heroArray = obj.getJSONArray("people");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                //getting the json object of the particular index inside the array


                                JSONObject heroObject = heroArray.getJSONObject(i);






                                name.add(heroObject.getString("name"));
                                biophoto.add(heroObject.getString("biophoto"));
                                country.add(heroObject.getString("country"));
                                countryflag.add(heroObject.getString("countryflag"));
                                launchdate.add(heroObject.getString("launchdate"));
                                careerdays.add(heroObject.getString("careerdays"));
                                title.add(heroObject.getString("title"));
                                location.add(heroObject.getString("location"));
                                bio.add(heroObject.getString("bio"));
                                biolink.add(heroObject.getString("biolink"));
                                twitter.add(heroObject.getString("twitter"));

                                TennisModel hero = new TennisModel(heroObject.getString("name"), heroObject.getString("biophoto"), heroObject.getString("biophotowidth"), heroObject.getString("biophotoheight"), heroObject.getString("country"), heroObject.getString("countryflag"), heroObject.getString("launchdate"), heroObject.getString("careerdays"), heroObject.getString("title"), heroObject.getString("location"), heroObject.getString("bio"), heroObject.getString("biolink"), heroObject.getString("twitter"));



                                heroList.add(hero);

                            }

                            TennisAdapter adapter = new TennisAdapter(heroList, getApplicationContext());

                            //adding the adapter to listview
                            listView.setAdapter(adapter);



                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                    Log.v("isimler", (String) name.get(position));
                                   // mWebView.loadDataWithBaseURL(null, ((String) linkler.get(position)).toString(), "text/html", Key.STRING_CHARSET_NAME, null);
                                   // listView.setVisibility(GONE);



                                    LinearLayout linearLayout= findViewById(R.id.linerlll);

                                    final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);





                                    asname.setText(name.get(position));
                                    ascountry.setText(country.get(position));
                                    aslocation.setText(location.get(position));
                                    asdate.setText(launchdate.get(position));
                                    asbio.setText(bio.get(position));


                                    if (!twitter.get(position).equals(""))
                                    {

                                        String tlink =twitter.get(position);
                                        t_link.setText(tlink.substring(19));
                                    }
                                    else
                                    {


                                        t_link.setText("Couldn't Find Twitter");
                                    }









                                    Picasso.get().load(biophoto.get(position)).error(R.drawable.icon).placeholder(R.drawable.icon).into(asimage);
                                    Picasso.get().load(countryflag.get(position)).error(R.drawable.loding).placeholder(R.drawable.loding).into(asflag);



                                    mViewBg =findViewById(R.id.bg);
                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);


                                    bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                                        @Override
                                        public void onStateChanged(@NonNull View bottomSheet, int newState) {
                                            if (newState == BottomSheetBehavior.STATE_COLLAPSED)
                                                mViewBg.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                                            Log.d("durum", "onSlide: slideOffset" + slideOffset + "");
                                            mViewBg.setVisibility(View.VISIBLE);
                                            mViewBg.setAlpha(slideOffset);
                                        }
                                    });


                                    mViewBg.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                                        }
                                    });




                                }
                            });




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        // Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //adding the string request to request queue
        requestQueue.add(stringRequest);






    }



    @Override
    public void onBackPressed() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to exit ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
