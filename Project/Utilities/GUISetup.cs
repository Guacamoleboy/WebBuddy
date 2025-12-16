// Imports
using System;
using System.Drawing;
using System.Windows.Forms;
using Project.Theme;
using AppVersion = Project.VersionControl.Version;

// Package
namespace Project.Utilities;

public static class GUISetup {

    // Attributes

    // __________________________________________

    public static void SetupMainFormSize(Form form, float scale) {
        var screen = Screen.PrimaryScreen.Bounds;
        int width = (int)(screen.Width * scale);
        int height = (int)(screen.Height * scale);
        form.Size = new Size(width, height);
        form.StartPosition = FormStartPosition.CenterScreen;
    }

    // __________________________________________

    public static void SetupMainFormColors(Form form) {
        form.BackColor = ColorScheme.MainBlue;                                  // Main Background Color
    }

    // __________________________________________

    public static void SetupMainFormScaling(Form form) {
        form.SuspendLayout();
        form.AutoScaleDimensions = new SizeF(7F, 15F);
        form.AutoScaleMode = AutoScaleMode.Font;
        form.ResumeLayout(false);
    }

    // __________________________________________

    public static void SetupMainFormInfo(Form form) {
        form.Text = $"WebBuddy | Version {AppVersion.Current}";                 // App TitleBar Text
        form.Icon = new Icon("Resources/Icon/logo-32.ico");                     // App TitleBar Icon
    }

}