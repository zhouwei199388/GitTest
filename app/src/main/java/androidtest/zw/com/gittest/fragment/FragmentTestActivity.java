package androidtest.zw.com.gittest.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidtest.zw.com.gittest.R;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2016/9/1.
 */
public class FragmentTestActivity extends FragmentActivity {

    @BindViews({R.id.tv_one, R.id.tv_two, R.id.tv_three})
    List<TextView> mTextViews;


    FragmentTransaction mTransaction;
    FragmentManager mFragmentManager;

    TestFragmentOne mFragmentOne;
    TestFragmentTwo mFragmentTwo;
    TestFragmentThree mFragmentThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ButterKnife.bind(this);
        initFragment();
    }

    private void initFragment() {
        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        mFragmentOne = new TestFragmentOne();
        mFragmentTwo = new TestFragmentTwo();
        mFragmentThree = new TestFragmentThree();
        mTransaction.add(R.id.fragment, mFragmentOne);
        mTransaction.commit();
    }

    @OnClick({R.id.tv_one, R.id.tv_two, R.id.tv_three})
    public void transactionFragment(View view) {
        mTransaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.tv_one:
                mTransaction.replace(R.id.fragment, mFragmentOne);
                break;
            case R.id.tv_two:
                mTransaction.replace(R.id.fragment, mFragmentTwo);
                break;
            case R.id.tv_three:
                mTransaction.replace(R.id.fragment, mFragmentThree);
                break;
        }
        mTransaction.commit();
    }
}
