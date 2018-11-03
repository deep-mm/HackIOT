package dupd.com.smartbag;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dupd.com.smartbag.Entities.RFIDEntity;

/**
 * Created by Dhaval on 04-11-2018.
 */

public class ExampleDialog extends AppCompatDialogFragment {
    EditText name;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.open_dialog,null);
        name = view.findViewById(R.id.name);
        builder.setView(view).setTitle("Book Name")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final FirebaseDatabase database = FirebaseDatabase.getInstance();
                        final DatabaseReference myRef = database.getReference().child("rfid");
                        myRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    RFIDEntity receiveRFIDEntity = ds.getValue(RFIDEntity.class);
                                    RFIDEntity b = new RFIDEntity();
                                    b.setBookName(name.getText().toString());
                                    b.setInBag(receiveRFIDEntity.getInBag());
                                    System.out.println("val" + b);
                                    myRef.setValue(b);
                                }

                                    /*System.out.println("further"+ds.getValue());
                                    DatabaseReference ref1 = myRef.child(ds.getValue().toString());
                                    for(DataSnapshot d :ds.getChildren())
                                    {
                                        System.out.println("ref1"+ref1);
                                        if(d.getKey().equals("Book Name"))
                                        {
                                            ref1.child(d.getKey().toString()).setValue(name.getText().toString());
                                        }
                                        System.out.println("further"+d.getValue());


                                    }*/

                                }


                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });


        return builder.create();
    }
}
