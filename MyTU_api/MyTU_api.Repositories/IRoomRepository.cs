using MyTU_api.Models;

namespace MyTU_api.Repositories
{
    public interface IRoomRepository
    {
        Task<RoomDetailsDto?> GetDetails(int roomId);
    }
}
