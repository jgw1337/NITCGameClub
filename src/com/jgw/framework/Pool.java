package com.jgw.framework;

import java.util.ArrayList;
import java.util.List;

public class Pool<T> {
	public interface PoolObjectFactory<T> {
		public T createObject();
	}
	
	private final List<T> FREE_OBJECTS;
	private final PoolObjectFactory<T> FACTORY;
	private final int MAX_SIZE;
	
	public Pool(PoolObjectFactory<T> FACTORY, int MAX_SIZE) {
		this.FACTORY = FACTORY;
		this.MAX_SIZE = MAX_SIZE;
		this.FREE_OBJECTS = new ArrayList<T>(MAX_SIZE);
	}
	
	public T newObject() {
		T object = null;
		
		if (FREE_OBJECTS.size() == 0) {
			object = FACTORY.createObject();
		} else {
			object = FREE_OBJECTS.remove(FREE_OBJECTS.size() - 1);
		}
		
		return object;
	}
	
	public void free(T object) {
		if (FREE_OBJECTS.size() < MAX_SIZE) {
			FREE_OBJECTS.add(object);
		}
	}
}
