package com.tuvarna.mytu.listeners.click;

import com.tuvarna.mytu.models.NavigationRoute;

public interface INavigationCallback {
    void onGetRouteSuccess(NavigationRoute route);
    void onGetRouteFailure(Throwable t);
}
