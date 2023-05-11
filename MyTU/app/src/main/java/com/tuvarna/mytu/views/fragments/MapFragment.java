package com.tuvarna.mytu.views.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tuvarna.mytu.R;
import com.tuvarna.mytu.models.Building;
import com.tuvarna.mytu.repositories.BuildingRepository;
import com.tuvarna.mytu.repositories.BuildingsCallback;
import com.tuvarna.mytu.util.Constants;
import com.tuvarna.mytu.util.CustomMapView;
import com.tuvarna.mytu.util.FloorSelector;

import org.osmdroid.api.IMapController;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.overlay.MapEventsOverlay;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements BuildingsCallback {

    BuildingRepository buildingRepository;

    private CustomMapView map = null;
    private double lastZoomLevel = 0;
    private IMapController mapController;
    Spinner spinner;

    public MapFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("19621795", "On create view");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        buildingRepository = new BuildingRepository();

        this.map = view.findViewById(R.id.map);
        spinner = (Spinner) view.findViewById(R.id.floor_spinner);
        mapController = this.map.getController();
        //map.setTileSource(TileSourceFactory.MAPNIK);

        // Create a custom tile source
        final ITileSource tileSource = new XYTileSource( "HOT", 1, 19, 256, ".png",
                new String[] {
                        Constants.TILE_SOURCE_URL
                },"© Tsvetelin Petrov");
        this.map.setTileSource(tileSource);

        this.map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        this.map.setMultiTouchControls(true);
        this.map.setMinZoomLevel(18.5);
        //this.map.setMaxZoomLevel(22.910904337235227);
        this.map.setTilesScaledToDpi(false);
        this.map.setScrollableAreaLimitLatitude(43.225418, 43.222118, 0);
        this.map.setScrollableAreaLimitLongitude(27.932039, 27.940900, 0);

        mapController.setZoom(19.4);
        GeoPoint startPoint = new GeoPoint(43.223401, 27.935145);
        mapController.setCenter(startPoint);

        this.map.addRotationGesture();
        this.map.drawBuildingsByImage(R.raw.full_zoom_out);
        this.map.drawBuildingsByImage(R.raw.full_0);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.floor_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        FloorSelector fs=new FloorSelector(map,spinner,0);
        fs.selection();

        this.map.addMapListener(new MapListener() {
            @Override
            public boolean onScroll(ScrollEvent event) {
                return false;
            }

            @Override
            public boolean onZoom(ZoomEvent event) {
                double zoomLevel = event.getZoomLevel();
                //Log.i("19621795", "Zoom level: " + zoomLevel);

                if(zoomLevel > 20 && lastZoomLevel < 20) {
                    Log.i("19621795", "Change 1");
                    map.setLevel(map.getLevel());
                }

                if(zoomLevel < 20 && lastZoomLevel > 20) {
                    Log.i("19621795", "Change 2");
                    map.toggleZoomOutOverlay();
                }

                lastZoomLevel = zoomLevel;
                return false;
            }
        });

        MapEventsReceiver mReceive = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                //Toast.makeText(getContext(),p.getLatitude() + " - "+p.getLongitude(),Toast.LENGTH_LONG).show();
                Log.i("19621795_p", p.getLatitude() + ", "+p.getLongitude());

                return false;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false;
            }
        };


        MapEventsOverlay OverlayEvents = new MapEventsOverlay(getContext(), mReceive);
        map.getOverlays().add(OverlayEvents);

        map.drawBuildingsPolygons();

        //map.toggleZoomOutLayout(true);

        buildingRepository.getAllBuildings(this);

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
        Log.i("19621795_", "Resume");
        mapController.setZoom(19.4);
    }

    @Override
    public void onPause() {
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        this.map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
        Log.i("19621795_", "Pause");
    }

    @Override
    public void onBuildingsReceived(List<Building> buildings) {
        Log.i("19621795_", "Buildings received");
        for (Building building : buildings) {
            Log.i("19621795_", building.toString());
        }
    }

    @Override
    public void onBuildingsReceiveFailure(Throwable t) {
        Log.i("19621795_", "Buildings NOT received." + t.getMessage());
    }
}