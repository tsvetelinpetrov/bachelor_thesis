using MyTU_api.Infrastructure;
using MyTU_api.Models;
using Microsoft.EntityFrameworkCore;

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
                    StrokeColor = b.StrokeColor
                })
                //.AsNoTracking()
                .ToArrayAsync();

            return buildings;
        }
    }
}
