using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace MyTU_api.Infrastructure.Migrations
{
    /// <inheritdoc />
    public partial class ColorsJunkRemoved : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "FillColor",
                table: "Rooms");

            migrationBuilder.DropColumn(
                name: "StrokeColor",
                table: "Rooms");

            migrationBuilder.DropColumn(
                name: "BgColor",
                table: "Labels");

            migrationBuilder.DropColumn(
                name: "FgColor",
                table: "Labels");

            migrationBuilder.DropColumn(
                name: "Rotation",
                table: "Labels");

            migrationBuilder.DropColumn(
                name: "Size",
                table: "Labels");

            migrationBuilder.DropColumn(
                name: "FillColor",
                table: "Buildings");

            migrationBuilder.DropColumn(
                name: "StrokeColor",
                table: "Buildings");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "FillColor",
                table: "Rooms",
                type: "int",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<int>(
                name: "StrokeColor",
                table: "Rooms",
                type: "int",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<int>(
                name: "BgColor",
                table: "Labels",
                type: "int",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<int>(
                name: "FgColor",
                table: "Labels",
                type: "int",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<float>(
                name: "Rotation",
                table: "Labels",
                type: "float",
                nullable: false,
                defaultValue: 0f);

            migrationBuilder.AddColumn<int>(
                name: "Size",
                table: "Labels",
                type: "int",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<int>(
                name: "FillColor",
                table: "Buildings",
                type: "int",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<int>(
                name: "StrokeColor",
                table: "Buildings",
                type: "int",
                nullable: false,
                defaultValue: 0);
        }
    }
}
