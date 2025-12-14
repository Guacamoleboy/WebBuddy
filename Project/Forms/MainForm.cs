// Imports
using System;
using System.Windows.Forms;
using Project.Theme;
using Project.UI;
using Project.Utilities;

// Package
namespace Project.Forms {

    public partial class MainForm : Form {

        // Attributes
        private Tray tray;

        // _________________________________________________

        public MainForm() {
            InitializeComponent();
        }

        // _________________________________________________

        private void MainForm_Load(object sender, EventArgs e) {
            
            // GUI setup
            GUISetup.SetupMainFormSize(this, 0.6f);
            GUISetup.SetupMainFormColors(this);
            GUISetup.SetupMainFormInfo(this);
            GUISetup.SetupMainFormScaling(this);

            // Tray
            tray = new Tray(this);

        }

        // _________________________________________________
        // Prevents app from shutting down when closed

        protected override void OnFormClosing(FormClosingEventArgs e) {
            tray.HandleFormClosing(e);
        }

        // _________________________________________________

        protected override void Dispose(bool disposing) {

            if (disposing) {
                tray.Dispose();
                components.Dispose();
            }

            base.Dispose(disposing);

        }

    }

}