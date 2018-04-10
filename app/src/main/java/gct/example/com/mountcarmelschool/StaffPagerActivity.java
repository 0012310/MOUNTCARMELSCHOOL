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

import java.util.ArrayList;
import java.util.List;

import gct.example.com.mountcarmelschool.classes.CommonMethods;

public class StaffPagerActivity extends AppCompatActivity {

    ViewPager viewpagerStaff;
    TabLayout tabLayoutStaff;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_pager);
        viewpagerStaff = (ViewPager) findViewById(R.id.viewpagerStaff);
        tabLayoutStaff = (TabLayout) findViewById(R.id.tabLayoutStaff);

        context = StaffPagerActivity.this;
        setupViewPager(viewpagerStaff);
        tabLayoutStaff.setupWithViewPager(viewpagerStaff);
        viewpagerStaff.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutStaff));
        String e_mail = CommonMethods.getPreference(context,"e_mail");
        //String e_mail = getIntent().getStringExtra("e_mail");
        CommonMethods.setPreference(context,"e_mail",e_mail);
        Log.d("e_mail",""+e_mail);

    }

    private void setupViewPager(ViewPager viewPager) {
        viewpagerStaff.setOffscreenPageLimit(2);   // its for giving response from cache while using volley
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new GeneralDetails(), "\tGeneral Details\t");
        adapter.addFragment(new OtherDetails(), "\tOther Details\t");
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