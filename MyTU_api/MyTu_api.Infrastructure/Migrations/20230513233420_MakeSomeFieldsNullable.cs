using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace MyTU_api.Infrastructure.Migrations
{
    /// <inheritdoc />
    public partial class MakeSomeFieldsNullable : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Buildings_GraphNodes_GraphNodeId",
                table: "Buildings");

            migrationBuilder.DropForeignKey(
                name: "FK_Buildings_Labels_LabelId",
                table: "Buildings");

            migrationBuilder.DropForeignKey(
                name: "FK_Rooms_Floor_FloorId",
                table: "Rooms");

            migrationBuilder.DropForeignKey(
                name: "FK_Rooms_GraphNodes_GraphNodeId",
                table: "Rooms");

            migrationBuilder.DropForeignKey(
                name: "FK_Rooms_Labels_LabelId",
                table: "Rooms");

            migrationBuilder.AlterColumn<int>(
                name: "LabelId",
                table: "Rooms",
                type: "int",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "int");

            migrationBuilder.AlterColumn<int>(
                name: "GraphNodeId",
                table: "Rooms",
                type: "int",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "int");

            migrationBuilder.AlterColumn<int>(
                name: "FloorId",
                table: "Rooms",
                type: "int",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "int");

            migrationBuilder.AlterColumn<int>(
                name: "LabelId",
                table: "Buildings",
                type: "int",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "int");

            migrationBuilder.AlterColumn<int>(
                name: "GraphNodeId",
                table: "Buildings",
                type: "int",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "int");

            migrationBuilder.AddForeignKey(
                name: "FK_Buildings_GraphNodes_GraphNodeId",
                table: "Buildings",
                column: "GraphNodeId",
                principalTable: "GraphNodes",
                principalColumn: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_Buildings_Labels_LabelId",
                table: "Buildings",
                column: "LabelId",
                principalTable: "Labels",
                principalColumn: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_Rooms_Floor_FloorId",
                table: "Rooms",
                column: "FloorId",
                principalTable: "Floor",
                principalColumn: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_Rooms_GraphNodes_GraphNodeId",
                table: "Rooms",
                column: "GraphNodeId",
                principalTable: "GraphNodes",
                principalColumn: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_Rooms_Labels_LabelId",
                table: "Rooms",
                column: "LabelId",
                principalTable: "Labels",
                principalColumn: "Id");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Buildings_GraphNodes_GraphNodeId",
                table: "Buildings");

            migrationBuilder.DropForeignKey(
                name: "FK_Buildings_Labels_LabelId",
                table: "Buildings");

            migrationBuilder.DropForeignKey(
                name: "FK_Rooms_Floor_FloorId",
                table: "Rooms");

            migrationBuilder.DropForeignKey(
                name: "FK_Rooms_GraphNodes_GraphNodeId",
                table: "Rooms");

            migrationBuilder.DropForeignKey(
                name: "FK_Rooms_Labels_LabelId",
                table: "Rooms");

            migrationBuilder.AlterColumn<int>(
                name: "LabelId",
                table: "Rooms",
                type: "int",
                nullable: false,
                defaultValue: 0,
                oldClrType: typeof(int),
                oldType: "int",
                oldNullable: true);

            migrationBuilder.AlterColumn<int>(
                name: "GraphNodeId",
                table: "Rooms",
                type: "int",
                nullable: false,
                defaultValue: 0,
                oldClrType: typeof(int),
                oldType: "int",
                oldNullable: true);

            migrationBuilder.AlterColumn<int>(
                name: "FloorId",
                table: "Rooms",
                type: "int",
                nullable: false,
                defaultValue: 0,
                oldClrType: typeof(int),
                oldType: "int",
                oldNullable: true);

            migrationBuilder.AlterColumn<int>(
                name: "LabelId",
                table: "Buildings",
                type: "int",
                nullable: false,
                defaultValue: 0,
                oldClrType: typeof(int),
                oldType: "int",
                oldNullable: true);

            migrationBuilder.AlterColumn<int>(
                name: "GraphNodeId",
                table: "Buildings",
                type: "int",
                nullable: false,
                defaultValue: 0,
                oldClrType: typeof(int),
                oldType: "int",
                oldNullable: true);

            migrationBuilder.AddForeignKey(
                name: "FK_Buildings_GraphNodes_GraphNodeId",
                table: "Buildings",
                column: "GraphNodeId",
                principalTable: "GraphNodes",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Buildings_Labels_LabelId",
                table: "Buildings",
                column: "LabelId",
                principalTable: "Labels",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Rooms_Floor_FloorId",
                table: "Rooms",
                column: "FloorId",
                principalTable: "Floor",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Rooms_GraphNodes_GraphNodeId",
                table: "Rooms",
                column: "GraphNodeId",
                principalTable: "GraphNodes",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Rooms_Labels_LabelId",
                table: "Rooms",
                column: "LabelId",
                principalTable: "Labels",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
