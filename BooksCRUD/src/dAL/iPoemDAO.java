package dAL;

import java.util.List;

public interface iPoemDAO {
    public void addPoem(String title, List<String> verses);
    public void editPoem(String oldTitle, String newTitle, List<String> verses);
    
    public void deletePoem(String title);
    public List<String> getAllPoems();
}
