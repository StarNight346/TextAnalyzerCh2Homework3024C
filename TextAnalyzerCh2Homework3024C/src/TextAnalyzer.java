import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

//Creating a class to handle which words are in the file and their frequency
class Word {
	
	String word;
	int frequency;
	
	Word(String word) {
		this.word = word;
		frequency = 1;
	}
	
	public int getFrequency() {
		return this.frequency;
	}
}

public class TextAnalyzer {
	
	//Create lists
	static List<Word> WordList = new ArrayList<Word>();
	static List<String> StringList = new ArrayList<String>();
	
	//creates a list of every word in the file
	public static List<String> ReadFile(String file) {
		
		
		//Try to read all lines in the file
		try {
		    Scanner scnr = new Scanner(new File(file));
		    while (scnr.hasNextLine()) {
		    String data = scnr.next();
		    StringList.add(data);
		      }
		    scnr.close();
		}
		//Catches exceptions if found
		catch (Exception e) {
			System.out.println("You have an error.");
			e.printStackTrace();
		}
		
		return StringList;
	}
	
	//checks to see if word is in unique word array and adds frequency if it is
	public static void CheckWord(String word) {
		
		if(WordList.size() == 0) {
			WordList.add(new Word(word));
		} else {
			boolean isMatch = false;
			
			for (Word input: WordList) {
				
			if(word.equals(input.word)) {
				input.frequency += 1;
				isMatch = true;
			}				
		  }
			if (isMatch == false) {
				WordList.add(new Word(word));
			}
		}
	 }
	
	//Method to sort the word by frequency
	public static List<Word> SortWord (List<Word> wordList) {
		
		Comparator<Word> byFrequency = Comparator.comparing(Word::getFrequency);
		wordList.sort(byFrequency.reversed());
		
		return wordList;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//reads file
		ReadFile("TheRavenPoem.txt");	
		
		//checks words
		for (String input: StringList)
			{
			CheckWord(input);
			}
		
		//sort words
		WordList = SortWord(WordList);
		
		//outprints words
		for (Word input: WordList)
		{
		System.out.print("Word: " + input.word + "\n " + "Frequency: " + input.frequency + "\n\n");;
		}
		
	}

}
