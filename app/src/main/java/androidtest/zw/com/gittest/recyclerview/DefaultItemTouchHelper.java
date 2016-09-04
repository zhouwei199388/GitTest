package androidtest.zw.com.gittest.recyclerview;

/**
 * Created by 15090 on 2016/9/4.
 */
public class DefaultItemTouchHelper extends MyItemTouchHelper {

    ItemTouchCallback mItemTouchCallBack;

    public DefaultItemTouchHelper(ItemTouchCallback.OnItemTouchCallBackListener listener) {
        super(new ItemTouchCallback(listener));
        mItemTouchCallBack = (ItemTouchCallback) getCallBack();
    }

    public void setDragEnable(boolean canDrag){
        mItemTouchCallBack.setCanDrag(canDrag);
    }

    public void setSwipeEnable(boolean canSwipe){
        mItemTouchCallBack.setCanSwipe(canSwipe);
    }
}
