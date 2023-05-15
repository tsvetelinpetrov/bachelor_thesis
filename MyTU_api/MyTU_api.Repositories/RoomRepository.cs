using MyTU_api.Entities;
using MyTU_api.Infrastructure;
using MyTU_api.Models;
using Microsoft.EntityFrameworkCore;

namespace MyTU_api.Repositories
{
    public class RoomRepository : IRoomRepository
    {
        private readonly MyTU_apiDbContext _dbContext;

        public RoomRepository(MyTU_apiDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public Task<RoomDetailsDto?> GetDetails(int roomId)
        {
            var roomDetails = _dbContext.RoomDetails
                .Where(r => r.Room.Id == roomId)
                .Select(r => new RoomDetailsDto
                {
                    Id = r.Id,
                    Room = new Room
                    {
                        Id = r.Room.Id,
                        Label = r.Room.Label
                    },
                    ImageUrl = (r != null && r.ImageUrl != null && r.ImageUrl != "") ? "http://creativecode.tu-varna.bg/mapsource/images/" + r.ImageUrl : null,
                    Description = r.Description
                })
                .FirstOrDefaultAsync();
            return roomDetails;
        }
    }
}
