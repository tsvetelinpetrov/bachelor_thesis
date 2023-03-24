using AdditionalTools.Models;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Security;
using System.Security.Policy;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace AdditionalTools
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private async void Button_Click(object sender, RoutedEventArgs e)
        {
            TextRange textRange = new TextRange(TilesRichTextBox.Document.ContentStart, TilesRichTextBox.Document.ContentEnd);
            var list = textRange.Text.Split('\n');
            string path = pathTextBox.Text;

            foreach (String item in list)
            {
                MapTile mapTile = ParseMapTile(item);
                if(mapTile != null)
                {
                    string zoomDir = path + "\\" + mapTile.ZoomLevel.ToString();
                    string finalDir = zoomDir + "\\" + mapTile.Folder.ToString();
                    string finalDirAndFile = finalDir + "\\" + mapTile.Name.ToString() + ".png";
                    string fileName = mapTile.Name.ToString() + ".png";
                    if (!Directory.Exists(zoomDir))
                    {
                        Directory.CreateDirectory(zoomDir);
                    }
                    if (!Directory.Exists(finalDir))
                    {
                        Directory.CreateDirectory(finalDir);
                    }

                    string imageUrl = @"https://tile.openstreetmap.org";
                    imageUrl += "/" + mapTile.ZoomLevel.ToString() + "/" + mapTile.Folder.ToString() + "/" + mapTile.Name.ToString();
                    imageUrl += ".png";
                    Console.WriteLine(imageUrl);
                    try
                    {
                        using (WebClient client = new WebClient())
                        {
                            client.Headers.Add(HttpRequestHeader.UserAgent, "Other");
                            System.Net.ServicePointManager.SecurityProtocol = System.Net.SecurityProtocolType.Tls12;
                            client.DownloadFile(new Uri(imageUrl), finalDirAndFile);
                        }
                    }
                    catch (Exception)
                    {

                        Console.WriteLine("ERROR!");
                    }
                    
                    //await DownloadImageAsync(finalDir, fileName, new Uri(imageUrl));

                }
            }
        }

        private async Task DownloadImageAsync(string directoryPath, string fileName, Uri uri)
        {
            using var httpClient = new HttpClient();

            // Get the file extension
            var uriWithoutQuery = uri.GetLeftPart(UriPartial.Path);
            var fileExtension = System.IO.Path.GetExtension(uriWithoutQuery);

            // Create file path and ensure directory exists
            var path = System.IO.Path.Combine(directoryPath, $"{fileName}{fileExtension}");
            Directory.CreateDirectory(directoryPath);

            // Download the image and write to the file
            var imageBytes = await httpClient.GetByteArrayAsync(uri);
            await File.WriteAllBytesAsync(path, imageBytes);
        }

        private MapTile ParseMapTile(string str)
        {
            str = str.Trim();
            if (!IsSourceString(str))
                return null;

            MapTile tile = new MapTile();

            string text = "/655x0;ThumbnailDimension=0x0";
            Regex pattern = new Regex(@"/(?<zoomLevel>\d+)/(?<folder>\d+)/(?<name>\d+)");
            Match match = pattern.Match(str);
            tile.ZoomLevel = int.Parse(match.Groups["zoomLevel"].Value);
            tile.Folder = int.Parse(match.Groups["folder"].Value);
            tile.Name = int.Parse(match.Groups["name"].Value);
            Console.WriteLine($"tile {tile.Name}");

            return tile;
        }

        private bool IsSourceString(string str)
        {
            string pattern = @"^[\/{1}][0-9]+[\/{1}][0-9]+[\/{1}][0-9]+$";
            Regex rg = new Regex(pattern);
            return rg.IsMatch(str);
        }
    }
}
