using MyTU_api.Models;

namespace MyTU_api.Services
{
    public interface INavigationService
    {
        /// <summary>
        /// Generates a navigation route between the specified start and end nodes.
        /// </summary>
        /// <param name="startNodeId">The ID of the start node.</param>
        /// <param name="endNodeId">The ID of the end node.</param>
        /// <returns>A task that represents the asynchronous operation. The task result contains the NavigationRouteDto object representing the generated navigation route.</returns>
        Task<NavigationRouteDto> GenerateRoute(int startNodeId, int endNodeId);
    }
}
