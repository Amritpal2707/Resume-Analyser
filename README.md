# Resume-Analyser

To run this project, you'll need to add the following dependencies to your project:

1. Apache PDFBox (for parsing PDF files)
2. JFreeChart (for creating charts)


You can add these dependencies using Maven or by downloading the JAR files and adding them to your project's classpath.

This resume analyzer project does the following:

1. Allows users to upload a PDF resume file.
2. Parses the PDF file and extracts the text.
3. Analyzes the resume for keyword frequency, total word count, and presence of important sections.
4. Displays the analysis results in a text area.
5. Generates visual reports using bar charts and pie charts to show:
->  Keyword frequency
-> Total word count
-> Distribution of resume sections

To use the application:

1. Run the `ResumeAnalyzerApp` class.
2. Click the "Upload Resume" button to select a PDF resume file.
3. The application will analyze the resume and display the results in the text area and as charts in the bottom panel.
