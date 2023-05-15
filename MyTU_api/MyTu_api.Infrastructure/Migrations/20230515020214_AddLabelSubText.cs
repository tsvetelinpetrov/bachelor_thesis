using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace MyTU_api.Infrastructure.Migrations
{
    /// <inheritdoc />
    public partial class AddLabelSubText : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "SubText",
                table: "Labels",
                type: "longtext",
                nullable: false)
                .Annotation("MySql:CharSet", "utf8mb4");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "SubText",
                table: "Labels");
        }
    }
}
