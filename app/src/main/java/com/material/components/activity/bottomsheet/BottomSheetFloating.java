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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
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
import com.material.components.adapter.AdapterGridTwoLineLight;
import com.material.components.helper.DragItemTouchHelper;
import com.material.components.model.Image;
import com.material.components.utils.Tools;
import com.material.components.widget.SpacingItemDecoration;
import com.material.nominees.NomineeMasterObject;
import com.material.nominees.NomineesEntity;
import com.material.utility.JsonObjectConversion;
import com.material.utility.RecyclerViewPositionHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BottomSheetFloating extends AppCompatActivity {

    private View parent_view;

    private static volatile RecyclerView recyclerView;
    private static volatile AdapterGridTwoLineLight mAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private int voteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bottom_sheet_floating);
        parent_view = findViewById(android.R.id.content);


        initToolbar("Nominees");
        Bundle b = getIntent().getExtras();
        int voteType, voteBy; // or other values
        voteType = b.getInt("voteType");
        voteId = b.getInt("voteId");
        voteBy = b.getInt("voteBy");
        if (voteType == 1) {
            showCustomDialog("ORDER BY DRAGGING", "Long-Press -> Drag & Place in Favoured Order");
        } else if (voteType == 2) {
            showCustomDialog("SINGLE SELECTION", "Long-Press to Select Favourite");
        } else if (voteType == 3) {
            showCustomDialog("MULTIPLE SELECTION", "Long-Press to Select Favourite");
        }
        initComponent(voteType, voteBy);

    }

    private void initComponent(final int voteType, final int voteBy) {
        int gridColumnsPerRow = 2;
        if (voteBy == 1) {
            //    gridColumnsPerRow=1;
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnsPerRow));
        recyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(this, 4), false));
//        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        String url = "http://192.168.1.40:8090/nominees";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonObjectConversion jsonConversion = new JsonObjectConversion();
                        NomineeMasterObject nomineeMasterObject = (NomineeMasterObject) jsonConversion.jsonToObject(response, NomineeMasterObject.class);
                        List<NomineesEntity> nomineesEntityList = nomineeMasterObject.getNomineesEntityList();
                        final List<Image> items = new ArrayList<>();
                        int count = 0;
                        if (null == nomineesEntityList) {
                            Snackbar.make(parent_view, "No Nominees Set for this Poll", Snackbar.LENGTH_LONG).show();
                            showCustomDialog("No Nominees Set for this Poll", null);
                            return;
                        }
                        for (NomineesEntity nomineesEntity1 : nomineesEntityList) {
                            final Image image = new Image();
                            image.name = (nomineesEntity1.getNomineeName());
                            image.url = nomineesEntity1.getNomineeImage();
                            image.counter = (count);
                            image.brief = nomineesEntity1.getNomineesDescription();
                            items.add(image);
                            count++;
                        }

                        //set data and list adapter
                        mAdapter = new AdapterGridTwoLineLight(BottomSheetFloating.this, items, voteBy);
                        recyclerView.setAdapter(mAdapter);
                        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                int asad = dx;
                                super.onScrolled(recyclerView, dx, dy);
                                if (dy > 0) {
                                    int asads = dx;
                                    // Scrolling up
                                } else {
                                    int assad = dx;
                                    // Scrolling down
                                }


                            }

                            @Override
                            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);

//                                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
//                                    // Do something
//                                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
//                                    // Do something
//                                } else {
//                                    // Do something
//                                }
                            }
                        });


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
//                                    if(voteBy==2) {
                                showDialogImageFull(imageView.getDrawable(), nomineeName);
                                //  }
                                Snackbar.make(parent_view, albumName.getText(), Snackbar.LENGTH_LONG).show();

                            }

                            @Override
                            public List<Image> onLongItemClick(final RecyclerView.ViewHolder view, List<Image> obj, int position) {
                                List<Image> images = obj;
                                RecyclerViewPositionHelper mRecyclerViewHelper = RecyclerViewPositionHelper.createHelper(recyclerView);
                                int a, b, c, d;
                                a = mRecyclerViewHelper.findFirstCompletelyVisibleItemPosition();
                                b = mRecyclerViewHelper.findFirstVisibleItemPosition();
                                c = mRecyclerViewHelper.findLastCompletelyVisibleItemPosition();
                                d = mRecyclerViewHelper.findLastVisibleItemPosition();
                                int selectedCurrentPosition = position - b;
                                if (voteType == 2 | voteType == 3) {
                                    for (int count = 0; count <= recyclerView.getChildCount() - 1; count++) {
                                        View view1 = (View) recyclerView.getChildAt(count);
                                        MaterialRippleLayout materialRippleLayout = (MaterialRippleLayout) view1;
                                        LinearLayout layout = (LinearLayout) materialRippleLayout.getChildAt(0);
                                        LinearLayout layout1 = (LinearLayout) layout.getChildAt(1);
                                        final TextView counter = (TextView) layout1.getChildAt(2);
                                        Drawable[] dd = counter.getCompoundDrawables();


                                        if (dd[0] == null && selectedCurrentPosition == count) {
                                            counter.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done, 0, 0, 0);
                                            obj.get(position).selected = 1;
                                            obj.get(position).voteType = 2;
                                            if (voteType == 2) {
                                                for (int da = 0; da < items.size(); da++) {
                                                    if (da != position) {
                                                        obj.get(da).selected = 0;
                                                        //counter.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                                    }
                                                }
                                            }

                                        } else if (voteType == 3 && counter.getCompoundDrawables()[0] != null && selectedCurrentPosition == count) {
                                            counter.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                            obj.get(position).selected = 0;

                                        } else if (voteType == 2 && counter.getCompoundDrawables()[0] != null) {
                                            counter.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                        }
                                        // obj.get(position).counter = count;

                                    }
                                }
                                String sdf = "df";
//                                else if (voteType == 3) {
//                                    View view1 = (View) recyclerView.getChildAt(view.getPosition());
//                                    MaterialRippleLayout materialRippleLayout = (MaterialRippleLayout) view1;
//                                    LinearLayout layout = (LinearLayout) materialRippleLayout.getChildAt(0);
//                                    LinearLayout layout1 = (LinearLayout) layout.getChildAt(1);
//                                    final TextView counter = (TextView) layout1.getChildAt(2);
//                                    Drawable[] dd = counter.getCompoundDrawables();
//                                    if (dd[0] == null) {
//                                        counter.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done, 0, 0, 0);
//                                    } else {
//                                        counter.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                                    }
//
//                                }
                                return obj;
                            }
                        });


                        if (voteType == 1) {
                            ItemTouchHelper.Callback callback = new DragItemTouchHelper(mAdapter);
                            mItemTouchHelper = new ItemTouchHelper(callback);
                            mItemTouchHelper.attachToRecyclerView(recyclerView);

                        }
                        final int[] overallXScroll = new int[1];
                        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                super.onScrolled(recyclerView, dx, dy);

                                overallXScroll[0] = overallXScroll[0] + dx;
                                String sad = "sd";


                            }
                        });


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
                params.put("voteID", String.valueOf(voteId));
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
        Tools.setSystemBarColor(this, R.color.transparent);
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
