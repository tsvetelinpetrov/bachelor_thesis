using Microsoft.Extensions.Logging;
using MyTU_api.Models;
using MyTU_api.Repositories;

namespace MyTU_api.Services
{
    public class RoomService : IRoomService
    {
        private readonly IRoomRepository _roomRepository;
        private readonly ILogger _logger;

        public RoomService(IRoomRepository roomRepository, ILogger<RoomService> logger)
        {
            _roomRepository = roomRepository;
            _logger = logger;
        }

        public Task<RoomDetailsDto> GetDetails(int roomId)
        {
            var roomDetails = _roomRepository.GetDetails(roomId);
            return roomDetails;
        }
    }
}
