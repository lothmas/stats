package com.material.components.activity.bottomnavigation;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.material.components.R;
import com.material.components.utils.Tools;
import com.material.components.utils.ViewAnimation;
import com.mikhaellopez.circularimageview.CircularImageView;

import static android.support.v7.widget.CardView.*;

public class BottomNavigationIcon extends AppCompatActivity {

    private TabLayout tab_layout;
    private ActionBar actionBar;
    private NestedScrollView nested_scroll_view;
    private CardView trendingCardView;
    private LinearLayout outter1;
    private LinearLayout outter2;
    private CardView cardView1;
    private CircularImageView circularImageView1;
    private View view;
    private MediaController mediaControls;
    private int position = 0;
    private ProgressDialog progressDialog;
    VideoView videoView;
    private ImageButton ImageButtonSRC;
    ;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_icon);

        initToolbar();
        initComponent();
        trendingCardView();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Trending");
        actionBar.setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_20);
    }

    private void initComponent() {
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
                switch (tab.getPosition()) {
                    case 0:
                        actionBar.setTitle("Trending");
                        break;
                    case 1:
                        actionBar.setTitle("Explore");
                        break;
                    case 2:
                        actionBar.setTitle("Post Vote");
                        break;
                    case 3:
                        actionBar.setTitle("History");
                        break;
                    case 4:
                        actionBar.setTitle("Profile");
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


    @TargetApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void trendingCardView() {

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater1 = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        outter1 = findViewById(R.id.outter1);
        LinearLayout.LayoutParams linearLayout16_LayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 10);
       int loopNumbers=5;
        for (int a = 0; a < loopNumbers; a++) {


            CardView cardView = (CardView) inflater.inflate(R.layout.card_view, null);
            LinearLayout layout1= (LinearLayout) cardView.getChildAt(0);
            LinearLayout layout2= (LinearLayout) layout1.getChildAt(0);

            CircularImageView circularImageView= (CircularImageView) layout2.getChildAt(0);
            Drawable profilePic = getResources().getDrawable(R.drawable.fifa);
            circularImageView.setImageDrawable(profilePic);

            LinearLayout layout3= (LinearLayout) layout2.getChildAt(2);

            TextView textView1= (TextView) layout3.getChildAt(0);
            textView1.setText("New Defination"+a);

            LinearLayout layout4= (LinearLayout) layout3.getChildAt(1);
            TextView textView2= (TextView) layout4.getChildAt(0);
            textView2.setText(R.string.owner_title);

            TextView textView3= (TextView) layout4.getChildAt(1);
            textView3.setText(" "+"Owner Name");

            TextView textView4= (TextView) layout1.getChildAt(1);
            textView4.setText("Message Brief Discription, Narrative Statement. Who Will Win 2018 FIFA World Cup Russia™?.");

            ImageView image1= (ImageView) layout1.getChildAt(2);
            Drawable mainPic = getResources().getDrawable(R.drawable.ronaldo);
            image1.setImageDrawable(mainPic);

//            videoView = (VideoView) layout1.getChildAt(2);
//            videoView.setVideoPath("/BMWvsMercedes2.mp4");
//
//
//            //set the media controller buttons
//            if (mediaControls == null) {
//                mediaControls = new MediaController(this);
//            }
//
//
//
//            // create a progress bar while the video file is loading
//            progressDialog = new ProgressDialog(this);
//            // set a title for the progress bar
//            progressDialog.setTitle("JavaCodeGeeks Android Video View Example");
//            // set a message for the progress bar
//            progressDialog.setMessage("Loading...");
//            //set the progress bar not cancelable on users' touch
//            progressDialog.setCancelable(false);
//            // show the progress bar
//          //  progressDialog.show();
//
//            try {
//                //set the media controller in the VideoView
//                videoView.setMediaController(mediaControls);
//
//                //set the uri of the video to be played
//                String videoLocation ="android.resource://BMWvsMercedes1.3";
////                myVideoView.setVideoURI(Uri.parse("" + getPackageName() + "/" + R.raw.kitkat));
//                videoView.setVideoURI(Uri.parse(videoLocation));
//
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//
//            videoView.requestFocus();
//            //we also set an setOnPreparedListener in order to know when the video file is ready for playback
//            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//
//                public void onPrepared(MediaPlayer mediaPlayer) {
//                    // close the progress bar and play the video
//                    progressDialog.dismiss();
//                    //if we have a position on savedInstanceState, the video playback should start from here
//                    videoView.seekTo(position);
//                    if (position == 0) {
//                        videoView.start();
//                    } else {
//                        //if we come from a resumed activity, video playback will be paused
//                        videoView.pause();
//                    }
//                }
//            });


            LinearLayout.LayoutParams btwnViewConfig =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 30);
            LinearLayout.LayoutParams endViewConfig =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150);

            LinearLayout layout5= (LinearLayout) layout1.getChildAt(4);
            View endView=(View) layout1.getChildAt(5);
            if(loopNumbers-1==a){
                endView.setLayoutParams(endViewConfig);

            }else {
                endView.setLayoutParams(btwnViewConfig);
            }

            LinearLayout layout6= (LinearLayout) layout5.getChildAt(0);



            outter1.addView(cardView);

            View view = (View) inflater1.inflate(R.layout.view_spacer, null);
            outter1.addView(view);

//            View view =outter1.getChildAt(0);
//            view.setLayoutParams(linearLayout16_LayoutParams);
//            outter1.removeViewAt(0);
//            outter1.addView(view);

        }

    }

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
}