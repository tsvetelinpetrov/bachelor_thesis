using Microsoft.AspNetCore.Mvc;
using MyTU_api.Services;

namespace MyTU_api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class BuildingController : ControllerBase
    {
        private readonly IBuildingService _buildingService;
        public BuildingController(IBuildingService buildingService)
        {
            _buildingService = buildingService;
        }

        // GET: api/<BuildingController>
        [HttpGet]
        public async Task<IActionResult> Get()
        {
            var buildings = await _buildingService.Get();
            return Ok(buildings);
        }
    }
}
