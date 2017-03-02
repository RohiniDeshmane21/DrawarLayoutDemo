package com.example.android.drawarlayout;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Boolean isFabOpen = false;
    private com.getbase.floatingactionbutton.FloatingActionButton appoint,review,fav,share;
    com.getbase.floatingactionbutton.FloatingActionsMenu fab;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appoint = (com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.appoint);
        review = (com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.review);
        fav = (com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.fav);
        fab = (com.getbase.floatingactionbutton.FloatingActionsMenu) findViewById(R.id.fab_menu);
        share=(com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.fav);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);

        appoint.setOnClickListener(this);
        review.setOnClickListener(this);
        fav.setOnClickListener(this);
        share.setOnClickListener(this);

       // fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
      /*  fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab_menu:
                animateFAB();
                break;
        }
    }

    public void animateFAB(){
        if(isFabOpen){
            fab.startAnimation(rotate_backward);
            appoint.startAnimation(fab_close);
            review.startAnimation(fab_close);
            fav.startAnimation(fab_close);
            share.startAnimation(fab_close);
            appoint.setClickable(false);
            review.setClickable(false);
            fav.setClickable(false);
            share.setClickable(false);
            isFabOpen = false;
            // Log.d("Raj", "close");
        } else {
            fab.startAnimation(rotate_forward);
            appoint.startAnimation(fab_open);
            review.startAnimation(fab_open);
            fav.startAnimation(fab_open);
            share.startAnimation(fab_open);
            appoint.setClickable(true);
            review.setClickable(true);
            fav.setClickable(true);
            share.setClickable(true);
            isFabOpen = true;
            // Log.d("Raj","open");
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();

        if (id == R.id.nav_fav) {
            Favourite fragment = new Favourite();
            transaction.replace(R.id.content_frame, fragment);
            transaction.commit();
            setTitle("Favorite");

        } else if (id == R.id.nav_recentView) {
            RecentViewed fragment = new RecentViewed();
            transaction.replace(R.id.content_frame, fragment);
            transaction.commit();
            setTitle("Recently Viewed");
        } else if(id == R.id.been_there) {
            BeenThere fragment = new BeenThere();
            transaction.replace(R.id.content_frame, fragment);
            transaction.commit();
            setTitle("Been There");
        }  else if(id == R.id.nav_appointment) {
            AppointmentsBooked fragment = new AppointmentsBooked();
            transaction.replace(R.id.content_frame, fragment);
            transaction.commit();
            setTitle("Appointments Booked");
        } else if(id == R.id.nav_offers) {
            SavedOffers fragment = new SavedOffers();
            transaction.replace(R.id.content_frame, fragment);
            transaction.commit();
            setTitle("Saved Offers");
        }  else if(id == R.id.nav_share) {
            LooksShared fragment = new LooksShared();
            transaction.replace(R.id.content_frame, fragment);
            transaction.commit();
            setTitle("Looks Shared");
        } else if(id == R.id.nav_referFriend) {
            ReferFriend fragment = new ReferFriend();
            transaction.replace(R.id.content_frame, fragment);
            transaction.commit();
            setTitle("Refer a Friend");
        } else if(id == R.id.nav_suggestion) {
            SuggestBusiness fragment = new SuggestBusiness();
            transaction.replace(R.id.content_frame, fragment);
            transaction.commit();
            setTitle("Suggest a Bussiness");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}