package dAL;

import java.util.ArrayList;
import java.util.List;

import transferObject.VersesTO;

public class DALFascade implements iDALFascade{
	private iVersesDAO verseDAO;
	private IBookDAO bookdao;
	private iPoemDAO poemDAO;
	public DALFascade(PoemDAO Obj)
	{
		this.poemDAO= Obj;
	}
	public DALFascade(VersesDAO obj) {
		this.verseDAO = obj;
	}

	public DALFascade(BookDAL bookdao)
	{
		super();
		this.bookdao = bookdao;
	}

	@Override
	public int getPoemID(String PoemTitle) {
		return this.verseDAO.getPoemID(PoemTitle);
	}

	@Override
	public boolean insertPoemsintoDB() {
		return this.verseDAO.insertPoemsintoDB();
	}

	@Override
	public ArrayList<VersesTO> fetchPoems() {
		return this.verseDAO.fetchPoems();
	}

	@Override
	public void AddBook(String str1,String str2,String str3) {
		
		bookdao.AddBook(str1,str2,str3);
		
	}

	@Override
	public String SelectBook() {
		return  bookdao.SelectBook();
	}

	@Override
	public void updateBook(String str1,String str2,String str3) {

		bookdao.updateBook(str1,str2,str3);
	}

	@Override
	public void deleteBook(String str1) {

		bookdao.deleteBook(str1);
		
	}

	@Override
	public void addPoem(String title, List<String> verses) {
		// TODO Auto-generated method stub
		this.poemDAO.addPoem(title, verses);
	}

	@Override
	public void editPoem(String oldTitle, String newTitle, List<String> verses) {
		// TODO Auto-generated method stub
	this.poemDAO.editPoem(oldTitle, newTitle, verses);	
	}

	@Override
	public void deletePoem(String title) {
		// TODO Auto-generated method stub
		this.poemDAO.deletePoem(title);
	}

	@Override
	public List<String> getAllPoems() {
		// TODO Auto-generated method stub
		return this.poemDAO.getAllPoems();
	}
	@Override
	public boolean searchBook(String str1) {
		// TODO Auto-generated method stub
		
		return this.searchBook(str1);
	}



}
