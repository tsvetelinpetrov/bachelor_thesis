using MyTU_api.Entities;
using MyTU_api.Infrastructure;
using Microsoft.EntityFrameworkCore;

namespace MyTU_api.Repositories
{
    public class NavigationRepository : INavigationRepository
    {
        private readonly MyTU_apiDbContext _dbContext;

        public NavigationRepository(MyTU_apiDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public async Task<IEnumerable<GraphNode?>> GetGraphNodes()
        {
            return await _dbContext.GraphNodes.ToArrayAsync();
        }

        public async Task<IEnumerable<GraphNodesEdge?>> GetGraphNodeEdges()
        {
            return await _dbContext.GraphNodesEdges.ToArrayAsync();
        }
    }
}
