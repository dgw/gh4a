package com.gh4a;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.ViewGroup;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.gh4a.fragment.ContentListFragment;
import com.gh4a.fragment.RepositoryFragment;

public class RepositoryNewActivity extends BaseSherlockFragmentActivity {

    private static final int NUM_ITEMS = 3;
    private String mRepoOwner;
    private String mRepoName;
    private RepositoryAdapter mAdapter;
    private ViewPager mPager;
    private ActionBar mActionBar;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_new);
        
        Bundle data = getIntent().getExtras().getBundle(Constants.DATA_BUNDLE);
        mRepoOwner = data.getString(Constants.Repository.REPO_OWNER);
        mRepoName = data.getString(Constants.Repository.REPO_NAME);
        
        mActionBar = getSupportActionBar();
        mAdapter = new RepositoryAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        
        mPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {}
            
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {}

            @Override
            public void onPageSelected(int arg0) {
                Log.d("ViewPager", "onPageSelected: " + arg0);
                mActionBar.getTabAt(arg0).select();
                }
        });
        
        mActionBar.setTitle(mRepoName);
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mActionBar.setDisplayShowTitleEnabled(true);
        Tab tab = mActionBar
                .newTab()
                .setText(R.string.about)
                .setTabListener(
                        new TabListener<SherlockFragmentActivity>(this, 0 + "", mPager));
        mActionBar.addTab(tab);
        
        tab = mActionBar
                .newTab()
                .setText(R.string.repo_files)
                .setTabListener(
                        new TabListener<SherlockFragmentActivity>(this, 1 + "", mPager));
        mActionBar.addTab(tab);
        
        tab = mActionBar
                .newTab()
                .setText(getResources().getQuantityString(R.plurals.commit, 2))
                .setTabListener(
                        new TabListener<SherlockFragmentActivity>(this, 2 + "", mPager));
        mActionBar.addTab(tab);
        
    }
    
    public class RepositoryAdapter extends FragmentStatePagerAdapter {

        public RepositoryAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            if (position == 0) {
                return RepositoryFragment.newInstance(RepositoryNewActivity.this.mRepoOwner,
                        RepositoryNewActivity.this.mRepoName);
            }
            else if (position == 1) {
                return ContentListFragment.newInstance(RepositoryNewActivity.this.mRepoOwner,
                        RepositoryNewActivity.this.mRepoName);
            }
            else if (position == 2) {
                return RepositoryFragment.newInstance(RepositoryNewActivity.this.mRepoOwner,
                        RepositoryNewActivity.this.mRepoName);
            }
            else {
                return RepositoryFragment.newInstance(RepositoryNewActivity.this.mRepoOwner,
                        RepositoryNewActivity.this.mRepoName);
            }
        }
        
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //
        }
    }
}