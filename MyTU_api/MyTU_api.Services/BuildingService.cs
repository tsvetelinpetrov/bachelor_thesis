using Microsoft.Extensions.Logging;
using MyTU_api.Models;
using MyTU_api.Repositories;

namespace MyTU_api.Services
{
    public class BuildingService : IBuildingService
    {
        private readonly IBuildingRepository _buildingRepository;
        private readonly ILogger _logger;

        public BuildingService(IBuildingRepository buildingRepository, ILogger<BuildingService> logger)
        {
            _buildingRepository = buildingRepository;
            _logger = logger;
        }

        public async Task<IEnumerable<BuildingDto>> Get()
        {
            var buildings = await _buildingRepository.Get();
            return buildings;
        }

        public Task<BuildingDetailsDto> GetDetails(int buildingId)
        {
            var buildingDetails = _buildingRepository.getDetails(buildingId);
            return buildingDetails;
        }
    }
}
