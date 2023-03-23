using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MyTU_api.Entities
{
    public class Building
    {
        public int Id { get; set; }
        public Label Label { get; set; }
        public virtual List<BuildingPoint> Points { get; set; }
        public int FillColor { get; set; }
        public int StrokeColor { get; set; }
        

    }
}
