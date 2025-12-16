// Imports
using Project.Forms;
using Project.Services;
using Project.Data.Local.Mapper;
using Microsoft.Toolkit.Uwp.Notifications;

// Package
namespace Project {

    internal static class Program {

        // Attributes
        [STAThread]

        // _______________________________________________________

        static void Main() {
            
            /* //Blocker Test
            var exePath = AppContext.BaseDirectory;
            var dbPath = System.IO.Path.Combine(exePath, "Data", "Local", "validated.db");
            var connection = $"Data Source={dbPath}";
            var websiteMapper = new WebsiteMapper(connection);
            var validationService = new ValidationService(websiteMapper);
            string domain = "fog.guacamoleboy.dk";

            // Validate 
            var blocked = validationService.IsWebsiteBlocked(domain);
            
            // Alert
            System.Windows.Forms.MessageBox.Show(
                blocked ? $"NOT SAFE | {domain} er ikke sikker."
                    : $"SAFE | {domain} er sikker"
            );
            */
            
            ApplicationConfiguration.Initialize(); // GUI Settings
            Application.Run(new MainForm()); // App Start 

        }

    }

}