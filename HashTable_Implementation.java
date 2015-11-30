import java.util.ArrayList;

public class HasherTest {

	public static void main(String[] args) {		
		Dummy james = new Dummy("James", 20);
		Dummy jimmy = new Dummy("Jimmy", 25);
		Dummy alex = new Dummy("Alex", 30);
		Dummy tom = new Dummy("Tom", 35);
		Dummy max = new Dummy("Max", 40);
		Dummy jack = new Dummy("Jack", 45);
		Dummy julie = new Dummy("Julie", 50);
		Dummy chris = new Dummy("Chris", 55);
		Dummy tom2 = new Dummy("Tom", 75); // This should replace the first "tom"
		
		Dummy[] dummies = {james, jimmy, alex, tom, max, jack, julie, chris, tom2};
		
		/* Test: Insert Elements. */
		Hasher<String, Dummy> hash = new Hasher<String, Dummy>(3);
		for (Dummy d : dummies) {
			hash.put(d.getName(), d);
		}
		
		hash.printTable();
		
		/* Test: Recall */
		for (Dummy d : dummies) {
			String name = d.getName();
			Dummy dummy = hash.get(name);
			System.out.println("Dummy named " + name + ": " + dummy.toString());
		}
	}
}

class Hasher<K, V> {
	private ArrayList<EntryNode<K, V>> array;
	
	public Hasher(int capacity) {
		array = new ArrayList<EntryNode<K, V>>();
		array.ensureCapacity(capacity);
		// Make sure array has enough initial elements
		for	(int i = 0; i < capacity; i++) {
			array.add(null);
		}
	}
	
	public void put(K key, V value) {
		EntryNode<K, V> entry = this.getEntryForKey(key);
		if (entry != null) {
			entry.value = value;
			return;
		}
		
		entry = new EntryNode<K, V>(key, value);
		int index = this.getIndexForKey(key);
		if (array.get(index) != null) {
			entry.next = array.get(index);
			entry.next.prev = entry;
		}
		array.set(index, entry);
	}
	
	public V get(K key) {
		EntryNode<K, V> entry = this.getEntryForKey(key);
		return entry == null ? null : entry.value;
	}
	
	public boolean remove(K key) {
		EntryNode<K, V> entry = this.getEntryForKey(key);
		if (entry  == null) {
			return false;
		}
		
		if (entry.prev != null) {
			entry.prev.next= entry.next;
		} else {
			// this EntryNode is the head
			int index = this.getIndexForKey(key);
			array.set(index, entry.next);
		}
		if (entry.next != null) {
			entry.next.prev = entry.prev;
		}		
		return true;
	}
	
	/*Get associated EntryNode in the internal array with key, need to search linkedlist*/
	private EntryNode<K, V> getEntryForKey(K key) {
		int index = this.getIndexForKey(key);
		EntryNode<K, V> entry = array.get(index);
		while (entry != null) {
			if (entry.key == key) {
				return entry;
			} 
			entry = entry.next;
		}
		return null;
	}
	
	/* Simple fucntion to map a key to an index.
	 * Need modify in the future.
	 * */
	public int getIndexForKey(K key) {
		return Math.abs(key.hashCode() % array.size());
	}
	
	public void printTable() {
		for (int i = 0; i < array.size(); i++) {
			String s = array.get(i) == null ? "" : array.get(i).printLinkedNotes();
			System.out.println(i + ": " + s);
		}
	}
	
	private class EntryNode<K, V> {
		public EntryNode<K, V> prev;
		public EntryNode<K, V> next;
		public K key;
		public V value;
		public EntryNode(K k, V v) {
			key = k;
			value = v;
		}
		
		public String printLinkedNodes() {
			String data = "(" + key + "," + value + ")";
			if (next != null) {
				return data + "->" + next.printLinkedNodes();
			} else {
				return data;
			}
		}
	}
}

class Dummy {
	private String name;
	private int age;
	public Dummy(String n, int a) {
		name = n;
		age = a;
	}
	
	@Override 
	public String toString() {
		return "(" + name + ", " + age + ")";
	}
	
	public int getAge() {
		return age;
	}
	
	public String getName() {
		return name;
	}
}
