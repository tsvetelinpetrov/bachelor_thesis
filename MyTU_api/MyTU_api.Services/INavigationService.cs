using MyTU_api.Models;

namespace MyTU_api.Services
{
    public interface INavigationService
    {
        Task<NavigationRouteDto> GenerateRoute(int locationType1, int locationType2, int locationId1, int locationId2);
    }
}
