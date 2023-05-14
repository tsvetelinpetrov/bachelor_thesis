using Microsoft.Extensions.Logging;
using MyTU_api.Libraries;
using MyTU_api.Models;
using MyTU_api.Repositories;

namespace MyTU_api.Services
{
    public class NavigationService : INavigationService
    {
        private readonly INavigationRepository _navigationRepository;
        private readonly ILogger _logger;

        public NavigationService(INavigationRepository navigationRepository, ILogger<NavigationService> logger)
        {
            _navigationRepository = navigationRepository;
            _logger = logger;
        }

        public async Task<NavigationRouteDto> GenerateRoute(int locationType1, int locationType2, int node1Id, int node2Id)
        {
            var graphNodes = await _navigationRepository.GetGraphNodes();
            var graphNodeEdges = await _navigationRepository.GetGraphNodeEdges();
            NavigarionGraph navigarionGraph = new NavigarionGraph(graphNodes.Count());
            navigarionGraph.SetNodes(graphNodes.ToList());
            navigarionGraph.SetEdges(graphNodeEdges.ToList());

            //List<int> invalidNodes = new List<int> { 2, 4 };
            //List<Tuple<int, int>> invalidEdges = new List<Tuple<int, int>> { Tuple.Create(7, 5) };
            List<int> invalidNodes = new List<int> {};
            List<Tuple<int, int>> invalidEdges = new List<Tuple<int, int>> {};
            NavigationRouteDto shortestPath = navigarionGraph.ShortestPath(node1Id - 1, node2Id - 1, invalidNodes, invalidEdges);

            foreach (var edge in graphNodeEdges.ToList())
            {
                double dist = Distance(edge.NodeX.Latitude, edge.NodeX.Longitude, edge.NodeY.Latitude, edge.NodeY.Longitude);
                //Console.WriteLine(Math.Round(dist, 2));
            }

            return shortestPath;
        }

        private const double EarthRadius = 6371000; // meters

        public static double Distance(double lat1, double lon1, double lat2, double lon2)
        {
            var dLat = ToRadians(lat2 - lat1);
            var dLon = ToRadians(lon2 - lon1);

            var a = Math.Sin(dLat / 2) * Math.Sin(dLat / 2) +
                    Math.Cos(ToRadians(lat1)) * Math.Cos(ToRadians(lat2)) *
                    Math.Sin(dLon / 2) * Math.Sin(dLon / 2);

            var c = 2 * Math.Atan2(Math.Sqrt(a), Math.Sqrt(1 - a));

            return EarthRadius * c;
        }

        private static double ToRadians(double degrees)
        {
            return degrees * Math.PI / 180;
        }
    }
}
