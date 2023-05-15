using MyTU_api.Entities;
using MyTU_api.Models;

namespace MyTU_api.Libraries
{
    public class NavigarionGraph : INavigarionGraph
    {
        private int VerticlesNum;
        private List<Tuple<int, double, bool>>[] Adjacency;
        List<GraphNode>? graphNodes;
        List<GraphNodesEdge>? graphNodesEdges;

        public NavigarionGraph(int VerticlesNum)
        {
            this.VerticlesNum = VerticlesNum;
            Adjacency = new List<Tuple<int, double, bool>>[VerticlesNum];
            for (int i = 0; i < VerticlesNum; i++)
            {
                Adjacency[i] = new List<Tuple<int, double, bool>>();
            }
        }

        public void AddEdge(int firstVertexId, int secondVertexId, double weight, bool valid)
        {
            Adjacency[firstVertexId].Add(Tuple.Create(secondVertexId, weight, valid));
            Adjacency[secondVertexId].Add(Tuple.Create(firstVertexId, weight, valid));
        }

        public void SetNodes(List<GraphNode> graphNodes)
        {
            this.graphNodes = graphNodes;
        }

        public void SetEdges(List<GraphNodesEdge> graphNodesEdges)
        {
            this.graphNodesEdges = graphNodesEdges;
            foreach (var edge in graphNodesEdges)
            {
                Adjacency[edge.NodeX.Id - 1].Add(Tuple.Create(edge.NodeY.Id - 1, edge.Weight, edge.IsValid));
                Adjacency[edge.NodeY.Id - 1].Add(Tuple.Create(edge.NodeX.Id - 1, edge.Weight, edge.IsValid));
            }
        }

        public NavigationRouteDto ShortestPath(int src, int dest, List<int> invalidNodes, List<Tuple<int, int>> invalidEdges)
        {
            double[] dist = new double[VerticlesNum];
            bool[] visited = new bool[VerticlesNum];
            int[] prev = new int[VerticlesNum];

            for (int i = 0; i < VerticlesNum; i++)
            {
                dist[i] = double.MaxValue;
                visited[i] = false;
            }

            dist[src] = 0;
            prev[src] = -1;

            for (int count = 0; count < VerticlesNum - 1; count++)
            {
                int u = MinDistance(dist, visited);
                visited[u] = true;

                foreach (var v in Adjacency[u])
                {
                    if (!visited[v.Item1] && dist[u] + v.Item2 < dist[v.Item1]
                        && !invalidNodes.Contains(v.Item1) && IsValidEdge(u, v.Item1, invalidEdges))
                    {
                        dist[v.Item1] = dist[u] + v.Item2;
                        prev[v.Item1] = u;
                    }
                }
            }

            var path = new List<int>();
            List<GraphNode> nodes = new List<GraphNode>();
            int curr = dest;
            while (curr != -1)
            {
                path.Add(curr);
                nodes.Add(graphNodes[curr]);
                curr = prev[curr];
            }
            path.Reverse();
            nodes.Reverse();

            var graphRoute = new NavigationRouteDto();
            graphRoute.Distance = dist[dest];
            graphRoute.Nodes = nodes;
            graphRoute.IsValid = true;
            graphRoute.DisabledPeople = false;
            graphRoute.ForStudents = false;

            return graphRoute;
        }

        private int MinDistance(double[] dist, bool[] visited)
        {
            double min = double.MaxValue;
            int minIndex = -1;

            for (int v = 0; v < VerticlesNum; v++)
            {
                if (visited[v] == false && dist[v] <= min)
                {
                    min = dist[v];
                    minIndex = v;
                }
            }

            return minIndex;
        }

        private bool IsValidEdge(int u, int v, List<Tuple<int, int>> invalidEdges)
        {
            foreach (var edge in invalidEdges)
            {
                if ((edge.Item1 == u && edge.Item2 == v) || (edge.Item1 == v && edge.Item2 == u))
                {
                    return false;
                }
            }

            return true;
        }
    }
}