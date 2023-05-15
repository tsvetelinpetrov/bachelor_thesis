using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace MyTU_api.Infrastructure.Migrations
{
    /// <inheritdoc />
    public partial class RemoveSubTitleRoomBuildingDetails : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "SubTitle",
                table: "RoomDetails");

            migrationBuilder.DropColumn(
                name: "SubTitle",
                table: "BuildingDetails");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "SubTitle",
                table: "RoomDetails",
                type: "longtext",
                nullable: false)
                .Annotation("MySql:CharSet", "utf8mb4");

            migrationBuilder.AddColumn<string>(
                name: "SubTitle",
                table: "BuildingDetails",
                type: "longtext",
                nullable: false)
                .Annotation("MySql:CharSet", "utf8mb4");
        }
    }
}
