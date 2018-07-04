package com.material.components.activity.bottomnavigation;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.PorterDuff;
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
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.Tools;
import com.material.components.utils.ViewAnimation;
import com.mikhaellopez.circularimageview.CircularImageView;

public class BottomNavigationIcon extends AppCompatActivity {

    private TabLayout tab_layout;
    private ActionBar actionBar;
    private NestedScrollView nested_scroll_view;
    private CardView trendingCardView;
    private LinearLayout outter1;
    private LinearLayout outter2;
    private LinearLayout outter3;
    private LinearLayout outter4;
    private LinearLayout outter5;

    private TextView textView2;
    private ImageView imageView1;
    private TextView textView3;

    private CircularImageView circularImageView;
private  ImageButton ImageButtonSRC;;




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
    private void trendingCardView(){
        outter1= (LinearLayout) findViewById(R.id.outter1);
        CardView cardView = null;

        LinearLayout.LayoutParams cardViewConfig = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        cardViewConfig.setMargins(10, 0, 10, 0);
        //cardView.setLayoutParams(cardViewConfig);
        cardView.setVisibility(View.VISIBLE);
        cardView.setRadius(0);
        cardView.setElevation(4);

        outter2.setLayoutParams(cardViewConfig);
        outter2.setOrientation(LinearLayout.VERTICAL);
        cardView.addView(outter2);

//        trendingOutter1.setLayoutParams(trendingLayoutParams);
//        trendingOutter1.setOrientation(LinearLayout.HORIZONTAL);
//        trendingOutter1.setDescendantFocusability(LinearLayout.FOCUS_BLOCK_DESCENDANTS);

        LinearLayout.LayoutParams outter3Config = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        cardViewConfig.setMargins(15, 15, 15, 5);
        outter3.setLayoutParams(cardViewConfig);
        outter3.setGravity(Gravity.CENTER_VERTICAL);
        outter3.setOrientation(LinearLayout.HORIZONTAL);
        outter2.addView(outter3);

        LinearLayout.LayoutParams circularImageViewConfig = new LinearLayout.LayoutParams(R.dimen.spacing_xxlarge, R.dimen.spacing_xxlarge);
        circularImageView.setLayoutParams(circularImageViewConfig);
        circularImageView.setBackgroundResource(R.drawable.fifa);
//        app:civ_border="false" />
        outter3.addView(circularImageView);



        LinearLayout.LayoutParams outter4Config = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        outter4.setLayoutParams(outter4Config);
        outter4.setOrientation(LinearLayout.VERTICAL);
        outter3.addView(outter4);


        LinearLayout.LayoutParams textView2Config = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView2.setLayoutParams(textView2Config);
        textView2.setText("2018 FIFA World Cup Russia™");
        textView2.setTextColor(R.color.grey_90);
        textView2.setTextAppearance(R.style.TextAppearance_AppCompat_Subhead);
        textView2.setGravity(Gravity.CENTER_VERTICAL);
        outter4.addView(textView2);

        LinearLayout.LayoutParams imageView1Config = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        imageView1.setLayoutParams(imageView1Config);
        imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView1.setBackgroundResource(R.drawable.ronaldo);

        LinearLayout.LayoutParams textView3Config = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView3.setLayoutParams(textView2Config);
      //  textView3.setBackground(R.color.grey_5);
//        android:layout_marginTop="@dimen/spacing_medium"




        LinearLayout.LayoutParams outter5Config = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, R.dimen.spacing_xxlarge);
        outter5.setLayoutParams(outter5Config);
        outter5.setOrientation(LinearLayout.HORIZONTAL);
        outter5.setGravity(Gravity.CENTER_VERTICAL);
        outter5.setPadding(R.dimen.spacing_large,0,R.dimen.spacing_large,0);





    }

}