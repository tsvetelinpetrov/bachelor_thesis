using Microsoft.AspNetCore.Mvc;
using MyTU_api.Services;

namespace MyTU_api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class RoomController : ControllerBase
    {
        private readonly IRoomService _roomService;

        public RoomController(IRoomService roomService)
        {
            _roomService = roomService;
        }

        // GET api/<RoomController>/details/<RoomId>
        [HttpGet("details/{roomId}")]
        public async Task<IActionResult> GetDetails(int roomId)
        {
            var roomDetails = await _roomService.GetDetails(roomId);
            return Ok(roomDetails);
        }

    }
}
