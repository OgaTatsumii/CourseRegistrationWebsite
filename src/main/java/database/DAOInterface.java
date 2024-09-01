package database;

import java.util.ArrayList;

public interface DAOInterface<T> {
	
	public ArrayList<T> selectAll();
	
	public T selectByID(String t);
	
	public int insert(T t);
	
	public int insertAll(ArrayList<T> arr);
	
	public int delete(T t);
	
	public int deleteMany(ArrayList<T> arr);
	
	public void deleteAll();
	
	public int update(T t);
}