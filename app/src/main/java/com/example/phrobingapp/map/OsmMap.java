package com.example.phrobingapp.map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.phrobingapp.BuildConfig;
import com.example.phrobingapp.R;
import com.example.phrobingapp.databinding.ActivityOsmMapBinding;
import com.example.phrobingapp.menu.Input2;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayManager;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.concurrent.ExecutionException;

import static org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK;
import static org.osmdroid.views.CustomZoomButtonsController.Visibility.ALWAYS;
import static org.osmdroid.views.CustomZoomButtonsController.Visibility.NEVER;

public class OsmMap extends AppCompatActivity implements LocationListener {
    ActivityOsmMapBinding binding;
    private DisplayMetrics dm;
    private IMapController mapController;

    // Overlay
    protected MyLocationNewOverlay mLocationOverlay;
    private CompassOverlay mCompassOverlay;
    private ScaleBarOverlay mScaleBarOverlay;
    private RotationGestureOverlay mRotationGestureOverlay;
    public static boolean a;

    // Location
    private LocationManager lm;
    private Location currentLocation = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepare();
        setUpMap();
        setListener();
    }

    private void prepare() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_osm_map);
    }

    private void setUpMap() {
        mapController = binding.mapOSM.getController();
        //5.6 and newer
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
        setZoom();
        setCompassOverlay();
        setScaleBar();
        setRotation();
        centeringMap();

        //map.setTilesScaledToDpi(true);
        binding.mapOSM.setFlingEnabled(true);
        binding.mapOSM.setTileSource(new XYTileSource("HttpMapnik",
                0, 19, 256, ".png", new String[] {
                "http://a.tile.openstreetmap.org/",
                "http://b.tile.openstreetmap.org/",
                "http://c.tile.openstreetmap.org/" },
                "© OpenStreetMap contributors"));

        Overlay overlay = new Overlay() {
            @Override
            public boolean onDoubleTap(MotionEvent e, MapView mapView) {
                return true;
            }
        };
        final OverlayManager overlayManager = binding.mapOSM.getOverlayManager();
        if (!overlayManager.contains(overlay)) {
            overlayManager.add(overlay);
        }
    }

    private void setZoom() {
        binding.mapOSM.getZoomController().setVisibility(ALWAYS);
        binding.mapOSM.setMultiTouchControls(true);
        mapController.setZoom(10.0);
    }

    private void setCompassOverlay() {
        this.mCompassOverlay = new CompassOverlay(this, new InternalCompassOrientationProvider(this), binding.mapOSM);
        mCompassOverlay.enableCompass();
//        binding.mapOSM.getOverlays().add(this.mCompassOverlay);
    }

    private void setScaleBar() {
        dm = getResources().getDisplayMetrics();
        mScaleBarOverlay = new ScaleBarOverlay(binding.mapOSM);
        mScaleBarOverlay.setCentred(true);
        mScaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        binding.mapOSM.getOverlays().add(this.mScaleBarOverlay);
    }

    private void setRotation() {
        mRotationGestureOverlay = new RotationGestureOverlay(binding.mapOSM);
        mRotationGestureOverlay.setEnabled(true);
    }

    private void setListener() {
        binding.pilihTitik.setOnClickListener(v -> {
            String latitude = String.valueOf(binding.mapOSM.getMapCenter().getLatitude());
            String longitude = String.valueOf(binding.mapOSM.getMapCenter().getLongitude());
            Toast.makeText(this, String.valueOf(a), Toast.LENGTH_SHORT).show();
            if(a){
                Intent i = new Intent();
                String latlong = latitude + ", " + longitude;
                i.putExtra("result", latlong);
                setResult(Activity.RESULT_OK, i);
                finish();
            }else{
                Intent i = new Intent();
                String latlong = latitude + ", " + longitude;
                i.putExtra("result", latlong);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
        binding.icCenterMaps.setOnClickListener(v -> {
            centeringMap();
        });
    }

    private void centeringMap() {

        if (currentLocation == null) {
            double longitude = -7.304776;
            double latitude = 112.773144;
            GeoPoint point = new GeoPoint(
                    latitude,
                    longitude
            );
            mapController.animateTo(point);
        } else {
            GeoPoint point = new GeoPoint(
                    currentLocation
            );
            mapController.animateTo(point);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.mapOSM.onResume();
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            //this fails on AVD 19s, even with the appcompat check, says no provided named gps is available
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0l, 0f, this);
        }catch (Exception ex){}
        try{
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0l,0f,this);
        }catch (Exception ex){}
        mScaleBarOverlay.disableScaleBar();
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.mapOSM.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lm = null;
        currentLocation = null;

        mLocationOverlay = null;
        mCompassOverlay = null;
        mScaleBarOverlay = null;
        mRotationGestureOverlay = null;
//        btFollowMe = null;
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;
    }

    // deprecated
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
