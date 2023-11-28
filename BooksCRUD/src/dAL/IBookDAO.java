package dAL;

public interface IBookDAO {
	
	public void AddBook(String str1,String str2,String str3);
	public String SelectBook();
	public void updateBook(String str1,String str2,String str3);
	public void deleteBook(String str1);
	boolean searchBook(String str1);
}
