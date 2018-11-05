package dupd.com.smartbag.Utilities;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dupd.com.smartbag.Entities.RFIDEntity;
import dupd.com.smartbag.Listners.OnRFIDEntityChangeListener;

public class RFIDUtility {

        private List<RFIDEntity> rfidEntities;
        private OnRFIDEntityChangeListener onRFIDEntityChangeListener;
        private DatabaseReference mRFIDListDatabaseReference;
        private ValueEventListener valueEventListener;
        private ChildEventListener childEventListener;

        public RFIDUtility(){
            rfidEntities = new ArrayList<>();
            mRFIDListDatabaseReference = FirebaseDatabase.getInstance().getReference().child("rfid");
            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    rfidEntities = new ArrayList<>();
                    rfidEntities.clear();
                    Iterator<DataSnapshot> iEEIterables = dataSnapshot.getChildren().iterator();
                    while(iEEIterables.hasNext()){
                        DataSnapshot temp = iEEIterables.next();
                        RFIDEntity rfidEntity = temp.getValue(RFIDEntity.class);
                        rfidEntities.add(rfidEntity);
                    }
                    onRFIDEntityChangeListener.OnDataChenged(rfidEntities);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    rfidEntities.add(dataSnapshot.getValue(RFIDEntity.class));
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    rfidEntities.remove(dataSnapshot.getKey());
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            mRFIDListDatabaseReference.keepSynced(true);
            mRFIDListDatabaseReference.addValueEventListener(valueEventListener);
            mRFIDListDatabaseReference.addChildEventListener(childEventListener);

        }

        public RFIDUtility(OnRFIDEntityChangeListener onRFIDEntityChangeListener) {
            this();
            this.onRFIDEntityChangeListener = onRFIDEntityChangeListener;
        }

        public OnRFIDEntityChangeListener getOnRFIDEntityChangeListener() {
            return onRFIDEntityChangeListener;
        }

        public void setOnRFIDEntityChangeListener(OnRFIDEntityChangeListener onRFIDEntityChangeListener) {
            this.onRFIDEntityChangeListener = onRFIDEntityChangeListener;
        }

        public void removeUpdating(){
            mRFIDListDatabaseReference.removeEventListener(valueEventListener);
            mRFIDListDatabaseReference.removeEventListener(childEventListener);
        }

        public void startUpdating(){
            mRFIDListDatabaseReference.addValueEventListener(valueEventListener);
            mRFIDListDatabaseReference.addChildEventListener(childEventListener);
        }

        public List<RFIDEntity> getRfidEntities() {
            return rfidEntities;
        }

        public void setRfidEntities(List<RFIDEntity> rfidEntities) {
            this.rfidEntities = rfidEntities;
        }

        public void addRFIDEntity(String RFIDId,RFIDEntity rfidEntity){
            mRFIDListDatabaseReference.child(RFIDId).setValue(rfidEntity);
        }
    }
