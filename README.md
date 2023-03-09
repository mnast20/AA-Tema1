# AA-Tema1

# Algorithms for String Processing

## Edit distance:
### Levenshtein distance:
The Levenshtein Distance supports 3 operations: substitution, deletion and insertion. Its aim is to transform one string into another using the minimum number of single character edits. Also called Edit Distance, it represents the difference between the two strings. In order to calculate this distance, a (n + 1) x (m + 1) matrix is used, whereas n and m are the lengths of the two strings. The first column and row are set from the start to represent the cost of building the strings. Afterwards, the algorithm fills up the rest of the matrix as follows: When the position [i][j] is reached in the matrix, it means that the first "i" characters of the first string will be compared to the first "j" characters of the second string. In order to get the difference/distance of the two substrings, the costs of each action (insertion, deletion, substitution) are compared to one another and the minimum value will become the result. After traversing the entire matrix, the Levenshtein Distance between the two strings will be positioned at the bottom right corner.

### Longest Common Subsequence distance:
Unlike the Levenshtein Distance, the LCS Distance supports only 2 operations: insertion and deletion. Once again, a (n + 1) x (m + 1) matrix is used, but in this case, the first row and column will be declared as empty because there is no common subsequence yet. The LCS function consists in filling the matrix and getting the length of the longest common subsequence. Whenever the character at index "i" in the first string and the one at index "j" in the second string are equal, the LCS increases in length.  
Once the matrix was filled and the LCS distance was found, the Edit Distance will be calculated using the formula (n + m - 2 * LCSDistance), because the cost of a substitution is double the cost of an insertion/deletion.  
In order to get the Longest Common Subsequence, the matrix will be traversed from bottom right corner towards to the highest left corner. If the characters at index "i" in the first string and at index "j" in the second string are equal, the character will be added to the subsequence. A counter will be used to check if the newly built subsequence has reached its meant length. Because the traversal was from right to left, the resulted subsequence will be/come out reversed so in order to get the appropriate/proper result, the string will converted to its meant form.  

### Tests:
I wanted to treat different cases for the Edit Distance algorithms, such as comparing two entirely different words, two words that are the same, shorter and longer words. I mostly focused on the longer strings because I wanted to see the reaction time of the program.The longer words were chosen based off of articles discussing the ranking of the longest words. In the last test for Edit Distance I managed to include the word with the longest length (approximately 189000 characters) and compared it to another word present in the list of longest words.

### Links for the longest words:
- https://irisreading.com/10-longest-words-in-the-english-language/
- https://en.wikipedia.org/wiki/Longest_word_in_English
- https://en.wikipedia.org/wiki/Longest_words
- https://www.grammarly.com/blog/14-of-the-longest-words-in-english/
- https://www.getunderlined.com/writings/longest-word-in-english/

### Algorithms and Implementation:
Both the algorithms and implementations are taken from the Wikipedia pages for both types of distances. However, the function for finding the actual subsequence string was implemented by me. I chose the dynamic programming approach because the time complexity of the two algorithms is O(m * n).

### Links:
- (Levenshtein Distance) https://en.wikipedia.org/wiki/Levenshtein_distance
- (LCS Distance) https://en.wikipedia.org/wiki/Longest_common_subsequence_problem

## Pattern Searching using Trie:
The pattern Searching for both Tries is done case sensitive.

### Standard Trie:
A Standard Trie inserts every letter of a word one after another, meaning that a letter will become the child of its predecessor. In case there is a text needed to be inserted, the Trie would have a number of levels equal to the length of the text and the search for any word that isn't the first will be impossible. In order to avoid such a scenario, I decided to split the text into words and insert every word in the Trie so that searching for a prefix/word will be possible. In order to insert as much text (from the original) as possible in the trie, I settled on considering typographical symbols and punctuation marks as parts of words. However, if a suffix is searched, the Trie will not be able to find it.  
If a new word is inserted, for every letter that doesn't have a corresponding child node, a new one will be created at the position of the character in the ASCII table. Once the entire word is inserted, the lastly created node will be marked as the end of word.  
The search function checks if the current node has a child occupying the position of the searched character in the ASCII table. In case one of the pattern's letters was not found, it means that the pattern was either not a word or not a prefix in the text.  
The delete function recursively traverses the Trie, searching for the node corresponding to the last character in the given word. Once it is found and confirmed that it is also the end of a word, the visited nodes are being deleted from last to first. I also use a checking class in order to verify if the word was deleted properly and if the pattern still remains a prefix in the text. However, my focus on this project wasn't the deletion of words, but pattern searching using Tries, therefore, I make one deletion per test, the first given pattern to be more exact.

### Suffix Tree:
A Suffix Tree is meant to store every substring of a given text so that searching for any type of pattern will be an easy task. Knowing this, we can safely determine that this Trie will need to allocate much more memory than the Standard Trie. In addition, every child node will store a list of indexes representing every appearance in the text.  
Inserting a substring is done recursively; for every character, new child node is created at its designated position (index in the ASCII table) in the array of cildren nodes. The index of the character in the original string will be added to the node's list of indexes signifying where that character appeared in the text.  
The search function is recursively called for every character in the given pattern. If the proper index in the array of children nodes is not empty, then the search continues, advancing down the hierarchy. Finally, the list of indexes where the last character (in the pattern) was found will be returned. In order to get the exact indexes of where the pattern starts in the text, the length of the pattern will be substracted from every index.

### Tests:
First, I tested the algorithms on smaller strings containing few words separated only by spaces. I also tried generating strings using an online generator but there weren't many patterns repeating themselves. Lastly, I settled on using pieces of articles up to 2500 characters because I mostly wanted to test the limits of the Suffix Tree, which from what I learned through testing, cannot exceed 1250 characters on Linux VM with 4 GB RAM (limit of 2600 characters on Windows with 16 GB RAM), since it generates an "Out of Memory" error (the program probably ran out of memory). The edit distance tests were checked using online calculators and the GeeksForGeeks site and the Trie tests were verified with the following commands:
    head -2 test_number.in | tail -1 | grep - bo "pattern" for the Suffix Trie search
    head -2 test_number.in | tail -1 | grep "pattern" for the Standard Trie search

### Algorithms and Implementation:
Both the algorithms and implementations are inspired from the solutions provided by GeeksForGeeks, but there are some minor tweaks I made so that the algorithms would accept any ASCII characters (Standard Tree's implementation). The time complexity for the insert/search/delete word functions is O(word length). As for the Suffix Tree, the time complexity is O(pattern length + number of appearances).

### Links:
- (Standard Trie) https://www.geeksforgeeks.org/trie-insert-and-search/  
                  https://www.geeksforgeeks.org/trie-delete/?ref=lbp
- (Suffix Trie) https://www.geeksforgeeks.org/pattern-searching-using-trie-suffixes/?ref=lbp

## Project files:
### Edit Distance:
- DistanceMain.java: This file contains the "main" function for the Edit Distance Algorithms.
The two strings will be extracted from the input file and the output file will be generated and
opened.

- distance package:
    - TestEditDistance.java: This class will be used to test the main functionalities of the LCS and Levenshtein distances. Within it, the values for LCS and Levenshtein distances will be displayed in the output file.
    - EditDistance.java: This instance contains the common functions of the two analyzed distances, which are getting the Edit Distance value, calculating the specific distance and creating the initial matrix.
    - LevenshteinDistance.java: The class incorporating the Levenshtein Distance methods (calculating the values of Levenshtein distance and Edit distance).
    - LongestCommonSubsequenceDistance.java: The class including the LCS Distance methods (calculating the values of LCS distance and Edit distance, creating the matrix, and getting the LCS string).

### Trie:
- TrieMain.java: This file contains the "main" function for the Trie Algorithms. The text and the list of patterns will be extracted from the input file and the output file will be generated and opened.

- trie package:
    - TestTrie.java: This class will be used to test the main functionalities of the Standard Trie and the Suffix Tree. After the text is inserted in both trees, a search will be performed and the result will be displayed in the output file. In the case of the standard tree, the deletion function will also be tested for the first pattern in the list.
    - Trie.java: An instance incorporating the common methods used for the Tries: insert and delete.
    - TrieNode.java: Class defining the methods and attributes (its list of children nodes) of a Standard Trie Node.
    - SuffixTrieNode.java: Class defining the methods and attributes (its list of children nodes and its list of appearances' indexes in text) of a Suffix Trie Node.
    - StandardTrie.java: The class containing the Standard Trie functionalities. (insering a text, searching for a pattern, deleting a word).
    - SuffixTrie.java: The class containing the Suffix Trie functionalities. (insering a text, searching for a pattern).
