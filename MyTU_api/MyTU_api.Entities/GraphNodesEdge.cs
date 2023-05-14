namespace MyTU_api.Entities
{
    public class GraphNodesEdge
    {
        public int Id { get; set; }
        public virtual GraphNode NodeX { get; set; }
        public virtual GraphNode NodeY { get; set; }
        public double Weight { get; set; }
        public bool IsValid { get; set; }
        public bool DisabledPeople { get; set; }
        public bool ForStudents { get; set; }
    }
}
