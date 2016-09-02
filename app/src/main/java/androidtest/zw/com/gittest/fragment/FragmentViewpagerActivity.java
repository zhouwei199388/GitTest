package androidtest.zw.com.gittest.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import androidtest.zw.com.gittest.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 2016/9/1.
 */
public class FragmentViewpagerActivity extends FragmentActivity {


    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;


    private String[] mTabString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_for_viewpager);
        ButterKnife.bind(this);
        mTabString = getResources().getStringArray(R.array.TabTitle);

        mViewPager.setAdapter(new TabFragmentAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }


    class TabFragmentAdapter extends FragmentPagerAdapter {

        public TabFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new TestFragmentOne();
                    break;
                case 1:
                    fragment = new TestFragmentTwo();
                    break;
                case 2:
                    fragment = new TestFragmentThree();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return mTabString == null ? 0 : mTabString.length;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mTabString[position];
        }
    }
}
