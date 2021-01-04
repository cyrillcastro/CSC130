/**
 * Cyrill Castro
 * Project 3
 * CSC 130
 * 
 * WordCount. Includes Selection Sort as sorting algorithm. Modified so that it 
 * fits for three arguments instead of one. 
 */

import java.io.IOException;

/**
 * An executable that counts the words in a files and prints out the counts in
 * descending order. You will need to modify this file.
 */
public class WordCount {
	
	private static int totalCount = 0;

    private static DataCount<String>[] countWords(DataCounter<String> structure, String file) {
    	DataCounter<String> counter = structure;
    	
        try {
            FileWordReader reader = new FileWordReader(file);
            String word = reader.nextWord();
            while (word != null) {
                counter.incCount(word);
                word = reader.nextWord();
            }
        } catch (IOException e) {
            System.err.println("Error processing " + file + e);
            System.exit(1);
        }

        DataCount<String>[] counts = counter.getCounts();
        sortByDescendingCount(counts);
        
        for (DataCount<String> c : counts) 
        	totalCount += c.count;
       
        return counts;
    }

    /**
     * Sort the count array in descending order of count. If two elements have
     * the same count, they should be in alphabetical order (for Strings, that
     * is. In general, use the compareTo method for the DataCount.data field).
     * 
     * This code uses insertion sort. It does not assume that the array is in 
     * alphabetical order. So if the counts are already sorted, the loop breaks.
     * If the counts are the same, the program checks if the words are in
     * alphabetical order using compareTo. If the number is less than zero, 
     * then the loop breaks. 
     * 
     * The generic parameter syntax here is new, but it just defines E as a
     * generic parameter for this method, and constrains E to be Comparable. You
     * shouldn't have to change it.
     * 
     * @param counts array to be sorted.
     */
    private static <E extends Comparable<? super E>> void sortByDescendingCount(
            DataCount<E>[] counts) {
        for (int i = 1; i < counts.length; i++) {
            DataCount<E> x = counts[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (counts[j].count > x.count) {
                    break;
                }
                if (counts[j].count == x.count) {
                	int cmp = counts[j].data.compareTo(x.data);
                	if (cmp < 0)
                		break;
                }
                counts[j + 1] = counts[j];
            }
            counts[j + 1] = x;
        }
    }
    
    private static void frequency(DataCount<String>[] counts) {
    	for (DataCount<String> c : counts) {
    		if ((double) c.count/totalCount <= 0.01 && (double) c.count/totalCount >= 0.0001)
	            System.out.println((double) c.count/totalCount + " \t" + c.data);
		}
    }
    
    private static void num_unique(DataCount<String>[] counts) {
    	for (DataCount<String> c : counts)
	            System.out.println(c.count + " \t" + c.data);
 		System.out.println("Number of unique words: " + counts.length);
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java WordCount [-b|-a|-h] [-frequency|-num_unique] <input file>");
            System.err.println("\t-b for bst");
            System.err.println("\t-a for avl");
            System.err.println("\t-h for hash");
            System.err.println("\t-frequency for list of word frequencies");
            System.err.println("\t-num_unique for number of unique words");
            System.exit(1);
        }
        
        DataCounter<String> structure = null;
        DataCount<String>[] wordCounts = null;
    	
    	if (args[0].compareTo("-b") == 0) {
            structure = new BinarySearchTree<String>();
    		wordCounts = countWords(structure, args[2]);
    	} else if (args[0].compareTo("-a") == 0) {
    		structure = new Avl<String>();
    		wordCounts = countWords(structure, args[2]);
    	} else if (args[0].compareTo("-h") == 0) {
    		structure = new HashTable();
    		wordCounts = countWords(structure, args[2]);
    	} else {
    		System.err.println("\tSaw " + args[0] + " instead of [-b|-a|-h] as first argument");
    		System.exit(1);
    	}
    	
    	if (args[1].compareTo("-frequency") == 0) {
    		frequency(wordCounts);
        } else if (args[1].compareTo("-num_unique") == 0) {
        	num_unique(wordCounts);
 		} else {
     		System.err.println("\tSaw " + args[1] + " instead of [-frequency|-num_unique] as second argument");
     		System.exit(1);
     	}
    	
    	System.out.println("Total words: " + totalCount);
    }
}