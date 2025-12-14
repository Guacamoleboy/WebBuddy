// Package
namespace Project.UI {

// Imports
using System;
using System.Drawing;
using System.Windows.Forms;

    // __________________________________________

    public class Tray : IDisposable {

        // Attributes
        private NotifyIcon trayIcon;
        private Form parent;
        private bool closingApp = false;

        // __________________________________________

        public Tray(Form parent) {

            // Initial Setup
            this.parent = parent;
            var trayMenu = new ContextMenuStrip();
                
            trayIcon = new NotifyIcon {                                             // Tray Visuals
                Icon = new Icon("Resources/Icon/logo-16.ico"),
                Text = "WebBuddy",
                Visible = true
            };

            trayIcon.DoubleClick += (s, e) => {                                     // Double Click Handle
                parent.Show();
                parent.WindowState = FormWindowState.Normal;
                parent.ShowInTaskbar = true;
                parent.Activate();
            };

            trayMenu.Items.Add("Open", null, (s, e) => {                            // Open Handle
                parent.Show();
                parent.WindowState = FormWindowState.Normal;
                parent.ShowInTaskbar = true;
                parent.Activate();
            });

            trayMenu.Items.Add("Close", null, (s, e) => {                           // Close Handle
                closingApp = true;
                trayIcon.Visible = false;
                Application.Exit();
            });

            trayIcon.ContextMenuStrip = trayMenu;

        }

        // __________________________________________

        public void HandleFormClosing(FormClosingEventArgs e) {
            if (!closingApp) {
                e.Cancel = true;
                parent.Hide();
            }
        }

        // __________________________________________
        // IDisposable method

        public void Dispose() { 
            trayIcon?.Dispose();
        }

    }
}