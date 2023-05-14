
using Microsoft.EntityFrameworkCore;
using MyTU_api.Entities;

namespace MyTU_api.Infrastructure
{
    public class MyTU_apiDbContext : DbContext
    {
        public MyTU_apiDbContext(DbContextOptions<MyTU_apiDbContext> options)
            : base(options)
        {
            
        }

        public DbSet<Building> Buildings { get; set; }
        public DbSet<BuildingPoint> BuildingsPoint { get; set; }
        public DbSet<Label> Labels { get; set; }
        public DbSet<Room> Rooms { get; set; }
        public DbSet<RoomPoint> RoomPoints { get; set; }
        public DbSet<Floor> Floor { get; set; }
        public DbSet<BuildingDetails> BuildingDetails { get; set; }
        public DbSet<RoomDetails> RoomDetails { get; set; }
        public DbSet<GraphNode> GraphNodes { get; set; }
        public DbSet<GraphNodesEdge> GraphNodesEdges { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
        }
    }
}