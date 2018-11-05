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
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dupd.com.smartbag.Adapters.BagAdapter;
import dupd.com.smartbag.Adapters.TimeAdapter;
import dupd.com.smartbag.Entities.RFIDEntity;
import dupd.com.smartbag.Entities.TimeTableEntity;
import dupd.com.smartbag.Entities.WeatherDataEntity;
import dupd.com.smartbag.Listners.OnRFIDEntityChangeListener;
import dupd.com.smartbag.Listners.OnTimeTableEntityChangeListener;
import dupd.com.smartbag.Listners.OnWeatherDataEntityChangeListener;
import dupd.com.smartbag.R;
import dupd.com.smartbag.Utilities.RFIDUtility;
import dupd.com.smartbag.Utilities.TimeTableUtility;
import dupd.com.smartbag.Utilities.WeatherDataUtility;

public class TodayActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<RFIDEntity> rfid = new ArrayList<RFIDEntity>();
    private String day;
    private TextView tempText,humidityText,timeText,dayText;
    private RFIDUtility rfidUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tempText = (TextView) findViewById(R.id.temp_text);
        humidityText = (TextView) findViewById(R.id.humidity_text);
        timeText = (TextView) findViewById(R.id.time_text) ;
        dayText = (TextView) findViewById(R.id.day_text);

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
// full name form of the day
        day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String str = sdf.format(new Date());

        dayText.setText(day);
        timeText.setText(str);

        WeatherDataUtility weatherDataUtility = new WeatherDataUtility();
        weatherDataUtility.setOnWeatherDataEntityChangeListener(new OnWeatherDataEntityChangeListener() {
            @Override
            public void OnDataChenged(List<WeatherDataEntity> newWeatherDataEntities) {
                if(newWeatherDataEntities.size()!=0) {
                    tempText.setText("Temp: " + Double.toString(newWeatherDataEntities.get(0).getTemperature())+" C");
                    humidityText.setText("Humidity: " + Double.toString(newWeatherDataEntities.get(0).getHumidity()));
                }
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String str = sdf.format(new Date());
                timeText.setText(str);
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        System.out.println("rfid1"+rfid);
        BagAdapter adapter = new BagAdapter(rfid);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

  /*      TimeTableUtility timeTableUtility = new TimeTableUtility();
        timeTableUtility.setOnTimeTableEntityChangeListener(new OnTimeTableEntityChangeListener() {
            @Override
            public void OnDataChenged(List<TimeTableEntity> newTimeTableEntities) {
                System.out.println("yyyy: "+newTimeTableEntities);
                final ArrayList<RFIDEntity> rfidEntities = new ArrayList<>();
                final ArrayList<RFIDEntity> allrfidEntities = new ArrayList<>();

                for(int i=0;i<newTimeTableEntities.size();i++){
                    if(newTimeTableEntities.get(i).getDay().equalsIgnoreCase(day)){
                        List<RFIDEntity> rfidEntities1 = newTimeTableEntities.get(i).getRfidEntities();
                        rfidEntities.addAll(rfidEntities1);
                    }
                    System.out.println("yyyy: rfidEntities "+rfidEntities);


                }
            }
        });*/

        rfidUtility = new RFIDUtility();
        rfidUtility.setOnRFIDEntityChangeListener(new OnRFIDEntityChangeListener() {
            @Override
            public void OnDataChenged(List<RFIDEntity> newRFIDEntity) {
                System.out.println("yyyy: new "+newRFIDEntity);
                rfid.clear();
                rfid.addAll(newRFIDEntity);
                for(int k=0;k<rfid.size();k++){
                    if(rfid.get(k).getBookName().equals("")) {
                        rfid.remove(k);
                        k--;
                    }
                }
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
        }
         else if (id == R.id.newBook) {
            Intent intent = new Intent(TodayActivity.this,AddNew.class);
            startActivity(intent);
            rfidUtility.removeUpdating();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
