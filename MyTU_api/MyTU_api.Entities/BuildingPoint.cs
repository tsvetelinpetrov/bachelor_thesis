namespace MyTU_api.Entities
{
    public class BuildingPoint
    {
        public int Id { get; set; }
        public double MLongitude { get; set; }
        public double MLatitude { get; set; }
        public int? BuildingId { get; set; }
        
    }
}
