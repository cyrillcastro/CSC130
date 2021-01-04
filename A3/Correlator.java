public class Correlator {

	public static void main(String[] args) {
		if (args.length != 3) {
	            System.err.println("Incorrect number of arguments");
	            System.err.println("Usage: ");
	            System.err.println("\tjava Correlator <-b for bst, -a for avl, or -h for hash> <filename1>"
	            		+ " <filename2>");
	            System.exit(1);
        }
	    correlate(args);
	}

	private static void correlate(String[] args) {
		DataCounter<String> counter = null;
		
		if (args[0].compareTo("-b") == 0) 
    		counter = new BinarySearchTree<String>();
    	else if (args[0].compareTo("-a") == 0) 
    		counter = new Avl<String>();
    	else if (args[0].compareTo("-h") == 0)
    		counter = new HashTable();
    	else {
    		System.err.println("\t Saw" + args[0] + " instead of -b, -a, or -h as first argument");
    		System.exit(1);
    	}
    	
    	if (args[1].compareTo("-frequency") == 0) 
    		counter = new BinarySearchTree<String>();
    	else if (args[1].compareTo("-num_unique") == 0) 
    		counter = new Avl<String>();
    	else {
    		System.err.println("\t Saw" + args[1] + " instead of -frequency or -num_unique as second"
    				+ " argument");
    		System.exit(1);
    	}
	}

}
