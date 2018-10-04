package com.material.components.activity.bottomsheet;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.balysv.materialripple.MaterialRippleLayout;
import com.material.components.R;
import com.material.components.activity.list.ListMultiSelection;
import com.material.components.adapter.AdapterGridTwoLineLight;
import com.material.components.helper.DragItemTouchHelper;
import com.material.components.helper.MultipleSelectorHelper;
import com.material.components.model.Image;
import com.material.components.utils.Tools;
import com.material.components.widget.SpacingItemDecoration;
import com.material.nominees.NomineeMasterObject;
import com.material.nominees.NomineesEntity;
import com.material.utility.JsonObjectConversion;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BottomSheetFloating extends AppCompatActivity {

    private View parent_view;

    private RecyclerView recyclerView;
    private AdapterGridTwoLineLight mAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bottom_sheet_floating);
        parent_view = findViewById(android.R.id.content);


        initToolbar("Nominees");
        Bundle b = getIntent().getExtras();
        int voteType = -1; // or other values
            voteType = b.getInt("voteType");
            if(voteType==1){
                showCustomDialog("Order By Dragging To Nominate","Long-Press -> Drag & Place in Favoured Order");
            }
            else  if(voteType==2){
                showCustomDialog("Select one To Nominate","Long-Press to Select Favourite");
            }
            else  if(voteType==3){
                showCustomDialog("Select One / Multiple To Nominate","Long-Press to Select Favourite");
            }
        initComponent(voteType);

    }

    private void initComponent(final int voteType) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(this, 4), false));
        recyclerView.setHasFixedSize(true);



        String url = "http://192.168.1.40:8090/nominees";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonObjectConversion jsonConversion = new JsonObjectConversion();
                        NomineeMasterObject nomineeMasterObject = (NomineeMasterObject) jsonConversion.jsonToObject(response, NomineeMasterObject.class);
                        List<NomineesEntity> nomineesEntityList = nomineeMasterObject.getNomineesEntityList();
                        final List<Image> items = new ArrayList<>();
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                                .permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        int count = 0;

                        for (NomineesEntity nomineesEntity1 : nomineesEntityList) {
                            final Image image = new Image();
                            image.name = (nomineesEntity1.getNomineeName());
                            image.url=nomineesEntity1.getNomineeImage();
                            image.counter = (count);
                            image.brief = nomineesEntity1.getNomineesDescription();
                            items.add(image);
                            count++;
                        }

                        //set data and list adapter
                        mAdapter = new AdapterGridTwoLineLight(BottomSheetFloating.this, items);
                        recyclerView.setAdapter(mAdapter);

                        // on item list clicked
                        mAdapter.setOnItemClickListener(new AdapterGridTwoLineLight.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, Image obj, int position) {

                                LinearLayout layout = (LinearLayout) view;
                                RelativeLayout relativeLayout = (RelativeLayout) layout.getChildAt(0);
                                ImageView imageView = (ImageView) relativeLayout.getChildAt(0);

                                LinearLayout layout1 = (LinearLayout) layout.getChildAt(1);
                                LinearLayout layout2 = (LinearLayout) layout1.getChildAt(0);
                                TextView albumName = (TextView) layout2.getChildAt(1);
                                TextView nomineeName = (TextView) layout2.getChildAt(0);

                                showDialogImageFull(imageView.getDrawable(), nomineeName);
                                Snackbar.make(parent_view, albumName.getText(), Snackbar.LENGTH_LONG).show();

                            }

                            @Override
                            public void onLongItemClick(RecyclerView.ViewHolder view, Image obj, int position) {
                                if(voteType==3){
//                                    for (int count = 0; count < recyclerView.getChildCount()-1; count++) {
                                        View view1 = (View) recyclerView.getChildAt(view.getPosition());
                                        MaterialRippleLayout materialRippleLayout = (MaterialRippleLayout) view1;
                                        LinearLayout layout = (LinearLayout) materialRippleLayout.getChildAt(0);
                                        LinearLayout layout1 = (LinearLayout) layout.getChildAt(1);
                                        TextView counter = (TextView) layout1.getChildAt(2);
                                        counter.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done, 0, 0, 0);

                                //    }
                                }
                            }
                        });



                        if(voteType==1){
                            ItemTouchHelper.Callback callback = new DragItemTouchHelper(mAdapter);
                            mItemTouchHelper = new ItemTouchHelper(callback);
                            mItemTouchHelper.attachToRecyclerView(recyclerView);

                        }
                        else if(voteType==2){

                        }
                        else if(voteType==3){

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        // Log.d("Error.Response", response);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("voteID", String.valueOf(7));
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(postRequest);


    }

    private void initToolbar(String pollTitle) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(pollTitle);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_20);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nomninee, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getTitle().equals("polled")) {
            BottomSheetFloating.super.onBackPressed();

        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private void showDialogImageFull(Drawable img, TextView mainText) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_image);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.toFullScreenImage);
        imageView.setImageDrawable(img);
        TextView nomineeName = (TextView) dialog.findViewById(R.id.nomineeName);
        nomineeName.setText(mainText.getText());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();

    }

    private void showCustomDialog(String title, String description) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_info);
        TextView dialogTitle = (TextView) dialog.findViewById(R.id.title);
        dialogTitle.setText(title);
        TextView dialogDescription = (TextView) dialog.findViewById(R.id.content);
        dialogDescription.setText(description);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        ((AppCompatButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), ((AppCompatButton) v).getText().toString() + " Clicked", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
}
