using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MyTU_api.Entities
{
    public class BuildingDetails
    {
        public int Id { get; set; }
        public virtual Building? Building { get; set; }
        public String ImageUrl { get; set; }
        public String SubTitle { get; set; }
        public String Description { get; set; }
    }
}
