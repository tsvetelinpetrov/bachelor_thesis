using MyTU_api.Infrastructure;
using MyTU_api.Models;
using Microsoft.EntityFrameworkCore;
using MyTU_api.Entities;

namespace MyTU_api.Repositories
{
    public class BuildingRepository : IBuildingRepository
    {
        private readonly MyTU_apiDbContext _dbContext;

        public BuildingRepository(MyTU_apiDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public async Task<IEnumerable<BuildingDto?>> Get()
        {
            var buildings = await _dbContext.Buildings
                //.Include(c => c.Points)
                .Select(b => new BuildingDto
                {
                    Id = b.Id,
                    Label = b.Label,
                    Points = b.Points,
                    Floors = b.Floors,
                    FillColor = b.FillColor,
                    StrokeColor = b.StrokeColor,
                    GraphNode = b.GraphNode
                })
                //.AsNoTracking()
                .ToArrayAsync();

            return buildings;
        }

        public Task<BuildingDetailsDto?> getDetails(int buildingId)
        {
            var buildingDetails = _dbContext.BuildingDetails
                .Where(b => b.Building.Id == buildingId)
                .Select(b => new BuildingDetailsDto
                {
                    Id = b.Id,
                    Building = new Building
                    {
                        Id = b.Building.Id,
                        Label = b.Building.Label
                    },
                    ImageUrl = (b != null && b.ImageUrl != null && b.ImageUrl != "") ? "http://creativecode.tu-varna.bg/mapsource/images/" + b.ImageUrl : null,
                    Description = b.Description
                })
                //.AsNoTracking()
                .FirstOrDefaultAsync();
            return buildingDetails;
        }
    }
}
