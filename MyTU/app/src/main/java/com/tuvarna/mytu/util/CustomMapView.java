package com.tuvarna.mytu.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

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
    private List<MapStructurePolygon> buildingPolygons = new ArrayList<>();
    private MapStructurePolygon mSelectedPolygon;

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

        if(this.getZoomLevelDouble() < 20)
            toggleZoomOutOverlay();
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
        if ((int) this.getZoomLevelDouble() < label.getMinZoom()
                || (int) this.getZoomLevelDouble() > label.getMaxZoom()) {
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

    @SuppressLint("ClickableViewAccessibility")
    public void drawBuildingsPolygons() {
        MapStructurePolygon polygon = new MapStructurePolygon();
        polygon.setPoints(Arrays.asList(
                new GeoPoint(43.22428955948367, 27.934192697046626),
                new GeoPoint(43.224421549993075, 27.934200650041333),
                new GeoPoint(43.22440351797615, 27.934743532967985),
                new GeoPoint(43.22454915111607, 27.93475202501088),
                new GeoPoint(43.22455069824412, 27.934706248331366),
                new GeoPoint(43.224773355596156, 27.93471924011027),
                new GeoPoint(43.22473872956639, 27.93579481358563),
                new GeoPoint(43.22451630907547, 27.935781762303833),
                new GeoPoint(43.22453625926984, 27.93519372938917),
                new GeoPoint(43.22438903347902, 27.93518451609097),
                new GeoPoint(43.22438619590949, 27.93526636505956),
                new GeoPoint(43.22425547896848, 27.935258681656222)));
        polygon.getOutlinePaint().setStrokeWidth(0);
        polygon.setObjId(1);
        polygon.setType(1);
        buildingPolygons.add(polygon);
        this.getOverlayManager().add(polygon);
        polygon = new MapStructurePolygon();
        polygon.setPoints(Arrays.asList(
                new GeoPoint(43.22436904555783, 27.935269168063428),
                new GeoPoint(43.22427385148676, 27.935265156082778),
                new GeoPoint(43.22426817087379, 27.9354333867189),
                new GeoPoint(43.22425979731834, 27.93543297261357),
                new GeoPoint(43.22425786424133, 27.935488706702614),
                new GeoPoint(43.224249859707854, 27.935488467298114),
                new GeoPoint(43.224248041441804, 27.935537193559355),
                new GeoPoint(43.2242489228269, 27.93554736642281),
                new GeoPoint(43.22425085951879, 27.935557119646404),
                new GeoPoint(43.22425208724309, 27.935562909881355),
                new GeoPoint(43.22425623729685, 27.93557382589796),
                new GeoPoint(43.224258744620876, 27.935578880488293),
                new GeoPoint(43.22426339613895, 27.93558597589913),
                new GeoPoint(43.224271367698826, 27.93559449513819),
                new GeoPoint(43.22428099927847, 27.935601234591957),
                new GeoPoint(43.22428677476715, 27.935604319553164),
                new GeoPoint(43.22429412381632, 27.935605790842374),
                new GeoPoint(43.22432834902533, 27.935607766040533),
                new GeoPoint(43.224326867288326, 27.93565670339305),
                new GeoPoint(43.224320375906906, 27.93565618228675),
                new GeoPoint(43.22431751312308, 27.93573839522972),
                new GeoPoint(43.22431261381691, 27.93573785141288),
                new GeoPoint(43.22429472479223, 27.936269936230644),
                new GeoPoint(43.22438451620539, 27.936275092346705),
                new GeoPoint(43.224384423487, 27.936279456748792),
                new GeoPoint(43.22444196142635, 27.9362829597452),
                new GeoPoint(43.224447170096866, 27.936281589861352),
                new GeoPoint(43.224493005710876, 27.93628431872372),
                new GeoPoint(43.22449425395217, 27.936245552661433),
                new GeoPoint(43.22444380598073, 27.93624240771794),
                new GeoPoint(43.22445902324501, 27.935782159829984),
                new GeoPoint(43.22449332509108, 27.935784538394927),
                new GeoPoint(43.224494609249795, 27.93574575490959),
                new GeoPoint(43.224405882003815, 27.935739502131128),
                new GeoPoint(43.22440774937733, 27.935676868395404),
                new GeoPoint(43.22440058601515, 27.93567593110589),
                new GeoPoint(43.22440213025017, 27.935619441759712),
                new GeoPoint(43.224394863745324, 27.935618371782425),
                new GeoPoint(43.22439712903653, 27.935560888590174),
                new GeoPoint(43.22438981427516, 27.935560888590174),
                new GeoPoint(43.22439134616243, 27.935503391087053),
                new GeoPoint(43.224384222886286, 27.935503391087053),
                new GeoPoint(43.224385754773685, 27.935445420569692),
                new GeoPoint(43.22437859319971, 27.9354452628983),
                new GeoPoint(43.22438016338446, 27.935388238409388),
                new GeoPoint(43.22437323159298, 27.935387292380966),
                new GeoPoint(43.224374150725595, 27.935330583234816),
                new GeoPoint(43.22436721893344, 27.935329321863662),
                new GeoPoint(43.22436904679967, 27.935269135573947)
                ));
        polygon.getOutlinePaint().setStrokeWidth(0);
        polygon.setObjId(2);
        polygon.setType(1);
        buildingPolygons.add(polygon);
        this.getOverlayManager().add(polygon);


        this.setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // Check if the touch event is inside any of the polygons
                for (MapStructurePolygon polygon1 : buildingPolygons) {
                    if (polygon1.contains(event)) {
                        // Highlight the corners of the selected polygon
                        mSelectedPolygon = polygon1;
                        mSelectedPolygon.getOutlinePaint().setStrokeWidth(10);
                        mSelectedPolygon.getOutlinePaint().setColor(Color.parseColor(
                                Constants.MAP_POLYGON_SELECT_STROKE_COLOR));
                        view.invalidate();
                        return false;
                    }
                }

                // If the touch event is not inside any polygon, un-highlight the previous selected polygon
                /*if (mSelectedPolygon != null) {
                    mSelectedPolygon.setStrokeWidth(0);
                    mSelectedPolygon.setStrokeColor(Color.TRANSPARENT);
                    map.invalidate();
                    mSelectedPolygon = null;
                }*/
            }
            return false;
        });
    }

}
