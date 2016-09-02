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

import androidtest.zw.com.gittest.fragment.FragmentTestActivity;
import androidtest.zw.com.gittest.fragment.FragmentViewpagerActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        mWeightString = getResources().getStringArray(R.array.WeightString);
        ButterKnife.bind(this);
        mRecycerView.setLayoutManager(new LinearLayoutManager(this));
        mRecycerView.addItemDecoration(new RecyclerViewItemDecoration(this,
                RecyclerViewItemDecoration.VERTICAL_LIST));
        mRecycerView.setAdapter(new MainAdapter());
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


    class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {


        @Override
        public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .item_main_recyclerview, null);
            return new MainHolder(view);
        }

        @Override
        public void onBindViewHolder(MainHolder holder, int position) {
            holder.textView.setText(mWeightString[position]);
        }

        @Override
        public int getItemCount() {
            return mWeightString == null ? 0 : mWeightString.length;
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
