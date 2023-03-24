namespace MyTU_api.Entities
{
    public class Floor
    {
        public int Id { get; set; }
        public int Level { get; set; }
        public virtual ICollection<Room> Rooms { get; set; }
        public int BuildingId { get; set; }
    }
}
