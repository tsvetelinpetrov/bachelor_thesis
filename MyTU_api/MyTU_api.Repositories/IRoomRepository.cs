using MyTU_api.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MyTU_api.Repositories
{
    public interface IRoomRepository
    {
        Task<RoomDetailsDto?> GetDetails(int roomId);
    }
}
