// Package
namespace Project.Forms {

// Imports
using Project.Theme;
using Project.UI;

    public partial class MainForm : Form { // : Form -> inherits from Form

        // Attributes

        // _______________________________________________________

        public MainForm() { // Constructor
            InitializeComponent();

            /*
             
            Custom TitleBar Attempt
            _______________________

            FormBorderStyle = FormBorderStyle.None;
            Controls.Add(new TitleBar(this));

            */

        }

        // _______________________________________________________

        private void MainForm_Load(object sender, EventArgs e) {

            // Initial
            var screen = Screen.PrimaryScreen.Bounds;
            float scale = 0.6f; // 60% as float value

            // Calculate and set height / width
            int width = (int)(screen.Width * scale);
            int height = (int)(screen.Height * scale);

            // Sets app size dynamicly depending on screen size it's loaded on
            this.Size = new Size(width, height);
            this.StartPosition = FormStartPosition.CenterScreen;

            this.BackColor = ColorScheme.MainBlue;

        }

    }

}