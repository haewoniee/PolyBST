package tree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 *  
 */
 public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	/* Provide whatever instance variables you need */
	 private K key;
	 private V value;
	 private Tree<K,V> left, right;

	/**
	 * Only constructor we need.
	 * @param key
	 * @param value
	 * @param left
	 * @param right
	 */
	 
	public NonEmptyTree(K key, V value, Tree<K,V> left, Tree<K,V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right; 
	}
	
	/* 
	 * Returns the value associated with the key by this Tree,
	 * returns null if nothing matches
	 */
	public V search(K key) {
		int compare = this.key.compareTo(key);
		if (compare < 0) {
			return right.search(key);
		} else if (compare > 0) {
			return left.search(key);
		} else if (compare == 0) {
			return value;
		} else {
			return null;
		}
	}
	
	/* Inserts key and value to the Tree;
	 * If the key matches any key inside, just update the value;
	 * First compares the key of this and key given
	 * if compareNumber > 0, replace left with left.insert
	 * if compareNumber < 0, replace right with right.insert
	 * Returns the updated version of this tree
	 */
	
	public NonEmptyTree<K, V> insert(K key, V value) {
		int compare = this.key.compareTo(key);
		if (compare == 0) {
			this.value = value;
		} else if (compare > 0) {
			left = left.insert(key, value);
		} else if (compare < 0) {
			right = right.insert(key, value);
		}
		return this;
	}
	
	/*
	 * Deletes any binding the key has in this tree Returns reference to updated
	 * tree or This
	 * 
	 */
	
	public Tree<K, V> delete(K key) {
		if (this.key.compareTo(key) > 0) {
			left = left.delete(key);
		}
		if (this.key.compareTo(key) < 0) {
			right = right.delete(key);
		}
		if (this.key.compareTo(key) == 0) {
			try {
				return new NonEmptyTree<K, V>(right.min(), search(right.min()),
						left, right.delete(right.min()));
			} catch (TreeIsEmptyException e) {
				try {
					return new NonEmptyTree<K, V>(left.max(), search(left.max()),
							left.delete(left.max()), right);
				} catch (TreeIsEmptyException e1) {
					return EmptyTree.getInstance();
				}
			}
		}
		return this;
	}
	
	public K max() {
		try {
			return right.max();
		} catch (TreeIsEmptyException e) {
			return key;
		}
	}

	public K min() {
		try {
			return left.min();
		} catch (TreeIsEmptyException e) {
			return key;
		}
	}

	public int size() {
		return right.size() + left.size() + 1;
	}

	public void addKeysToCollection(Collection<K> c) {
		c.add(key); 
		right.addKeysToCollection(c);
		left.addKeysToCollection(c);	
	}
	
	/* Returns a Tree containing all entries
	 * between fromKey and toKey, inclusive.
	 * It may NOT modify the original tree
	 * (a common mistake while implementing this method).
	 */
	public Tree<K,V> subTree(K fromKey, K toKey) {
		if (key.compareTo(fromKey) < 0) {
			return right.subTree(fromKey, toKey);
		}
		if (key.compareTo(toKey) > 0) {
			return left.subTree(fromKey, toKey);
		}
		else {
			Tree<K,V> newTree = new NonEmptyTree<K, V>(key, value, 
					left.subTree(fromKey, toKey), right.subTree(fromKey, toKey));
			return newTree;
		}
	}
	
	
	public int height() {
		return  Math.max(right.height(), left.height()) + 1;
	}
	
	public void inorderTraversal(TraversalTask<K,V> p) {
		left.inorderTraversal(p);
		p.performTask(key, value);
		right.inorderTraversal(p);
	}
	
	public void rightRootLeftTraversal(TraversalTask<K,V> p) {
		right.rightRootLeftTraversal(p);
		p.performTask(key, value);
		left.rightRootLeftTraversal(p);
	}	
}