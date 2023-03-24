using MyTU_api.Models;

namespace MyTU_api.Services
{
    public interface IBuildingService
    {
        Task<IEnumerable<BuildingDto>> Get();
    }
}
