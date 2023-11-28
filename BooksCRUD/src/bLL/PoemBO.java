package bLL;

import java.util.List;

import dAL.PoemDAO;

public class PoemBO implements iPoemBO{
    private PoemDAO poemRepository;

    public PoemBO() {
        this.poemRepository = new PoemDAO();
    }

    public void addPoem(String title, List<String> verses) {
        // Validate input, perform business logic, and call DAL to add a poem
        poemRepository.addPoem(title, verses);
    }

    public void editPoem(String oldTitle, String newTitle, List<String> verses) {
        // Validate input, perform business logic, and call DAL to edit a poem
        poemRepository.editPoem(oldTitle, newTitle, verses);
    }

    public void deletePoem(String title) {
        // Call DAL to delete a poem and its verses
        poemRepository.deletePoem(title);
    }

    public List<String> getAllPoems() {
        // Call DAL to fetch all poems
        return poemRepository.getAllPoems();
    }
}
