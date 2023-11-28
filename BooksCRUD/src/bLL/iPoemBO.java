package bLL;

import java.util.List;

public interface iPoemBO {
	public List<String> getAllPoems();
	   public void deletePoem(String title);
	   public void editPoem(String oldTitle, String newTitle, List<String> verses);
	   public void addPoem(String title, List<String> verses);
}
