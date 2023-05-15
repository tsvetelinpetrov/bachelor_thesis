package com.tuvarna.mytu.libraries;

import com.tuvarna.mytu.models.NavigationRoute;
import com.tuvarna.mytu.util.ArrowPolyline;
import com.tuvarna.mytu.util.CustomMapView;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;

public class RoutePainter {
    private final CustomMapView mapView;

    public RoutePainter(CustomMapView mapView) {
        this.mapView = mapView;
    }

    // Draw the route on the map
    public void drawRoute(NavigationRoute route) {
        removeRoute();
        ArrowPolyline routePolyline = mapView.getRoutePolyline();

        // Check if the map doesn't already contain the route
        if(!mapView.getOverlays().contains(routePolyline))
            mapView.getOverlays().add(routePolyline);

        // Create a list to store the points
        List<GeoPoint> newPoints = new ArrayList<>();

        // Add the geographic points that define the route
        for (var p : route.getNodes()) {
            if(p.getFloorLevel() != mapView.getLevel())
                continue;
            newPoints.add(new GeoPoint(p.getLatitude(), p.getLongitude()));
        }
        routePolyline.setPoints(newPoints);

        // Add the route to the map
        mapView.getOverlayManager().add(routePolyline);
        // Apply the polyline on the map
        mapView.invalidate();
    }

    // Remove the route from the map
    public void removeRoute() {
        ArrowPolyline routePolyline = mapView.getRoutePolyline();
        List<GeoPoint> newPoints = new ArrayList<>();
        routePolyline.setPoints(newPoints);

        if(mapView.getOverlays().contains(routePolyline))
            mapView.getOverlays().remove(routePolyline);
        mapView.invalidate();
    }

}
