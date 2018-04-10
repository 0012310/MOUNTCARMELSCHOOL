package gct.example.com.mountcarmelschool;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gct.example.com.mountcarmelschool.classes.CommonMethods;

public class ViewPagerActivity extends AppCompatActivity {

    TabLayout tabLayoutExploreActivity;
    ViewPager viewpagerExploreActivity;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        viewpagerExploreActivity = (ViewPager) findViewById(R.id.viewpagerExploreActivity);
        tabLayoutExploreActivity = (TabLayout) findViewById(R.id.tabLayoutExploreActivity);
        context = ViewPagerActivity.this;
        setupViewPager(viewpagerExploreActivity);
        tabLayoutExploreActivity.setupWithViewPager(viewpagerExploreActivity);
        viewpagerExploreActivity.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutExploreActivity));
        String e_mail = getIntent().getStringExtra("e_mail");
        CommonMethods.setPreference(context,"e_mail",e_mail);
        Log.d("e_mail",e_mail);
      //  Toast.makeText(context, "e_mail from viewpager"+e_mail, Toast.LENGTH_SHORT).show();
        viewpagerExploreActivity.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        viewpagerExploreActivity.setOffscreenPageLimit(4);   // its for giving response from cache thred while using volley
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Genral(), "\tGeneral\t");
        adapter.addFragment(new Parent(), "\tParent\t");
        adapter.addFragment(new Sibling(), "\tSibling\t");
        adapter.addFragment(new Medical(), "\tMedical\t");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
