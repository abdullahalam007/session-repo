package bLL;

import dAL.BookDAL;

public class BookBLL implements IBookBO{
	private BookDAL bookDAL;
	

	public BookBLL() {
		bookDAL = new BookDAL();
	}

	public void addBook(String name, String author, String deathDate) {
		bookDAL.AddBook(name,author,deathDate);
	}

	public void updateBook(String name,String author,String deathDate) {
		bookDAL.updateBook(name,author,deathDate);
	}

	public void deleteBook(String str1) {
		bookDAL.deleteBook(str1);
	}

	public String getBooks() {
		return bookDAL.SelectBook();
	}
	
	public boolean searchBook(String str1)
	{
		return bookDAL.searchBook(str1);
	}

	@Override
	public void updateBook(int id, String name) {
		// TODO Auto-generated method stub
		
	}



}
