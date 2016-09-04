package androidtest.zw.com.gittest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidtest.zw.com.gittest.fragment.FragmentTestActivity;
import androidtest.zw.com.gittest.fragment.FragmentViewpagerActivity;
import androidtest.zw.com.gittest.recyclerview.DefaultItemTouchHelper;
import androidtest.zw.com.gittest.recyclerview.ItemTouchCallback;
import androidtest.zw.com.gittest.recyclerview.RecyclerViewItemDecoration;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 2016/8/31.
 */
public class MainActivity extends Activity {

    @BindView(R.id.main_recyclerView)
    RecyclerView mRecycerView;


    private String[] mWeightString;

    private List<String> mList = new ArrayList<>();

    private MainAdapter mainAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        mWeightString = getResources().getStringArray(R.array.WeightString);
        ButterKnife.bind(this);
        setList();
        mRecycerView.setLayoutManager(new LinearLayoutManager(this));
        mRecycerView.addItemDecoration(new RecyclerViewItemDecoration(this,
                RecyclerViewItemDecoration.VERTICAL_LIST));
        mainAdapter = new MainAdapter();
        mRecycerView.setAdapter(mainAdapter);
        DefaultItemTouchHelper itemTouchHelper = new DefaultItemTouchHelper(onItemTouchCallBackListener);
        itemTouchHelper.attachToRecyclerView(mRecycerView);
        itemTouchHelper.setDragEnable(true);
        itemTouchHelper.setSwipeEnable(true);
    }

    Intent intent;

    private void toWeightActivity(int position) {
        intent = new Intent();
        switch (position) {
            case 0:
                intent.setClass(this, FragmentTestActivity.class);
                break;
            case 1:
                intent.setClass(this, FragmentViewpagerActivity.class);
                break;
        }
        startActivity(intent);
    }


    private void setList() {
        for (int i = 0; i < mWeightString.length; i++) {
            mList.add(mWeightString[i]);
        }
    }


    private ItemTouchCallback.OnItemTouchCallBackListener onItemTouchCallBackListener = new ItemTouchCallback.OnItemTouchCallBackListener() {
        @Override
        public void onSwiped(int position) {
            if (mWeightString != null) {
                mList.remove(position);
                mainAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public boolean onMove(int touchPosition, int replacePosition) {
            if (mWeightString != null) {
                Collections.swap(mList, touchPosition, replacePosition);
                mainAdapter.notifyItemMoved(touchPosition, replacePosition);
                return true;
            }
            return false;
        }
    };


    class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {


        @Override
        public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .item_main_recyclerview, null);
            return new MainHolder(view);
        }

        @Override
        public void onBindViewHolder(MainHolder holder, int position) {
            holder.textView.setText(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        class MainHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public MainHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toWeightActivity(getLayoutPosition());
                    }
                });
                textView = (TextView) itemView.findViewById(R.id.tv_title);
            }
        }
    }


}
