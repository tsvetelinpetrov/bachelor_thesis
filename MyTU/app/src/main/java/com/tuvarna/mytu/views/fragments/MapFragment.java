package com.tuvarna.mytu.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuvarna.mytu.R;
import com.tuvarna.mytu.api.ApiRequest;
import com.tuvarna.mytu.models.Building;
import com.tuvarna.mytu.util.CustomMapView;

import org.osmdroid.api.IMapController;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {

    private CustomMapView map = null;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        this.map = view.findViewById(R.id.map);
        //map.setTileSource(TileSourceFactory.MAPNIK);

        // Create a custom tile source
        final ITileSource tileSource = new XYTileSource( "HOT", 1, 19, 256, ".png",
                new String[] {
                        "http://creativecode.tu-varna.bg/mapsource/"
                },"© OpenStreetMap contributors");
        this.map.setTileSource(tileSource);
        /*this.map.setTileSource(new OnlineTileSourceBase("USGS Topo", 0, 20, 256, ".png",
                new String[] { "http://creativecode.tu-varna.bg/mapsource/" }) {

            @Override
            public String getTileURLString(long pMapTileIndex) {
                return getBaseUrl()
                        + MapTileIndex.getZoom(pMapTileIndex)
                        + "/" + MapTileIndex.getX(pMapTileIndex)
                        + "/" + MapTileIndex.getY(pMapTileIndex)
                        + mImageFilenameEnding;
            }
        });*/

        this.map.addMapListener(new MapListener() {
            @Override
            public boolean onScroll(ScrollEvent event) {
                return false;
            }

            @Override
            public boolean onZoom(ZoomEvent event) {
                map.getOverlays().clear();
                map.drawAll(0);
                return false;
            }
        });

        this.map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        this.map.setMultiTouchControls(true);
        this.map.setMinZoomLevel(18.5);
        this.map.setTilesScaledToDpi(false);
        this.map.setScrollableAreaLimitLatitude(43.225418, 43.222118, 0);
        this.map.setScrollableAreaLimitLongitude(27.932039, 27.940900, 0);

        IMapController mapController = this.map.getController();
        mapController.setZoom(5.5);
        GeoPoint startPoint = new GeoPoint(43.223401, 27.935145);
        mapController.setCenter(startPoint);

        RotationGestureOverlay mRotationGestureOverlay = new RotationGestureOverlay(this.map);
        mRotationGestureOverlay.setEnabled(true);
        this.map.setMultiTouchControls(true);
        this.map.getOverlays().add(mRotationGestureOverlay);

        downloadData();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        this.map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onPause() {
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        this.map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }

    void downloadData() {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.100:5184/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiRequest request = retrofit.create(ApiRequest.class);
            Call<List<Building>> call = request.getBuildings();

            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (response.isSuccessful()) {
                        List<Building> buildings = (List<Building>) response.body();

                        map.setBuildings(buildings);
                        map.drawAll(0);
                        longInfo(String.valueOf(response.code()));
                    } else {
                        longInfo(String.valueOf(response.code()));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    //longInfo(t.getMessage());
                }
            });
        } catch (Exception e) {

        }

    }

    public static void longInfo(String str) {
        if (str.length() > 4000) {
            Log.i("19621795_", str.substring(0, 4000));
            longInfo(str.substring(4000));
        } else
            Log.i("19621795_", str);
    }
}