package com.tuvarna.mytu.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tuvarna.mytu.R;
import com.tuvarna.mytu.models.Building;
import com.tuvarna.mytu.models.Floor;
import com.tuvarna.mytu.models.Label;
import com.tuvarna.mytu.models.Room;

import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomMapView extends MapView {

    List<Building> buildings = new ArrayList<>();
    private int level = 0;
    private double lastZoomLevelBuildingDrawn = 0;
    private List<BuildingPolygon> buildingPolygons = new ArrayList<>();
    private List<RoomPolygon> roomPolygons = new ArrayList<>();
    private BuildingPolygon selectedBuildingPolygon = null;
    private RoomPolygon selectedRoomPolygon = null;

    public CustomMapView(Context context, MapTileProviderBase tileProvider,
                         Handler tileRequestCompleteHandler, AttributeSet attrs) {
        super(context, tileProvider, tileRequestCompleteHandler, attrs);
    }

    public CustomMapView(Context context, MapTileProviderBase tileProvider,
                         Handler tileRequestCompleteHandler, AttributeSet attrs,
                         boolean hardwareAccelerated) {
        super(context, tileProvider, tileRequestCompleteHandler, attrs, hardwareAccelerated);
    }

    public CustomMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomMapView(Context context) {
        super(context);
    }

    public CustomMapView(Context context, MapTileProviderBase aTileProvider) {
        super(context, aTileProvider);
    }

    public CustomMapView(Context context, MapTileProviderBase aTileProvider,
                         Handler tileRequestCompleteHandler) {
        super(context, aTileProvider, tileRequestCompleteHandler);
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public void addBuilding(Building building) {
        this.buildings.add(building);
    }

    public void setLevel(int level) {
        this.level = level;
        switch (level) {
            case 0:
                drawBuildingsByImage(R.raw.full_0);
                showBuildingFloor(R.raw.full_0);
                break;
            case 1:
                drawBuildingsByImage(R.raw.full_1);
                showBuildingFloor(R.raw.full_1);
                break;
            case 2:
                drawBuildingsByImage(R.raw.full_2);
                showBuildingFloor(R.raw.full_2);
                break;
            case 3:
                drawBuildingsByImage(R.raw.full_3);
                showBuildingFloor(R.raw.full_3);
                break;
        }

        if(this.getZoomLevelDouble() < 20){
            toggleZoomOutOverlay();
        }
    }

    public int getLevel() {
        return this.level;
    }

    public void drawBuilding(Building building, int level) {
        Polygon polyline = new Polygon(this);
        polyline.setPoints(building.getPoints());
        //polyline.getFillPaint().setColor(building.getFillColor());
        //polyline.getOutlinePaint().setColor(building.getStrokeColor());
        polyline.getOutlinePaint().setStrokeWidth(4);
        this.getOverlays().add(polyline);
        this.invalidate();

        if ((int) this.getZoomLevelDouble() > 19) {
            /*for(Floor f:building.getFloors()){
                if(this.level==f.getLevel()) {
                    drawFloor(f);
                    break;
                }
            }*/
        }
        if (building.getLabel() != null) {
            building.getLabel().setLocation(polyline.getInfoWindowLocation());
            drawLabel(building.getLabel());
        }
    }

    public void drawRoom(Room room) {
        Polygon polyline = new Polygon(this);
        polyline.setPoints(room.getPoints());
        //polyline.getFillPaint().setColor(room.getFillColor());
        //polyline.getOutlinePaint().setColor(room.getStrokeColor());
        //polyline.getOutlinePaint().setStrokeWidth(room.getStrokeWidth());

        this.getOverlays().add(polyline);
        this.invalidate();

        room.getLabel().setLocation(polyline.getInfoWindowLocation());
        drawLabel(room.getLabel());
    }

    public void drawFloor(Floor floor) {
        Polygon polyline=new Polygon(this);

        this.getOverlays().add(polyline);
        this.invalidate();

        for(Room r: floor.getRooms()){
            drawRoom(r);
        }
    }

    public void drawLabel(Label label) {

        Marker mm = new Marker(this);
        //mm.setTextLabelBackgroundColor(label.getBgColor());
        //mm.setTextLabelFontSize(label.getSize());
        //mm.setTextLabelForegroundColor(label.getFgColor());
        mm.setTitle(label.getText());
        //mm.setRotation(label.getRotation());
        mm.setTextIcon(label.getText());
        mm.setPosition(label.getLocation());

        this.getOverlays().add(mm);
        this.invalidate();
    }

    static GeoPoint getCentroid(List<GeoPoint> points) {
        if(points.isEmpty()) {
            return new GeoPoint(0.0, 0.0);
        } else if (points.size() == 1) {
            return points.get(0);
        }
        double lowerX = points.get(0).getLatitude();
        double lowerY = points.get(0).getLongitude();
        double higherX = points.get(0).getLatitude();
        double higherY = points.get(0).getLongitude();
        for (int i = 1; i < points.size(); i++) {
            if(points.get(i).getLatitude() > higherX) {
                higherX = points.get(i).getLatitude();
            }
            if(points.get(i).getLatitude() < lowerX) {
                lowerX = points.get(i).getLatitude();
            }
            if(points.get(i).getLongitude() > higherY) {
                higherY = points.get(i).getLongitude();
            }
            if(points.get(i).getLongitude() < lowerY) {
                lowerY = points.get(i).getLongitude();
            }
        }
        //Log.i("19621795_", "getCentroid: " + ((higherX + lowerX) / 2) + " " + ((higherY + lowerY) / 2));
        return new GeoPoint((higherX + lowerX) / 2, (higherY + lowerY) / 2);
    }

    public void drawBuildingsByImage(int resourceId) {
        for(int i = 0; i < this.getOverlays().size(); i++) {
            if(!(this.getOverlays().get(i) instanceof CustomGroundOverlay))
                continue;
            CustomGroundOverlay customGroundOverlay = (CustomGroundOverlay) this.getOverlays().get(i);
            if(customGroundOverlay.getResouceId() == resourceId) {
                return;
            }
        }
        try {
            InputStream inputStream = getResources().openRawResource(resourceId);
            GeoPoint overlayCenterPoint = new GeoPoint(43.224496, 27.935245);
            CustomGroundOverlay groundOverlay = new CustomGroundOverlay();
            groundOverlay.setPosition(overlayCenterPoint);

            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            Drawable d = new BitmapDrawable(getResources(), bitmap);
            groundOverlay.setImage(d.mutate());
            groundOverlay.setDimensions(170f);
            groundOverlay.setTransparency(0);
            groundOverlay.setBearing(2.5f);
            groundOverlay.setEnabled(false);
            groundOverlay.setResouceId(resourceId);
            this.getOverlays().add(groundOverlay);
            lastZoomLevelBuildingDrawn = this.getZoomLevelDouble();
        } catch (Exception e) {
            Log.e("19621795", e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public void showBuildingFloor(int floor) {
        for(int i = 0; i < this.getOverlays().size(); i++) {
            if(!(this.getOverlays().get(i) instanceof CustomGroundOverlay))
                continue;
            CustomGroundOverlay customGroundOverlay = (CustomGroundOverlay) this.getOverlays().get(i);
            customGroundOverlay.setEnabled(false);
            if(customGroundOverlay.getResouceId() == floor) {
                customGroundOverlay.setEnabled(true);
            }
        }
        this.invalidate();
    }

    public void toggleZoomOutOverlay() {
        for(int i = 0; i < this.getOverlays().size(); i++) {
            if(!(this.getOverlays().get(i) instanceof CustomGroundOverlay))
                continue;
            CustomGroundOverlay customGroundOverlay = (CustomGroundOverlay) this.getOverlays().get(i);
            customGroundOverlay.setEnabled(false);
            if(customGroundOverlay.getResouceId() == R.raw.full_zoom_out) {
                customGroundOverlay.setEnabled(true);
            }
        }
        this.invalidate();
    }

    public void addRotationGesture() {
        CustomRotationGestureOverlay mRotationGestureOverlay = new CustomRotationGestureOverlay(this);
        mRotationGestureOverlay.setEnabled(true);
        this.getOverlays().add(mRotationGestureOverlay);
    }

    public void generateBuildingPolygons(List<Building> buildings) {
        for(Building building : buildings) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View markerLayout = inflater.inflate(R.layout.custom_marker_layout, null);
            ImageView markerIcon = markerLayout.findViewById(R.id.marker_icon);
            TextView markerText = markerLayout.findViewById(R.id.marker_text);
            markerIcon.setImageResource(R.drawable.ic_calendar);
            markerIcon.setVisibility(View.GONE);
            markerText.setText(Html.fromHtml(building.getLabel().getMapText()));

            Bitmap bitmap = createBitmapFromView(markerLayout);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);

            Marker marker = new Marker(this);
            marker.setTextLabelBackgroundColor(Color.TRANSPARENT);
            marker.setTextLabelForegroundColor(Color.BLUE);
            marker.setIcon(bitmapDrawable);
            marker.setPosition(getCentroid(building.getPoints()));
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            marker.setInfoWindow(null);
            marker.setOnMarkerClickListener((marker1, mapView) -> false);

            BuildingPolygon polygon = new BuildingPolygon(building, marker);
            polygon.setInfoWindow(marker.getInfoWindow());
            polygon.setPoints(building.getPoints());
            polygon.getOutlinePaint().setStrokeWidth(0);
            polygon.getOutlinePaint().setColor(Color.TRANSPARENT);
            polygon.setOnClickListener((polygon1, mapView, eventPos) -> {
                BuildingPolygon polygon3 = (BuildingPolygon) polygon1;
                if(selectedBuildingPolygon != null) {
                    selectedBuildingPolygon.getOutlinePaint().setStrokeWidth(0);
                    selectedBuildingPolygon.getOutlinePaint().setColor(Color.TRANSPARENT);
                }
                selectedBuildingPolygon = (BuildingPolygon) polygon1;
                selectedBuildingPolygon.getOutlinePaint().setStrokeWidth(10);
                selectedBuildingPolygon.getOutlinePaint().setColor(Color.parseColor(
                        Constants.MAP_POLYGON_SELECT_STROKE_COLOR));
                selectedBuildingPolygon.getLabelMarker().setTextLabelForegroundColor(Color.parseColor(
                        Constants.MAP_POLYGON_SELECT_STROKE_COLOR));
                mapView.invalidate();
                return false;
            });
            buildingPolygons.add(polygon);
        }
    }

    @SuppressLint({"ClickableViewAccessibility", "UseCompatLoadingForDrawables"})
    public void drawBuildingsPolygons() {
        for(BuildingPolygon buildingPolygon : buildingPolygons) {
            this.getOverlayManager().add(buildingPolygon);
            this.getOverlayManager().add(buildingPolygon.getLabelMarker());
            this.invalidate();
        }
    }

    private Bitmap createBitmapFromView(View view) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.draw(canvas);
        return bitmap;
    }

    public void generateRoomPolygons(List<Building> buildings) {
        for(Building building : buildings) {
            for (Floor fl : building.getFloors()) {
                for (Room room : fl.getRooms()) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    View markerLayout = inflater.inflate(R.layout.custom_marker_layout, null);
                    ImageView markerIcon = markerLayout.findViewById(R.id.marker_icon);
                    TextView markerText = markerLayout.findViewById(R.id.marker_text);
                    markerIcon.setVisibility(View.GONE);
                    if(room.getLabel().getIcon() != 0) {
                        markerIcon.setImageResource(IconSelector.getIconId(room.getLabel().getIcon()));
                        markerIcon.setVisibility(View.VISIBLE);
                    }
                    if(room.getLabel().getIconColor().matches("^#[0-9a-fA-F]+$")) {
                        markerIcon.setColorFilter(Color.parseColor(room.getLabel().getIconColor()));
                    }
                    markerText.setText(Html.fromHtml(room.getLabel().getMapText()));

                    Bitmap bitmap = createBitmapFromView(markerLayout);
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);

                    Marker marker = new Marker(this);
                    marker.setTextLabelBackgroundColor(Color.TRANSPARENT);
                    marker.setTextLabelForegroundColor(Color.BLUE);
                    marker.setIcon(bitmapDrawable);
                    if(room.getLabel().getLatitude() == 0 && room.getLabel().getLongitude() == 0) {
                        marker.setPosition(getCentroid(room.getPoints()));
                    } else {
                        marker.setPosition(new GeoPoint(room.getLabel().getLatitude(), room.getLabel().getLongitude()));
                    }
                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                    marker.setInfoWindow(null);
                    marker.setOnMarkerClickListener((marker1, mapView) -> false);

                    RoomPolygon polygon = new RoomPolygon(room, fl.getLevel(), marker);
                    polygon.setInfoWindow(marker.getInfoWindow());
                    polygon.setPoints(room.getPoints());
                    polygon.getOutlinePaint().setStrokeWidth(0);
                    polygon.getOutlinePaint().setColor(Color.TRANSPARENT);
                    polygon.setOnClickListener((polygon1, mapView, eventPos) -> {
                        if(selectedRoomPolygon != null) {
                            selectedRoomPolygon.getOutlinePaint().setStrokeWidth(0);
                            selectedRoomPolygon.getOutlinePaint().setColor(Color.TRANSPARENT);
                        }
                        polygon1.getOutlinePaint().setStrokeWidth(10);
                        polygon1.getOutlinePaint().setColor(Color.parseColor(
                                Constants.MAP_POLYGON_SELECT_STROKE_COLOR));
                        selectedRoomPolygon = (RoomPolygon) polygon1;
                        mapView.invalidate();
                        return false;
                    });
                    roomPolygons.add(polygon);
                }
            }
        }

    }

    public void drawRoomPolygons(int floor) {
        for(RoomPolygon roomPolygon : roomPolygons) {
            if(roomPolygon.getFloor() == floor) {
                this.getOverlayManager().add(roomPolygon);
                this.getOverlayManager().add(roomPolygon.getLabelMarker());
                this.invalidate();
            }
        }
    }

    public void deselectBuildingPolygon() {
        if(selectedBuildingPolygon != null) {
            selectedBuildingPolygon.getOutlinePaint().setStrokeWidth(0);
            selectedBuildingPolygon.getOutlinePaint().setColor(Color.TRANSPARENT);
            selectedBuildingPolygon = null;
        }
    }

    public void deselectRoomPolygon() {
        if(selectedRoomPolygon != null) {
            selectedRoomPolygon.getOutlinePaint().setStrokeWidth(0);
            selectedRoomPolygon.getOutlinePaint().setColor(Color.TRANSPARENT);
            selectedRoomPolygon = null;
            this.invalidate();
        }
    }

    public void removeBuildingPolygons() {
        for(BuildingPolygon buildingPolygon : buildingPolygons) {
            this.getOverlayManager().remove(buildingPolygon.getLabelMarker());
            this.getOverlayManager().remove(buildingPolygon);
        }
        this.invalidate();
    }

    public void removeRoomPolygons() {
        for(RoomPolygon roomPolygon : roomPolygons) {
            this.getOverlayManager().remove(roomPolygon);
            this.getOverlayManager().remove(roomPolygon.getLabelMarker());
        }
        this.invalidate();
    }

}
