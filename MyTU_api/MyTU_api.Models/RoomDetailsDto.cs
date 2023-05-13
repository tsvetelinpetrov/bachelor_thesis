using MyTU_api.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MyTU_api.Models
{
    public class RoomDetailsDto
    {
        public int Id { get; set; }
        public Room Room { get; set; }
        public String ImageUrl { get; set; }
        public String SubTitle { get; set; }
        public String Description { get; set; }
    }
}
