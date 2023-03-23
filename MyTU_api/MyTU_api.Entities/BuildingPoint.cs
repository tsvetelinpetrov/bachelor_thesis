using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MyTU_api.Entities
{
    public class BuildingPoint
    {
        public int Id { get; set; }
        public double MLongitude { get; set; }
        public double MLatitude { get; set; }
        public Building Building { get; set; }
        
    }
}
