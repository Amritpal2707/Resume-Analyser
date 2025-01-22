import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ReportGenerator {
    public JPanel generateReport(Map<String, Integer> keywordCounts, int totalWords, Map<String, Integer> sectionCounts) {
        JPanel reportPanel = new JPanel(new GridLayout(2, 2));

        reportPanel.add(createKeywordBarChart(keywordCounts));
        reportPanel.add(createWordCountPieChart(totalWords));
        reportPanel.add(createSectionsPieChart(sectionCounts));

        return reportPanel;
    }

    private ChartPanel createKeywordBarChart(Map<String, Integer> keywordCounts) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : keywordCounts.entrySet()) {
            dataset.addValue(entry.getValue(), "Count", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Keyword Frequency",
                "Keywords",
                "Count",
                dataset
        );

        return new ChartPanel(chart);
    }

    private ChartPanel createWordCountPieChart(int totalWords) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Total Words", totalWords);

        JFreeChart chart = ChartFactory.createPieChart(
                "Total Word Count",
                dataset,
                true,
                true,
                false
        );

        return new ChartPanel(chart);
    }

    private ChartPanel createSectionsPieChart(Map<String, Integer> sectionCounts) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : sectionCounts.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Resume Sections",
                dataset,
                true,
                true,
                false
        );

        return new ChartPanel(chart);
    }
}