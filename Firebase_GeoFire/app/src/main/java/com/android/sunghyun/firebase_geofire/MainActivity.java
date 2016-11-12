package com.android.sunghyun.firebase_geofire;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("path/to/geofire");
        GeoFire geoFire = new GeoFire(ref);

        String room_id1 = ref.push().getKey();
        geoFire.setLocation(room_id1, new GeoLocation(37.7355889, -122.4436973));

        String room_id2 = ref.push().getKey();
        geoFire.setLocation(room_id2, new GeoLocation(37.7553389, -122.4451953));

        String room_id3 = ref.push().getKey();
        //geoFire.setLocation(room_id3, new GeoLocation(37.7653689, -122.4452673));



        // 특정 범위내의 firebase 키값들 가져오기
        // creates a new query around [37.7832, -122.4056] with a radius of 0.6 kilometers
        // 중심좌표
        GeoLocation center = new GeoLocation(37.7832, -122.4056);
        float range = 5.0f;

        GeoQuery geoQuery = geoFire.queryAtLocation(center, range);

        // 좌표를 담을 그릇을 세팅
        final Set<String> nearby = new HashSet<>();

        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                //System.out.println(String.format("Key %s entered the search area at [%f,%f]", key, location.latitude, location.longitude));
                nearby.add(key);
            }

            @Override
            public void onKeyExited(String key) {
                //System.out.println(String.format("Key %s is no longer in the search area", key));
                nearby.remove(key);
            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {
                System.out.println(String.format("Key %s moved within the search area to [%f,%f]", key, location.latitude, location.longitude));

            }

            @Override
            public void onGeoQueryReady() {
                System.out.println("All initial data has been loaded and events have been fired!");
                System.out.println("room sount=" + nearby.size());
                for(String key : nearby) {
                    System.out.println("room_id="+key);
                }
            }

            @Override
            public void onGeoQueryError(DatabaseError error) {
                System.err.println("There was an error with this query: " + error);
            }
        });
    }
}
