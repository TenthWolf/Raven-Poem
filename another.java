
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import java.nio.charset.StandardCharsets;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class another {

	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		long time = System.currentTimeMillis();
		String Word;

        Map<String, Word> countMap = new HashMap<String, Word>();

        //connect to wikipedia and get the HTML
        System.out.println("Downloading page...");
        Document doc = Jsoup.connect("http://en.wikipedia.org/").get();

        //Get the actual text from the page, excluding the HTML
        String text = doc.body().text();

        System.out.println("Analyzing text...");
        //Create BufferedReader so the words can be counted
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8))));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split("[^A-ZÃ…Ã„Ã–a-zÃ¥Ã¤Ã¶]+");
            for (String word : words) {
                if (" ".equals(word)) {
                    continue;
                }

                Word wordObj = countMap.get(word);
                if (wordObj == null) {
                    wordObj = new Word();
                    wordObj.word = word;
                    wordObj.count = 0;
                    countMap.put(word, wordObj);
                }

                wordObj.count++;
            }
        }

        reader.close();

        SortedSet<Word> sortedWords = new TreeSet<Word>(countMap.values());
        int i = 0;
        int maxWordsToDisplay = 10;

        String[] wordsToIgnore = {"the", "and", "a"};

        for (Word word : sortedWords) {
            if (i >= maxWordsToDisplay) { //10 is the number of words you want to show frequency for
                break;
            }

            if (Arrays.asList(wordsToIgnore).contains(word.word)) {
                i++;
                maxWordsToDisplay++;
            } else {
                System.out.println(word.count + "\t" + word.word);
                i++;
            }

        }

        time = System.currentTimeMillis() - time;

        System.out.println("Finished in " + time + " ms");
    }
	
	public static void print (String string) {
		System.out.println(string);
	}

}
