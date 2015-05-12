import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class HaikuGenerator 
{
   

	public HaikuGenerator()
	{
		final String fileLocation = "ENTER FILE LOCATION HERE";

		System.out.println(findHaiku(fileLocation));
	}
	public static void main(String[] args) 
	{
		new HaikuGenerator();
	}
	
	public static String findHaiku(String fileLocation)
	{
		String word;
		int sylNum = 0;
		int verse = 5;
		String haiku = "";
		String allHaikus = "";
		Scanner sc;

		try 
		{
			sc = new Scanner(new FileReader(new File(fileLocation)));

			while(sc.hasNext())
			{
				word = sc.next();

				sylNum += countSyllables(word);
				haiku += word + " ";

				if(sylNum == verse)
				{
					haiku += "\n";
					
					if (verse == 5) //5
					{	
						verse += 7; //12

					}
					else if (verse == 12)
					{
						verse += 5;	//17

					}
					else if (verse == 17)
					{	
						verse = 5;	//5 again
						
						allHaikus += haiku + "----------------------\n";
						
						haiku = "";
						sylNum = 0;
					}
					
					
				}
				//resets the count if the word syllables add up to more than the current verse
				else if (sylNum > verse)
				{	
					haiku = "";
					sylNum = 0;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found.");
		}
		return allHaikus;
	}

	public static int countSyllables(String word) 
	{
		int syl = 0;
		boolean vowel = false;
		int length = word.length();

		//check each word for vowels (don't count more than one vowel in a row)
		for(int i=0; i<length; i++) 
		{
			if(isVowel(word.charAt(i)) && (vowel==false)) 
			{
				vowel = true;
				syl++;
			} 
			else if (isVowel(word.charAt(i)) && (vowel==true))
			{
				vowel = true;
			} 
			else 
			{
				vowel = false;
			}
		}

		char tmpC = word.charAt(word.length()-1);
		//check for 'e' at the end, as long as not a word w/ one syllable
		if (((tmpC == 'e') || (tmpC == 'E')) && (syl != 1)) 
		{
			syl--;
		}
		return syl;
	}

	//check if a char is a vowel (count y)
	public static boolean isVowel(char c) 
	{
		if ((c == 'a') || (c == 'A')) 
			{ return true;  }
		else if ((c == 'e') || (c == 'E'))
			{ return true;  }
		else if ((c == 'i') || (c == 'I')) 
			{ return true;  }
		else if ((c == 'o') || (c == 'O')) 
			{ return true;  }
		else if ((c == 'u') || (c == 'U')) 
			{ return true;  }
		else if ((c == 'y') || (c == 'Y')) 
			{ return true;  }
		else                              
			{ return false; }
	}
}

