using MyTU_api.Models;

namespace MyTU_api.Repositories
{
    public interface IBuildingRepository
    {
        Task<IEnumerable<BuildingDto?>> Get();
        Task<BuildingDetailsDto?> getDetails(int buildingId);
    }
}
