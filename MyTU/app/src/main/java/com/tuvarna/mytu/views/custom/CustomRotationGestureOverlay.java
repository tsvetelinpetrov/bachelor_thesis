package com.tuvarna.mytu.views.custom;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;

public class CustomRotationGestureOverlay extends RotationGestureOverlay {
    protected int resouceId;

    public CustomRotationGestureOverlay(MapView mapView) {
        super(mapView);
        this.resouceId = 0;
    }

    public int getResouceId() {
        return resouceId;
    }

    public void setResouceId(int resouceId) {
        this.resouceId = resouceId;
    }
}
