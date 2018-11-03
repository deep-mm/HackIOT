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
import dupd.com.smartbag.Entities.WeatherDataEntity;
import dupd.com.smartbag.Listners.OnRFIDEntityChangeListener;
import dupd.com.smartbag.Listners.OnWeatherDataEntityChangeListener;

public class WeatherDataUtility {

        private List<WeatherDataEntity> weatherDataEntities;
        private OnWeatherDataEntityChangeListener onWeatherDataEntityChangeListener;
        private DatabaseReference mWeatherDataListDatabaseReference;
        private ValueEventListener valueEventListener;
        private ChildEventListener childEventListener;

        public WeatherDataUtility(){
            weatherDataEntities = new ArrayList<>();
            mWeatherDataListDatabaseReference = FirebaseDatabase.getInstance().getReference().child("weather");
            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(onWeatherDataEntityChangeListener!=null)
                        onWeatherDataEntityChangeListener.OnDataChenged(weatherDataEntities);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    weatherDataEntities.add(dataSnapshot.getValue(WeatherDataEntity.class));
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    weatherDataEntities.remove(dataSnapshot.getKey());
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            mWeatherDataListDatabaseReference.keepSynced(true);
            mWeatherDataListDatabaseReference.addValueEventListener(valueEventListener);
            mWeatherDataListDatabaseReference.addChildEventListener(childEventListener);

        }

        public WeatherDataUtility(OnWeatherDataEntityChangeListener onTrainListChangeListener) {
            this();
            this.onWeatherDataEntityChangeListener = onTrainListChangeListener;
        }

        public OnWeatherDataEntityChangeListener getOnWeatherDataEntityChangeListener() {
            return onWeatherDataEntityChangeListener;
        }

        public void setOnWeatherDataEntityChangeListener(OnWeatherDataEntityChangeListener onWeatherDataEntityChangeListener) {
            this.onWeatherDataEntityChangeListener = onWeatherDataEntityChangeListener;
        }

        public void removeUpdating(){
            mWeatherDataListDatabaseReference.removeEventListener(valueEventListener);
            mWeatherDataListDatabaseReference.removeEventListener(childEventListener);
        }

        public void startUpdating(){
            mWeatherDataListDatabaseReference.addValueEventListener(valueEventListener);
            mWeatherDataListDatabaseReference.addChildEventListener(childEventListener);
        }

        public List<WeatherDataEntity> getWeatherDataEntities() {
            return weatherDataEntities;
        }

        public void setWeatherDataEntities(List<WeatherDataEntity> weatherDataEntities) {
            this.weatherDataEntities = weatherDataEntities;
        }

        public void addWeatherData(String key, WeatherDataEntity weatherDataEntity){
            mWeatherDataListDatabaseReference.child(key).setValue(weatherDataEntity);
        }
    }
