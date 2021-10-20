package com.tahauddin.syed;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * 
 * @author Syed Tahauddin
 *
 */
public class App {

	public static void main(String[] args) {
		
		Employee employee = Employee.getInstance();
		
		/**
		 * this is for Cloneable interface
		 * we called clone() to get a copy of employee object
		 * Note : when we call clone() on any object then constructor will not be exeucted.
		 * hence we dont have to rely on constructor to check weather object is creaated or not.
		 * we have to check the hashcode of original object and cloned object.
		 * if we call clone() then by default it will be shallow cloning.
		 */
		try {
			// this method will throw an exception as we have 
			// overriden in our singleton class
			Employee employeeClonedObject = (Employee) employee.clone();
			System.out.println("Original Object Hash Code "+ employee.hashCode());
			System.out.println("Cloned   Object Hash Code "+ employeeClonedObject.hashCode());
			System.out.println(employee.hashCode() == employeeClonedObject.hashCode());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		
		
		/**
		 * 
		 */
		Employee serializationEmployee = serilizationMethod(employee);
		System.out.println("Serialization is Done..");
		System.out.println("Serialization Object Hash Code " + serializationEmployee.hashCode());
		
		/**
		 * 
		 */
		Employee deserializationEmployee = deserializationMethod();
		System.out.println("Deserialization is Done..");
		System.out.println("Deserialization Object Hash Code " + deserializationEmployee.hashCode());
		System.out.println(serializationEmployee == deserializationEmployee);
		
	}
	
	
	
	private static Employee deserializationMethod() {
		Employee employee = null;
		try {
			FileInputStream fileInputStream = new FileInputStream("employee.ser");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			employee = (Employee)objectInputStream.readObject();
			objectInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return employee;
		
	}
	
	/**
	 * calling serialization process to create a serialization on file
	 * and create java object based on serialization object
	 */
	private static Employee serilizationMethod(Employee employee) {
		
		
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("employee.ser");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(employee);
			objectOutputStream.flush();
			objectOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return employee;

	}

}
