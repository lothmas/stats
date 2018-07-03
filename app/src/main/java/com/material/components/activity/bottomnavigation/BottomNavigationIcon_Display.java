package com.material.components.activity.bottomnavigation;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.material.components.R;
import com.material.components.utils.Tools;
import com.material.components.utils.ViewAnimation;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.Tools;
import com.material.components.utils.ViewAnimation;

public class BottomNavigationIcon_Display {


    public RelativeLayout initLayout(Context context) {
        RelativeLayout root = new RelativeLayout(context);
        ViewGroup.LayoutParams root_LayoutParams =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        root_LayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        root_LayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        root.setPadding((int) getResources().getDimension(R.dimen.activity_horizontal_margin), (int) getResources().getDimension(R.dimen.activity_vertical_margin), 0, (int) getResources().getDimension(R.dimen.activity_vertical_margin));
        //tools:context="com.example.androidtest.MainActivity"    //not support
        root.setLayoutParams(root_LayoutParams);

        FrameLayout abc = new FrameLayout(context);
        RelativeLayout.LayoutParams abc_LayoutParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        abc.setId(@ id/abc);
        abc_LayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        abc_LayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        abc_LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        abc_LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        root.addView(abc);
        abc.setLayoutParams(abc_LayoutParams);

        TextView textView1 = new TextView(context);
        FrameLayout.LayoutParams textView1_LayoutParams =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        textView1.setId(@ id/textView1);
        textView1.setText("Small Text");
        //android:textAppearance="?abc"    //not support
        abc.addView(textView1);
        textView1.setLayoutParams(textView1_LayoutParams);

        return root;
    }
}
