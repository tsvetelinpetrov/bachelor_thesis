namespace MyTU_api.Entities
{
    public class Room
    {
        public int Id { get; set; }
        public int LabelId { get; set; }
        public virtual Label Label { get; set; }
        public int FillColor { get; set; }
        public int StrokeColor { get; set; }
        public virtual ICollection<RoomPoint> Points { get; set; }
        public int FloorId { get; set; }
    }
}
