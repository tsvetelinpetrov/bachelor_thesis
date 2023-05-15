package com.tuvarna.mytu.util;

import android.util.Log;

import com.tuvarna.mytu.libraries.RoutePainter;
import com.tuvarna.mytu.listeners.click.INavigationCallback;
import com.tuvarna.mytu.models.NavigationRoute;
import com.tuvarna.mytu.models.Room;
import com.tuvarna.mytu.repositories.NavigationRepository;
import com.tuvarna.mytu.views.fragments.MapFragment;

public class Navigation implements INavigationCallback {
    private MapFragment mapFragment;
    private Room startRoom;
    private Room endRoom;
    private NavigationRoute navigationRoute;
    private NavigationRepository navigationRepository;

    public Navigation(MapFragment mapFragment, Room startRoom, Room endRoom) {
        this.mapFragment = mapFragment;
        this.startRoom = startRoom;
        this.endRoom = endRoom;

        navigationRepository = new NavigationRepository();
    }

    public void navigateInit() {
        navigationRepository.getNavigationRoute(this, startRoom.getGraphNode().getId(),
                endRoom.getGraphNode().getId());
    }

    @Override
    public void onGetRouteSuccess(NavigationRoute route) {
        Log.i("19621795", "onGetRouteSuccess: " + route.getNodes());
        this.navigationRoute = route;
        this.prepareLayout();
        this.drawRoute();
    }

    @Override
    public void onGetRouteFailure(Throwable t) {
        Log.e("19621795", "onGetRouteFailure: " + t.getMessage());
    }

    private void prepareLayout() {
        mapFragment.hideDestinationChoseLayout();
        //mapFragment.hideBottomSheet();
        mapFragment.getMap().deselectRoomPolygon();
        mapFragment.getMap().deselectBuildingPolygon();
    }

    private void drawRoute() {
        RoutePainter routePainter = new RoutePainter(mapFragment.getMap());
        routePainter.drawRoute(navigationRoute);
    }


}
