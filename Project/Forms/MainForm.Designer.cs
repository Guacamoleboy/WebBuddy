// Package
namespace Project.Forms {

// Imports
using Project.VersionControl;

    partial class MainForm {

        // Attributes
        private System.ComponentModel.IContainer components = null; // Overall container for components

        // _______________________________________________________
  
        protected override void Dispose(bool disposing) { // Clears components
            if (disposing && (components != null)) {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        // _______________________________________________________

        private void InitializeComponent() {

            SuspendLayout(); // Layout Optimization

            AutoScaleDimensions = new SizeF(7F, 15F); // DPI (NUMBER) & Font Scaling (F)
            AutoScaleMode = AutoScaleMode.Font; // Makes sure it scales correct on all screen sizes
            ClientSize = new Size(800, 450); // GUI Size
            Text = $"WebBuddy | Version {Version.Current}"; // GUI Title
            this.Icon = new Icon("Resources/Icon/logo-32.ico");

            Load += MainForm_Load;

            ResumeLayout(false); // Layout Optimization

        }

    }

}