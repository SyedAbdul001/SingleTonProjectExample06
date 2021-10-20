package com.tahauddin.syed;

import java.io.Serializable;

/**
 * 
 * @author Syed Tahauddin
 * 
 *         If out singleton class is implementing serializable interface then
 *         singleton pattern can be breakable by using deserialization. hence to
 *         avoid it we override one method.
 *
 */
public class Employee implements Serializable, Cloneable {


	private static final long serialVersionUID = 1L;


	private Employee() {
		System.out.println("Object Created..");
	}

	public static Employee getInstance() {
		return InnerEmployee.instance;
	}

	private static class InnerEmployee {
		private static Employee instance = new Employee();

	}
	
	
	/**
	 * this is to stop anyone from cloning our object.
	 * its our wish if we want to throw an excception we can 
	 * orelse we can return an existing object.
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return InnerEmployee.instance;
	//	throw new CloneNotSupportedException();
	}
	
	/**
	 * this method will be called internally by readObject() hence we have to
	 * explicitly call this method to stop user by creating a new objecct by de
	 * serialization.
	 */
	public Object readResolve() {
		return InnerEmployee.instance;
	}
}
