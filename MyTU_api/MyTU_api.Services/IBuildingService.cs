using MyTU_api.Models;

namespace MyTU_api.Services
{
    public interface IBuildingService
    {
        /// <summary>
        /// Retrieves a collection of buildings.
        /// </summary>
        /// <returns>A task that represents the asynchronous operation. The task result contains the collection of BuildingDto objects.</returns>
        Task<IEnumerable<BuildingDto>> Get();

        /// <summary>
        /// Retrieves the details of a specific building.
        /// </summary>
        /// <param name="buildingId">The ID of the building.</param>
        /// <returns>A task that represents the asynchronous operation. The task result contains the BuildingDetailsDto object representing the building details.</returns>
        Task<BuildingDetailsDto> GetDetails(int buildingId);
    }

}
