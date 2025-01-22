import java.util.*;
import java.util.regex.*;

public class ResumeAnalyzer {
    private String resumeText;

    public ResumeAnalyzer(String resumeText) {
        this.resumeText = resumeText.toLowerCase();
    }

    public Map<String, Integer> analyzeKeywords(List<String> keywords) {
        Map<String, Integer> keywordCounts = new HashMap<>();
        for (String keyword : keywords) {
            Pattern pattern = Pattern.compile("\\b" + keyword.toLowerCase() + "\\b");
            Matcher matcher = pattern.matcher(resumeText);
            int count = 0;
            while (matcher.find()) {
                count++;
            }
            keywordCounts.put(keyword, count);
        }
        return keywordCounts;
    }

    public int countTotalWords() {
        String[] words = resumeText.split("\\s+");
        return words.length;
    }

    public Map<String, Integer> analyzeSections() {
        Map<String, Integer> sectionCounts = new HashMap<>();
        String[] sections = {"education", "experience", "skills", "projects"};
        for (String section : sections) {
            Pattern pattern = Pattern.compile("\\b" + section + "\\b", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(resumeText);
            int count = 0;
            while (matcher.find()) {
                count++;
            }
            sectionCounts.put(section, count);
        }
        return sectionCounts;
    }
}