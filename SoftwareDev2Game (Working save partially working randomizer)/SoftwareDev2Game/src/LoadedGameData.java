import java.util.Map;

public class LoadedGameData {private Player loadedPlayer;
    private Map<String, Room> loadedRooms;

    public LoadedGameData(Player loadedPlayer, Map<String, Room> loadedRooms) {
        this.loadedPlayer = loadedPlayer;
        this.loadedRooms = loadedRooms;
    }

    public Player getLoadedPlayer() {
        return loadedPlayer;
    }

    public Map<String, Room> getLoadedRooms() {
        return loadedRooms;
    }
}
