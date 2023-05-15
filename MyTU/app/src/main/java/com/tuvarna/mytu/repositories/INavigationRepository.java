package com.tuvarna.mytu.repositories;


import com.tuvarna.mytu.listeners.click.INavigationCallback;

public interface INavigationRepository {
    void getNavigationRoute(INavigationCallback callback, int startNodeId, int endNodeId);
}
