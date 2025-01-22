import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GUI extends JFrame {
    private JButton uploadButton;
    private JTextArea resultArea;
    private JPanel reportPanel;

    public GUI() {
        setTitle("Resume Analyzer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        uploadButton = new JButton("Upload Resume");
        resultArea = new JTextArea();
        reportPanel = new JPanel();

        uploadButton.addActionListener(e -> uploadAndAnalyzeResume());

        add(uploadButton, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
        add(reportPanel, BorderLayout.SOUTH);
    }

    private void uploadAndAnalyzeResume() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                ResumeParser parser = new ResumeParser();
                String resumeText = parser.parseResume(selectedFile);

                ResumeAnalyzer analyzer = new ResumeAnalyzer(resumeText);
                List<String> keywords = Arrays.asList("java", "python", "javascript", "html", "css", "sql");
                Map<String, Integer> keywordCounts = analyzer.analyzeKeywords(keywords);
                int totalWords = analyzer.countTotalWords();
                Map<String, Integer> sectionCounts = analyzer.analyzeSections();

                displayResults(keywordCounts, totalWords, sectionCounts);

                ReportGenerator reportGenerator = new ReportGenerator();
                reportPanel.removeAll();
                reportPanel.add(reportGenerator.generateReport(keywordCounts, totalWords, sectionCounts));
                reportPanel.revalidate();
                reportPanel.repaint();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error analyzing resume: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void displayResults(Map<String, Integer> keywordCounts, int totalWords, Map<String, Integer> sectionCounts) {
        StringBuilder sb = new StringBuilder();
        sb.append("Resume Analysis Results:\n\n");
        sb.append("Total Words: ").append(totalWords).append("\n\n");
        sb.append("Keyword Frequency:\n");
        for (Map.Entry<String, Integer> entry : keywordCounts.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        sb.append("\nSections Found:\n");
        for (Map.Entry<String, Integer> entry : sectionCounts.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        resultArea.setText(sb.toString());
    }
}