package com.material.components.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.material.components.R;
import com.material.components.helper.DragItemTouchHelper;
import com.material.components.model.Image;
import com.material.components.utils.ItemAnimation;
import com.material.components.utils.Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdapterGridTwoLineLight extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements DragItemTouchHelper.MoveHelperAdapter {

    private List<Image> items = new ArrayList<>();

    private OnLoadMoreListener onLoadMoreListener;
    RequestQueue queue1;

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;
    private AdapterListDrag.OnStartDragListener mDragStartListener = null;

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(items, fromPosition, toPosition);

        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Image obj, int position);
        void onLongItemClick(RecyclerView.ViewHolder view, Image obj, int position);

    }

    public void setDragListener(AdapterListDrag.OnStartDragListener dragStartListener) {
        this.mDragStartListener = dragStartListener;
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterGridTwoLineLight(Context context, List<Image> items) {
        this.items = items;
        ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView brief;
        public View lyt_parent;
        private TextView nomineeCounter;

        public OriginalViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
            name = (TextView) v.findViewById(R.id.name);
            brief = (TextView) v.findViewById(R.id.brief);
            lyt_parent = (View) v.findViewById(R.id.lyt_parent);
            nomineeCounter = (TextView) v.findViewById(R.id.nomineeCounter);


        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_image_two_line_light, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        queue1 = Volley.newRequestQueue(ctx);

        final Image obj = items.get(position);
        if (holder instanceof OriginalViewHolder) {
            final OriginalViewHolder view = (OriginalViewHolder) holder;
            view.name.setText(obj.name);
            view.brief.setText(obj.brief);
            view.nomineeCounter.setText(String.valueOf(position + 1));
            try {

// Retrieves an image specified by the URL, displays it in the UI.
                ImageRequest threadProfilePic = new ImageRequest(obj.url,
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap bitmap) {
                                Drawable d = new BitmapDrawable(null, bitmap);
                                view.image.setImageDrawable(d);

                            }
                        }, 0, 0, null,
                        new Response.ErrorListener() {
                            public void onErrorResponse(VolleyError error) {
                                // image.setImageDrw(R.drawable.cast3);
                            }
                        });
// Access the RequestQueue through your singleton class.
                queue1.add(threadProfilePic);
            } catch (Exception e) {
                //    circularImageView.setImageResource(R.drawable.cast3);
            }
            //Tools.displayImageOriginal(ctx, view.image, obj.image);
            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, items.get(position), position);
                    }
                }
            });

            view.lyt_parent.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongItemClick(holder, items.get(position), position);
                    return true;
                }
            });
            setAnimation(view.itemView, position);
        }
    }

    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, 4);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Image getItem(int position) {
        return items.get(position);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore(int current_page);
    }

}