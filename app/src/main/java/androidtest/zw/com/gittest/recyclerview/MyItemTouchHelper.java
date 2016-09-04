package androidtest.zw.com.gittest.recyclerview;

import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by 15090 on 2016/9/4.
 */
public class MyItemTouchHelper  extends ItemTouchHelper{


    private Callback mCallback;
    /**
     * Creates an ItemTouchHelper that will work with the given Callback.
     * <p/>
     * You can attach ItemTouchHelper to a RecyclerView via
     * {@link #attachToRecyclerView(RecyclerView)}. Upon attaching, it will add an item decoration,
     * an onItemTouchListener and a Child attach / detach listener to the RecyclerView.
     *
     * @param callback The Callback which controls the behavior of this touch helper.
     */
    public MyItemTouchHelper(Callback callback) {
        super(callback);
        this.mCallback = callback;
    }

    public Callback getCallBack(){
        return mCallback;
    }
}
