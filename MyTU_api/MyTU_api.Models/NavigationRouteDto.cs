using MyTU_api.Entities;

namespace MyTU_api.Models
{
    public class NavigationRouteDto
    {
        public List<GraphNode> Nodes { get; set; }
        public double Distance { get; set; }
        public bool IsValid { get; set; }
        public bool DisabledPeople { get; set; }
        public bool ForStudents { get; set; }
    }
}
