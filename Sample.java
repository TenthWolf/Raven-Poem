import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Sample {

	// Creates method for counting all words
	// HashMap will be used to store words
	// Iterator can be used to loop through collections. EX: .hasnext and .next
	// throws FileNotFound to prevent errors for popping up on Scanner
	static void countAllWords(String fileName, Map<String, Integer> text) throws FileNotFoundException {
		Scanner file = new Scanner(new File(fileName)); // reads from the file
		while (file.hasNext()) { // iterator loop returns true if there is another string in the file making it
									// // print the next word
			String line = file.next(); // starts at first word of the poem and proceeds to the next word after loop
										// ends
			Integer count = text.get(line); // create count to store number of appeared words
			if (count != null) { // count has to have a value
				count++; // increment after program runs
			} else
				count = 1;
			text.put(line, count); // counts the word and how many times each word appears
		}
		file.close();
	}

	// Creates Method for finding the word used most
	static void getMostUsed(String fileName, Map<String, Integer> text) throws FileNotFoundException {
		Scanner file = new Scanner(new File(fileName));
		while (file.hasNext()) { // check
			String line = file.next(); // Jumps to next word
			Integer count = text.get(line); // counts every word
			if (count != null) { // count has to have a value
				count++; // increment after program runs
			} else
				count = 1;
			text.put(line, count);
		}
		file.close();
	}

	// Creates method for getting the word that appeared most
	// Map.entrySet method returns a collection-view of the map, whose elements are
	// of this class.
	static int getMaxWordOccurrence(Map<String, Integer> max) {

		int top = 0; // initiate top number variable
		for (Map.Entry<String, Integer> poem : max.entrySet()) { // for loop
			if (poem.getValue() > top) { // poem returns the value corresponding to this entry
				top = poem.getValue(); // top receives the value from poem

			}
		}
		return top;
	}

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		// HashMap is used to store words
		Map<String, Integer> words = new HashMap<String, Integer>();
		countAllWords("C:\\Users\\17876\\Documents\\Raven.txt", words); // call CountAllWords method and search
																		// //forfile location
		words.forEach((k, v) -> System.out.println("Key: " + k + " , Value: " + v));

		Map<String, Integer> max = new HashMap<String, Integer>();
		getMostUsed("C:\\Users\\17876\\Documents\\Raven.txt", max); // calls getMostUsed method and search // for
																	// filelocation

		Collection<Integer> values = max.values();
		ArrayList<Integer> list = new ArrayList<Integer>(values);
		Collections.sort(list, Collections.reverseOrder());
		for (int i = 0; i < 20; i++)
			System.out.println(list.get(i));

		int total = getMaxWordOccurrence(max); // calls maxWordOccurrence method including the HashMap variable
		for (Entry<String, Integer> poem : max.entrySet()) {
			if (poem.getValue() == total) {
				System.out.println("The most used word is " + poem);
			}
		}

	}

}
