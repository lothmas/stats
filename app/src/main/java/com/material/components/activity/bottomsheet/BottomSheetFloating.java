package com.material.components.activity.bottomsheet;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.material.components.R;
import com.material.components.adapter.AdapterGridTwoLineLight;
import com.material.components.adapter.AdapterListDrag;
import com.material.components.data.DataGenerator;
import com.material.components.helper.DragItemTouchHelper;
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
    RequestQueue queue1;

    private View parent_view;

    private RecyclerView recyclerView;
    private AdapterGridTwoLineLight mAdapter;
    private ItemTouchHelper mItemTouchHelper;

    private BottomSheetBehavior mBehavior;
    private BottomSheetDialog mBottomSheetDialog;
    private View bottom_sheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_floating);
        parent_view = findViewById(android.R.id.content);

        showCustomDialog("Drag To Nominate","Nominate by making a Long-Press then Dragging & Placing in Order starting with your favourite");
        initComponent();
        initToolbar("Nominees");

    }

    private void initComponent() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new SpacingItemDecoration(6, Tools.dpToPx(this, 6), true));
        recyclerView.setHasFixedSize(false);


        queue1 = Volley.newRequestQueue(this);

        String url = "http://192.168.1.40:8090/nominees";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        String er = "sdsd";
                        // Log.d("Response", response);

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

                            try {
                                image.imageDrw = (drawableFromUrl(nomineesEntity1.getNomineeImage()));
                            } catch (Exception e) {
                                //do nothing
                            }
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
                                Snackbar.make(parent_view, obj.brief, Snackbar.LENGTH_SHORT).show();
                                showDialogImageFull(obj);
                                //showBottomSheetDialog();
                            }
                        });

                        mAdapter.setDragListener(new AdapterListDrag.OnStartDragListener() {
                            @Override
                            public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
                                mItemTouchHelper.startDrag(viewHolder);
                            }
                        });

                        ItemTouchHelper.Callback callback = new DragItemTouchHelper(mAdapter);
                        mItemTouchHelper = new ItemTouchHelper(callback);
                        mItemTouchHelper.attachToRecyclerView(recyclerView);

                        bottom_sheet = findViewById(R.id.bottom_sheet);
                        mBehavior = BottomSheetBehavior.from(bottom_sheet);
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
    // toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(pollTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    //  Tools.setSystemBarColor(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_basic, menu);
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


    private void showBottomSheetDialog() {
//        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
//            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        }

        final View view = getLayoutInflater().inflate(R.layout.sheet_floating, null);
        ((TextView) view.findViewById(R.id.name)).setText("");
        ((TextView) view.findViewById(R.id.brief)).setText("");
        ((TextView) view.findViewById(R.id.description)).setText(R.string.middle_lorem_ipsum);
        (view.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.hide();
            }
        });

        (view.findViewById(R.id.submit_rating)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Submit Rating", Toast.LENGTH_SHORT).show();
            }
        });

        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        // set background transparent
        ((View) view.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));

        //   mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });

    }

    private void showDialogImageFull(Image img) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_image);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.toFullScreenImage);
        imageView.setImageDrawable(img.imageDrw);
        TextView nomineeName = (TextView) dialog.findViewById(R.id.nomineeName);
        nomineeName.setText(img.name);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();

    }

    public Drawable drawableFromUrl(String url) throws IOException, Exception {

        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(x);
    }

    private void showCustomDialog(String title,String description) {
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
