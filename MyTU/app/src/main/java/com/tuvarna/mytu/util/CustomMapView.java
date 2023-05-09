package com.tuvarna.mytu.util;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

import com.tuvarna.mytu.models.Building;
import com.tuvarna.mytu.models.Floor;
import com.tuvarna.mytu.models.Label;
import com.tuvarna.mytu.models.Room;

import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polygon;

import java.util.ArrayList;
import java.util.List;

public class CustomMapView extends MapView {

    List<Building> buildings = new ArrayList<>();
    private int level = 0;

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
        polyline.setFillColor(room.getFillColor());
        polyline.setStrokeColor(room.getStrokeColor());
        polyline.setStrokeWidth(room.getStrokeWidth());

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
        mm.setTitle(label.getLabel());
        mm.setRotation(label.getRotation());
        mm.setTextIcon(label.getLabel());
        mm.setPosition(label.getLocation());

        this.getOverlays().add(mm);
        this.invalidate();
    }

    public void drawAll(int level) {
        for (Building b : this.buildings) {
            //map.getOverlays().clear();
            drawBuilding(b,level);
        }
    }

}
