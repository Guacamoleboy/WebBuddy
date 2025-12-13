// Package
namespace Project.Forms {

// Imports
using Project.VersionControl;

    partial class MainForm {

        // Attributes
        private System.ComponentModel.IContainer components = null;

        // _______________________________________________________
  
        protected override void Dispose(bool disposing) {
            if (disposing && (components != null)) {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        // _______________________________________________________

        private void InitializeComponent() {
            SuspendLayout();
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 450); // GUI Size
            Text = $"WebBuddy | Version {Version.Current}"; // GUI Title
            Load += MainForm_Load;
            ResumeLayout(false);
        }

    }

}