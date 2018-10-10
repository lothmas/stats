package com.material.components.helper;

import android.graphics.Canvas;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.material.utility.RecyclerViewPositionHelper;

public class DragItemTouchHelper extends ItemTouchHelper.Callback {

    public static final float ALPHA_FULL = 1.0f;

    private final MoveHelperAdapter mAdapter;

    public DragItemTouchHelper(MoveHelperAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        // Set movement flags based on the layout manager
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        if (source.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        int sourcer = source.getAdapterPosition();
        int des = target.getAdapterPosition();
        // Notify the adapter of the move
        mAdapter.onItemMove(source.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            // Fade out the view as it is swiped out of the parent's bounds
            final float alpha = ALPHA_FULL - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX);
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        // We only want the active item to change
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder instanceof TouchViewHolder) {
                // Let the view holder know that this item is being moved or dragged
                TouchViewHolder itemViewHolder = (TouchViewHolder) viewHolder;
                itemViewHolder.onItemSelected();
            }
        }

        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerViewPositionHelper mRecyclerViewHelper = RecyclerViewPositionHelper.createHelper(recyclerView);
        int b = mRecyclerViewHelper.findFirstVisibleItemPosition();

        for (int count = 0; count < recyclerView.getChildCount()-1; count++) {
             View view = (View) recyclerView.getChildAt(count);
             MaterialRippleLayout materialRippleLayout = (MaterialRippleLayout) view;
             LinearLayout layout = (LinearLayout) materialRippleLayout.getChildAt(0);
             LinearLayout layout1 = (LinearLayout) layout.getChildAt(1);
             TextView counter = (TextView) layout1.getChildAt(2);
             counter.setText(String.valueOf(b+1));
             b++;
        }

        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setAlpha(ALPHA_FULL);

        if (viewHolder instanceof TouchViewHolder) {
            // Tell the view holder it's time to restore the idle state
            TouchViewHolder itemViewHolder = (TouchViewHolder) viewHolder;
            itemViewHolder.onItemClear();
        }
        recyclerView.getAdapter().notifyDataSetChanged();

    }

    public interface MoveHelperAdapter {
        boolean onItemMove(int fromPosition, int toPosition);
    }

    public interface TouchViewHolder {
        void onItemSelected();

        void onItemClear();
    }
}
