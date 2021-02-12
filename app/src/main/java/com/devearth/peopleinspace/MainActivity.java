package com.devearth.peopleinspace;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView number,peopetext;


    LottieAnimationView lottir,space;
    Thread splashTread;
    Animation animation,animation1,bounce;




    ImageButton imageButton;



    String HTTP_JSON_URL = "https://www.howmanypeopleareinspacerightnow.com/peopleinspace.json";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

number= findViewById(R.id.number);

lottir=findViewById(R.id.as);
space=findViewById(R.id.space);
        peopetext=findViewById(R.id.peopetext);

imageButton = findViewById(R.id.imageButton);


        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bottom_to_original);
        animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.top_to_bottom);
        bounce = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce);


//        AnimationSet s = new AnimationSet(false);//false means don't share interpolators
//        s.addAnimation(animation);
//        s.addAnimation(bounce);
//        imageButton.startAnimation(s);
//




        number.setAnimation(animation);
        peopetext.setAnimation(animation);



        lottir.setAnimation(animation1);










        splashTread = new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(2500);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {



                                imageButton.startAnimation(bounce);

                            }
                        });

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            };
        };
        splashTread.start();


//
//        splashTread = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    int waited = 0;
//                    // Splash screen pause time
//                    while (waited < 2500) {
//                        sleep(100);
//                        waited += 100;
//                    }
//
//
//
//
//                } catch (InterruptedException e) {
//                    // do nothing
//                } finally {
//
//
//
//
//                    imageButton.setAnimation(bounce);
//                    imageButton.startAnimation(bounce);
//
//
//
//
//                }
//
//            }
//        };
//        splashTread.start();









        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




//                Animation fadeOut = new AlphaAnimation(1, 0);
//                fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
//                fadeOut.setStartOffset(1000);
//                fadeOut.setDuration(1000);
//                fadeOut.setRepeatMode(Animation.INFINITE);
//
//                AnimationSet animationfade = new AnimationSet(false); //change to false
//                animationfade.addAnimation(fadeOut);
//
//                lottir.setAnimation(fadeOut);
//                number.setAnimation(fadeOut);
//                imageButton.setAnimation(fadeOut);

//                lottir.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout));
//                number.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout));
//                imageButton.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout));
//

                lottir.animate()
                        .alpha(0f)
                        .setDuration(1000)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                lottir.setVisibility(View.INVISIBLE);
                            }
                        });

                number.animate()
                        .alpha(0f)
                        .setDuration(1000)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                number.setVisibility(View.INVISIBLE);
                            }
                        });
                peopetext.animate()
                        .alpha(0f)
                        .setDuration(1000)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                peopetext.setVisibility(View.INVISIBLE);
                            }
                        });

                imageButton.animate()
                        .alpha(0f)
                        .setDuration(1000)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(final Animator animation) {
                                imageButton.setVisibility(View.INVISIBLE);

                                space.setProgress(0);
                                space.setVisibility(View.VISIBLE);
                                space.playAnimation();




                                splashTread = new Thread() {
                                    @Override
                                    public void run() {
                                        try {
                                            int waited = 0;
                                            // Splash screen pause time
                                            while (waited < 2700) {
                                                sleep(100);
                                                waited += 100;
                                            }


                                        } catch (InterruptedException e) {
                                            // do nothing
                                        } finally {



                                            Intent intent = new Intent(MainActivity.this,ActroActivity.class);
                                            startActivity(intent);
                                        }

                                    }
                                };
                                splashTread.start();






                            }
                        });


            }
        });



loadHeroList();










//number.setText((int) space.getProgress());











    }







    private void loadHeroList() {
        //getting the progressbar


        //making the progressbar visible


        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, HTTP_JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion



                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            Log.v("astro :", obj.toString());
                            //we have the array named hero inside the object
                            //so here we are getting that json array


                            number.setText(obj.getString("number"));


                            JSONArray heroArray = obj.getJSONArray("people");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject heroObject = heroArray.getJSONObject(i);






                            }

//                            //creating custom adapter object
//                            AdapterCategory adapter = new AdapterCategory(getApplicationContext(), (ArrayList<ListeleModel>) heroList);
//
//
//                            recyclerView.setAdapter(adapter);
//                            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//                            recyclerView.setLayoutManager(mLayoutManager);
                            // recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

                            //adding the adapter to listview


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
    protected void onPostResume() {
        super.onPostResume();





        number.setAnimation(animation);


        lottir.setAnimation(animation1);

        imageButton.setAnimation(animation);





    }

    @Override
    protected void onPause() {
        super.onPause();

        space.setProgress(0);

        space.setVisibility(View.GONE);

        lottir.setVisibility(View.VISIBLE);
        imageButton.setVisibility(View.VISIBLE);
        number.setVisibility(View.VISIBLE);



    }
}
