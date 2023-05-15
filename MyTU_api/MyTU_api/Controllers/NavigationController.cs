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

        // GET: api/<NavigationController>/getRoute/startNodeId/endNodeId
        [HttpGet("getRoute/{startNodeId}/{endNodeId}")]
        public async Task<IActionResult> GetRoute(int startNodeId, int endNodeId)
        {
            NavigationRouteDto route = await _navigationService.GenerateRoute(startNodeId, endNodeId);
            return Ok(route);
        }

    }
}
