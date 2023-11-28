package bLL;

import java.util.ArrayList;

import dAL.DALFascade;
import dAL.VersesDAO;
import dAL.iDALFascade;
import transferObject.VersesTO;

public class verseFetcherBO implements iVerseFetcherBO{

	private dAL.DALFascade verseFetch;
	@Override
	public ArrayList<VersesTO> fetchPoems()
	{
		return verseFetch.fetchPoems();
		
	}
	@Override
	public void insertIntoDB()
	{
		verseFetch.insertPoemsintoDB();
	}
	public verseFetcherBO() {
		// TODO Auto-generated constructor stub
		verseFetch = new DALFascade(new VersesDAO());
	}

}
