namespace MyTU_api.Entities
{
    public class Building
    {
        public int Id { get; set; }
        public virtual Label? Label { get; set; }
        public virtual ICollection<BuildingPoint> Points { get; set; }
        public virtual ICollection<Floor> Floors { get; set; }
        public int FillColor { get; set; }
        public int StrokeColor { get; set; }
        public virtual GraphNode? GraphNode { get; set; }
    }
}
