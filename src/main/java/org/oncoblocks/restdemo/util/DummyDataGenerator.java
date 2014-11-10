package org.oncoblocks.restdemo.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Random;

/**
 * Inspects any class and creates dummy data within it.
 * 
 * @author ecerami
 *
 */
public class DummyDataGenerator {
	private static Random randomGenerator = new Random();

	public static void createDummyData (Object obj) {
			Class currentClass = obj.getClass();
			Field[]fieldList = currentClass.getDeclaredFields();
			
			//  Make all fields accessible.
			for (Field currentField:  fieldList) {
				currentField.setAccessible(true);
				Class fieldType = currentField.getType();
				String fieldName = currentField.getName();
				try {
					if (fieldType == Integer.class) {
						currentField.set (obj, new Integer(randomGenerator.nextInt(1000)));
					} else if (fieldType == Double.class) {
						currentField.set (obj, new Double(randomGenerator.nextDouble()));
					} else if (fieldType == String.class) {
						currentField.set (obj, "VALUE_" + fieldName.toUpperCase());
					}
				} catch (IllegalArgumentException e) {
					System.out.println(e);
				} catch (IllegalAccessException e) {
					System.out.println(e);
				}
			}
	}
}

