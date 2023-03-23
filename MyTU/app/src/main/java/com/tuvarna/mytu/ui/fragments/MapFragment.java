package com.tuvarna.mytu.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuvarna.mytu.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.MapTileIndex;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {

    private MapView map = null;

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
        this.map.setTileSource(new OnlineTileSourceBase("USGS Topo", 0, 18, 256, ".png?key=yourkeyandnotthisstringorxxxxx",
                new String[] { "http://creativecode.tu-varna.bg/mapsource/" }) {

            @Override
            public String getTileURLString(long pMapTileIndex) {
                return getBaseUrl()
                        + MapTileIndex.getZoom(pMapTileIndex)
                        + "/" + MapTileIndex.getX(pMapTileIndex)
                        + "/" + MapTileIndex.getY(pMapTileIndex)
                        + mImageFilenameEnding;
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
}