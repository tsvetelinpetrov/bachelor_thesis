using MyTU_api.Entities;

namespace MyTU_api.Models
{
    public class BuildingDetailsDto
    {
        public int Id { get; set; }
        public Building Building { get; set; }
        public String ImageUrl { get; set; }
        public String Description { get; set; }
    }
}
