package com.tuvarna.mytu.views.fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
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
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.overlay.MapEventsOverlay;

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
    ConstraintLayout progressBarHolder;
    List<Building> buildings;

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

        progressBarHolder = view.findViewById(R.id.progress_bar_holder);
        progressBarHolder.setVisibility(View.VISIBLE);

        buildingRepository = new BuildingRepository();

        this.map = view.findViewById(R.id.map);
        spinner = (Spinner) view.findViewById(R.id.floor_spinner);
        mapController = this.map.getController();

        if(Constants.TILE_MAP_SOURCE == "mapnik") {
            map.setTileSource(TileSourceFactory.MAPNIK);
        } else if(Constants.TILE_MAP_SOURCE == "custom") {
            // Create a custom tile source
            final ITileSource tileSource = new XYTileSource( "HOT",
                    1, 19,256, ".png",
                    new String[] {
                            Constants.TILE_SOURCE_URL
                    },"© " + R.string.ts_petrov);
            this.map.setTileSource(tileSource);
        }

        this.map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        this.map.setMultiTouchControls(true);
        this.map.setMinZoomLevel(Constants.MAP_MIN_ZOOM_LEVEL);
        this.map.setMaxZoomLevel(Constants.MAP_MAX_ZOOM_LEVEL);
        this.map.setTilesScaledToDpi(false);
        this.map.setScrollableAreaLimitLatitude(43.225418, 43.222118, 0);
        this.map.setScrollableAreaLimitLongitude(27.932039, 27.940900, 0);

        mapController.setZoom(Constants.MAP_STARTING_ZOOM_LEVEL);
        GeoPoint startPoint = new GeoPoint(Constants.MAP_STARTING_POINT);
        mapController.setCenter(startPoint);

        this.map.addRotationGesture();
        this.map.drawBuildingsByImage(R.raw.full_zoom_out);
        this.map.drawBuildingsByImage(R.raw.full_0);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.floor_array, android.R.layout.simple_spinner_item);
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
                    map.setLevel(map.getLevel());
                    map.removeBuildingPolygons();
                    map.deselectRoomPolygon();
                    map.drawRoomPolygons(map.getLevel());
                }

                if(zoomLevel < 20 && lastZoomLevel > 20) {
                    map.toggleZoomOutOverlay();
                    map.removeRoomPolygons();
                    map.deselectBuildingPolygon();
                    map.drawBuildingsPolygons();
                }

                lastZoomLevel = zoomLevel;
                return false;
            }
        });

        MapEventsReceiver mReceive = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
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
        this.buildings = buildings;
        map.setBuildings(buildings);
        map.generateBuildingPolygons(buildings);
        map.generateRoomPolygons(buildings);

        map.drawBuildingsPolygons();
        map.drawRoomPolygons(0);
        map.removeRoomPolygons();
        progressBarHolder.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBuildingsReceiveFailure(Throwable t) {
        buildingRepository.getAllBuildings(MapFragment.this);
        Log.i("19621795_", "Buildings NOT received." + t.getMessage());
    }

    public static void longInfo(String str) {
        if (str.length() > 4000) {
            Log.i("19621795_", str.substring(0, 4000));
            longInfo(str.substring(4000));
        } else
            Log.i("19621795_", str);
    }
}