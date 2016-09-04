package androidtest.zw.com.gittest.recyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by 15090 on 2016/9/4.
 */
public class ItemTouchCallback extends ItemTouchHelper.Callback {

    private OnItemTouchCallBackListener mItemTochCallbackListener;

    private boolean isCanDrag = false;
    private boolean isCanSwipe = false;


    public ItemTouchCallback(OnItemTouchCallBackListener listener) {
        this.mItemTochCallbackListener = listener;
    }


    public void setCanDrag(boolean isCanDrag) {
        this.isCanDrag = isCanDrag;
    }

    public void setCanSwipe(boolean isCanSwipe) {
        this.isCanSwipe = isCanSwipe;
    }


    public boolean isLongPressDragEnabled() {
        return isCanDrag;
    }


    public boolean isItemViewSwipeEnabled() {
        return isCanSwipe;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipeFlag = 0;
            return makeMovementFlags(dragFlag, swipeFlag);
        } else if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int orientation = linearLayoutManager.getOrientation();
            int dragFlag = 0;
            int swipeFlag = 0;

            if (orientation == LinearLayoutManager.HORIZONTAL) {
                swipeFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            } else if (orientation == LinearLayoutManager.VERTICAL) {
                dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                swipeFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            }
            return makeMovementFlags(dragFlag, swipeFlag);
        }
        return 0;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
            target) {
        if (mItemTochCallbackListener != null) {
            return mItemTochCallbackListener.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        if (mItemTochCallbackListener != null) {
            mItemTochCallbackListener.onSwiped(viewHolder.getAdapterPosition());
        }

    }


    public interface OnItemTouchCallBackListener {
        void onSwiped(int position);

        boolean onMove(int touchPosition, int replacePosition);
    }

}
