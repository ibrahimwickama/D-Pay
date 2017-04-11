package com.example.wickerlabs.d_pay03;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {

    ViewPager viewPager;
    static TabHost tabHost;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViewPager();
        initTabHost();

                // function to set when notification is clicked to open a Specific Frag Tab
                // basing on a string key passed fron building the notification
               try {
                    if (getIntent().getAction().equals("OpenTab1")) {
                        tabHost.setCurrentTab(1);
                    } else {
                        tabHost.setCurrentTab(0);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
    }

    // Creating Tabs on the screen
    private void initTabHost() {
        tabHost=(TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        String[] tabNames={"MALIPO","KUPONI"};

        for(int i=0; i<tabNames.length; i++){
            TabHost.TabSpec tabSpec;
            tabSpec= tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(getApplicationContext()));
            tabHost.addTab(tabSpec);
        }
        tabHost.setOnTabChangedListener(this);
    }

    public class FakeContent implements TabHost.TabContentFactory {
        Context context;
        public FakeContent(Context mcontext) {
            context = mcontext;
        }

        @Override
        public View createTabContent(String tag) {

            View fakeView= new View(context);
            fakeView.setMinimumHeight(0);
            fakeView.setMinimumWidth(0);
            return fakeView;
        }
    }

    // method for Submitting and adding Fragments to Page view
    private void initViewPager() {

        viewPager= (ViewPager)findViewById(R.id.view_page);

        List<Fragment> listFragments = new ArrayList<Fragment>();
        listFragments.add(new MalipoTab());
        listFragments.add(new KuponiTab());

        FragmentAdapter myFragmentPagerAdapter = new FragmentAdapter(getSupportFragmentManager(), listFragments);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.setOnPageChangeListener(this);


    }

    // implemented methods from the mainActivity class
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int selectedItem) {
        tabHost.setCurrentTab(selectedItem);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onTabChanged(String tabId) {
        int selectedItem= tabHost.getCurrentTab();
        viewPager.setCurrentItem(selectedItem);

        HorizontalScrollView hScrollView=(HorizontalScrollView)findViewById(R.id.h_scrollView);
        View tabView= tabHost.getCurrentTabView();
        int scrollPos = tabHost.getLeft()-(hScrollView.getWidth()- tabView.getWidth())/2;
        hScrollView.smoothScrollTo(scrollPos,0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
            // Actions for actionBar icon top Right
        int id = item.getItemId();

        switch (id){
            case R.id.action_about:
                Toast.makeText(MainActivity.this, "abouts Click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_resetCards:
                    // CLears all cards in Kuponi Tab
              /*  KuponiTab.arrayList.clear();
                KuponiTab.recyclerAdapter.notifyDataSetChanged();  */

                    // includes the files created on sharedPreference
              /*  SharedPreferences sharedEzy =getSharedPreferences("EzyCards", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedEzy.edit();
                editor.clear().commit();

                SharedPreferences sharedTigo =getSharedPreferences("TigoCards", Context.MODE_PRIVATE);
                SharedPreferences.Editor editorT= sharedTigo.edit();
                editorT.clear().commit();

                SharedPreferences sharedVoda =getSharedPreferences("VodaCards", Context.MODE_PRIVATE);
                SharedPreferences.Editor editorV= sharedVoda.edit();
                editorV.clear().commit();

                SharedPreferences sharedAirTel =getSharedPreferences("AirTelCards", Context.MODE_PRIVATE);
                SharedPreferences.Editor editorAirTel= sharedAirTel.edit();
                editorAirTel.clear().commit();  */

                try{
                    this.dbHandler.deleteCard();
                    KuponiTab.recyclerAdapter.notifyDataSetChanged();
                }catch (Exception e){
                    e.printStackTrace();
                }

                Toast.makeText(MainActivity.this, "Cards Reset", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(null);
    }
}
