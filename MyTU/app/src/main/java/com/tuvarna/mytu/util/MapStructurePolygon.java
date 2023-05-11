package com.tuvarna.mytu.util;

import org.osmdroid.views.overlay.Polygon;

public class MapStructurePolygon extends Polygon {
    int objId;
    int type;

    public MapStructurePolygon() {
        this.objId = 0;
        this.type = 0;
    }

    public MapStructurePolygon(int objId, int type) {
        this.objId = objId;
        this.type = type;
    }

    public int getObjId() {
        return objId;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
