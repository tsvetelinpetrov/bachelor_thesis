using MyTU_api.Models;

namespace MyTU_api.Repositories
{
    public interface IRoomRepository
    {
        /// <summary>
        /// Retrieves the details of a specific room.
        /// </summary>
        /// <param name="roomId">The ID of the room to retrieve details for.</param>
        /// <returns>A task that represents the asynchronous operation. The task result contains the nullable RoomDetailsDto object with the details of the room.</returns>
        Task<RoomDetailsDto?> GetDetails(int roomId);
    }
}
