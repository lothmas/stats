//package com.material.components.activity.bottomnavigation;
//
//import android.content.Context;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.material.components.R;
//import com.material.components.utils.Tools;
//import com.material.components.utils.ViewAnimation;
//
//import android.graphics.PorterDuff;
//import android.os.Bundle;
//import android.support.design.widget.TabLayout;
//import android.support.v4.widget.NestedScrollView;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.Toolbar;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import com.material.components.R;
//import com.material.components.utils.Tools;
//import com.material.components.utils.ViewAnimation;
//
//import android.content.Context;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.content.Context;
//import android.view.Gravity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.ImageView.ScaleType;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
////import com.sickworm.ax2j.JResources;
////import com.sickworm.ax2j.R;
//
//public class BottomNavigationIcon_Display {
//
//
//
//
//
//    public RelativeLayout initLayout(Context context) {
//        final float scale = context.getResources().getDisplayMetrics().density;
//        JResources getResources() = new JResources(context);
//
//        RelativeLayout root = new RelativeLayout(context);
//        ViewGroup.LayoutParams root_LayoutParams =
//                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        root.setId(R.id.container);
//        root_LayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        root_LayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
//        root.setBackgroundColor(getResources().getColor(R.color.grey_5));
//        root.setLayoutParams(root_LayoutParams);
//
//        LinearLayout linearLayout = new LinearLayout(context);
//        RelativeLayout.LayoutParams linearLayout_LayoutParams =
//                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        linearLayout_LayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        linearLayout_LayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
//        linearLayout.setOrientation(LinearLayout.VERTICAL);
//        root.addView(linearLayout);
//        linearLayout.setLayoutParams(linearLayout_LayoutParams);
//
//        Void appbar = new android.support.design.widget.AppBarLayout(context);
//        LinearLayout.LayoutParams appbar_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        //android:id="@+id/appbar"    //not support
//        //android:layout_width="match_parent"    //not support
//        //android:background="@android:color/white"    //not support
//        //android:theme="@style/AppTheme.AppBarOverlay"    //not support
//        linearLayout.addView(appbar);
//        appbar.setLayoutParams(appbar_LayoutParams);
//
//        Void toolbar = new android.support.v7.widget.Toolbar(context);
//        Void.LayoutParams toolbar_LayoutParams =
//                new Void.LayoutParams(Void.LayoutParams.WRAP_CONTENT, Void.LayoutParams.WRAP_CONTENT);
//        //android:id="@+id/toolbar"    //not support
//        //android:layout_width="match_parent"    //not support
//        //android:layout_height="?attr/actionBarSize"    //not support
//        //app:contentInsetStartWithNavigation="0dp"    //not support
//        //app:popupTheme="@style/ThemeOverlay.AppCompat.Light"    //not support
//        //app:theme="@style/Toolbar.Light"    //not support
//        appbar.addView(toolbar);
//        toolbar.setLayoutParams(toolbar_LayoutParams);
//
//        Void nested_scroll_view = new android.support.v4.widget.NestedScrollView(context);
//        LinearLayout.LayoutParams nested_scroll_view_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        //android:id="@+id/nested_scroll_view"    //not support
//        //android:layout_width="match_parent"    //not support
//        //android:layout_height="match_parent"    //not support
//        //android:scrollbars="none"    //not support
//        //android:scrollingCache="true"    //not support
//        linearLayout.addView(nested_scroll_view);
//        nested_scroll_view.setLayoutParams(nested_scroll_view_LayoutParams);
//
//        LinearLayout linearLayout1 = new LinearLayout(context);
//        Void.LayoutParams linearLayout1_LayoutParams =
//                new Void.LayoutParams(Void.LayoutParams.WRAP_CONTENT, Void.LayoutParams.WRAP_CONTENT);
//        linearLayout1_LayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        //android:descendantFocusability="blocksDescendants"    //not support
//        linearLayout1.setOrientation(LinearLayout.VERTICAL);
//        nested_scroll_view.addView(linearLayout1);
//        linearLayout1.setLayoutParams(linearLayout1_LayoutParams);
//
//        View view2 = new View(context);
//        LinearLayout.LayoutParams view2_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        view2_LayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        view2_LayoutParams.height = (int) getResources().getDimension(R.dimen.spacing_middle);
//        linearLayout1.addView(view2);
//        view2.setLayoutParams(view2_LayoutParams);
//
//        Void cardView3 = new android.support.v7.widget.CardView(context);
//        LinearLayout.LayoutParams cardView3_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        //android:layout_width="match_parent"    //not support
//        //android:layout_marginLeft="@dimen/spacing_middle"    //not support
//        //android:layout_marginRight="@dimen/spacing_middle"    //not support
//        //android:visibility="visible"    //not support
//        //app:cardCornerRadius="0dp"    //not support
//        //app:cardElevation="4dp"    //not support
//        linearLayout1.addView(cardView3);
//        cardView3.setLayoutParams(cardView3_LayoutParams);
//
//        LinearLayout linearLayout4 = new LinearLayout(context);
//        Void.LayoutParams linearLayout4_LayoutParams =
//                new Void.LayoutParams(Void.LayoutParams.WRAP_CONTENT, Void.LayoutParams.WRAP_CONTENT);
//        linearLayout4_LayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        linearLayout4.setOrientation(LinearLayout.VERTICAL);
//        cardView3.addView(linearLayout4);
//        linearLayout4.setLayoutParams(linearLayout4_LayoutParams);
//
//        LinearLayout linearLayout5 = new LinearLayout(context);
//        LinearLayout.LayoutParams linearLayout5_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        linearLayout5_LayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        linearLayout5_LayoutParams.bottomMargin = (int) getResources().getDimension(R.dimen.spacing_medium);
//        linearLayout5_LayoutParams.leftMargin = (int) getResources().getDimension(R.dimen.spacing_large);
//        linearLayout5_LayoutParams.rightMargin = (int) getResources().getDimension(R.dimen.spacing_large);
//        linearLayout5_LayoutParams.topMargin = (int) getResources().getDimension(R.dimen.spacing_large);
//        linearLayout5.setGravity(Gravity.CENTER_VERTICAL);
//        linearLayout5.setOrientation(LinearLayout.HORIZONTAL);
//        linearLayout4.addView(linearLayout5);
//        linearLayout5.setLayoutParams(linearLayout5_LayoutParams);
//
//        Void circularImageView6 = new com.mikhaellopez.circularimageview.CircularImageView(context);
//        LinearLayout.LayoutParams circularImageView6_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        //android:layout_width="@dimen/spacing_xxlarge"    //not support
//        //android:layout_height="@dimen/spacing_xxlarge"    //not support
//        //android:foreground="@color/overlay_light_20"    //not support
//        //android:src="@drawable/fifa"    //not support
//        //app:civ_border="false"    //not support
//        linearLayout5.addView(circularImageView6);
//        circularImageView6.setLayoutParams(circularImageView6_LayoutParams);
//
//        View view7 = new View(context);
//        LinearLayout.LayoutParams view7_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        view7_LayoutParams.width = (int) getResources().getDimension(R.dimen.spacing_large);
//        view7_LayoutParams.height = (int) (0 * scale + 0.5f);
//        linearLayout5.addView(view7);
//        view7.setLayoutParams(view7_LayoutParams);
//
//        LinearLayout linearLayout8 = new LinearLayout(context);
//        LinearLayout.LayoutParams linearLayout8_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        linearLayout8_LayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        linearLayout8.setOrientation(LinearLayout.VERTICAL);
//        linearLayout5.addView(linearLayout8);
//        linearLayout8.setLayoutParams(linearLayout8_LayoutParams);
//
//        TextView textView9 = new TextView(context);
//        LinearLayout.LayoutParams textView9_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        textView9_LayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        textView9.setGravity(Gravity.CENTER_VERTICAL);
//        textView9.setText("2018 FIFA World Cup Russia™");
//        //android:textAppearance="@style/TextAppearance.AppCompat.Subhead"    //not support
//        textView9.setTextColor(getResources().getColor(R.color.grey_90));
//        linearLayout8.addView(textView9);
//        textView9.setLayoutParams(textView9_LayoutParams);
//
//        LinearLayout linearLayout10 = new LinearLayout(context);
//        LinearLayout.LayoutParams linearLayout10_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        linearLayout10_LayoutParams.topMargin = (int) getResources().getDimension(R.dimen.spacing_small);
//        linearLayout10.setOrientation(LinearLayout.HORIZONTAL);
//        linearLayout8.addView(linearLayout10);
//        linearLayout10.setLayoutParams(linearLayout10_LayoutParams);
//
//        TextView textView11 = new TextView(context);
//        LinearLayout.LayoutParams textView11_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        textView11_LayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        textView11.setGravity(Gravity.CENTER_VERTICAL);
//        textView11.setText("owner ");
//        //android:textAppearance="@style/TextAppearance.AppCompat.Caption"    //not support
//        textView11.setTextColor(getResources().getColor(R.color.grey_40));
//        linearLayout10.addView(textView11);
//        textView11.setLayoutParams(textView11_LayoutParams);
//
//        TextView textView12 = new TextView(context);
//        LinearLayout.LayoutParams textView12_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        textView12_LayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        textView12.setGravity(Gravity.CENTER_VERTICAL);
//        textView12.setText("FIFA.COM");
//        //android:textAppearance="@style/TextAppearance.AppCompat.Caption"    //not support
//        textView12.setTextColor(getResources().getColor(R.color.light_blue_400));
//        textView12.setTypeface(bold);
//        linearLayout10.addView(textView12);
//        textView12.setLayoutParams(textView12_LayoutParams);
//
//        TextView textView13 = new TextView(context);
//        LinearLayout.LayoutParams textView13_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        textView13_LayoutParams.setMargins((int) getResources().getDimension(R.dimen.spacing_large), (int) getResources().getDimension(R.dimen.spacing_large), (int) getResources().getDimension(R.dimen.spacing_large), (int) getResources().getDimension(R.dimen.spacing_large));
//        textView13.setLineSpacing(4dp, 0);
//        textView13.setText("Who Will Win 2018 FIFA World Cup Russia™");
//        //android:textAppearance="@style/TextAppearance.AppCompat.Caption"    //not support
//        textView13.setTextColor(getResources().getColor(R.color.grey_60));
//        linearLayout4.addView(textView13);
//        textView13.setLayoutParams(textView13_LayoutParams);
//
//        ImageView imageView14 = new ImageView(context);
//        LinearLayout.LayoutParams imageView14_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        imageView14_LayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        imageView14_LayoutParams.height = (int) (200 * scale + 0.5f);
//        imageView14.setScaleType(ScaleType.CENTER_CROP);
//        imageView14.setImageResource(@drawable/ronaldo);
//        linearLayout4.addView(imageView14);
//        imageView14.setLayoutParams(imageView14_LayoutParams);
//
//        View view15 = new View(context);
//        LinearLayout.LayoutParams view15_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        view15_LayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        view15_LayoutParams.height = (int) (1 * scale + 0.5f);
//        view15_LayoutParams.topMargin = (int) getResources().getDimension(R.dimen.spacing_medium);
//        view15.setBackgroundColor(getResources().getColor(R.color.grey_5));
//        linearLayout4.addView(view15);
//        view15.setLayoutParams(view15_LayoutParams);
//
//        LinearLayout linearLayout16 = new LinearLayout(context);
//        LinearLayout.LayoutParams linearLayout16_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        linearLayout16_LayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        linearLayout16_LayoutParams.height = (int) getResources().getDimension(R.dimen.spacing_xxlarge);
//        linearLayout16.setGravity(Gravity.CENTER_VERTICAL);
//        linearLayout16.setOrientation(LinearLayout.HORIZONTAL);
//        linearLayout16.setPadding((int) getResources().getDimension(R.dimen.spacing_large), 0, 0, 0);
//        linearLayout4.addView(linearLayout16);
//        linearLayout16.setLayoutParams(linearLayout16_LayoutParams);
//
//        LinearLayout linearLayout17 = new LinearLayout(context);
//        LinearLayout.LayoutParams linearLayout17_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        linearLayout17.setGravity(Gravity.CENTER_VERTICAL);
//        linearLayout17.setOrientation(LinearLayout.HORIZONTAL);
//        linearLayout16.addView(linearLayout17);
//        linearLayout17.setLayoutParams(linearLayout17_LayoutParams);
//
//        ImageView imageView18 = new ImageView(context);
//        LinearLayout.LayoutParams imageView18_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        imageView18_LayoutParams.width = (int) getResources().getDimension(R.dimen.spacing_mlarge);
//        imageView18_LayoutParams.height = (int) getResources().getDimension(R.dimen.spacing_mlarge);
//        imageView18_LayoutParams.setMarginEnd((int) getResources().getDimension(R.dimen.spacing_middle));
//        imageView18_LayoutParams.rightMargin = (int) getResources().getDimension(R.dimen.spacing_middle);
//        imageView18.setImageTintList(@color/light_green_300);
//        //app:srcCompat="@drawable/ic_thumb_up"    //not support
//        linearLayout17.addView(imageView18);
//        imageView18.setLayoutParams(imageView18_LayoutParams);
//
//        TextView textView19 = new TextView(context);
//        LinearLayout.LayoutParams textView19_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        textView19.setGravity(Gravity.CENTER_VERTICAL);
//        textView19.setText("cast");
//        //android:textAppearance="@style/TextAppearance.AppCompat.Caption"    //not support
//        textView19.setTextColor(getResources().getColor(R.color.grey_40));
//        linearLayout17.addView(textView19);
//        textView19.setLayoutParams(textView19_LayoutParams);
//
//        View view20 = new View(context);
//        LinearLayout.LayoutParams view20_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        view20_LayoutParams.width = (int) getResources().getDimension(R.dimen.spacing_large);
//        view20_LayoutParams.height = (int) (0 * scale + 0.5f);
//        linearLayout16.addView(view20);
//        view20.setLayoutParams(view20_LayoutParams);
//
//        LinearLayout linearLayout21 = new LinearLayout(context);
//        LinearLayout.LayoutParams linearLayout21_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        linearLayout21_LayoutParams.width = (int) (0 * scale + 0.5f);
//        linearLayout21_LayoutParams.weight = 1;
//        linearLayout21.setGravity(Gravity.CENTER_VERTICAL);
//        linearLayout21.setOrientation(LinearLayout.HORIZONTAL);
//        linearLayout16.addView(linearLayout21);
//        linearLayout21.setLayoutParams(linearLayout21_LayoutParams);
//
//        ImageView imageView22 = new ImageView(context);
//        LinearLayout.LayoutParams imageView22_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        imageView22_LayoutParams.width = (int) getResources().getDimension(R.dimen.spacing_mlarge);
//        imageView22_LayoutParams.height = (int) getResources().getDimension(R.dimen.spacing_mlarge);
//        imageView22_LayoutParams.setMarginEnd((int) getResources().getDimension(R.dimen.spacing_middle));
//        imageView22_LayoutParams.rightMargin = (int) getResources().getDimension(R.dimen.spacing_middle);
//        //app:srcCompat="@drawable/logo"    //not support
//        linearLayout21.addView(imageView22);
//        imageView22.setLayoutParams(imageView22_LayoutParams);
//
//        TextView textView23 = new TextView(context);
//        LinearLayout.LayoutParams textView23_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        textView23.setGravity(Gravity.CENTER_VERTICAL);
//        textView23.setText("statistics");
//        //android:textAppearance="@style/TextAppearance.AppCompat.Caption"    //not support
//        textView23.setTextColor(getResources().getColor(R.color.grey_40));
//        linearLayout21.addView(textView23);
//        textView23.setLayoutParams(textView23_LayoutParams);
//
//        TextView textView24 = new TextView(context);
//        LinearLayout.LayoutParams textView24_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        textView24.setGravity(Gravity.END | Gravity.RIGHT);
//        textView24.setText("3h ago");
//        //android:textAppearance="@style/TextAppearance.AppCompat.Body1"    //not support
//        textView24.setTextColor(getResources().getColor(R.color.grey_40));
//        linearLayout16.addView(textView24);
//        textView24.setLayoutParams(textView24_LayoutParams);
//
//        LinearLayout linearLayout25 = new LinearLayout(context);
//        RelativeLayout.LayoutParams linearLayout25_LayoutParams =
//                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        linearLayout25_LayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        linearLayout25_LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
//        linearLayout25.setOrientation(LinearLayout.VERTICAL);
//        root.addView(linearLayout25);
//        linearLayout25.setLayoutParams(linearLayout25_LayoutParams);
//
//        View view26 = new View(context);
//        LinearLayout.LayoutParams view26_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        view26_LayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        view26_LayoutParams.height = (int) (4 * scale + 0.5f);
//        view26.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_gradient_soft));
//        linearLayout25.addView(view26);
//        view26.setLayoutParams(view26_LayoutParams);
//
//        Void tab_layout = new android.support.design.widget.TabLayout(context);
//        LinearLayout.LayoutParams tab_layout_LayoutParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        //android:id="@+id/tab_layout"    //not support
//        //android:layout_width="match_parent"    //not support
//        //android:layout_height="?attr/actionBarSize"    //not support
//        //android:background="@android:color/white"    //not support
//        //app:popupTheme="@style/ThemeOverlay.AppCompat.Light"    //not support
//        //app:tabIndicatorHeight="0dp"    //not support
//        //app:tabMode="fixed"    //not support
//        //app:theme="@style/Toolbar.Light"    //not support
//        linearLayout25.addView(tab_layout);
//        tab_layout.setLayoutParams(tab_layout_LayoutParams);
//
//        return root;
//    }
//
//}
