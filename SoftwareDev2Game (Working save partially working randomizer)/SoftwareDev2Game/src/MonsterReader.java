import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonsterReader {

    public static Map<String,Monster> readMonstersFromFile(String filePath){
        Map<String, Monster> monsters = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(":");
                String monsterName = parts[0].trim();
                String monsterDesc = parts[1].trim();
                int monsterAtk = Integer.parseInt(parts[2].trim());
                int monsterHP = Integer.parseInt(parts[3].trim());
                Monster monster = new Monster(monsterName,monsterDesc,monsterAtk,monsterHP);
                monsters.put(monsterName,monster);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return monsters;
    }

    public static List<Monster> getMonstersFromString(String monsterNames, Map<String,Monster> monsters) {
        List<Monster> monsterList = new ArrayList<>();
        String[] monsterNamesArray = monsterNames.split(",");
        for(String monsterName : monsterNamesArray){
            String monsterTrimName = monsterNames.trim();
            Monster matchingMonster = monsters.get(monsterTrimName);
            if(matchingMonster != null){
                monsterList.add(matchingMonster);
            }
        }
        return monsterList;
    }
}
