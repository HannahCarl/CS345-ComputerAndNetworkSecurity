//Hannah Carl
//Password Cracker HW 1
//CS 345

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class passwordCracker {
	
	
	//Method: Check hashes of password for match
	public static boolean checkPasswordMatches(String newHash, String givenHash, String word, BufferedWriter passFile) {
		boolean answer = false;
		
		if(newHash.equals(givenHash)) {
			answer = true;
			try {
				passFile.append(givenHash + ":" + word);
				passFile.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(givenHash + ":" + word);
			
		}
		
		
		return answer;
		
	}
	//Method: Buid sha 256 hash
	//get_SHA_256_Secure Password
    //Based on practice example code done in CS 360, SHAExample.java
	public static String sha256Hash (String wordToHash) {
		
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] messageDigest = md.digest(wordToHash.getBytes());
			// Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
  
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
  
            return hashtext;
		}
		
		// For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            System.out.println("Exception thrown"
                               + " for incorrect algorithm: " + e); 
  
            return null; 
        } 
	}
	
	//Method - Crack passwords
	public static void passwordCrack( List<String> pwList) throws FileNotFoundException {
		int numberOfPasswordsCracked = 0;
		int currPasswordCounter = 1;
		String hashedPassword = "";
		String hashedPassword2 = "";
		String hashedPassword3 = "";
		String hashedPassword4 = "";
		String hashedPassword5 = "";
		File fileWords = new File("/usr/share/dict/words");
		Scanner sc = new Scanner(fileWords);
		String wordFromDict = "";
		String wordFromDictExtraLetter = "";
		String wordToTry = "";
		String ruleOneWord = "";
		String ruleOneWord2 = "";
		String ruleThreeWord = "";
		String ruleThreeWord2 = "";
		String ruleThreeWord3 = "";
		String ruleFiveWord = "";
		String ruleFiveWord2 = "";
		int ruleTwoNum = 9999;
		int ruleFourNum = 999999;
		NumberFormat formatRuleFourA = new DecimalFormat("000000");
		NumberFormat formatRuleFourB = new DecimalFormat("00000");
		NumberFormat formatRuleFourC = new DecimalFormat("0000");
		NumberFormat formatRuleFourD = new DecimalFormat("000");
		NumberFormat formatRuleFourE = new DecimalFormat("00");
		String ruleFourWord = "";
		String ruleFourWordA = "";
		String ruleFourWordB = "";
		String ruleFourWordC = "";
		String ruleFourWordD = "";
		String ruleFourWordE = "";
		String[] specialChar = {"!","*","~","#","!*", "!~","!#", "*!","*~", "*#","~!","~*","~#","#!","#*","#~","!*~", "!*#", "!~*", "!~#", "!#*", "!#~", "*!~", "*!#", "*~!", "*~#", "*#!", "*#~", "~!*", "~!#", "~*!", "~*#", "~#!", "~#*", "#!*", "#!~", "#*!", "#*~", "#~!", "#~*","!*~#", "!*#~", "!~*#", "!~#*", "!#*~", "!#~*", "*!~#", "*!#~", "*~!#", "*~#!", "*#!~", "*#~!", "~!*#", "~!#*", "~*!#", "~*#!", "~#!*", "~#*!", "#!*~", "#!~*", "#*!~", "#*~!", "#~!*", "#~*!"};
		String ruleTwoWord = "";
		int[] numberList = {0,1,2,3,4,5,6,7,8,9};
		boolean matchFound = false;
		boolean matchFound2 = false;
		boolean matchFound3 = false;
		boolean matchFound4 = false;
		boolean matchFound5 = false;
		NumberFormat formatter = new DecimalFormat("0000");

		//New file for password output
		File file = new File("crackedPasswords.txt");
		file.delete();
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try (BufferedWriter crackPassFile = new BufferedWriter(new FileWriter("crackedPasswords.txt",true))){
			
			//Loop to check all passwords
			while(numberOfPasswordsCracked < pwList.size()) {
				
				
				
				while(sc.hasNextLine()) {
					
					if(numberOfPasswordsCracked < pwList.size()) {
						wordFromDict = sc.nextLine();
						wordToTry = wordFromDict;
						//Rule #1
						//Regular 7 letter words
						if(wordToTry.length() == 7) {
							wordToTry = wordToTry.substring(0, 1).toUpperCase() + wordToTry.substring(1).toLowerCase();
							for(int i = 0; i < 10; i++) {
								ruleOneWord = wordToTry + i;
								hashedPassword = sha256Hash(ruleOneWord);
								//System.out.println("Rule one word: " + ruleOneWord + "HashedPassword: " + hashedPassword);
								for(int k = 0; k < pwList.size();k++) {
									matchFound = checkPasswordMatches(hashedPassword, pwList.get(k), ruleOneWord, crackPassFile);
									if(matchFound) {
										numberOfPasswordsCracked += 1;
										matchFound = false;
									}
									
								}
								
								
								for(int j= 0; j< numberList.length; j++) {
									ruleOneWord2 = ruleOneWord + numberList[j];
									hashedPassword = sha256Hash(ruleOneWord2);
									//System.out.println("Rule one word: " + ruleOneWord + "HashedPassword: " + hashedPassword);
									for(int k = 0; k < pwList.size();k++) {
										matchFound = checkPasswordMatches(hashedPassword, pwList.get(k), ruleOneWord2, crackPassFile);
										if(matchFound) {
											numberOfPasswordsCracked += 1;
											matchFound = false;
										}
										
									}
								}
							}
						}
					}
					if(numberOfPasswordsCracked < pwList.size()) {
						//Plural 6 letter words
						if(wordToTry.length() == 6) {
							wordToTry = wordToTry.substring(0, 1).toUpperCase() + wordToTry.substring(1).toLowerCase();
							for(int i = 0; i < 10; i++) {
								ruleOneWord = wordToTry + "s" + i;
								hashedPassword = sha256Hash(ruleOneWord);
								//System.out.println("Rule one word: " + ruleOneWord + "HashedPassword: " + hashedPassword);
								for(int k = 0; k < pwList.size();k++) {
									matchFound = checkPasswordMatches(hashedPassword, pwList.get(k), ruleOneWord, crackPassFile);
									if(matchFound) {
										numberOfPasswordsCracked += 1;
										matchFound = false;
									}
									
								}
								for(int j= 0; j< numberList.length; j++) {
									ruleOneWord2 = ruleOneWord + numberList[j];
									hashedPassword = sha256Hash(ruleOneWord2);
									
									for(int k = 0; k < pwList.size();k++) {
										matchFound = checkPasswordMatches(hashedPassword, pwList.get(k), ruleOneWord2, crackPassFile);
										if(matchFound) {
											numberOfPasswordsCracked += 1;
											matchFound = false;
										}
										
									}
								}
							}
						}
						//end of Rule #1
					}
					
					if(numberOfPasswordsCracked < pwList.size()) {
						//Rule #3
						if(wordToTry.length() == 5) {
							ruleThreeWord = new String (wordFromDict);
							ruleThreeWord2 = new String (wordFromDict);
							ruleThreeWord3 = new String (wordFromDict);
							ruleThreeWord = ruleThreeWord.replace('a', '@');
							ruleThreeWord = ruleThreeWord.replace('l', '1');
							ruleThreeWord2 = ruleThreeWord.replace('a', '@');
							ruleThreeWord3 = ruleThreeWord.replace('l', '1');
							
							//System.out.println("Word to try : " + wordToTry + " RuleThreeWord : " + ruleThreeWord);
							hashedPassword = sha256Hash(ruleThreeWord);
							hashedPassword2 = sha256Hash(ruleThreeWord2);
							hashedPassword3 = sha256Hash(ruleThreeWord3);
							for(int k = 0; k < pwList.size();k++) {
								if(ruleThreeWord.equals(ruleThreeWord2) || ruleThreeWord.equals(ruleThreeWord3)) {
									matchFound = checkPasswordMatches(hashedPassword, pwList.get(k), ruleThreeWord, crackPassFile);
								}
								else {
									matchFound = checkPasswordMatches(hashedPassword, pwList.get(k), ruleThreeWord, crackPassFile);
									matchFound2 = checkPasswordMatches(hashedPassword2, pwList.get(k), ruleThreeWord2, crackPassFile);
									matchFound3 = checkPasswordMatches(hashedPassword3, pwList.get(k), ruleThreeWord3, crackPassFile);
								}
								if(matchFound) {
									numberOfPasswordsCracked += 1;
									matchFound = false;
								}
								if(matchFound2) {
									numberOfPasswordsCracked += 1;
									matchFound2 = false;
								}
								if(matchFound3) {
									numberOfPasswordsCracked += 1;
									matchFound3 = false;
								}
								
							}
						}//end of Rule #3
					}
					
					if(numberOfPasswordsCracked < pwList.size()) {
						//Rule #5
						hashedPassword = sha256Hash(wordFromDict);
						for(int k = 0; k < pwList.size();k++) {
							matchFound = checkPasswordMatches(hashedPassword, pwList.get(k), wordFromDict, crackPassFile);
							if(matchFound) {
								numberOfPasswordsCracked += 1;
								matchFound = false;
							}
						}
					}
					if(numberOfPasswordsCracked < pwList.size()) {
						//Add extra last letter
						wordFromDictExtraLetter = wordFromDict + wordFromDict.charAt(wordFromDict.length()-1);
						hashedPassword = sha256Hash(wordFromDictExtraLetter);
						for(int k = 0; k < pwList.size();k++) {
							matchFound = checkPasswordMatches(hashedPassword, pwList.get(k), wordFromDictExtraLetter, crackPassFile);
							if(matchFound) {
								numberOfPasswordsCracked += 1;
								matchFound = false;
							}
						}
					}
					if(numberOfPasswordsCracked < pwList.size()) {
						//Add s
						ruleFiveWord = wordFromDict + "s";
						ruleFiveWord2 = wordFromDictExtraLetter + "s";
						hashedPassword = sha256Hash(ruleFiveWord);
						hashedPassword2 = sha256Hash(ruleFiveWord2);
						for(int k = 0; k < pwList.size();k++) {
							matchFound = checkPasswordMatches(hashedPassword, pwList.get(k), ruleFiveWord, crackPassFile);
							matchFound2 = checkPasswordMatches(hashedPassword2, pwList.get(k), ruleFiveWord2, crackPassFile);
							if(matchFound) {
								numberOfPasswordsCracked += 1;
								matchFound = false;
							}
							if(matchFound2) {
								numberOfPasswordsCracked += 1;
								matchFound2 = false;
							}
						}
					}
					if(numberOfPasswordsCracked < pwList.size()) {
						//Add ing
						ruleFiveWord = wordFromDict + "ing";
						ruleFiveWord2 = wordFromDictExtraLetter + "ing";
						hashedPassword = sha256Hash(ruleFiveWord);
						hashedPassword2 = sha256Hash(ruleFiveWord2);
						for(int k = 0; k < pwList.size();k++) {
							matchFound = checkPasswordMatches(hashedPassword, pwList.get(k), ruleFiveWord, crackPassFile);
							matchFound2 = checkPasswordMatches(hashedPassword2, pwList.get(k), ruleFiveWord2, crackPassFile);
							if(matchFound) {
								numberOfPasswordsCracked += 1;
								matchFound = false;
							}
							if(matchFound2) {
								numberOfPasswordsCracked += 1;
								matchFound2 = false;
							}
						}
					}
					if(numberOfPasswordsCracked < pwList.size()) {
						//Add ings
						ruleFiveWord = wordFromDict + "ings";
						ruleFiveWord2 = wordFromDictExtraLetter + "ings";
						hashedPassword = sha256Hash(ruleFiveWord);
						hashedPassword2 = sha256Hash(ruleFiveWord2);
						for(int k = 0; k < pwList.size();k++) {
							matchFound = checkPasswordMatches(hashedPassword, pwList.get(k), ruleFiveWord, crackPassFile);
							matchFound2 = checkPasswordMatches(hashedPassword2, pwList.get(k), ruleFiveWord2, crackPassFile);
							if(matchFound) {
								numberOfPasswordsCracked += 1;
								matchFound = false;
							}
							if(matchFound2) {
								numberOfPasswordsCracked += 1;
								matchFound2 = false;
							}
						}
					}
	
				}// end while sc 
				
				
				//Rule #2
				
				if(numberOfPasswordsCracked < pwList.size()) {
					while(ruleTwoNum > 999) {
						for(int i=0; i < specialChar.length; i++) {
							ruleTwoWord = specialChar[i] + ruleTwoNum;
							hashedPassword = sha256Hash(ruleTwoWord);
							
							for(int k = 0; k < pwList.size();k++) {
								matchFound = checkPasswordMatches(hashedPassword, pwList.get(k), ruleTwoWord, crackPassFile);
								if(matchFound) {
									numberOfPasswordsCracked += 1;
									matchFound = false;
								}
							}
						}
		
						ruleTwoNum = ruleTwoNum - 1;
					}
				}
				if(numberOfPasswordsCracked < pwList.size()) {
					while(ruleTwoNum >= 0) {
						for(int i=0; i < specialChar.length; i++) {
							ruleTwoWord = specialChar[i] + formatter.format(ruleTwoNum);
							
							//System.out.println(ruleTwoWord);
							hashedPassword = sha256Hash(ruleTwoWord);
							
							
							//System.out.println(ruleTwoWord);
							for(int k = 0; k < pwList.size();k++) {
								matchFound = checkPasswordMatches(hashedPassword, pwList.get(k), ruleTwoWord, crackPassFile);
								//System.out.println(matchFound);
								if(matchFound) {
									//System.out.println("We found a match!");
									numberOfPasswordsCracked += 1;
									matchFound = false;
									
								}//end if
							}//end for
						}//end for
		
						ruleTwoNum = ruleTwoNum - 1;
					}//end while
				}//end if
			
				
				
				//Rule #4
				if(numberOfPasswordsCracked < pwList.size()) {
					while(ruleFourNum > 99999) {
						
							ruleFourWord = "" + ruleFourNum;
							hashedPassword = sha256Hash(ruleFourWord);
							//System.out.println(ruleFourWord);
							for(int k = 0; k < pwList.size();k++) {
								matchFound = checkPasswordMatches(hashedPassword, pwList.get(k), ruleFourWord, crackPassFile);
								if(matchFound) {
									numberOfPasswordsCracked += 1;
									matchFound = false;
								}
							
						}
		
						ruleFourNum = ruleFourNum - 1;
					}
				}
				if(numberOfPasswordsCracked < pwList.size()) {
					while(ruleFourNum >= 0) {
						
							ruleFourWordA = "" + formatRuleFourA.format(ruleFourNum);
							ruleFourWordB = "" + formatRuleFourB.format(ruleFourNum);
							ruleFourWordC = "" + formatRuleFourC.format(ruleFourNum);
							ruleFourWordD = "" + formatRuleFourD.format(ruleFourNum);
							ruleFourWordE = "" + formatRuleFourE.format(ruleFourNum);
							
							/*System.out.println(ruleFourWordA);
							System.out.println(ruleFourWordB);
							System.out.println(ruleFourWordC);
							System.out.println(ruleFourWordD);
							System.out.println(ruleFourWordE);*/
							
							//System.out.println(ruleTwoWord);
							hashedPassword = sha256Hash(ruleFourWordA);
							hashedPassword2 = sha256Hash(ruleFourWordB);
							hashedPassword3 = sha256Hash(ruleFourWordC);
							hashedPassword4 = sha256Hash(ruleFourWordD);
							hashedPassword5 = sha256Hash(ruleFourWordE);
							
							
							//System.out.println(ruleTwoWord);
							for(int k = 0; k < pwList.size();k++) {
								matchFound = checkPasswordMatches(hashedPassword, pwList.get(k), ruleFourWordA, crackPassFile);
								matchFound2 = checkPasswordMatches(hashedPassword2, pwList.get(k), ruleFourWordB, crackPassFile);
								matchFound3 = checkPasswordMatches(hashedPassword3, pwList.get(k), ruleFourWordC, crackPassFile);
								matchFound4 = checkPasswordMatches(hashedPassword4, pwList.get(k), ruleFourWordD, crackPassFile);
								matchFound5 = checkPasswordMatches(hashedPassword5, pwList.get(k), ruleFourWordE, crackPassFile);
								//System.out.println(matchFound);
								if(matchFound) {
									//System.out.println("We found a match!");
									numberOfPasswordsCracked += 1;
									matchFound = false;
									
								}//end if
								if(matchFound2) {
									//System.out.println("We found a match!");
									numberOfPasswordsCracked += 1;
									matchFound2 = false;
									
								}//end if
								if(matchFound3) {
									//System.out.println("We found a match!");
									numberOfPasswordsCracked += 1;
									matchFound3 = false;
									
								}//end if
								if(matchFound4) {
									//System.out.println("We found a match!");
									numberOfPasswordsCracked += 1;
									matchFound4 = false;
									
								}//end if
								if(matchFound5) {
									//System.out.println("We found a match!");
									numberOfPasswordsCracked += 1;
									matchFound5 = false;
									
								}//end if
							}//end for
						
		
						ruleFourNum = ruleFourNum - 1;
					}//end while
				}//end if
				
				//To prevent accidental infinite loop if password does not get cracked
				currPasswordCounter +=1;
				if(currPasswordCounter > pwList.size()) {
					break;
				}//end if
			}// end While	
			sc.close(); //Close scanner
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			
	}//end method
			
		
	public static void main(String[] args) throws Exception{
		File file = new File("password_hashes.txt");
		
		Scanner sc = new Scanner(file);
		String nextLine = "";
		List<String> passwordList = new ArrayList<>();
		
		
		
		while(sc.hasNextLine()) {
			nextLine = sc.nextLine();
			//System.out.println(nextLine);
			if(nextLine.indexOf(':') != -1) {
				nextLine = nextLine.replaceAll("\\s+","");
				nextLine = nextLine.replaceAll("}","");
				nextLine = nextLine.toLowerCase();
				String[] currLine = nextLine.split(":");
				passwordList.add(currLine[1]);
				//System.out.println(currLine[1]);
			}//end if
			
			
			//passwordList.add(currLine[1]);
			
			
			
		}//end while
		
	
	passwordCrack(passwordList);
	
	//System.out.println("End of password cracker");	
	
	sc.close();
	
	}//end main

	

}//end class
