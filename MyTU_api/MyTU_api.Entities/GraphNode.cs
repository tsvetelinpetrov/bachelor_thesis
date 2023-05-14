namespace MyTU_api.Entities
{
    public class GraphNode
    {
        public int Id { get; set; }
        public double Latitude { get; set; }
        public double Longitude { get; set; }
        public bool IsValid { get; set; }
        public bool DisabledPeople { get; set; }
        public bool ForStudents { get; set; }
    }
}
