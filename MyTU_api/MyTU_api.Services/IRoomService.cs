using MyTU_api.Models;

namespace MyTU_api.Services
{
    public interface IRoomService
    {
        /// <summary>
        /// Retrieves the details of a room specified by the room ID.
        /// </summary>
        /// <param name="roomId">The ID of the room.</param>
        /// <returns>A task that represents the asynchronous operation. The task result contains the RoomDetailsDto object representing the details of the room.</returns>
        Task<RoomDetailsDto> GetDetails(int roomId);
    }

}
