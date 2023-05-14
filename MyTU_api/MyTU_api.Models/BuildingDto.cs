using MyTU_api.Entities;

namespace MyTU_api.Models
{
    public class BuildingDto
    {
        public int Id { get; set; }
        public Label Label { get; set; }
        public ICollection<BuildingPoint> Points { get; set; }
        public ICollection<Floor> Floors { get; set; }
        public int FillColor { get; set; }
        public int StrokeColor { get; set; }
        public GraphNode GraphNode { get; set; }
    }
}
