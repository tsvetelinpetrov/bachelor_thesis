using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MyTU_api.Entities
{
    public class ScheduleDay
    {
        public int Id { get; set; }
        public String dayName { get; set; }

        public virtual ICollection<ScheduleClass>? ScheduleClasses { get; set; }

    }
}
