package dupd.com.smartbag.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import dupd.com.smartbag.Entities.RFIDEntity;
import dupd.com.smartbag.Entities.TimeTableEntity;
import dupd.com.smartbag.Entities.WeatherDataEntity;
import dupd.com.smartbag.ExampleDialog;
import dupd.com.smartbag.Listners.OnRFIDEntityChangeListener;
import dupd.com.smartbag.Listners.OnTimeTableEntityChangeListener;
import dupd.com.smartbag.Listners.OnWeatherDataEntityChangeListener;
import dupd.com.smartbag.R;
import dupd.com.smartbag.Utilities.RFIDUtility;
import dupd.com.smartbag.Utilities.TimeTableUtility;
import dupd.com.smartbag.Utilities.WeatherDataUtility;

public class MainActivity extends AppCompatActivity {
    TextView bookname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("rfid");

        bookname = (TextView)findViewById(R.id.book);

        RFIDUtility rfidUtility = new RFIDUtility();
        rfidUtility.setOnRFIDEntityChangeListener(new OnRFIDEntityChangeListener() {
            @Override
            public void OnDataChenged(List<RFIDEntity> newRFIDEntity) {
                TimeTableEntity timeTableEntity = new TimeTableEntity("Monday",newRFIDEntity);
                TimeTableUtility timeTableUtility = new TimeTableUtility();
                timeTableUtility.addTimeTableEntity(timeTableEntity.getDay(),timeTableEntity);
                timeTableUtility.setOnTimeTableEntityChangeListener(new OnTimeTableEntityChangeListener() {
                    @Override
                    public void OnDataChenged(List<TimeTableEntity> newTimeTableEntities) {
                        //System.out.println("xxxxx time: "+newTimeTableEntities);
                    }
                });
            }
        });
        RFIDEntity rfidEntity = new RFIDEntity("123456","Maths",0);
        rfidUtility.addRFIDEntity("123456",rfidEntity);

        /*WeatherDataUtility weatherDataUtility = new WeatherDataUtility();
        WeatherDataEntity weatherDataEntity = new WeatherDataEntity("10","20");
        weatherDataUtility.addWeatherData("Today",weatherDataEntity);
        weatherDataUtility.setOnWeatherDataEntityChangeListener(new OnWeatherDataEntityChangeListener() {
            @Override
            public void OnDataChenged(List<WeatherDataEntity> newWeatherDataEntities) {
                System.out.println("xxxxx weather: "+newWeatherDataEntities);
            }
        });*/



        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {


                        RFIDEntity b = new RFIDEntity();
                        Toast.makeText(getApplicationContext(), "iiii", Toast.LENGTH_LONG).show();
                      //  System.out.println("datas" + ds);
                       // b.setBookname(ds.getValue(RFIDEntity.class).getBookname());
                       *//* b.setBookname(ds.getValue(RFIDEntity.class).getBookname());
                        bookname.setText(b.getBookname());

*//*



                      // System.out.println("further"+ds.getValue());
                       for(DataSnapshot d :ds.getChildren())
                       {
                       //    System.out.println("further"+d.getValue());
                           if(d.getKey().equals("bookname"))
                           {
                               if(d.getValue().toString().equals(""))
                               {
                                   Toast.makeText(getApplicationContext(),"Book is Blank",Toast.LENGTH_LONG).show();
                               //    System.out.println("blank");
                                   opendialog();
                               }
                               else
                               bookname.setText(d.getValue().toString());
                           }

                       }


                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {


                    RFIDEntity b = new RFIDEntity();
                    Toast.makeText(getApplicationContext(), "iiii", Toast.LENGTH_LONG).show();
                  //  System.out.println("datas" + ds);
                    // b.setBookname(ds.getValue(RFIDEntity.class).getBookname());
                       *//* b.setBookname(ds.getValue(RFIDEntity.class).getBookname());
                        bookname.setText(b.getBookname());

*//*



                    System.out.println("further"+ds.getValue());
                    for(DataSnapshot d :ds.getChildren())
                    {
                        System.out.println("further"+d.getValue());
                        if(d.getKey().equals("Book Name"))
                        {
                            if(d.getValue().toString().equals(""))
                            {
                                Toast.makeText(getApplicationContext(),"Book is Blank",Toast.LENGTH_LONG).show();
                                System.out.println("blank");
                                opendialog();
                            }
                            else
                                bookname.setText(d.getValue().toString());
                        }

                    }


                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


    }
    public void opendialog()
    {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(),"Dialog");
    }
}
