import javax.swing.SwingUtilities;

public class ResumeAnalyzerApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.setVisible(true);
        });
    }
}