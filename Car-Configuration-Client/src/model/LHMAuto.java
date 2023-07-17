/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package model;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class LHMAuto<K, V> {
	private K key;
	private V val;
	private LinkedHashMap<K, V> lHashMap;
	
	public LHMAuto()
	{
		lHashMap = new LinkedHashMap<K, V>();
	}
	
	// K - String, V - Automobile
	public LHMAuto(K key, V val)
	{
		this.key = key;
		this.val = val;
		lHashMap = new LinkedHashMap<K, V>();
	}
	
	// add - Add an object to the linked hash map
	public V add(K key, V val)
	{
		lHashMap.put(key, val);
		return val;
	}
	
	// get - Get an object from the linked hash map provided a key
	public V get(K key)
	{
		return lHashMap.get(key);
	}
	
	// searchByKey - Check if a key exists within the linked hash map
	public boolean searchByKey(K key)
	{
		return lHashMap.containsKey(key);
	}
	
	// searchByValue - Checks if an object exists within the linked hash map
	public boolean searchByValue(V val)
	{
		return lHashMap.containsValue(val);
	}
	
	// update - Updates a value provided a key
	public void updateVal(K key, V newVal)
	{
		lHashMap.replace(key, newVal);
	}
	
	// delete - Delete a specific entry
	public V delete(K key)
	{
		return lHashMap.remove(key);
	}
	
	// clear - Delete all entries in the linked hash map
	public void deleteAll()
	{
		lHashMap.clear();
	}
	
	// iterate - Iterates over the entries within the linked hash map and prints the entries
	// Unsure about the functionality of iterating - should be doing something besides printing?
	public void iterate()
	{
		Set st = lHashMap.keySet();
		Iterator it = st.iterator();
		
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
	}
	
	// Getters and setters
	// getKey
	public K getKey()
	{
		return key;
	}
	
	// setKey
	public void setKey(K newKey)
	{
		key = newKey;
	}
	
	// getVal
	public V getVal()
	{
		return val;
	}
	
	// setVal
	public void setVal(V newVal)
	{
		val = newVal;
	}
	
	// print - Prints the name of K and V
	public void printKeyVal()
	{
		System.out.println("Type of K is " + key.getClass().getName() + "\n" +
			"Type of V is " + val.getClass().getName());
	}
	
	// printAll - Print all elements of the linked hash map by iterating over it
	public void printAll()
	{
		System.out.printf("\nList of all Automobiles in LinkedHashMap:\n");
		iterate();
	}
	
	// getAllModelsAsString - Return all elements of the hash map as a String
	public String getAllModelsAsString()
	{
		String models = "";
		int count = 0;
		
		Set st = lHashMap.keySet();
		Iterator it = st.iterator();
		
		while(it.hasNext())
		{
			count++;
			models += count + ". " + it.next().toString() + "\n";
		}

		return models;
	}
}