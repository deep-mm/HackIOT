package dupd.com.smartbag;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dupd.com.smartbag.Entities.RFIDEntity;

/**
 * Created by Dhaval on 04-11-2018.
 */

public class ExampleDialog extends AppCompatDialogFragment {
    EditText name;
    Spinner spinner;
    ArrayList<RFIDEntity> rfid = new ArrayList<RFIDEntity>();
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.open_dialog,null);
        name = view.findViewById(R.id.name);
        spinner = view.findViewById(R.id.spinner2);
        String[] arraySpinner = new String[rfid.size()];

        for(int i=0;i<rfid.size();i++)
        {
            arraySpinner[i] = rfid.get(i).getId();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        String selected = spinner.getSelectedItem().toString();

        for(int i=0;i<rfid.size();i++)
        {
            if(rfid.get(i).getId().equals(selected))
            {
                name.setText(rfid.get(i).getBookName());
            }
        }


        builder.setView(view).setTitle("Book Name")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });


        return builder.create();
    }
}
