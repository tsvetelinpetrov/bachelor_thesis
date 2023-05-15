package com.tuvarna.mytu.repositories;

import com.tuvarna.mytu.listeners.callback.INavigationCallback;

public interface INavigationRepository {
    /**
     * Retrieves a navigation route between two nodes.
     *
     * @param callback      The callback to be invoked with the retrieved navigation route.
     * @param startNodeId   The ID of the starting node.
     * @param endNodeId     The ID of the end node.
     */
    void getNavigationRoute(INavigationCallback callback, int startNodeId, int endNodeId);
}