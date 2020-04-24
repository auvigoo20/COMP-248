// -------------------------------------------------------
// Assignment 3
// Written by: Auvigoo Ahmed 40128901
// For COMP 248 Section P - Fall 2019
// -------------------------------------------------------

/*The purpose of this program is to generate 10000 passwords by selecting three random words from the text "Alice in Wonderland"
 * and meeting certain criteria. The password must not contain a newline character, it must not contain a one-letter word, it must not 
 * contain the same word more than once, it must contain an uppercase character, a lowercase character and a special case character. If the 
 * program  generates a password without a lowercase character, the program stops generating the passwords. Additionally,
 * after each valid password is generated, the number of failed passwords generated before the new one was generated is displayed.*/

import java.util.Random;

public class Assignment3Q1 {

	public static void main(String[] args) {

		final String[][][] book = {
				{ 
						{ "ALICE was beginning to get very tired of sitting by her sister on the\n",
						"bank, and of having nothing to do. Once or twice she had peeped into the\n",
						"book her sister was reading, but it had no pictures or conversations in\n",
						"it, \"and what is the use of a book,\" thought Alice, \"without pictures or\n", 
						"conversations?\"\n" },

						{ "So she was considering in her OWN mind (as well as she could, for the\n",
						"day made her feel very sleepy and stupid), whether the pleasure of\n",
						"making a daisy-chain would be worth the trouble of getting up and\n",
						"picking the daisies, when suddenly a White Rabbit with pink eyes ran\n", 
						"close by her.\n" },

						{ "There was nothing so very remarkable in that, nor did Alice think it so\n",
						"very much out of the way to hear the Rabbit say to itself, \"Oh dear! Oh\n",
						"dear! I shall be too late!\" But when the Rabbit actually took a watch\n",
						"out of its waistcoat-pocket and looked at it and then hurried on, Alice\n",
						"started to her feet, for it flashed across her mind that she had never\n",
						"before seen a rabbit with either a waistcoat-pocket, or a watch to take\n",
						"out of it, and, burning with curiosity, she ran across the field after\n",
						"it and was just in time to see it pop down a large rabbit-hole, under\n",
						"the hedge. In another moment, down went Alice after it!" }
				},
				{ 
						{ "\"WHAT a curious feeling!\" said Alice. \"I must be shutting up like a\n", 
						"telescope!\"\n" },

						{ "And so it was indeed! She was now only ten inches high, and her face\n",
						"brightened up at the thought that she was now the right size for going\n",
						"through the little door into that lovely garden.\n" },

						{ "After awhile, finding that nothing more happened, she decided on going\n",
						"into the garden at once; but, alas for poor Alice! When she got to the\n",
						"door, she found she had forgotten the little golden key, and when she\n",
						"went back to the table for it, she found she could not possibly reach\n",
						"it: she could see it quite plainly through the glass and she tried her\n",
						"best to climb up one of the legs of the table, but it was too slippery,\n",
						"and when she had tired herself out with trying, the poor little thing\n",
						"sat down and cried.\n" },

						{ "\"Come, there's no use in crying like that!\" said Alice to herself rather\n",
						"sharply. \"I advise you to leave off this minute!\" She generally gave\n",
						"herself very good advice (though she very seldom followed it), and\n",
						"sometimes she scolded herself so severely as to bring tears into her\n", 
						"eyes.\n" },

						{ "Soon her eye fell on a little glass box that was lying under the table:\n",
						"she opened it and found in it a very small cake, on which the words \"EAT\n",
						"ME\" were beautifully marked in currants. \"Well, I'll eat it,\" said\n",
						"Alice, \"and if it makes me grow larger, I CAN reach the key; and if it\n",
						"makes me grow smaller, I can creep under the door: so either way I'll\n",
						"get into the garden, and I don't care which happens!\"\n" },

						{ "She ate a little bit and said anxiously to herself, \"Which way? Which\n",
						"way?\" holding her hand on the top of her head to feel which way she was\n",
						"growing; and she was quite surprised to find that she remained the same\n",
						"size. So she set to work and very soon finished off the cake." }
				},
				{ 
						{ "The King and Queen of Hearts were seated on their throne when they\n",
						"arrived, with a great crowd assembled about them--all sorts of little\n",
						"birds and beasts, as well as the whole pack of cards: the Knave was\n",
						"standing before them, in chains, with a soldier on each side to guard\n",
						"him; and near the King was the White Rabbit, with a trumpet in one hand\n",
						"and a scroll of parchment in the other. In the very middle of the court\n",
						"was a table, with a large DISH of tarts upon it. \"I wish they'd get the\n",
						"trial done,\" Alice thought, \"and hand 'round the refreshments!\"\n" },

						{ "The judge, by the way, was the King and he wore his crown over his great\n",
						"wig. \"That's the jury-box,\" thought Alice; \"and those twelve creatures\n",
						"(some were animals and some were birds) I suppose they are the jurors.\"\n" },

						{ "Just then the White Rabbit cried out \"Silence in the court!\"\n" },

						{ "\"HERALD! read the accusation!\" said the King." } 
				} 
				};

		Random rand = new Random();// The random number generator function
		int pageNum;//The chosen page number
		int paragraphNum;//The chosen paragraph number
		int lineNum;//The chosen line number
		
		String wordArray[] = new String[3];// The array containing the three randomly chosen words
		int totalPass = 0;// Counter for the total number of generated passwords
		int newLine = 0;//Counter for the number of passwords containing newline characters generated
		int single = 0;//Counter for the number of passwords containing single characters generated
		int sameWord = 0;//Counter for the number of passwords containing the same word generated
		int passLength = 0;//Counter for the number of passwords not respecting the length generated
		int upperCase = 0;//Counter for the number of passwords not containing an uppercase character generated
		int lowerCase = 0;//Counter for the number of passwords not containing a lowercase character generated
		int special = 0;//Counter for the number of passwords not containing a special character generated

		//Generating 10000 passwords
		while (totalPass < 10000) {


			for (int i = 0; i < 3; i++) {
			// Finding the random page, paragraph and line
				pageNum = rand.nextInt(book.length);
				paragraphNum = rand.nextInt(book[pageNum].length);
				lineNum=rand.nextInt(book[pageNum][paragraphNum].length);
				
			//Choosing random words from the chosen line
				String chosenLine = book[pageNum][paragraphNum][lineNum];
				String[] lineWords = chosenLine.split(" ");

				int numOfWords = lineWords.length;

				int randomWordNum = rand.nextInt(numOfWords);
				String word = lineWords[randomWordNum];

			//Filling up wordArray with 3 chosen random words
				wordArray[i] = word;

			}

			// New Line
			if (wordArray[0].contains("\n") || wordArray[0].contains("\n") || wordArray[1].contains("\n")
					|| wordArray[1].contains("\n") || wordArray[2].contains("\n") || wordArray[2].contains("\n")) {

				newLine++;
				continue;
			}
			
			// Single
			if (wordArray[0].length()<=1 || wordArray[1].length()<=1 || wordArray[2].length()<=1 ) {

				single++;
				continue;
			}

			// Same Word
			if (wordArray[0].compareTo(wordArray[1]) == 0 || wordArray[0].compareTo(wordArray[2]) == 0
					|| wordArray[1].compareTo(wordArray[2]) == 0) {

				sameWord++;
				continue;

			}

			//Concatenating the three words to form a candidate password
			String candidatePass = (wordArray[0].concat(wordArray[1])).concat(wordArray[2]);

			// Length
			int candidateLength = candidatePass.length();
			if (candidateLength > 16 || candidateLength < 8) {

				passLength++;
				continue;

			}
			
			// Upper Case
			if (candidatePass.equals(candidatePass.toLowerCase())) {

				upperCase++;
				continue;
			}
			
			//Special
			int specialCount = 0;
			for (int i = 0; i <= candidateLength - 1; i++) {//Comparing each character's ASCII number to non-alphabetical ASCII numbers using casting

				char letter = candidatePass.charAt(i);
				if (((int) letter < 65 || (int) letter > 90) && ((int) letter < 97 || (int) letter > 122)) {
					specialCount++;
				}
			}
			if (specialCount != 1) {
				special++;
				continue;
			}
			
			// Lower Case
			if (candidatePass.equals(candidatePass.toUpperCase())) {

				lowerCase++;
				break;//Exiting the while loop if a password that does not meet the lower case requirement is generated	
			}
			

			
			//When the candidate password skips all the previous if-statements, it satisfies each condition and prints out
				totalPass++;
				System.out.printf("Password = %-13s", candidatePass);//Properly formatting the output
				System.out.print("\t\tNewLine = " + newLine);
				System.out.print("  Single = " + single);
				System.out.print("  Equal = " + sameWord);
				System.out.print("  Length = " + passLength);
				System.out.print("  Upper = " + upperCase);
				System.out.print("  Lower = " + lowerCase);
				System.out.println("  Special = " + special);

				//Re-initializing each counter for the next password
				newLine = 0;
				single = 0;
				sameWord = 0;
				passLength = 0;
				upperCase = 0;
				lowerCase = 0;
				special = 0;
		}
		
		
		System.out.println("\nTotal Passwords Generated: " + totalPass);
		System.out.println("\nThanks for using the password generator game. Good bye");//Closing message

	}//end of main
}//end of class
