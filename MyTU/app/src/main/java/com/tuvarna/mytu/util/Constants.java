package com.tuvarna.mytu.util;

import org.osmdroid.util.GeoPoint;

public class Constants {
    public final static String BASE_API_URL = "http://192.168.0.100:5184/";


    // Map settings
    public final static String TILE_MAP_SOURCE = "custom";
    public final static String TILE_SOURCE_URL = "http://creativecode.tu-varna.bg/mapsource/";
    public final static double MAP_MIN_ZOOM_LEVEL = 18.5; // Default 18.5
    public final static double MAP_MAX_ZOOM_LEVEL = 22.910904337235227; // Default 22.910904337235227
    public final static double MAP_STARTING_ZOOM_LEVEL = 19.4; // Default 19.4
    public final static GeoPoint MAP_STARTING_POINT = new GeoPoint(43.223401, 27.935145); // Default 43.223401, 27.935145
    public final static String MAP_POLYGON_SELECT_STROKE_COLOR = "#FF9C00";

}
