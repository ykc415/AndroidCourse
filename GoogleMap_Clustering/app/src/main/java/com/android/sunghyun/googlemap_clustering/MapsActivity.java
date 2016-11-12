package com.android.sunghyun.googlemap_clustering;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import org.json.JSONException;

import java.io.InputStream;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        setMap();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setMap();
    }

    private void setMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        setCluster();


    }


    private ClusterManager<Item> clusterManager;

    private void setCluster() {
        LatLng center = new LatLng(51.503186 , -0.126446);
        float zoomLevel = 10f;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, zoomLevel));

        clusterManager = new ClusterManager<Item>(this, mMap);
        // 모양바꾸기
        // DefaultClusterRenderer 를 상속받아서 Custom 클래스 생성후에
        // clusterManager.setRenderer(커스텀클래스);

        // 클러스터(그룹)를 클릭했을 때 발생
        clusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<Item>() {
            @Override
            public boolean onClusterClick(Cluster<Item> cluster) {
                Toast.makeText(MapsActivity.this, "아이템 개수 = "+cluster.getSize(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // 마커단위를 클릭했을 때 발생
        clusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<Item>() {
            @Override
            public boolean onClusterItemClick(Item item) {
                Toast.makeText(MapsActivity.this, "마커좌표="+item.getPosition().latitude+","+
                        item.getPosition().longitude, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // 드래그가 완료된 시점에 동작하는 이벤트 리스너
        mMap.setOnCameraIdleListener(clusterManager);
        mMap.setOnMarkerClickListener(clusterManager);

        try {
            readItems();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void readItems() throws JSONException{
        InputStream inputStream = getResources().openRawResource(R.raw.radar_search);
        ItemReader itemReader = new ItemReader();
        List<Item> items = null;

        items = itemReader.read(inputStream);

        clusterManager.addItems(items);
    }

}
class Item implements ClusterItem {
    private LatLng coord;
    public Item(double lat, double lng) {
        coord = new LatLng(lat, lng);
    }

    @Override
    public LatLng getPosition() {
        return coord;
    }
}