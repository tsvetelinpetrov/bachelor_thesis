using MyTU_api.Entities;

namespace MyTU_api.Repositories
{
    public interface INavigationRepository
    {
        /// <summary>
        /// Retrieves a collection of graph nodes.
        /// </summary>
        /// <returns>A task that represents the asynchronous operation. The task result contains the collection of nullable GraphNode objects.</returns>
        Task<IEnumerable<GraphNode?>> GetGraphNodes();

        /// <summary>
        /// Retrieves a collection of graph node edges.
        /// </summary>
        /// <returns>A task that represents the asynchronous operation. The task result contains the collection of nullable GraphNodesEdge objects.</returns>
        Task<IEnumerable<GraphNodesEdge?>> GetGraphNodeEdges();
    }

}
