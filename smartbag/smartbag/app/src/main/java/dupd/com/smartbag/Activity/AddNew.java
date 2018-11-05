package dupd.com.smartbag.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import dupd.com.smartbag.Adapters.BagAdapter;
import dupd.com.smartbag.Entities.RFIDEntity;
import dupd.com.smartbag.Listners.OnRFIDEntityChangeListener;
import dupd.com.smartbag.R;
import dupd.com.smartbag.Utilities.RFIDUtility;

public class AddNew extends AppCompatActivity {
    Spinner spinner;
    EditText bookname;
    Button submit;
    String selected;
    int inbag;
    String[] arraySpinner;
    List<RFIDEntity> rfid = new ArrayList<RFIDEntity>();
    private RFIDUtility rfidUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);


        bookname = findViewById(R.id.bookname);
        submit = findViewById(R.id.confirm);
        rfidUtility = new RFIDUtility();
        rfidUtility.setOnRFIDEntityChangeListener(new OnRFIDEntityChangeListener() {
            @Override
            public void OnDataChenged(List<RFIDEntity> newRFIDEntity) {
                rfid =newRFIDEntity;
                System.out.println("rfid"+rfid);
                spinner = findViewById(R.id.spinner3);
                arraySpinner = new String[rfid.size()];
                for(int i=0;i<rfid.size();i++)
                {
                    arraySpinner[i] = rfid.get(i).getId().toString();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, arraySpinner);
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        // your code here
                        selected = spinner.getSelectedItem().toString();
                        for(int i=0;i<rfid.size();i++)
                        {
                            if(rfid.get(i).getId().equals(selected))
                            {
                                inbag = rfid.get(i).getInBag();
                                bookname.setText(rfid.get(i).getBookName());
                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });
            }
        });


    //    selected = spinner.getSelectedItem().toString();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RFIDEntity rfidEntity = new RFIDEntity();
                rfidEntity.setId(selected);
                rfidEntity.setBookName(bookname.getText().toString());
                rfidEntity.setInBag(inbag);
                System.out.println("selected"+selected);
                rfidUtility.addRFIDEntity(selected,rfidEntity);
                rfidUtility.removeUpdating();
                Intent intent = new Intent(getApplicationContext(),TodayActivity.class);
                startActivity(intent);
            }
        });

    }
}
