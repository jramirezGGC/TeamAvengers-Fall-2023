import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    private String name;
    private String desc;
    private String ans;
    private String hint;
    public Puzzle(String puzzleName, String puzzlePrompt, String puzzleAns, String puzzleHint) {
        this.name = puzzleName;
        this.desc = puzzlePrompt;
        this.ans = puzzleAns;
        this.hint = puzzleHint;
    }
    public String getName(){
        return name;
    }
    public String getDesc(){
        return desc;
    }
    public String getAns(){
        return ans;
    }

    public void help(){
        System.out.println("return puzzle hint");
    }

    public static String getPuzzleAsString(List<Puzzle> puzzles) {
        List<String> puzzleNames = new ArrayList<>();
        puzzles.stream().forEach(puzzle -> {
            puzzleNames.add(puzzle.getName());
        });
        if(puzzles.size()>1){
            return String.join(",",puzzleNames);
        }
        return puzzleNames.toString();
    }

    public static String getPuzzleAns(List<Puzzle> puzzles){
        List<String> puzzleAns = new ArrayList<>();
        puzzles.stream().forEach(puzzle ->{
            puzzleAns.add(puzzle.getAns());
        });
        return String.join(",",puzzleAns);
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", ans='" + ans + '\'' +
                ", hint='" + hint + '\'' +
                '}';
    }


}
