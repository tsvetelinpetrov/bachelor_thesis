using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MyTU_api.Entities
{
    public class Label
    {
        public int Id { get; set; }
        public string Text { get; set; }
        public int Size { get; set; }
        public int BgColor { get; set; }
        public int FgColor { get; set; }
        public float Rotation { get; set; }
        public double MinZoom { get; set; }
        public double MaxZoom { get; set; }


    }
}
