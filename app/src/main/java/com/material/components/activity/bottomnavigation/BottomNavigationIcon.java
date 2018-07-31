package com.material.components.activity.bottomnavigation;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.flexbox.FlexboxLayout;
import com.material.components.R;
import com.material.components.activity.FullScreenMediaController;
import com.material.components.activity.stepper.StepperWizardLight;
import com.material.components.activity.verification.VerificationPhone;
import com.material.components.model.Event;
import com.material.components.utils.Tools;
import com.material.components.utils.ViewAnimation;
import com.material.trending.Trending;
import com.material.trending.TrendingMasterObject;
import com.material.utility.JsonMessageSender;
import com.material.utility.JsonObjectConversion;
import com.material.utility.SetConnection;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import static android.provider.DocumentsContract.getDocumentId;
import static android.provider.DocumentsContract.isDocumentUri;

public class BottomNavigationIcon extends AppCompatActivity {

    private ProgressBar progress_bar;
    private LinearLayout lyt_no_connection;
    private AppCompatButton bt_retry;


    private static final int MAX_STEP = 4;
    LinearLayout stepper;

    //    RelativeLayout stepper_linear_layour[];
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private TextView btnNext;
    private String about_title_array[] = {
            "Welcome To Stats",
            "Cast Votes",
            "Make Nominations",
            "Get Stats"
    };
    private String about_description_array[] = {
            "swipe right",
            "left | swipe | right",
            "left | swipe | right",
            "done",
    };
    private int about_images_array[] = {
            R.drawable.swipe_right,
            R.drawable.swipe,
            R.drawable.swipe,
            R.drawable.swipe_done
    };

    private LinearLayout stepper_linear_layour[] = {
            stepper

    };
    static int count;

    private boolean noConnection;
    private TabLayout tab_layout;
    private ActionBar actionBar;
    private NestedScrollView nested_scroll_view;
    private CardView trendingCardView;
    private LinearLayout outter1;
    private CoordinatorLayout profile;
    private CardView cardView1;
    private CircularImageView circularImageView1;
    private View view;
    private MediaController mediaControls;
    private int position = 0;
    private ProgressDialog progressDialog;
    private ImageButton ImageButtonSRC;
    SetConnection setConnection = new SetConnection();
    JsonMessageSender jsonMessageSender = new JsonMessageSender();
    LayoutInflater inflater;
    LayoutInflater inflater1;
    RequestQueue queue1;
    RequestQueue queue2;
    RequestQueue queue3;
    RequestQueue queue4;

    private MediaController mediaController;


    private Button btnonce, btncontinuously, btnstop, btnplay;
    private VideoView vv;
    private MediaController mediacontroller;
    private Uri uri;
    private boolean isContinuously = false;
    private ProgressBar progressBar;
    private LinearLayout createVote;
    private LinearLayout noInternet;
    private CoordinatorLayout coordinatorLayout;
    private VideoView placeHolderImage;
    // MediaController mediaController;
    FlexboxLayout flexboxLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOnCreateVariables();
        initToolbar();
        initComponent();
        trendingCardView();
    }

    private void setOnCreateVariables() {
        setContentView(R.layout.activity_setting_profile);
        coordinatorLayout = ((CoordinatorLayout) findViewById(R.id.create_vote_attribute));
        placeHolderImage = ((VideoView) findViewById(R.id.create_vote_image_place_holder));

        listerners();

        /////////////////////////////////
        setContentView(R.layout.select_stats_to_pull);
        stepper = findViewById(R.id.select_stats_to_pull);

        //////////////////////////
        setContentView(R.layout.item_stepper_wizard);
        btnNext = (TextView) findViewById(R.id.btn_next);
        flexboxLayout = ((FlexboxLayout) findViewById(R.id.flex_box));

        //////////////////////////
        setContentView(R.layout.activity_bottom_navigation_icon);

        outter1 = findViewById(R.id.outter1);
        profile = findViewById(R.id.profile);
        createVote = findViewById(R.id.create_vote);
        noInternet = findViewById(R.id.no_internet);
        progressDialog = new ProgressDialog(this);

        //////////////////////////////
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
        lyt_no_connection = (LinearLayout) findViewById(R.id.lyt_no_connection);
        bt_retry = (AppCompatButton) findViewById(R.id.bt_retry);

        progress_bar.setVisibility(View.GONE);
        lyt_no_connection.setVisibility(View.VISIBLE);
        ////////////////////////


        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    private void listerners() {
        ((AppCompatButton) findViewById(R.id.custom_dialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });

        ((Button) findViewById(R.id.spn_from_date)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDatePickerLight((Button) view);
            }
        });

        ((Button) findViewById(R.id.spn_to_date)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDatePickerLight((Button) view);
            }
        });

        ((Button) findViewById(R.id.spn_from_time)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogTimePickerLight((Button) view);
            }
        });

        ((Button) findViewById(R.id.spn_to_time)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogTimePickerLight((Button) view);
            }
        });


        ((FloatingActionButton) findViewById(R.id.fab1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/* video/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });

        ((FloatingActionButton) findViewById(R.id.fab)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                Intent chooserIntent = Intent.createChooser(takePictureIntent, "Capture Image or Video");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{takeVideoIntent});
                startActivityForResult(chooserIntent, 1);
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        profile.setVisibility(View.INVISIBLE);
        createVote.setVisibility(View.INVISIBLE);
        noInternet.setVisibility(View.INVISIBLE);

        actionBar.setTitle("Trending");
        actionBar.setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_20);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem() + 1;
                if (current < MAX_STEP) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
//                    finish();
                    startActivity(new Intent(getApplicationContext(), BottomNavigationIcon.class));


                }
            }
        });

        ((ImageButton) findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BottomNavigationIcon.class));
            }
        });
    }

    private void initComponent() {


        viewPager = (ViewPager) findViewById(R.id.view_pager);
        //  btnNext = (Button) findViewById(R.id.btn_next);

        // adding bottom dots
        // bottomProgressDots(0);

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem() + 1;
                if (current < MAX_STEP) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
//                    finish();
                    startActivity(new Intent(getApplicationContext(), BottomNavigationIcon.class));


                }
            }
        });

//        ((ImageButton)findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), BottomNavigationIcon.class));
//            }
//        });

        nested_scroll_view = (NestedScrollView) findViewById(R.id.nested_scroll_view);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);


        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.trending1), 0);
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_search), 1);
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_add_box), 2);
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.trending), 3);
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_person), 4);

        // set icon color pre-selected
        tab_layout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.deep_orange_500), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(1).getIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(3).getIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(4).getIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_IN);

        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.deep_orange_500), PorterDuff.Mode.SRC_IN);

                if (!noConnection) {
                    noInternet.setVisibility(View.VISIBLE);
                }

                switch (tab.getPosition()) {
                    case 0:
                        actionBar.setTitle("Trending");
                        outter1.setVisibility(View.VISIBLE);
                        profile.setVisibility(View.INVISIBLE);
                        createVote.setVisibility(View.INVISIBLE);
                        // noInternet.setVisibility(View.INVISIBLE);

                        break;
                    case 1:
                        actionBar.setTitle("Explore");
                        outter1.setVisibility(View.GONE);
                        profile.setVisibility(View.INVISIBLE);
                        createVote.setVisibility(View.INVISIBLE);
                        noInternet.setVisibility(View.INVISIBLE);

                        break;
                    case 2:
                        actionBar.setTitle("Post Vote");
                        outter1.setVisibility(View.GONE);
                        profile.setVisibility(View.INVISIBLE);
                        createVote.setVisibility(View.VISIBLE);
                        noInternet.setVisibility(View.INVISIBLE);

                        break;
                    case 3:
                        actionBar.setTitle("History");
                        outter1.setVisibility(View.GONE);
                        profile.setVisibility(View.INVISIBLE);
                        createVote.setVisibility(View.INVISIBLE);
                        noInternet.setVisibility(View.INVISIBLE);

                        break;
                    case 4:
                        actionBar.setTitle("Profile");
                        outter1.setVisibility(View.INVISIBLE);
                        profile.setVisibility(View.VISIBLE);
                        createVote.setVisibility(View.INVISIBLE);
                        noInternet.setVisibility(View.INVISIBLE);

                        break;
                }

                ViewAnimation.fadeOutIn(nested_scroll_view);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // display image
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_1), R.drawable.image_8);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_2), R.drawable.image_9);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_3), R.drawable.image_15);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_4), R.drawable.image_14);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_5), R.drawable.image_12);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_6), R.drawable.image_2);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_7), R.drawable.image_5);

        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);


        bt_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                progress_bar.setVisibility(View.VISIBLE);
                lyt_no_connection.setVisibility(View.GONE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress_bar.setVisibility(View.GONE);
                        lyt_no_connection.setVisibility(View.VISIBLE);
                        trendingCardView();
                    }
                }, 1000);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.grey_60));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private void trendingCardView() {
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater1 = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        fetchResults();


    }

    public void fetchResults() {
        // Create a Request to get information from the provided URL.
        //home 192.168.88.223 , work 192.168.1.40
        String requestUrl = "http://192.168.1.40:8090/trending";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                requestUrl,
                listener,
                errorListener);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue1 = Volley.newRequestQueue(this);
        queue2 = Volley.newRequestQueue(this);
        queue.add(request);
    }


    Response.Listener<String> listener = new Response.Listener<String>() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onResponse(String response) {
            //

            JsonObjectConversion jsonConversion = new JsonObjectConversion();
            TrendingMasterObject trendingMasterObject = (TrendingMasterObject) jsonConversion.jsonToObject(response, TrendingMasterObject.class);
            List<Trending> trendingList = trendingMasterObject.getTrendingList();
            int a = 0;
            for (final Trending trending : trendingList) {

                CardView cardView = (CardView) inflater.inflate(R.layout.card_view, null);
                final LinearLayout layout1 = (LinearLayout) cardView.getChildAt(0);
                LinearLayout layout2 = (LinearLayout) layout1.getChildAt(0);

                final CircularImageView circularImageView = (CircularImageView) layout2.getChildAt(0);

                try {

// Retrieves an image specified by the URL, displays it in the UI.
                    ImageRequest threadProfilePic = new ImageRequest(trending.getProfilePic(),
                            new Response.Listener<Bitmap>() {
                                @Override
                                public void onResponse(Bitmap bitmap) {
                                    circularImageView.setImageBitmap(bitmap);
                                }
                            }, 0, 0, null,
                            new Response.ErrorListener() {
                                public void onErrorResponse(VolleyError error) {
                                    circularImageView.setImageResource(R.drawable.cast3);
                                }
                            });
// Access the RequestQueue through your singleton class.
                    queue1.add(threadProfilePic);
                } catch (Exception e) {
                    circularImageView.setImageResource(R.drawable.cast3);
                }


                LinearLayout layout3 = (LinearLayout) layout2.getChildAt(2);

                TextView textView1 = (TextView) layout3.getChildAt(0);
                textView1.setText(trending.getTitle());

                LinearLayout layout4 = (LinearLayout) layout3.getChildAt(1);
                TextView textView2 = (TextView) layout4.getChildAt(0);
                textView2.setText(R.string.owner_title);

                TextView textView3 = (TextView) layout4.getChildAt(1);
                textView3.setText("  " + trending.getOwner().toLowerCase());

                TextView textView4 = (TextView) layout1.getChildAt(1);
                textView4.setText(trending.getDescription());
                final VideoView videoView = (VideoView) layout1.getChildAt(2);


                if (trending.getDescriptionType() == 1) {

                    try {

                        ImageRequest threadMainPic = new ImageRequest(trending.getMainDisplay(),
                                new Response.Listener<Bitmap>() {
                                    @Override
                                    public void onResponse(Bitmap bitmap) {
//                                        image1.setImageBitmap(bitmap);
                                        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                                        int sdk = android.os.Build.VERSION.SDK_INT;
                                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                                            videoView.setBackgroundDrawable(drawable);
                                        } else {
                                            videoView.setBackground(drawable);
                                        }
                                    }
                                }, 0, 0, null,
                                new Response.ErrorListener() {
                                    public void onErrorResponse(VolleyError error) {
                                        //@TODO load error message or image
                                        //   image1.setImageResource(R.drawable.cast3);
                                    }
                                });
                        queue1.add(threadMainPic);

                    } catch (Exception e) {
                        //  image1.setImageResource(R.drawable.cast3);
                    }
                }


                if (trending.getDescriptionType() == 2) {

                    Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {

                            try {


                                String fullScreen = getIntent().getStringExtra("fullScreenInd");
                                if ("y".equals(fullScreen)) {
                                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
                                    getSupportActionBar().hide();
                                }

                                Uri videoUri = Uri.parse(trending.getMainDisplay());

                                videoView.setVideoURI(videoUri);

                                mediaController = new FullScreenMediaController(BottomNavigationIcon.this);
                                // mediaController.show();
                                mediaController.setAnchorView(videoView);
                                videoView.setMediaController(mediaController);


                                videoView.requestFocus();
                                //we also set an setOnPreparedListener in order to know when the video file is ready for playback
                                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                                    public void onPrepared(MediaPlayer mediaPlayer) {
                                        // close the progress bar and play the video
                                        progressDialog.dismiss();
                                        //if we have a position on savedInstanceState, the video playback should start from here
                                        videoView.seekTo(position);
                                        if (position == 0) {
                                        } else {
                                            //if we come from a resumed activity, video playback will be paused
                                            videoView.pause();
                                        }
                                    }
                                });


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });


                    thread.start();
                    noInternet.setVisibility(View.INVISIBLE);
                    outter1.setVisibility(View.VISIBLE);
                    noConnection = true;
                }


                LinearLayout.LayoutParams btwnViewConfig = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 30);
                LinearLayout.LayoutParams endViewConfig = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150);

                LinearLayout layout5 = (LinearLayout) layout1.getChildAt(4);
                View endView = (View) layout1.getChildAt(5);
                if (trendingList.size() - 1 == a) {
                    endView.setLayoutParams(endViewConfig);

                } else {
                    endView.setLayoutParams(btwnViewConfig);
                }

                LinearLayout layout6 = (LinearLayout) layout5.getChildAt(0);


                outter1.addView(cardView);

                View view = (View) inflater1.inflate(R.layout.view_spacer, null);
                outter1.addView(view);

                a++;
            }
        }
    };

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if (error.networkResponse != null) {
                System.out.print("Error Response code: " + error.networkResponse.statusCode);
            }
            if (error.networkResponse == null) {
                outter1.setVisibility(View.INVISIBLE);
                profile.setVisibility(View.INVISIBLE);
                createVote.setVisibility(View.INVISIBLE);
                noInternet.setVisibility(View.VISIBLE);


            }

        }
    };

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //we use onSaveInstanceState in order to store the video playback position for orientation change
//        savedInstanceState.putInt("Position", videoView.getCurrentPosition());
        //     videoView.pause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        //   super.onRestoreInstanceState(savedInstanceState);
        //we use onRestoreInstanceState in order to play the video playback from the stored position
        //  position = savedInstanceState.getInt("Position");
        //  videoView.seekTo(position);
    }


//    private void bottomProgressDots(int current_index) {
//        LinearLayout dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
//        ImageView[] dots = new ImageView[MAX_STEP];
//
//        dotsLayout.removeAllViews();
//        for (int i = 0; i < dots.length; i++) {
//            dots[i] = new ImageView(this);
//            int width_height = 15;
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
//            params.setMargins(10, 10, 10, 10);
//            dots[i].setLayoutParams(params);
//            dots[i].setImageResource(R.drawable.shape_circle);
//            dots[i].setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
//            dotsLayout.addView(dots[i]);
//        }
//
//        if (dots.length > 0) {
//            dots[current_index].setImageResource(R.drawable.shape_circle);
//            dots[current_index].setColorFilter(getResources().getColor(R.color.orange_400), PorterDuff.Mode.SRC_IN);
//        }
//    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(final int position) {
            //   bottomProgressDots(position);

            if (position == about_title_array.length - 1) {
                btnNext.setText(getString(R.string.GOT_IT));
                btnNext.setBackgroundColor(getResources().getColor(R.color.orange_400));
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                btnNext.setLayoutParams(params);
                btnNext.setTextColor(Color.WHITE);

            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.item_stepper_wizard, container, false);
            //   ((Button) view.findViewById(R.id.pop)).setText(about_title_array[position]);
//            ((TextView) view.findViewById(R.id.title)).setText(about_title_array[position]);
            ((TextView) view.findViewById(R.id.btn_next)).setText(about_description_array[position]);
            ((ImageView) view.findViewById(R.id.swipe_image)).setImageResource(about_images_array[position]);
            if (position == 0) {
                RelativeLayout relativeLayout = ((RelativeLayout) view.findViewById(R.id.lyt_parent));
                relativeLayout.removeAllViews();

                try {
                    relativeLayout.addView(coordinatorLayout);
                } catch (Exception exp) {
                    coordinatorLayout.removeAllViews();
                    relativeLayout.addView(coordinatorLayout);

                }
                flexboxLayout.setVisibility(View.INVISIBLE);
                try {
                    queue3 = Volley.newRequestQueue(BottomNavigationIcon.this);

                    ImageRequest threadMainPic1 = new ImageRequest("https://cdn.macrumors.com/article-new/2012/06/iphotoaperture.jpg",
                            new Response.Listener<Bitmap>() {
                                @Override
                                public void onResponse(Bitmap bitmap) {
//                                        image1.setImageBitmap(bitmap);
                                    Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                                    int sdk = android.os.Build.VERSION.SDK_INT;
                                    if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                                        placeHolderImage.setBackgroundDrawable(drawable);
                                    } else {
                                        placeHolderImage.setBackground(drawable);
                                    }
                                }
                            }, 0, 0, null,
                            new Response.ErrorListener() {
                                public void onErrorResponse(VolleyError error) {
                                    //@TODO load error message or image
                                    //   image1.setImageResource(R.drawable.cast3);
                                }
                            });
                    queue3.add(threadMainPic1);

                } catch (Exception e) {
                    //  image1.setImageResource(R.drawable.cast3);
                }
            }
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return about_title_array.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    public void genreClick(View view) {
        if (view instanceof Button) {
            Button b = (Button) view;
            if (b.isSelected()) {
                b.setTextColor(getResources().getColor(R.color.orange_500));
            } else {
                b.setTextColor(Color.WHITE);
            }
            b.setSelected(!b.isSelected());
        }
    }

    ///////////////
    private void displayDataResult(Event event) {
        ((TextView) findViewById(R.id.create_vote_title)).setText(event.title);
        ((TextView) findViewById(R.id.create_vote_description)).setText(event.description);
        ((TextView) findViewById(R.id.tv_location)).setText(event.location);
//        ((TextView) findViewById(R.id.tv_from)).setText(event.from);
        //       ((TextView) findViewById(R.id.tv_to)).setText(event.to);
        //       ((TextView) findViewById(R.id.tv_allday)).setText(event.is_allday.toString());
        ((TextView) findViewById(R.id.tv_timezone)).setText(event.timezone);
    }


    private void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_event);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        final Button spn_from_date = (Button) dialog.findViewById(R.id.spn_from_date);
        final Button spn_from_time = (Button) dialog.findViewById(R.id.spn_from_time);
        final Button spn_to_date = (Button) dialog.findViewById(R.id.spn_to_date);
        final Button spn_to_time = (Button) dialog.findViewById(R.id.spn_to_time);
        final TextView title = (TextView) dialog.findViewById(R.id.title);
        final EditText description = (EditText) dialog.findViewById(R.id.description);
        final EditText et_location = (EditText) dialog.findViewById(R.id.et_location);
        final AppCompatCheckBox cb_allday = (AppCompatCheckBox) dialog.findViewById(R.id.cb_allday);
        final AppCompatSpinner spn_timezone = (AppCompatSpinner) dialog.findViewById(R.id.spn_timezone);

        String[] timezones = getResources().getStringArray(R.array.timezone);
        ArrayAdapter<String> array = new ArrayAdapter<>(this, R.layout.simple_spinner_item, timezones);
        array.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spn_timezone.setAdapter(array);
        spn_timezone.setSelection(0);

        ((ImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ((Button) dialog.findViewById(R.id.bt_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event event = new Event();
                event.title = title.getText().toString();
                event.description = description.getText().toString();
                event.location = et_location.getText().toString();
//                event.from = spn_from_date.getText().toString() + " (" + spn_from_time.getText().toString() + ")";
                //               event.to = spn_to_date.getText().toString() + " (" + spn_to_time.getText().toString() + ")";
                //               event.is_allday = cb_allday.isChecked();
                event.timezone = spn_timezone.getSelectedItem().toString();
                displayDataResult(event);

                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }


    private void dialogDatePickerLight(final Button bt) {
        Calendar cur_calender = Calendar.getInstance();
        DatePickerDialog datePicker = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        long date_ship_millis = calendar.getTimeInMillis();
                        if (bt.getId() == R.id.spn_from_date) {
                            ((TextView) findViewById(R.id.spn_from_date)).setText(Tools.getFormattedDateSimple(date_ship_millis));
                        } else if (bt.getId() == R.id.spn_to_date) {
                            ((TextView) findViewById(R.id.spn_to_date)).setText(Tools.getFormattedDateSimple(date_ship_millis));
                        }
                    }
                },
                cur_calender.get(Calendar.YEAR),
                cur_calender.get(Calendar.MONTH),
                cur_calender.get(Calendar.DAY_OF_MONTH)
        );
        //set dark light
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.colorPrimary));
        datePicker.setMinDate(cur_calender);
        datePicker.show(getFragmentManager(), "Datepickerdialog");
    }

    private void dialogTimePickerLight(final Button bt) {
        Calendar cur_calender = Calendar.getInstance();
        TimePickerDialog datePicker = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                if (bt.getId() == R.id.spn_from_time) {
                    ((TextView) findViewById(R.id.spn_from_time)).setText(hourOfDay + " : " + minute);
                } else if (bt.getId() == R.id.spn_to_time) {
                    ((TextView) findViewById(R.id.spn_to_time)).setText(hourOfDay + " : " + minute);
                }
            }
        }, cur_calender.get(Calendar.HOUR_OF_DAY), cur_calender.get(Calendar.MINUTE), true);
        //set dark light
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.colorPrimary));
        datePicker.show(getFragmentManager(), "Timepickerdialog");
    }


    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();
                String filePath = null;
                try {
                    filePath = getPath(selectedImage);

                } catch (Exception ex) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    selectedImage = getImageUri(getApplicationContext(), imageBitmap);
                    filePath = getPath(selectedImage);
                }
                String file_extn = filePath.substring(filePath.lastIndexOf(".") + 1);
                File imgFile = new File(filePath);
                if (imgFile.exists()) {

//                    placeHolderImage.setZOrderOnTop(false);
//                    placeHolderImage.setZOrderOnTop(true);
//                    placeHolderImage.setZOrderMediaOverlay(true);
                    //Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();

                    placeHolderImage.setZOrderOnTop(false);
                    placeHolderImage.setBackgroundColor(Color.TRANSPARENT);


                    if (file_extn.equals("img") || file_extn.equals("jpg") || file_extn.equals("jpeg") || file_extn.equals("gif") || file_extn.equals("png")) {
                        //FINE

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        //      resizeBitmap(myBitmap,1080,840);
                        Drawable d = new BitmapDrawable(getResources(), myBitmap);

                        int sdk = android.os.Build.VERSION.SDK_INT;
                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            placeHolderImage.setBackgroundDrawable(d);
                        } else {
                            placeHolderImage.setBackground(d);
//                            placeHolderImage.getLayoutParams().width=1080;
//                            placeHolderImage.getLayoutParams().height=840;
                        }


                    }
                    if (file_extn.equals("mp4") || file_extn.equals("3gp")) {
                        Uri videoUri = Uri.parse(imgFile.getAbsolutePath());

                        placeHolderImage.setVideoURI(videoUri);

                        mediaController = new FullScreenMediaController(BottomNavigationIcon.this);
                        // mediaController.show();
                        mediaController.setAnchorView(placeHolderImage);
                        placeHolderImage.setMediaController(mediaController);


                        placeHolderImage.requestFocus();
                        //we also set an setOnPreparedListener in order to know when the video file is ready for playback
                        placeHolderImage.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                            @SuppressLint("WrongConstant")
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                // close the progress bar and play the video
                                progressDialog.dismiss();
//                                placeHolderImage.getLayoutParams().width=3000;
//                                placeHolderImage.getLayoutParams().height=840;
                                //if we have a position on savedInstanceState, the video playback should start from here
                                placeHolderImage.seekTo(position);
                                if (position == 0) {
                                } else {
                                    //if we come from a resumed activity, video playback will be paused
                                    placeHolderImage.pause();
                                }
                            }
                        });
                    }


                }

            }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        String imagePath = cursor.getString(column_index);

        return cursor.getString(column_index);
    }


    private boolean hasCamera() {
        if (getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_FRONT)) {
            return true;
        } else {
            return false;
        }
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    private static Bitmap resizeBitmap(Bitmap image, int maxWidth, int maxHeight) {
        if (maxHeight > 0 && maxWidth > 0) {
            int width = image.getWidth();
            int height = image.getHeight();
            float ratioBitmap = (float) width / (float) height;
            float ratioMax = (float) maxWidth / (float) maxHeight;

            int finalWidth = maxWidth;
            int finalHeight = maxHeight;
            if (ratioMax > ratioBitmap) {
                finalWidth = (int) ((float) maxHeight * ratioBitmap);
            } else {
                finalHeight = (int) ((float) maxWidth / ratioBitmap);
            }
            image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
            return image;
        } else {
            return image;
        }
    }
}