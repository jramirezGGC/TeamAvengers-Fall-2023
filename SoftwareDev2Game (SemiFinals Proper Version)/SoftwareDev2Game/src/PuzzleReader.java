import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PuzzleReader {
    public static Map<String,Puzzle> readPuzzlesFromFile(String filePath){
        Map<String, Puzzle> puzzles = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(":");
                String puzzleName = parts[0].trim();
                String puzzlePrompt = parts[1].trim();
                String puzzleAnswer = parts[2].trim();
                String puzzleHint = parts[3].trim();
                Puzzle puzzle = new Puzzle(puzzleName,puzzlePrompt,puzzleAnswer,puzzleHint);
                puzzles.put(puzzleName,puzzle);
            }


        } catch (IOException e) {
            throw new RuntimeException("Error reading the puzzle file: " + e.getMessage());
        }
        return puzzles;
    }
}
