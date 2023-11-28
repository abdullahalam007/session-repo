 package bLL;

import java.util.ArrayList;

import transferObject.VersesTO;

public interface iVerseFetcherBO {

	public void insertIntoDB();
	public ArrayList<VersesTO> fetchPoems();
}
