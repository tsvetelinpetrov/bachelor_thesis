using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace MyTU_api.Infrastructure.Migrations
{
    /// <inheritdoc />
    public partial class CreateGraphNodesAndEdges : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "GraphNodes",
                columns: table => new
                {
                    Id = table.Column<int>(type: "int", nullable: false)
                        .Annotation("MySql:ValueGenerationStrategy", MySqlValueGenerationStrategy.IdentityColumn),
                    Lattitude = table.Column<double>(type: "double", nullable: false),
                    Longitude = table.Column<double>(type: "double", nullable: false),
                    IsValid = table.Column<bool>(type: "tinyint(1)", nullable: false),
                    DisabledPeople = table.Column<bool>(type: "tinyint(1)", nullable: false),
                    ForStudents = table.Column<bool>(type: "tinyint(1)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_GraphNodes", x => x.Id);
                })
                .Annotation("MySql:CharSet", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "GraphNodesEdges",
                columns: table => new
                {
                    Id = table.Column<int>(type: "int", nullable: false)
                        .Annotation("MySql:ValueGenerationStrategy", MySqlValueGenerationStrategy.IdentityColumn),
                    NodeXId = table.Column<int>(type: "int", nullable: false),
                    NodeYId = table.Column<int>(type: "int", nullable: false),
                    Weight = table.Column<int>(type: "int", nullable: false),
                    IsValid = table.Column<bool>(type: "tinyint(1)", nullable: false),
                    DisabledPeople = table.Column<bool>(type: "tinyint(1)", nullable: false),
                    ForStudents = table.Column<bool>(type: "tinyint(1)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_GraphNodesEdges", x => x.Id);
                    table.ForeignKey(
                        name: "FK_GraphNodesEdges_GraphNodes_NodeXId",
                        column: x => x.NodeXId,
                        principalTable: "GraphNodes",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_GraphNodesEdges_GraphNodes_NodeYId",
                        column: x => x.NodeYId,
                        principalTable: "GraphNodes",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                })
                .Annotation("MySql:CharSet", "utf8mb4");

            migrationBuilder.CreateIndex(
                name: "IX_GraphNodesEdges_NodeXId",
                table: "GraphNodesEdges",
                column: "NodeXId");

            migrationBuilder.CreateIndex(
                name: "IX_GraphNodesEdges_NodeYId",
                table: "GraphNodesEdges",
                column: "NodeYId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "GraphNodesEdges");

            migrationBuilder.DropTable(
                name: "GraphNodes");
        }
    }
}
