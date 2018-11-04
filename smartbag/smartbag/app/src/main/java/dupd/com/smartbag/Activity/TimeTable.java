package dupd.com.smartbag.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import dupd.com.smartbag.Adapters.TimeAdapter;
import dupd.com.smartbag.Entities.TimeTableEntity;
import dupd.com.smartbag.ExampleDialog;
import dupd.com.smartbag.Listners.OnTimeTableEntityChangeListener;
import dupd.com.smartbag.R;
import dupd.com.smartbag.Utilities.TimeTableUtility;

public class TimeTable extends AppCompatActivity {
    Spinner spinner;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<TimeTableEntity> timeTableEntities;
    Button add;
    String selected="Monday";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        timeTableEntities = new ArrayList<TimeTableEntity>();
        spinner = findViewById(R.id.spinner);
        String[] arraySpinner = new String[] {
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday","Saturday","Sunday"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        add = findViewById(R.id.add_book);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        selected = spinner.getSelectedItem().toString();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExampleDialog exampleDialog = new ExampleDialog();
                exampleDialog.show(getSupportFragmentManager(),"Dialog");
            }
        });

        TimeTableUtility timeTableUtility = new TimeTableUtility();
        timeTableUtility.setOnTimeTableEntityChangeListener(new OnTimeTableEntityChangeListener() {
            @Override
            public void OnDataChenged(List<TimeTableEntity> newTimeTableEntities) {
                System.out.println("yyyy: "+newTimeTableEntities);
                //TimeAdapter timeAdapter = new TimeAdapter()
            }
        });
    }
}
