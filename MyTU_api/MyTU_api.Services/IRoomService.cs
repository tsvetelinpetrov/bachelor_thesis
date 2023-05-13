using MyTU_api.Entities;
using MyTU_api.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MyTU_api.Services
{
    public interface IRoomService
    {
        Task<RoomDetailsDto> GetDetails(int roomId);
    }
}
