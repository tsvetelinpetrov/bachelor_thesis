using MyTU_api.Entities;
using MyTU_api.Models;

namespace MyTU_api.Libraries
{
    public interface INavigarionGraph
    {
        /// <summary>
        /// Adds an edge between two vertices in the navigation graph.
        /// </summary>
        /// <param name="firstVertexId">The ID of the first vertex.</param>
        /// <param name="secondVertexId">The ID of the second vertex.</param>
        /// <param name="weight">The weight of the edge.</param>
        /// <param name="valid">A flag indicating whether the edge is valid.</param>
        void AddEdge(int firstVertexId, int secondVertexId, double weight, bool valid);

        /// <summary>
        /// Sets the list of graph nodes in the navigation graph.
        /// </summary>
        /// <param name="graphNodes">The list of graph nodes.</param>
        void SetNodes(List<GraphNode> graphNodes);

        /// <summary>
        /// Sets the list of graph node edges in the navigation graph.
        /// </summary>
        /// <param name="graphNodesEdges">The list of graph node edges.</param>
        void SetEdges(List<GraphNodesEdge> graphNodesEdges);

        /// <summary>
        /// Calculates the shortest path between a source node and a destination node in the navigation graph.
        /// </summary>
        /// <param name="src">The ID of the source node.</param>
        /// <param name="dest">The ID of the destination node.</param>
        /// <param name="invalidNodes">A list of invalid nodes to be avoided in the path.</param>
        /// <param name="invalidEdges">A list of invalid edges to be avoided in the path.</param>
        /// <returns>A NavigationRouteDto object representing the shortest path.</returns>
        NavigationRouteDto ShortestPath(int src, int dest, List<int> invalidNodes, List<Tuple<int, int>> invalidEdges);
    }
}
