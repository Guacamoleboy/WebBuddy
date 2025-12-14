// Imports
using System;
using System.Windows.Forms;

// Package
namespace Project.Forms {

    public partial class MainForm {

        // Attributes
        private System.ComponentModel.IContainer components = null;

        // __________________________________________________

        private void InitializeComponent() {
            this.SuspendLayout();
            this.Load += new System.EventHandler(this.MainForm_Load);
            this.ResumeLayout(false);
        }

    }

}