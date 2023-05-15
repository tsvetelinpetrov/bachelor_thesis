using MyTU_api.Models;

namespace MyTU_api.Services
{
    public interface INavigationService
    {
        Task<NavigationRouteDto> GenerateRoute(int startNodeId, int endNodeId);
    }
}
