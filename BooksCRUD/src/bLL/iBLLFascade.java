package bLL;

import java.util.ArrayList;
import java.util.List;

import transferObject.VersesTO;

public interface iBLLFascade extends iVerseFetcherBO,IBookBO,iPoemBO {
	public void insertIntoDB();
	public ArrayList<VersesTO> fetchPoems();
	public void addBook(String name, String author, String deathDate);
	public void updateBook(int id, String name) ;
	public void updateBook(String name,String author,String deathDate);
	public void deleteBook() ;
	public String getBooks(); 
	
	public List<String> getAllPoems();
	   public void deletePoem(String title);
	   public void editPoem(String oldTitle, String newTitle, List<String> verses);
	   public void addPoem(String title, List<String> verses);

}
