using MyTU_api.Models;

namespace MyTU_api.Services
{
    public interface IRoomService
    {
        Task<RoomDetailsDto> GetDetails(int roomId);
    }
}
