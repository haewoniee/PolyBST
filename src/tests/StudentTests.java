 package tests;

import java.util.HashSet;

import org.junit.Test;

import junit.framework.TestCase;
import tree.EmptyTree;
import tree.PlaceKeysValuesInArrayLists;
import tree.PolymorphicBST;

public class StudentTests extends TestCase {
	@Test
	public void testPutandGet() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		assertEquals(0, ptree.size());
		assertEquals(null, ptree.get(0));
		ptree.remove(1);
		assertEquals(0, ptree.size());
		ptree.put(2, "Two");
		assertEquals(1, ptree.size());
		assertEquals("Two", ptree.get(2));

		ptree.put(2, "Three");
		assertEquals(1, ptree.size());
		assertEquals("Three", ptree.get(2));
		
		ptree.put(1, "One");
		ptree.put(2, "Two");
		ptree.put(3, "Three");
		assertEquals(3, ptree.size());
		assertEquals("Two", ptree.get(2));
		assertEquals(null, ptree.get(30));
		}
	
	
	
	@Test
	public void testHeight() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		ptree.put(20, "Twenty");
		assertEquals(1, ptree.height());
		ptree.put(10, "Ten");
		assertEquals(2, ptree.height());
		ptree.put(50, "Fifty");
		assertEquals(2, ptree.height());
		ptree.put(40, "Fourty");
		assertEquals(3, ptree.height());
		ptree.put(60, "Sixty");
		assertEquals(3, ptree.height());
		ptree.put(45, "FourtyFive");
		assertEquals(4, ptree.height());
		ptree.put(44, "FourtyFour");
		assertEquals(5, ptree.height());
	}
	
	@Test
	public void testRightRootLeftTraversal() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		ptree.put(20, "Twenty");
		ptree.put(10, "Ten");
		ptree.put(50, "Fifty");
		ptree.put(40, "Fourty");
		ptree.put(60, "Sixty");
		ptree.put(45, "FourtyFive");
		ptree.put(44, "FourtyFour");
		PlaceKeysValuesInArrayLists<Integer, String> task = 
				new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.rightRootLeftTraversal(task);
		assertEquals(task.getKeys().toString(), "[60, 50, 45, 44, 40, 20, 10]");
	}

	@Test
	public void testAddtoCollection() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		HashSet<Integer> set = new HashSet<Integer>();
		assertEquals(set, ptree.keySet());
		ptree.put(20, "Twenty");
		ptree.put(10, "Ten");
		ptree.put(50, "Fifty");
		ptree.put(40, "Fourty");
		set.add(20);
		set.add(10);
		set.add(50);
		set.add(40);
		assertEquals(set, ptree.keySet());
		
	}

	@Test
	public void testSubTree() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		ptree.put(20, "Twenty");
		ptree.put(10, "Ten");
		ptree.put(50, "Fifty");
		ptree.put(40, "Fourty");
		ptree.put(60, "Sixty");
		ptree.put(45, "FourtyFive");
		ptree.put(44, "FourtyFour");
		PlaceKeysValuesInArrayLists<Integer, String> task = 
				new PlaceKeysValuesInArrayLists<Integer, String>();
		PolymorphicBST<Integer,String> sub = ptree.subMap(9, 41);
		sub.inorderTraversal(task);
		assertEquals("[10, 20, 40]", task.getKeys().toString());
	}
	
	@Test
	public void testMaxandMin() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		ptree.put(20, "Twenty");
		ptree.put(10, "Ten");
		ptree.put(50, "Fifty");
		ptree.put(40, "Fourty");
		ptree.put(60, "Sixty");
		ptree.put(45, "FourtyFive");
		ptree.put(44, "FourtyFour");
		assertEquals(Integer.valueOf(60), ptree.getMax());
		assertEquals(Integer.valueOf(10), ptree.getMin());
	}
	
	@Test
	public void testDelete() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		ptree.put(20, "Twenty");
		ptree.put(10, "Ten");
		ptree.put(50, "Fifty");
		ptree.put(40, "Fourty");
		ptree.put(60, "Sixty");
		ptree.put(45, "FourtyFive");
		ptree.put(44, "FourtyFour");
		ptree.remove(10);
		assertEquals(Integer.valueOf(20), ptree.getMin());
		assertEquals(Integer.valueOf(60), ptree.getMax());
		PlaceKeysValuesInArrayLists<Integer, String> task = 
				new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(task);
		assertEquals("[20, 40, 44, 45, 50, 60]", task.getKeys().toString());
	}
	
	@Test
	public void testDelete2() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		ptree.put(20, "Twenty");
		ptree.put(10, "Ten");
		ptree.put(50, "Fifty");
		ptree.put(40, "Fourty");
		ptree.put(60, "Sixty");
		ptree.put(45, "FourtyFive");
		ptree.put(44, "FourtyFour");
		ptree.remove(60);
		assertEquals(Integer.valueOf(50), ptree.getMax());
		PlaceKeysValuesInArrayLists<Integer, String> task = 
				new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(task);
		assertEquals("[10, 20, 40, 44, 45, 50]", task.getKeys().toString());

	}
	
	@Test
	public void testDelete3() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		ptree.put(60, "Sixty");
		ptree.remove(60);
		PlaceKeysValuesInArrayLists<Integer, String> task = 
				new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(task);
		assertEquals("[]", task.getKeys().toString());
	}
	 
	@Test
	public void testDelete4() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		ptree.remove(60);
		PlaceKeysValuesInArrayLists<Integer, String> task = 
				new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(task);
		assertEquals("[]", task.getKeys().toString());
	}
	
	@Test
	public void testDelete5() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		ptree.put(20, "Twenty");
		ptree.put(10, "Ten");
		ptree.put(14, "Fourteen");
		ptree.put(15, "Fifteen");
		ptree.remove(20);
		PlaceKeysValuesInArrayLists<Integer, String> task = 
				new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(task);
		assertEquals("[Ten, Fourteen, Fifteen]", task.getValues().toString());
	}
	
}