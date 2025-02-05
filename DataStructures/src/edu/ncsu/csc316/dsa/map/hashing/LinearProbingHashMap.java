package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * The LinearProbingHashMap is implemented as a hash table that uses linear
 * probing for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@link Map#put}, {@link Map#get}, and {@link Map#remove}.
 * 
 * The hash table resizes if the load factor exceeds 0.5.
 * 
 * The LinearProbingHashMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Charlie Austin (cjausti2) 
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {
	/** Table containing map entries that correlate to specific hash codes */
    private TableEntry<K, V>[] table;
    /** Variable used to keep track of the number of keys in the table. */
    private int size;

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table is initialized to have
     * the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * is initialized to have the provided capacity.
     * 
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    // Method constructed through help of textbook: Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
    // * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for(int i = 0; i < table.length; i++) {
        	if( !isAvailable(i)) {
        		collection.add(table[i]);
        	}
        }	
        return collection;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }

    private boolean isAvailable(int index) {
        return (table[index] == null || table[index].isDeleted());
    }

    @Override
    public V bucketGet(int hash, K key) {
    	// Find the index of the bucket in the table.
    	int j = findBucket(hash, key);
    	// If the index of the bucket is negative, then the key was not found.
    	if(j < 0) {
    		// Key not found thus return null.
    		return null;
    	}
    	// Key was found so return the value.
    	return table[j].getValue();
    }

    @Override
    public V bucketPut(int hash, K key, V value) {
    	// Find the index of the bucket in the table.
    	int j = findBucket(hash, key);
    	// If the index return was positive, then key already exists thus overwrite it.
    	if(j >= 0) {
    		// Get the old value.
    		V rtn = table[j].getValue();
    		// Update the bucket at the index to contain the new value.
    		table[j].setValue(value);
    		// Return the old value.
    		return rtn;
    	}
    	// Bucket index was negative. The key doesn't exist in the list so it must be added.
    	table[-(j + 1)] = new TableEntry<K, V>(key, value);
    	// Increment the size.
    	size++;
    	// Return null since it was the element occupying the space before insertion.
    	return null;
    }

    private int findBucket(int index, K key) {
    	int avail = -1;
    	int j = index;
    	do {
    		if(isAvailable(j)) {
    			if(avail == -1) {
    				avail = j;
    			} 
    			if(table[j] == null) {
    				return -(avail + 1);
    			}
    		} else if( table[j].getKey().equals(key)) {
				return j;
			}
    		j = (j + 1) % table.length;
    	} while (j != index);
    	return -(avail + 1);
    }

    @Override
    public V bucketRemove(int hash, K key) {
    	// Find the index of the bucket in the table.
    	int j = findBucket(hash, key);
    	// If index for bucket is negative, then key was not found.
    	if( j < 0) {
    		// Key not found so return null. (Doesn't exist in table).
    		return null;
    	}
    	// Get the value at the index.
    	V rtn = table[j].getValue();
    	// Set the bucket's index to be deleted.
    	table[j].setDeleted(true);
    	// Decrement size.
    	size--;
    	// Return the value removed.
    	return rtn;
    	
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected int capacity() {
        return table.length;
    }

    private static class TableEntry<K, V> extends MapEntry<K, V> {
    	/** Variable to store the value of whether something was deleted or not. */
        private boolean isDeleted;

        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}
