package dupd.com.smartbag.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import dupd.com.smartbag.Adapters.BagAdapter;
import dupd.com.smartbag.Entities.RFIDEntity;
import dupd.com.smartbag.Listners.OnRFIDEntityChangeListener;
import dupd.com.smartbag.R;
import dupd.com.smartbag.Utilities.RFIDUtility;

public class TodayActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<RFIDEntity> rfid = new ArrayList<RFIDEntity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        System.out.println("rfid1"+rfid);
        BagAdapter adapter = new BagAdapter(rfid);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        RFIDUtility rfidUtility = new RFIDUtility();
        new RFIDUtility().setOnRFIDEntityChangeListener(new OnRFIDEntityChangeListener() {
            @Override
            public void OnDataChenged(List<RFIDEntity> newRFIDEntity) {
                rfid =newRFIDEntity;
                System.out.println("rfid"+rfid);
                BagAdapter adapter = new BagAdapter(rfid);
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.today) {
            // Handle the camera action
        } else if (id == R.id.timetable) {

            Intent intent = new Intent(TodayActivity.this,TimeTable.class);
            startActivity(intent);
        } else if (id == R.id.newBook) {
            Intent intent = new Intent(TodayActivity.this,AddNew.class);
            startActivity(intent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
