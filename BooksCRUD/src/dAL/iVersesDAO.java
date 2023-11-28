package dAL;

import java.util.ArrayList;
import java.util.StringTokenizer;

import transferObject.VersesTO;

public interface iVersesDAO {
	public int getPoemID(String PoemTitle);
	public boolean insertPoemsintoDB();
	public ArrayList<VersesTO> fetchPoems();


}
