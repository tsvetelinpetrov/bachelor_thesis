package com.tuvarna.mytu.listeners.callback;

import com.tuvarna.mytu.models.NavigationRoute;

public interface INavigationCallback {
    /**
     * Called when the navigation route is successfully retrieved.
     *
     * @param route The NavigationRoute object representing the retrieved route.
     */
    void onGetRouteSuccess(NavigationRoute route);

    /**
     * Called when an error occurs while retrieving the navigation route.
     *
     * @param t The Throwable object representing the encountered error.
     */
    void onGetRouteFailure(Throwable t);
}