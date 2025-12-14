using System.Runtime.InteropServices;
using Project.Theme;

namespace Project.UI {

    public class TitleBar : Panel {

        // Attributes
        private Form _parent;
        private Button btnClose;
        private Button btnMinimize;
        private int borderRadius = 25;                                          // GUI Border Radius
        private int buttonSize = 24;                                            // Button Size
        private int margin = 12;                                                // Margin right + Gap between buttons

        // Release Client
        [DllImport("user32.dll")]
        private static extern bool ReleaseCapture();

        // Mouse Positioning in Client
        [DllImport("user32.dll")]
        private static extern int SendMessage(IntPtr hWnd, int Msg, int wParam, int lParam);

        // ___________________________________________________________

        public TitleBar(Form parent) {

            _parent = parent;

            Height = 32;                                                        // TitleBar Height
            Dock = DockStyle.Top;
            BackColor = ColorScheme.MainBlue;                                   // TitleBar Background Color
            MouseDown += TitleBar_MouseDown;

            // Close
            btnClose = new Button {
                Size = new Size(buttonSize, buttonSize),
                FlatStyle = FlatStyle.Flat,
                Image = new Icon("Resources/Icon/Close.ico").ToBitmap(),
                ImageAlign = ContentAlignment.MiddleCenter
            };

            btnClose.FlatAppearance.BorderSize = 0;                             // Removes border on Button
            btnClose.Click += (s, e) => _parent.Close();                        // Sender, event                     

            // Minimize
            btnMinimize = new Button {
                Size = new Size(buttonSize, buttonSize),
                FlatStyle = FlatStyle.Flat,
                Image = new Icon("Resources/Icon/Minimize.ico").ToBitmap(),
                ImageAlign = ContentAlignment.MiddleCenter
            };

            btnMinimize.FlatAppearance.BorderSize = 0;
            btnMinimize.Click += (s, e) => _parent.WindowState = FormWindowState.Minimized;

            // Add our buttons
            Controls.Add(btnClose);
            Controls.Add(btnMinimize);

            // Dynamic Button Resize
            // DO NOT REMOVE
            _parent.SizeChanged += (s, e) => {
                btnClose.Location = new Point(_parent.Width - buttonSize - margin, (Height - buttonSize) / 2);
                btnMinimize.Location = new Point(_parent.Width - 2 * buttonSize - 2 * margin, (Height - buttonSize) / 2);
                SetFormRegion();
            };

            // Initial position
            btnClose.Location = new Point(_parent.Width - buttonSize - margin, (Height - buttonSize) / 2);
            btnMinimize.Location = new Point(_parent.Width - 2 * buttonSize - 2 * margin, (Height - buttonSize) / 2);

        }

        // ___________________________________________________________

        private void TitleBar_MouseDown(object sender, MouseEventArgs e) {
            if (e.Button == MouseButtons.Left) {
                ReleaseCapture();
                SendMessage(_parent.Handle, 0xA1, 2, 0);                        // 0xA1 -> Non Client Area | 2 -> HTCAPTION (TitleBar)
            }
        }

        // ___________________________________________________________

        private void SetFormRegion() {

            // Adds Border Radius on our Client
            // DO NOT REMOVE

            var radius = new System.Drawing.Drawing2D.GraphicsPath();
            radius.AddArc(0, 0, borderRadius, borderRadius, 180, 90);
            radius.AddArc(_parent.Width - borderRadius, 0, borderRadius, borderRadius, 270, 90);
            radius.AddArc(_parent.Width - borderRadius, _parent.Height - borderRadius, borderRadius, borderRadius, 0, 90);
            radius.AddArc(0, _parent.Height - borderRadius, borderRadius, borderRadius, 90, 90);
            radius.CloseAllFigures();
            _parent.Region = new Region(radius);

        }

    }
}