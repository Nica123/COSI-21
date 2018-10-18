import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;

public class Tgame2{
  //this path to wordlist file
  String path = "wordlist.txt";
  //the wordlist stores "wrodlist.txt" 
  SinglyLinkedList <String>wordlist = new SinglyLinkedList<String>();
  //the list of player 
  DoublyLinkedList<People> peopleList = new DoublyLinkedList<People>();
  //the list of word that the players are passing
  SinglyLinkedList <String>wordsL = new SinglyLinkedList<String>();
  //the threshold of edit distance, word with lower distance will be adopt
  int WORD_FLEXIBILITY = 5;
  
  //This is the constructor of the Tgame
  public Tgame2(){
    //read txt File
    try {
		readTXT();
	} catch (FileNotFoundException e) {
		// throw exception while file is not found
		e.printStackTrace();
	}
    //start setup players
    peopleSetup();
    //start Tgame and get first sentence
    System.out.println("Please enter a sentence to start:");
    getWords();
    // pass the words from first person to the last person
    passingWords();
  }
  
  //this is the module to read Txt file and store words in to SinglyLinkedlist wordlist
  public void readTXT() throws FileNotFoundException {
    try{
      File file = new File(path);
      Scanner input = new Scanner(file);
      while(input.hasNextLine()){
        String word = input.nextLine();
        wordlist.insert(word);
      }
    } catch(FileNotFoundException e) {
      System.out.println("file not found!");
    }
  }
  // this is the module to setup player and add to DoublyLinkedlist peopleList
  public void peopleSetup(){
    int numP = 0;
    Scanner sc2= new Scanner(System.in);
    do {
      System.out.println("How many people would you like to add?");
      numP = sc2.nextInt();
    } while (numP < 1);
    for(int i = 0; i < numP; i++){
      System.out.println("The name is:");
      String name = sc2.next();
      System.out.println("The SpeakingClarity(0-1) is: ");
      double SpeakingClarity = sc2.nextDouble();
      System.out.println("The ListeningAbility(0-1) is: ");
      double ListeningAbility = sc2.nextDouble();
      peopleList.insert(new People(name,SpeakingClarity,ListeningAbility));
    }
  }
  // this is module to get the first sentence from the player and store in 'wordSL'- a SinglyLinkedList
  public void getWords(){
    Scanner sc3 = new Scanner(System.in);
    String firstWords = sc3.nextLine();
    String temp = "";
    for(int i=0; i < firstWords.length(); i++){
        if(firstWords.charAt(i) != ' '){
          temp = temp + firstWords.charAt(i);
        }
        if(i == firstWords.length()-1 || firstWords.charAt(i) == ' ') {
          wordsL.insert(temp);
          temp = "";
        }
    }
  }
  
  
  /**
  This method is to make a clone of the SinglyLinkedList which contain data of type String
  Time complexity is O(n) where n is the length of SinglyLinkedList sll
  @param sll, the SinglyLinkedList<String> to be copied
  @return a copy of SinglyLinkedList<String>
  */
  public SinglyLinkedList<String> cloneStringList(SinglyLinkedList<String> sll) {
	  SinglyLinkedNode<String> curr = sll.getHead();
	  SinglyLinkedList<String> temp = new SinglyLinkedList<String>();
 	  while(curr!=null) {
		  temp.insert(curr.getData());
		  curr = curr.getNext();
	  }
 	  return temp;
  }
  

  // this is the module to pass the words until the last player and print the says and hears
  public void passingWords(){
    DoublyLinkedNode<People> curr  = peopleList.getHead();
    People currP = null;
    People nextP = null;
    if(curr.next == null) {
      System.out.println(curr.getData().getName() + " announces to the group \"" + wordsL.toString() + "\"");
      return;
    }
    while(curr.next!= null){
      currP = curr.getData();
      nextP = curr.next.getData();
      boolean parseSucceed = false;
      int askCount = 0;
      do{
    	 System.out.println(currP.getName() + " says \"" + wordsL.toString() + "\" to " + nextP.getName() + ".");
    	 parseSucceed = parseWords(currP.getSpeakingClarity(),nextP.getListeningAbility(),nextP.getName());
    	 if(parseSucceed == false && askCount < 4) {
    		 System.out.println(nextP.getName() + " asks " + currP.getName()+" to repeat what they said.");
    	 }
    	 askCount += 1;
      }while(parseSucceed == false && askCount<5);
      // if ask reach 5 times, create new sentence of 5 randomly picked words
      if(parseSucceed == false && askCount == 5) {
    	  SinglyLinkedList<String> newSentence = new SinglyLinkedList<String>();
    	  for(int j = 0; j < 5; j++) {
      		  newSentence.insert(pickRandom());
      	  }
    	  wordsL = newSentence;
      }
      curr = curr.next;
    }
    if(currP != null) {
      System.out.println(nextP.getName() + " announces to the group \"" + wordsL.toString() + "\"");
    }
  }

  /**
  This method is to parse the words and also print the sentence next person hears and print ask sentence if over 3 misheard words
  Time complexity is O(n*k) n is the length of wordsL, k is the length of wordlist
  @param SpeakingClarity, current person's SpeakingClarity
  @param ListeningAbility, next person's ListeningAbility
  @param nxtName, next person's name
  @return true if equals or fewer than 3 misheard words, false if over 3
  */
  public boolean parseWords(double SpeakingClarity, double ListeningAbility, String nxtName){
    int index = 0;
    double rand;
    int countdrop = 0;
    String heardWrods = "";
    String currWord;
    //make a copy of wordsL
    SinglyLinkedList<String> temp = cloneStringList(wordsL);
    SinglyLinkedNode <String>curr = temp.getHead();
    String words = "";
    while(curr!=null){
      rand = Math.random();
      if(rand > SpeakingClarity * ListeningAbility){
    	countdrop += 1;
        currWord = temp.remove(index).getData();
        int rand2 = (int)Math.round(2 * Math.random() + 1);
        ///start here: how to drop 1-3 chars gives dropped word
        String droppedWord = dropChar(currWord, rand2);
        String newWord = null;
        SinglyLinkedNode <String>currWordlist = wordlist.getHead();
        while(currWordlist != null){
          if(calcDist(currWordlist.getData(),droppedWord) < WORD_FLEXIBILITY){
            newWord = currWordlist.getData();
            break;
          }
          currWordlist = currWordlist.getNext();
        }
        //end of while loop for the wordlist
        if(newWord != null){
          temp.insert(newWord,index);
        }
        words = words + "___ ";
        index +=1;
        curr = curr.getNext();
      } else{
        words = words + curr.getData() + " ";
        index += 1;
        curr = curr.getNext();
      }
    }
    if(temp.getHead() == null) {
  	  for(int j = 0; j < 5; j++) {
  		  temp.insert(pickRandom());
  	  }
  	  words = temp.toString();
    }
    //end of the while loop for all words in that round, print "...hears...", and save the modified sentence
    System.out.println(nxtName +" hears \"" + words + "\"");
    
    //check if over 3 words are dropped
    if(countdrop>3) {
    	return false;
    } else {
    	wordsL = temp;
    	return true;
    }
  }
  
  /**
  This method is to generate randomly picked word in the wordlist to make up new sentence
  Time complexity is O(n) n is the size of wordlist
  @return the randomly generated String from wordlist
  */
  private String pickRandom() {
	  int RandInd = (int)Math.round(Math.random()*(wordlist.size()-1));
	  SinglyLinkedNode<String> curr = wordlist.getHead();
	  for(int i = 0; i< RandInd; i++) {
		  curr = curr.getNext();
	  }
	  return curr.getData();
  }
  
  
  /**
  This method is to calculate minimum edit distance using dynamic programming credit to pseudocode online
  Time complexity is O(m*n) m is the size of word1, n is the size of word2
  @param word1, first word
  @param word2, second word1
  @return the edit distance
  */
  public int calcDist(String word1, String word2) {
  	int len1 = word1.length();
  	int len2 = word2.length();
    // len1+1, len2+1, because finally return tb[len1][len2] which stores the value for edit distance
  	int[][] tb = new int[len1 + 1][len2 + 1];
    for (int i = 0; i <= len1; i++) {
  		tb[i][0] = i;
  	}
    for (int j = 0; j <= len2; j++) {
  		tb[0][j] = j;
  	}
    //iterate though each words, and check the last char
  	for (int i = 0; i < len1; i++) {
  		char c1 = word1.charAt(i);
  		for (int j = 0; j < len2; j++) {
  			char c2 = word2.charAt(j);
        //if last two chars equal
  			if (c1 == c2) {
  				//update tb value for +1 length
  				tb[i + 1][j + 1] = tb[i][j];
  			} else {
  				int replace = tb[i][j] + 1;
  				int insert = tb[i][j + 1] + 1;
  				int delete = tb[i + 1][j] + 1;

  				int min;
  				if (replace > insert) {
  					min = insert;
  				}else {
  					min = replace;
  				}
  				if(delete > min) {
  					min = min;
  				}else {
  					min = delete;
  				}
  				tb[i + 1][j + 1] = min;
  			}
  		}
  	}
    return tb[len1][len2];
  }

  /**
  This method is to drop letters
  Time complexity is O(m*n) m is the size of String, n is the number of letters to drop, because s.substring()is now linear
  @param word, word need to drop letters
  @param n, number of letters to be dropped
  @return word after dropped letters
  */
  public String dropChar(String word, int n){
    for(int i=0; i<n; i++){
      int index = (int)(Math.round(word.length()-1)*Math.random()); //gives a random available index of the String
      if(word.length() == 0) {
    	  return "";
      }
      if(index == 0){
        word = word.substring(1);
      } else if (index == word.length()){
        word = word.substring(0,index);
      } else {
        word = word.substring(0,index) + word.substring(index+1);
      }
    }
    return word;
  }


}
