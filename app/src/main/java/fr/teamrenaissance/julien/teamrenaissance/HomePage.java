package fr.teamrenaissance.julien.teamrenaissance;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.teamrenaissance.julien.teamrenaissance.utils.MyFragmentAdapter;

public class HomePage extends AppCompatActivity {
    public static final String TAG = "HomeActivity";

    public static final String []sTitle = new String[]{"Accueil","Emprunter","Mes Prêts","Profil"};
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        initView();

        //In the latest versions of Android when using the compat library for toolbar,in order to get the menu items
        // to display in the toolbar you must do the following:
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    // SHOW ITEM TO THE APP BAR
    /*@Override
    public boolean onCreateOptionsMenu(android.view.Menu menu)
    {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }*/

    //handling click events
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Intent intent = new Intent();
                intent.setClass(this,Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    //DOUBLE CLICK TO QUIT
    long SystemTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis()- SystemTime < 1000 && SystemTime != 0){
                Intent intent = new Intent();
                intent.setAction("ExitApp");
                intent.setClass(this,Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }else{
                SystemTime = System.currentTimeMillis();
                Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(sTitle[0]));
        tabLayout.addTab(tabLayout.newTab().setText(sTitle[1]));
        tabLayout.addTab(tabLayout.newTab().setText(sTitle[2]));
        tabLayout.addTab(tabLayout.newTab().setText(sTitle[3]));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i(TAG,"onTabSelected:"+tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(Accueil.newInstance());
        fragments.add(Emprunter.newInstance());
        fragments.add(Mesprets.newInstance());
        fragments.add(Profil.newInstance());

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),fragments, Arrays.asList(sTitle));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG,"select page:"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
