using MyTU_api.Entities;

namespace MyTU_api.Models
{
    public class RoomDetailsDto
    {
        public int Id { get; set; }
        public Room Room { get; set; }
        public String ImageUrl { get; set; }
        public String Description { get; set; }
    }
}
