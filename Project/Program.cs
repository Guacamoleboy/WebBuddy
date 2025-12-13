// Package
namespace Project {

// Imports
using Project.Forms;

    internal static class Program {

        // Attributes
        [STAThread]

        // _______________________________________________________

        static void Main() {

            Console.WriteLine("Same as System.out.println('Text'); in java"); 

            ApplicationConfiguration.Initialize(); // GUI Settings
            Application.Run(new MainForm()); // App Start 

        }

    }

}