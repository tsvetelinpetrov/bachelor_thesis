package com.tuvarna.mytu.views.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.webkit.URLUtil;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.squareup.picasso.Picasso;
import com.tuvarna.mytu.R;
import com.tuvarna.mytu.models.Building;
import com.tuvarna.mytu.models.BuildingDetails;
import com.tuvarna.mytu.models.Room;
import com.tuvarna.mytu.models.RoomDetails;
import com.tuvarna.mytu.repositories.BuildingRepository;
import com.tuvarna.mytu.repositories.IBuildingsCallback;
import com.tuvarna.mytu.repositories.IRoomsCallback;
import com.tuvarna.mytu.repositories.RoomRepository;
import com.tuvarna.mytu.util.Constants;
import com.tuvarna.mytu.util.CustomMapView;
import com.tuvarna.mytu.util.FloorSelector;
import com.tuvarna.mytu.util.MapObjectClicker;

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
public class MapFragment extends Fragment implements IBuildingsCallback, IRoomsCallback, MapObjectClicker {

    BuildingRepository buildingRepository;
    RoomRepository roomRepository;

    private CustomMapView map = null;
    private double lastZoomLevel = 0;
    private IMapController mapController;
    private Spinner spinner;
    private ConstraintLayout progressBarHolder;
    private List<Building> buildings;

    LinearLayout bottomSheet;
    private BottomSheetBehavior mapBottomSheet;
    private Button navigateButton;
    private ConstraintLayout destinationChoseLayout;

    public MapFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("19621795", "On create view");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        progressBarHolder = view.findViewById(R.id.progress_bar_holder);
        progressBarHolder.setVisibility(View.VISIBLE);

        buildingRepository = new BuildingRepository();
        roomRepository = new RoomRepository();

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

        bottomSheet = view.findViewById(R.id.map_bottom_sheet);
        mapBottomSheet = BottomSheetBehavior.from(bottomSheet);
        //mapBottomSheet.setHideable(false);

        mapBottomSheet.setPeekHeight(360);
        mapBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);

        mapBottomSheet.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        // Do something when the bottom sheet is collapsed
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        // Do something when the bottom sheet is fully expanded
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        // Do something when the bottom sheet is half expanded
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        map.deselectBuildingPolygon();
                        map.deselectRoomPolygon();
                        map.invalidate();
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (slideOffset > 0.2 && slideOffset < 0.8) {
                    mapBottomSheet.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                } else if (slideOffset > 0.8) {
                    mapBottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    mapBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        mapBottomSheet.setHideable(true);
        mapBottomSheet.setState(BottomSheetBehavior.STATE_HIDDEN);

        navigateButton = view.findViewById(R.id.bottom_sheet_nav_button);
        destinationChoseLayout = view.findViewById(R.id.destination_chose_layout);
        destinationChoseLayout.setVisibility(View.GONE);
        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(map.getSelectedRoomPolygon() != null) {
                    Log.i("19621795_", map.getSelectedRoomPolygon().getRoom().getGraphNode().toString());
                    Animation animation = new AlphaAnimation(0f, 1f);
                    Animation animation2 = new AlphaAnimation(1f, 0f);
                    animation.setDuration(300);
                    animation2.setDuration(300);
                    destinationChoseLayout.startAnimation(animation);
                    destinationChoseLayout.setVisibility(View.VISIBLE);
                    bottomSheet.startAnimation(animation2);
                    bottomSheet.setVisibility(View.GONE);
                }
            }
        });

        ImageView destinationChoseLayoutBackButton = view.findViewById(R.id.destination_chose_layout_back_button);
        destinationChoseLayoutBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation1 = new AlphaAnimation(1f, 0.5f);
                animation1.setDuration(50);
                animation1.setRepeatCount(1);
                animation1.setRepeatMode(Animation.REVERSE);

                // Start animation
                v.startAnimation(animation1);
                bottomSheet.setVisibility(View.VISIBLE);
                Animation animation = new AlphaAnimation(1f, 0f);
                animation.setDuration(300);
                destinationChoseLayout.startAnimation(animation);
                destinationChoseLayout.setVisibility(View.GONE);

            }
        });

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
        map.generateBuildingPolygons(buildings, MapFragment.this);
        map.generateRoomPolygons(buildings, MapFragment.this);

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

    @Override
    public void onBuildingClick(Building building) {
        buildingRepository.getBuildingDetails(building.getId(), MapFragment.this);
    }

    @Override
    public void onBuildingDetailsReceived(BuildingDetails buildingDetails) {
        bottomSheet.setVisibility(View.VISIBLE);
        //mapBottomSheet.setHideable(false);
        mapBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
        TextView title = (TextView) bottomSheet.findViewById(R.id.bottom_sheet_title);
        TextView subTitle = (TextView) bottomSheet.findViewById(R.id.bottom_sheet_sub_title);
        TextView description = (TextView) bottomSheet.findViewById(R.id.bottom_sheet_description);
        ImageView image = (ImageView) bottomSheet.findViewById(R.id.bottom_sheet_image);
        if(URLUtil.isValidUrl(buildingDetails.getImageUrl())) {
            Picasso.get().load(buildingDetails.getImageUrl()).into(image);
            image.setVisibility(View.VISIBLE);
        } else {
            image.setVisibility(View.GONE);
        }
        title.setText(buildingDetails.getBuilding().getLabel().getText());
        subTitle.setText(buildingDetails.getSubTitle());
        description.setText(Html.fromHtml(buildingDetails.getDescription()));
    }

    @Override
    public void onBuildingDetailsReceiveFailure(Throwable t) {
        Log.i("19621795_", "Building Details NOT received." + t.getMessage());
        bottomSheet.setVisibility(View.INVISIBLE);
        mapBottomSheet.setHideable(true);
        mapBottomSheet.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public void onRoomClick(Room room) {
        Log.i("19621795_", "Room clicked");
        roomRepository.getRoomDetails(room.getId(), MapFragment.this);
    }

    @Override
    public void onRoomDetailsReceived(RoomDetails roomDetails) {
        bottomSheet.setVisibility(View.VISIBLE);
        //mapBottomSheet.setHideable(false);
        mapBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
        TextView title = (TextView) bottomSheet.findViewById(R.id.bottom_sheet_title);
        TextView subTitle = (TextView) bottomSheet.findViewById(R.id.bottom_sheet_sub_title);
        TextView description = (TextView) bottomSheet.findViewById(R.id.bottom_sheet_description);
        ImageView image = (ImageView) bottomSheet.findViewById(R.id.bottom_sheet_image);
        if(URLUtil.isValidUrl(roomDetails.getImageUrl())) {
            Picasso.get().load(roomDetails.getImageUrl()).into(image);
            image.setVisibility(View.VISIBLE);
        } else {
            image.setVisibility(View.GONE);
        }
        title.setText(roomDetails.getRoom().getLabel().getText());
        subTitle.setText(roomDetails.getSubTitle());
        description.setText(Html.fromHtml(roomDetails.getDescription()));
    }

    @Override
    public void onRoomDetailsReceiveFailure(Throwable t) {
        Log.i("19621795_", "Room Details NOT received." + t.getMessage());
        bottomSheet.setVisibility(View.INVISIBLE);
        mapBottomSheet.setHideable(true);
        mapBottomSheet.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    public static void longInfo(String str) {
        if (str.length() > 4000) {
            Log.i("19621795_", str.substring(0, 4000));
            longInfo(str.substring(4000));
        } else
            Log.i("19621795_", str);
    }
}