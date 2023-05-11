package com.tuvarna.mytu.util;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
public class FloorSelector {
    private CustomMapView map = null;
    private Spinner spinner;
    private int floor = 0;

    public FloorSelector(CustomMapView map, Spinner spinner, int floor) {
        this.map = map;
        this.spinner = spinner;
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void selection(){
        spinner.setSelection(1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch ((int) spinner.getSelectedItemId()) {
                    case 0: {
                        Log.i("spinner", "You selected " + spinner.getSelectedItem().toString());
                        floor = -1;
                        break;
                    }
                    case 1: {
                        Log.i("spinner", "You selected " + spinner.getSelectedItem().toString());
                        floor = 0;
                        break;
                    }
                    case 2: {
                        Log.i("spinner", "You selected " + spinner.getSelectedItem().toString());
                        floor = 1;
                        break;
                    }
                    case 3: {
                        Log.i("spinner", "You selected " + spinner.getSelectedItem().toString());
                        floor = 2;
                        break;
                    }
                    case 4: {
                        Log.i("spinner", "You selected " + spinner.getSelectedItem().toString());
                        floor = 3;
                        break;
                    }
                    case 5: {
                        Log.i("spinner", "You selected " + spinner.getSelectedItem().toString());
                        floor = 4;
                        break;
                    }
                    case 6: {
                        Log.i("spinner", "You selected " + spinner.getSelectedItem().toString());
                        floor = 5;
                        break;
                    }
                    default:
                        break;
                }
                map.setLevel(floor);
                //activity.drawAll(level);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    


}
