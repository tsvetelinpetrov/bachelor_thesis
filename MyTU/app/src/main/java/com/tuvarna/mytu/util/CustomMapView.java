package com.tuvarna.mytu.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;

import com.tuvarna.mytu.R;
import com.tuvarna.mytu.models.Building;
import com.tuvarna.mytu.models.Floor;
import com.tuvarna.mytu.models.Label;
import com.tuvarna.mytu.models.Room;

import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polygon;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomMapView extends MapView {

    List<Building> buildings = new ArrayList<>();
    private int level = 0;
    private double lastZoomLevelBuildingDrawn = 0;

    public CustomMapView(Context context, MapTileProviderBase tileProvider, Handler tileRequestCompleteHandler, AttributeSet attrs) {
        super(context, tileProvider, tileRequestCompleteHandler, attrs);
    }

    public CustomMapView(Context context, MapTileProviderBase tileProvider, Handler tileRequestCompleteHandler, AttributeSet attrs, boolean hardwareAccelerated) {
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

    public CustomMapView(Context context, MapTileProviderBase aTileProvider, Handler tileRequestCompleteHandler) {
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

        if(this.getZoomLevelDouble() < 20)
            toggleZoomOutOverlay();
    }

    public int getLevel() {
        return this.level;
    }

    public void drawBuilding(Building building, int level) {
        Polygon polyline = new Polygon(this);
        polyline.setPoints(building.getPoints());
        polyline.getFillPaint().setColor(building.getFillColor());
        polyline.getOutlinePaint().setColor(building.getStrokeColor());
        polyline.getOutlinePaint().setStrokeWidth(4);
        this.getOverlays().add(polyline);
        this.invalidate();

        if ((int) this.getZoomLevelDouble() > 19) {
            for(Floor f:building.getFloors()){
                if(this.level==f.getLevel()) {
                    drawFloor(f);
                    break;
                }
            }
        }
        if (building.getLabel() != null) {
            building.getLabel().setLocation(polyline.getInfoWindowLocation());
            drawLabel(building.getLabel());
        }
    }

    public void drawRoom(Room room) {
        Polygon polyline = new Polygon(this);
        polyline.setPoints(room.getPoints());
        polyline.getFillPaint().setColor(room.getFillColor());
        polyline.getOutlinePaint().setColor(room.getStrokeColor());
        polyline.getOutlinePaint().setStrokeWidth(room.getStrokeWidth());

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
        if ((int) this.getZoomLevelDouble() < label.getMinZoom() || (int) this.getZoomLevelDouble() > label.getMaxZoom()) {
            return;
        }
        Marker mm = new Marker(this);
        mm.setTextLabelBackgroundColor(label.getBgColor());
        mm.setTextLabelFontSize(label.getSize());
        mm.setTextLabelForegroundColor(label.getFgColor());
        mm.setTitle(label.getText());
        mm.setRotation(label.getRotation());
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

    public void drawAll(int level) {
        for (Building b : this.buildings) {
            //map.getOverlays().clear();
            drawBuilding(b,level);
        }
    }

    public void drawAllBuildingLevels() {
        List<Integer> levels = new ArrayList<>(
                Arrays.asList(R.raw.full_zoom_out,
                        R.raw.full_0)
        );
        for (Integer level : levels) {
            drawBuildingsByImage(level);
            this.getOverlays().get(1).setEnabled(false);
        }
    }

    public void drawBuildingsByImage(int resourceId) {
        for(int i = 1; i < this.getOverlays().size(); i++) {
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

}
