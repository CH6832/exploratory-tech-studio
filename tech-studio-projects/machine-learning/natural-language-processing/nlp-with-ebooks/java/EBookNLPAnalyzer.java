import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.tika.Tika;
import org.apache.tika.language.detect.LanguageDetector;
import org.apache.tika.language.detect.LanguageResult;

public class NLPApp {

    private static final String ROOT_DIR = "ebooks";
    private static final String OUTPUT_FILE = "output/text_analysis_report.json";

    public static void main(String[] args) throws IOException {
        StringBuilder ebooks = new StringBuilder();

        // Read content of all ebooks
        Files.walk(Paths.get(ROOT_DIR))
            .filter(Files::isRegularFile)
            .forEach(path -> {
                try {
                    String content = new String(Files.readAllBytes(path));
                    ebooks.append(content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        // Convert content to string
        String ebookContent = ebooks.toString();

        // Extract paragraphs containing the word "love"
        Pattern lovePattern = Pattern.compile("[^\n]+love[^\n]+");
        Matcher loveMatcher = lovePattern.matcher(ebookContent);
        List<String> paragraphsWithLove = new ArrayList<>();
        while (loveMatcher.find()) {
            paragraphsWithLove.add(loveMatcher.group());
        }

        // Tokenization
        InputStream tokenizerModelStream = new FileInputStream("en-token.bin");
        TokenizerModel tokenizerModel = new TokenizerModel(tokenizerModelStream);
        TokenizerME tokenizer = new TokenizerME(tokenizerModel);
        String[] tokens = tokenizer.tokenize(ebookContent);

        // Word frequency analysis
        Map<String, Integer> wordFrequency = Arrays.stream(tokens)
            .collect(Collectors.toMap(
                word -> word,
                word -> 1,
                Integer::sum
            ));

        // Stopword removal (simple list of stopwords, you can customize it)
        List<String> stopwords = Arrays.asList("the", "is", "in", "and", "to", "of", "that", "a", "with");
        List<String> filteredTokens = Arrays.stream(tokens)
            .filter(token -> !stopwords.contains(token.toLowerCase()))
            .collect(Collectors.toList());

        // Lemmatization (requires a dictionary-based lemmatizer for Java)
        InputStream dictLemmatizer = new FileInputStream("en-lemmatizer.dict");
        DictionaryLemmatizer lemmatizer = new DictionaryLemmatizer(dictLemmatizer);
        String[] lemmas = lemmatizer.lemmatize(filteredTokens.toArray(new String[0]), new String[filteredTokens.size()]);

        // Part-of-Speech tagging and Named Entity Recognition (NER) would use libraries like Apache OpenNLP or Stanford NLP

        // Sentiment Analysis - OpenNLP doesn't have this out of the box, you'd need a custom model or use alternatives

        // Output results as a JSON file
        Map<String, Object> results = new LinkedHashMap<>();
        results.put("Filtered Specific Word", paragraphsWithLove);
        results.put("Word Occurrence", wordFrequency);
        results.put("Filtered Tokens", filteredTokens);
        results.put("Lemmatized Tokens", Arrays.asList(lemmas));

        // Write JSON to file
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OUTPUT_FILE), "utf-8"))) {
            writer.write(new com.google.gson.GsonBuilder().setPrettyPrinting().create().toJson(results));
        }

        // Generate PDF report (using PDFBox)
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(100, 700);
        contentStream.showText("Text Analysis Report");
        contentStream.endText();
        contentStream.close();

        document.save("output/text_analysis_report.pdf");
        document.close();
    }
}
