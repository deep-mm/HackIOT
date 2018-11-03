package dupd.com.smartbag.Utilities;

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
import dupd.com.smartbag.Listners.OnRFIDEntityChangeListener;
import dupd.com.smartbag.Listners.OnTimeTableEntityChangeListener;

public class TimeTableUtility {

        private List<TimeTableEntity> timeTableEntities;
        private OnTimeTableEntityChangeListener onTimeTableEntityChangeListener;
        private DatabaseReference mTimeTableListDatabaseReference;
        private ValueEventListener valueEventListener;
        private ChildEventListener childEventListener;

        public TimeTableUtility(){
            timeTableEntities = new ArrayList<>();
            mTimeTableListDatabaseReference = FirebaseDatabase.getInstance().getReference().child("timetable");
            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(onTimeTableEntityChangeListener!=null)
                        onTimeTableEntityChangeListener.OnDataChenged(timeTableEntities);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    timeTableEntities.add(dataSnapshot.getValue(TimeTableEntity.class));
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    timeTableEntities.remove(dataSnapshot.getKey());
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            mTimeTableListDatabaseReference.keepSynced(true);
            mTimeTableListDatabaseReference.addValueEventListener(valueEventListener);
            mTimeTableListDatabaseReference.addChildEventListener(childEventListener);

        }

        public TimeTableUtility(OnTimeTableEntityChangeListener onTimeTableEntityChangeListener) {
            this();
            this.onTimeTableEntityChangeListener = onTimeTableEntityChangeListener;
        }

        public OnTimeTableEntityChangeListener getOnTimeTableEntityChangeListener() {
            return onTimeTableEntityChangeListener;
        }

        public void setOnTimeTableEntityChangeListener(OnTimeTableEntityChangeListener onTimeTableEntityChangeListener) {
            this.onTimeTableEntityChangeListener = onTimeTableEntityChangeListener;
        }

        public void removeUpdating(){
            mTimeTableListDatabaseReference.removeEventListener(valueEventListener);
            mTimeTableListDatabaseReference.removeEventListener(childEventListener);
        }

        public void startUpdating(){
            mTimeTableListDatabaseReference.addValueEventListener(valueEventListener);
            mTimeTableListDatabaseReference.addChildEventListener(childEventListener);
        }

        public List<TimeTableEntity> getTimeTableEntities() {
            return timeTableEntities;
        }

        public void setTimeTableEntities(List<TimeTableEntity> timeTableEntities) {
            this.timeTableEntities = timeTableEntities;
        }

        public void addTimeTableEntity(String TimeTableId,TimeTableEntity timeTableEntity){
            mTimeTableListDatabaseReference.child(TimeTableId).setValue(timeTableEntity);
        }
    }
