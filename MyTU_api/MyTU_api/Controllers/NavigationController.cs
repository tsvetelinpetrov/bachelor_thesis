using Microsoft.AspNetCore.Mvc;
using MyTU_api.Models;
using MyTU_api.Services;

namespace MyTU_api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class NavigationController : ControllerBase
    {
        private readonly INavigationService _navigationService;
        public NavigationController(INavigationService navigationService)
        {
            _navigationService = navigationService;
        }

        // GET: api/<NavigationController>/getRoute/<locationType1>/<locationType2>/locationId1/locationId2
        [HttpGet("getRoute/{locationType1}/{locationType2}/{locationId1}/{locationId2}")]
        public async Task<IActionResult> GetRoute(int locationType1, int locationType2, int locationId1, int locationId2)
        {
            NavigationRouteDto route = await _navigationService.GenerateRoute(locationType1, locationType2, locationId1, locationId2);
            return Ok(route);
        }

    }
}
