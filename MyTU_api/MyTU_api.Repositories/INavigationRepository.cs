using MyTU_api.Entities;

namespace MyTU_api.Repositories
{
    public interface INavigationRepository
    {
        Task<IEnumerable<GraphNode?>> GetGraphNodes();
        Task<IEnumerable<GraphNodesEdge?>> GetGraphNodeEdges();
    }
}
