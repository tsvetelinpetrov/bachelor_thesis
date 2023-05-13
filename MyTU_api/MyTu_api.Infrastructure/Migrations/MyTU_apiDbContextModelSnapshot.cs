﻿// <auto-generated />
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using MyTU_api.Infrastructure;

#nullable disable

namespace MyTU_api.Infrastructure.Migrations
{
    [DbContext(typeof(MyTU_apiDbContext))]
    partial class MyTU_apiDbContextModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "7.0.4")
                .HasAnnotation("Proxies:ChangeTracking", false)
                .HasAnnotation("Proxies:CheckEquality", false)
                .HasAnnotation("Proxies:LazyLoading", true)
                .HasAnnotation("Relational:MaxIdentifierLength", 64);

            modelBuilder.Entity("MyTU_api.Entities.Building", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<int>("FillColor")
                        .HasColumnType("int");

                    b.Property<int>("LabelId")
                        .HasColumnType("int");

                    b.Property<int>("StrokeColor")
                        .HasColumnType("int");

                    b.HasKey("Id");

                    b.HasIndex("LabelId");

                    b.ToTable("Buildings");
                });

            modelBuilder.Entity("MyTU_api.Entities.BuildingDetails", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<int>("BuildingId")
                        .HasColumnType("int");

                    b.Property<string>("Description")
                        .IsRequired()
                        .HasColumnType("longtext");

                    b.Property<string>("ImageUrl")
                        .IsRequired()
                        .HasColumnType("longtext");

                    b.Property<string>("SubTitle")
                        .IsRequired()
                        .HasColumnType("longtext");

                    b.HasKey("Id");

                    b.HasIndex("BuildingId");

                    b.ToTable("BuildingDetails");
                });

            modelBuilder.Entity("MyTU_api.Entities.BuildingPoint", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<int>("BuildingId")
                        .HasColumnType("int");

                    b.Property<double>("MLatitude")
                        .HasColumnType("double");

                    b.Property<double>("MLongitude")
                        .HasColumnType("double");

                    b.HasKey("Id");

                    b.HasIndex("BuildingId");

                    b.ToTable("BuildingsPoint");
                });

            modelBuilder.Entity("MyTU_api.Entities.Floor", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<int>("BuildingId")
                        .HasColumnType("int");

                    b.Property<int>("Level")
                        .HasColumnType("int");

                    b.HasKey("Id");

                    b.HasIndex("BuildingId");

                    b.ToTable("Floor");
                });

            modelBuilder.Entity("MyTU_api.Entities.Label", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<int>("BgColor")
                        .HasColumnType("int");

                    b.Property<int>("FgColor")
                        .HasColumnType("int");

                    b.Property<int>("Icon")
                        .HasColumnType("int");

                    b.Property<string>("IconColor")
                        .IsRequired()
                        .HasColumnType("longtext");

                    b.Property<double>("MLatitude")
                        .HasColumnType("double");

                    b.Property<double>("MLongitude")
                        .HasColumnType("double");

                    b.Property<string>("MapText")
                        .IsRequired()
                        .HasColumnType("longtext");

                    b.Property<double>("MaxZoom")
                        .HasColumnType("double");

                    b.Property<double>("MinZoom")
                        .HasColumnType("double");

                    b.Property<float>("Rotation")
                        .HasColumnType("float");

                    b.Property<int>("Size")
                        .HasColumnType("int");

                    b.Property<string>("Text")
                        .IsRequired()
                        .HasColumnType("longtext");

                    b.HasKey("Id");

                    b.ToTable("Labels");
                });

            modelBuilder.Entity("MyTU_api.Entities.Room", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<int>("FillColor")
                        .HasColumnType("int");

                    b.Property<int>("FloorId")
                        .HasColumnType("int");

                    b.Property<int>("LabelId")
                        .HasColumnType("int");

                    b.Property<int>("StrokeColor")
                        .HasColumnType("int");

                    b.HasKey("Id");

                    b.HasIndex("FloorId");

                    b.HasIndex("LabelId");

                    b.ToTable("Rooms");
                });

            modelBuilder.Entity("MyTU_api.Entities.RoomPoint", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<double>("MLatitude")
                        .HasColumnType("double");

                    b.Property<double>("MLongitude")
                        .HasColumnType("double");

                    b.Property<int>("RoomId")
                        .HasColumnType("int");

                    b.HasKey("Id");

                    b.HasIndex("RoomId");

                    b.ToTable("RoomPoints");
                });

            modelBuilder.Entity("MyTU_api.Entities.Building", b =>
                {
                    b.HasOne("MyTU_api.Entities.Label", "Label")
                        .WithMany()
                        .HasForeignKey("LabelId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Label");
                });

            modelBuilder.Entity("MyTU_api.Entities.BuildingDetails", b =>
                {
                    b.HasOne("MyTU_api.Entities.Building", "Building")
                        .WithMany()
                        .HasForeignKey("BuildingId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Building");
                });

            modelBuilder.Entity("MyTU_api.Entities.BuildingPoint", b =>
                {
                    b.HasOne("MyTU_api.Entities.Building", null)
                        .WithMany("Points")
                        .HasForeignKey("BuildingId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("MyTU_api.Entities.Floor", b =>
                {
                    b.HasOne("MyTU_api.Entities.Building", null)
                        .WithMany("Floors")
                        .HasForeignKey("BuildingId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("MyTU_api.Entities.Room", b =>
                {
                    b.HasOne("MyTU_api.Entities.Floor", null)
                        .WithMany("Rooms")
                        .HasForeignKey("FloorId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("MyTU_api.Entities.Label", "Label")
                        .WithMany()
                        .HasForeignKey("LabelId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Label");
                });

            modelBuilder.Entity("MyTU_api.Entities.RoomPoint", b =>
                {
                    b.HasOne("MyTU_api.Entities.Room", null)
                        .WithMany("Points")
                        .HasForeignKey("RoomId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("MyTU_api.Entities.Building", b =>
                {
                    b.Navigation("Floors");

                    b.Navigation("Points");
                });

            modelBuilder.Entity("MyTU_api.Entities.Floor", b =>
                {
                    b.Navigation("Rooms");
                });

            modelBuilder.Entity("MyTU_api.Entities.Room", b =>
                {
                    b.Navigation("Points");
                });
#pragma warning restore 612, 618
        }
    }
}
