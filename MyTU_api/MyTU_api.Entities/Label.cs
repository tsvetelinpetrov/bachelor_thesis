﻿namespace MyTU_api.Entities
{
    public class Label
    {
        public int Id { get; set; }
        public string Text { get; set; }
        public string SubText { get; set; }
        public string MapText { get; set; }
        public double MinZoom { get; set; }
        public double MaxZoom { get; set; }
        public double MLongitude { get; set; }
        public double MLatitude { get; set; }
        public int Icon { get; set; }
        public String IconColor { get; set; }
    }
}
