package bLL;

import java.util.ArrayList;
import java.util.List;

import transferObject.VersesTO;

public class BLLFascade implements iBLLFascade{

	private iVerseFetcherBO verseFetcher;
	private IBookBO bookfetcher;
	private iPoemBO poemFetcher;
	
	public BLLFascade(verseFetcherBO obj) {
	this.verseFetcher = obj;
	}
	public BLLFascade(PoemBO Obj)
	{
		this.poemFetcher = Obj;
	}

	public BLLFascade(BookBLL obj)
	{
		this.bookfetcher = obj;
	}
	
	@Override
	public void insertIntoDB() {
		this.verseFetcher.insertIntoDB();
	}

	@Override
	public ArrayList<VersesTO> fetchPoems() {
		// TODO Auto-generated method stub
		return this.verseFetcher.fetchPoems();
	}

	@Override
	public void addBook(String name, String author, String deathDate) {
		// TODO Auto-generated method stub
		bookfetcher.addBook(name, author, deathDate);
		
	}

	@Override
	public void updateBook(int id, String name) {
		// TODO Auto-generated method stub
		bookfetcher.updateBook(id, name);
	}

	@Override
	public String getBooks() {
		// TODO Auto-generated method stub
		return bookfetcher.getBooks();
	}

	@Override
	public List<String> getAllPoems() {
		// TODO Auto-generated method stub
		return this.poemFetcher.getAllPoems();
	}

	@Override
	public void deletePoem(String title) {
		// TODO Auto-generated method stub
	this.poemFetcher.deletePoem(title);
	}

	@Override
	public void editPoem(String oldTitle, String newTitle, List<String> verses) {
		// TODO Auto-generated method stub
		this.poemFetcher.editPoem(oldTitle, newTitle, verses);
	}

	@Override
	public void addPoem(String title, List<String> verses) {
		// TODO Auto-generated method stub
		this.poemFetcher.addPoem(title, verses);
	}
	@Override
	public void deleteBook(String str1) {
		// TODO Auto-generated method stub
		bookfetcher.deleteBook(str1);

		
	}
	@Override
	public void deleteBook() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateBook(String name, String author, String deathDate) {
		this.bookfetcher.updateBook(name, author, deathDate);
		
	}
	@Override
	public boolean searchBook(String str1) {
     
		return this.bookfetcher.searchBook(str1);
	}


}
