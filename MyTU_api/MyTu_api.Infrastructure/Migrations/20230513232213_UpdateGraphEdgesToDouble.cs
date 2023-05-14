using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace MyTU_api.Infrastructure.Migrations
{
    /// <inheritdoc />
    public partial class UpdateGraphEdgesToDouble : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "GraphNodeId",
                table: "Rooms",
                type: "int",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AlterColumn<double>(
                name: "Weight",
                table: "GraphNodesEdges",
                type: "double",
                nullable: false,
                oldClrType: typeof(int),
                oldType: "int");

            migrationBuilder.AddColumn<int>(
                name: "GraphNodeId",
                table: "Buildings",
                type: "int",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.CreateIndex(
                name: "IX_Rooms_GraphNodeId",
                table: "Rooms",
                column: "GraphNodeId");

            migrationBuilder.CreateIndex(
                name: "IX_Buildings_GraphNodeId",
                table: "Buildings",
                column: "GraphNodeId");

            migrationBuilder.AddForeignKey(
                name: "FK_Buildings_GraphNodes_GraphNodeId",
                table: "Buildings",
                column: "GraphNodeId",
                principalTable: "GraphNodes",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Rooms_GraphNodes_GraphNodeId",
                table: "Rooms",
                column: "GraphNodeId",
                principalTable: "GraphNodes",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Buildings_GraphNodes_GraphNodeId",
                table: "Buildings");

            migrationBuilder.DropForeignKey(
                name: "FK_Rooms_GraphNodes_GraphNodeId",
                table: "Rooms");

            migrationBuilder.DropIndex(
                name: "IX_Rooms_GraphNodeId",
                table: "Rooms");

            migrationBuilder.DropIndex(
                name: "IX_Buildings_GraphNodeId",
                table: "Buildings");

            migrationBuilder.DropColumn(
                name: "GraphNodeId",
                table: "Rooms");

            migrationBuilder.DropColumn(
                name: "GraphNodeId",
                table: "Buildings");

            migrationBuilder.AlterColumn<int>(
                name: "Weight",
                table: "GraphNodesEdges",
                type: "int",
                nullable: false,
                oldClrType: typeof(double),
                oldType: "double");
        }
    }
}
