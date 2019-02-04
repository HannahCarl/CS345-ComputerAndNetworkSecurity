# CS345-ComputerAndNetworkSecurity
# HW1 CS 345 - Hannah Carl

# Project Files in ‘src’ folder:
1) crackedPasswords.txt 
	-File contains cracked passwords
2) passwordCracker.java
	-Main password cracker code
3) password_hashes.txt
	-Contains password hashes strings to be cracked
    
# To run:
1) Use an IDE (ex. Eclipse) 
2) Open project HW1-PasswordCracker
3) Run 

1) Open terminal
2) cd into Folder HW1-PasswordCracker
3) Use command ' javac passwordCracker.java'
4) Use command 'java passwordCracker'

# To add hashes to be cracked:
1) open password_hashes.txt 
2) add hash strings in the format (username:encyption[:otherstuff] )
3) save file

**Another file may be used, and the file name and directory will need to be added to Line 481 in passwordCracker.java (File file = new File("src/password_hashes.txt”);)

# To view cracked passwords:
1) open crackedPasswords.txt
