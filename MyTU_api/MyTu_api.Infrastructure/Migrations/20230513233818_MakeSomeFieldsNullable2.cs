using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace MyTU_api.Infrastructure.Migrations
{
    /// <inheritdoc />
    public partial class MakeSomeFieldsNullable2 : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_BuildingDetails_Buildings_BuildingId",
                table: "BuildingDetails");

            migrationBuilder.DropForeignKey(
                name: "FK_BuildingsPoint_Buildings_BuildingId",
                table: "BuildingsPoint");

            migrationBuilder.DropForeignKey(
                name: "FK_Floor_Buildings_BuildingId",
                table: "Floor");

            migrationBuilder.DropForeignKey(
                name: "FK_RoomDetails_Rooms_RoomId",
                table: "RoomDetails");

            migrationBuilder.DropForeignKey(
                name: "FK_RoomPoints_Rooms_RoomId",
                table: "RoomPoints");

            migrationBuilder.AlterColumn<int>(
                name: "RoomId",
                table: "RoomPoints",
                type: "int",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "int");

            migrationBuilder.AlterColumn<int>(
                name: "RoomId",
                table: "RoomDetails",
                type: "int",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "int");

            migrationBuilder.AlterColumn<int>(
                name: "BuildingId",
                table: "Floor",
                type: "int",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "int");

            migrationBuilder.AlterColumn<int>(
                name: "BuildingId",
                table: "BuildingsPoint",
                type: "int",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "int");

            migrationBuilder.AlterColumn<int>(
                name: "BuildingId",
                table: "BuildingDetails",
                type: "int",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "int");

            migrationBuilder.AddForeignKey(
                name: "FK_BuildingDetails_Buildings_BuildingId",
                table: "BuildingDetails",
                column: "BuildingId",
                principalTable: "Buildings",
                principalColumn: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_BuildingsPoint_Buildings_BuildingId",
                table: "BuildingsPoint",
                column: "BuildingId",
                principalTable: "Buildings",
                principalColumn: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_Floor_Buildings_BuildingId",
                table: "Floor",
                column: "BuildingId",
                principalTable: "Buildings",
                principalColumn: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_RoomDetails_Rooms_RoomId",
                table: "RoomDetails",
                column: "RoomId",
                principalTable: "Rooms",
                principalColumn: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_RoomPoints_Rooms_RoomId",
                table: "RoomPoints",
                column: "RoomId",
                principalTable: "Rooms",
                principalColumn: "Id");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_BuildingDetails_Buildings_BuildingId",
                table: "BuildingDetails");

            migrationBuilder.DropForeignKey(
                name: "FK_BuildingsPoint_Buildings_BuildingId",
                table: "BuildingsPoint");

            migrationBuilder.DropForeignKey(
                name: "FK_Floor_Buildings_BuildingId",
                table: "Floor");

            migrationBuilder.DropForeignKey(
                name: "FK_RoomDetails_Rooms_RoomId",
                table: "RoomDetails");

            migrationBuilder.DropForeignKey(
                name: "FK_RoomPoints_Rooms_RoomId",
                table: "RoomPoints");

            migrationBuilder.AlterColumn<int>(
                name: "RoomId",
                table: "RoomPoints",
                type: "int",
                nullable: false,
                defaultValue: 0,
                oldClrType: typeof(int),
                oldType: "int",
                oldNullable: true);

            migrationBuilder.AlterColumn<int>(
                name: "RoomId",
                table: "RoomDetails",
                type: "int",
                nullable: false,
                defaultValue: 0,
                oldClrType: typeof(int),
                oldType: "int",
                oldNullable: true);

            migrationBuilder.AlterColumn<int>(
                name: "BuildingId",
                table: "Floor",
                type: "int",
                nullable: false,
                defaultValue: 0,
                oldClrType: typeof(int),
                oldType: "int",
                oldNullable: true);

            migrationBuilder.AlterColumn<int>(
                name: "BuildingId",
                table: "BuildingsPoint",
                type: "int",
                nullable: false,
                defaultValue: 0,
                oldClrType: typeof(int),
                oldType: "int",
                oldNullable: true);

            migrationBuilder.AlterColumn<int>(
                name: "BuildingId",
                table: "BuildingDetails",
                type: "int",
                nullable: false,
                defaultValue: 0,
                oldClrType: typeof(int),
                oldType: "int",
                oldNullable: true);

            migrationBuilder.AddForeignKey(
                name: "FK_BuildingDetails_Buildings_BuildingId",
                table: "BuildingDetails",
                column: "BuildingId",
                principalTable: "Buildings",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_BuildingsPoint_Buildings_BuildingId",
                table: "BuildingsPoint",
                column: "BuildingId",
                principalTable: "Buildings",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Floor_Buildings_BuildingId",
                table: "Floor",
                column: "BuildingId",
                principalTable: "Buildings",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_RoomDetails_Rooms_RoomId",
                table: "RoomDetails",
                column: "RoomId",
                principalTable: "Rooms",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_RoomPoints_Rooms_RoomId",
                table: "RoomPoints",
                column: "RoomId",
                principalTable: "Rooms",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
