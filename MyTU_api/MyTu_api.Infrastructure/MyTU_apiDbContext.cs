
using Microsoft.EntityFrameworkCore;

namespace MyTU_api.Infrastructure
{
    public class MyTU_apiDbContext : DbContext
    {
        public MyTU_apiDbContext(DbContextOptions<MyTU_apiDbContext> options)
            : base(options)
        {

        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
        }
    }
}