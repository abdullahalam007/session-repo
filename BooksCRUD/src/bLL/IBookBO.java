package bLL;

public interface IBookBO {
	
	public void addBook(String name, String author, String deathDate);
	public void updateBook(int id, String name) ;
	public void deleteBook(String str1) ;
	public String getBooks();
	public boolean searchBook(String str1);
	public void updateBook(String name,String author,String deathDate);

}
