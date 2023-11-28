package dAL;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import transferObject.VersesTO;

public interface iDALFascade extends iVersesDAO,IBookDAO,iPoemDAO {public int getPoemID(String PoemTitle);
public boolean insertPoemsintoDB();
public ArrayList<VersesTO> fetchPoems();
public void AddBook(String str1,String str2,String str3);
public String SelectBook();
public void updateBook(String str1,String str2,String str3);
public void deleteBook(String str1);
public boolean searchBook(String str1);
public void addPoem(String title, List<String> verses);
public void editPoem(String oldTitle, String newTitle, List<String> verses);
public void deletePoem(String title);
public List<String> getAllPoems();

}
