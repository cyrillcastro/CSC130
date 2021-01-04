/**
 * Cyrill Castro
 * Project 3
 * CSC 130
 * 
 * Implementation using HashTable.
 * HashTable works only with Strings, whereas the DataCounter interface is
 * generic.  You need the String contents to write your hashcode code.
 */

/**
 * Random class will be used to generate keys.
 */

import java.util.LinkedList;

public class HashTable implements DataCounter<String> {
	
	/**
	 * The array that will become the hash table.
	 */
	protected LinkedList<hashObject>[] table;
	
	/**
	 * Variable to hold the size of the hashtable
	 */
	protected int size;
	
	/**
	 * Variable to hold the number of elements in the hashtable 
	 */
	protected int count;
	
	/**
	 * Variable to hold the load factor of the hash table. 
	 */
	protected double lambda;
	
	/**
	 * Inner class to create objects and keys to put into the hash table. 
	 */
	protected class hashObject {
		
		public String data;
		public String key;
		public int count;
		
		/**
		 * Object is created, and count will start off as one.
		 * 
		 * @param data
		 */
		public hashObject(String data) {
			this.data = data;
			this.key = data;
			this.count = 1;
		}
		
		public String getData() {
			return this.data;
		}
		
		public String getKey() {
			return this.key;
		}
		
		public int getCount() {
			return count;
		}
		
		public void incCount() {
			this.count++;
		}
		
	}

	@SuppressWarnings("unchecked")
	public HashTable() {
		count = 0;
		if (size < 53) {
			size = 53;
		} else {
			size = nextPrime(size);
		}
		
		table = new LinkedList[size];
		for (int i = 0; i < size; i++) {
			if (table[i] == null) 
				table[i] = new LinkedList<hashObject>();
		}
		
		lambda = 0.5;
	}
	
    /**
     * Increment the count for a particular data element.
     * 
     * @param data data element whose count to increment.
     */
    public void incCount(String data) {
    	hashObject obj = new hashObject(data);
    	int index = -1;
    	
    	if (contains(obj.getKey())) {
    		obj = get(obj.getKey());
    		obj.incCount();
    	} else {
    		index = hashing(obj.getKey());
    		table[index].add(obj);
    		count++;
    	}
    	
    	if (count > lambda * size) 
    		resize();
    	
    }
    
    private static int nextPrime(int n) {
    	int[] storage = {53, 97, 193, 389, 769, 15433079, 6151, 12289, 24593, 49157, 98317, 1996613, 393241, 786433, 383241, 6291469, 12582917};
    
    	for (int i = 0; i < storage.length; i++) {
    		if (n == storage[i]) 
    			n = storage [i + 1];
    	}
    	return n;
    }
    
    @SuppressWarnings("unchecked")
	private void resize() {
    	LinkedList<hashObject>[] old = table;
    	int oldSize = size;
    	
    	size = nextPrime(2*oldSize);
    	
    	table = new LinkedList[size];
		for (int i = 0; i < size; i++) {
			if (table[i] == null) 
				table[i] = new LinkedList<hashObject>();
		}
		
		lambda = 0.5;
		
		for (int i = 0; i < oldSize; i++) {
			for (int j = 0; j < old[i].size(); j++) {
				reinsert(old[i].get(j));
			}
		}
 		
    }
    
    private void reinsert(hashObject obj) {
    	int index = hashing(obj.getKey());
    	table[index].add(obj);
    }
    
    public boolean contains(String key) {
    	boolean found = false;
    	int index = hashFunction(key);
    	
    	for (int i = 0; i < table.length; i++) {
    		if ((table[index]).get(i).getKey().equals(key)) {
    			found = true;
    		}
    	}
    	return found;
    }
    
    public hashObject get(String key) {
    	int index = hashFunction(key);
    	hashObject target = null;
    	
    	for (int i = 0; i < table[index].size(); i++) {
    		if (table[index].get(i).getKey().equals(key)) {
    			target = table[index].get(i);
    			break;
    		}
    	}
    	
    	return target;
    }
    
    
    
    private int hashFunction(String key) {
    	int index;
    	
    	index = hashing(key) % size;
    	
    	return index;
    }
    
    private int hashing(String key) {
    	int index = 0;
    	char[] keyarray = key.toCharArray();
    	
    	for (int i = 0; i < key.length(); i++) 
    		index = 17 * index + keyarray[i];
    	
    	return index;
    }
    
    /**
     * The number of unique data elements in the structure.
     * 
     * @return the number of unique data elements in the structure.
     */
    public int getSize() {
    	return count;
    }

    /**
     * Get an array of all of the data counts in the DataCounter structure. The
     * array should contain exactly one DataCount instance for each unique
     * element inserted into the structure. The elements do not need to be in
     * any particular order.
     * 
     * @return an array of the data counts.
     */
    public DataCount<String>[] getCounts() {
    	@SuppressWarnings("unchecked")
		DataCount<String>[] counter = new DataCount[count];
    	int k = 0;
    	for (int i = 0; i < table.length; i++) {
    		if (!table[i].isEmpty()) {
    			for (int j = 0; j < table[i].size(); j++) {
    				counter[k] = new DataCount<String>(table[i].get(j).getData(), table[i].get(j).getCount());
    				k++;
    			}
    		}
    	}
    	
    	return counter;
    }
    
}
