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
    public String getHint(){
        return hint;
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

    public static String getPuzzleDesc(List<Puzzle> puzzles){
        List<String> puzzleDesc = new ArrayList<>();
        puzzles.stream().forEach(puzzle -> {
            puzzleDesc.add(puzzle.getDesc());
        });
        return String.join(",",puzzleDesc);
    }

    public static String getPuzzleHint(List<Puzzle> puzzles){
        List<String> puzzleHint = new ArrayList<>();
        puzzles.stream().forEach(puzzle ->{
            puzzleHint.add(puzzle.getHint());
        });
        return String.join(",",puzzleHint);
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
