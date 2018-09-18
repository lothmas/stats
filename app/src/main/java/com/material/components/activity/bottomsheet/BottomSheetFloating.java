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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import com.material.components.data.DataGenerator;
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

    private BottomSheetBehavior mBehavior;
    private BottomSheetDialog mBottomSheetDialog;
    private View bottom_sheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_floating);
        parent_view = findViewById(android.R.id.content);

        initComponent();
        initToolbar();
        //showBottomSheetDialog();
    }

    private void initComponent() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(this, 4), true));
        recyclerView.setHasFixedSize(true);


        queue1 = Volley.newRequestQueue(this);

        String url = "http://192.168.1.40:8090/nominees";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        String er="sdsd";
                       // Log.d("Response", response);

                        JsonObjectConversion jsonConversion = new JsonObjectConversion();
                        NomineeMasterObject nomineeMasterObject = (NomineeMasterObject) jsonConversion.jsonToObject(response, NomineeMasterObject.class);
                        List<NomineesEntity> nomineesEntityList = nomineeMasterObject.getNomineesEntityList();
                        final List<Image> items =new ArrayList<>();
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                                .permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        int count=0;
                        for(NomineesEntity nomineesEntity1:nomineesEntityList){
                            final Image image=new Image();
                            image.name=(nomineesEntity1.getNomineeName());
//                            try {
//
//// Retrieves an image specified by the URL, displays it in the UI.
//                                ImageRequest threadProfilePic = new ImageRequest(nomineesEntity1.getNomineeImage(),
//                                        new Response.Listener<Bitmap>() {
//                                            @Override
//                                            public void onResponse(Bitmap bitmap) {
//                                                Drawable d = new BitmapDrawable(getResources(), bitmap);
//                                                image.setImageDrw(d);
//                                                items.add(image);
//                                            }
//                                        }, 0, 0, null,
//                                        new Response.ErrorListener() {
//                                            public void onErrorResponse(VolleyError error) {
//                                               // image.setImageDrw(R.drawable.cast3);
//                                            }
//                                        });
//// Access the RequestQueue through your singleton class.
//                                queue1.add(threadProfilePic);
//                            } catch (Exception e) {
//                            //    circularImageView.setImageResource(R.drawable.cast3);
//                            }

                            try {
                             //   image.image = drawableFromUrl(nomineesEntity1.getNomineeImage());
//                                image.name = name_arr[i];
//                                image.brief = date_arr[randInt(date_arr.length - 1)];
//                                image.counter = r.nextBoolean() ? randInt(500) : null;
//                                image.imageDrw = ctx.getResources().getDrawable(obj.image);


                                image.imageDrw=(drawableFromUrl(nomineesEntity1.getNomineeImage()));
                                image.counter=(count);
                                image.brief=("Test");
                                items.add(image);
                                count++;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }




                    //    items.addAll(items);

                        //set data and list adapter
                        mAdapter = new AdapterGridTwoLineLight(BottomSheetFloating.this, items);
                        recyclerView.setAdapter(mAdapter);

                        // on item list clicked
                        mAdapter.setOnItemClickListener(new AdapterGridTwoLineLight.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, Image obj, int position) {
                                Snackbar.make(parent_view, obj + " clicked", Snackbar.LENGTH_SHORT).show();
                                showDialogImageFull(obj);
                                //showBottomSheetDialog();
                            }
                        });



                        bottom_sheet = findViewById(R.id.bottom_sheet);
                        mBehavior = BottomSheetBehavior.from(bottom_sheet);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                       // Log.d("Error.Response", response);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("voteID", String.valueOf(7));
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(postRequest);





    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Nominees");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
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
        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

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
        ImageView imageView=(ImageView) dialog.findViewById(R.id.toFullScreenImage);
        imageView.setImageDrawable(img.imageDrw);
        TextView nomineeName=(TextView)dialog.findViewById(R.id.nomineeName);
        nomineeName.setText(img.name);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();

    }

    public Drawable drawableFromUrl(String url) throws IOException {

        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(x);
    }
}
