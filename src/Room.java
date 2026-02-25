/**
 * Represents a room in the dungeon with directional exits and up to two monsters.
 */
public class Room {
    public static final int MAX_MONSTERS = 2;

    private final String name;
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    private final Monster[] monsters;
    private int numMonsters;

    public Room(String name) {
        this.name = name;
        this.monsters = new Monster[MAX_MONSTERS];
        this.numMonsters = 0;
    }

    public String getName() {
        return this.name;
    }

    public Room getNorth() {
        return north;
    }

    public Room getSouth() {
        return south;
    }

    public Room getEast() {
        return east;
    }

    public Room getWest() {
        return west;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public boolean addMonster(Monster monster) {
        if (monster == null || numMonsters >= MAX_MONSTERS) {
            return false;
        }
        this.monsters[numMonsters] = monster;
        numMonsters++;
        return true;
    }

    public Monster[] getMonsters() {
        Monster[] copy = new Monster[numMonsters];
        for (int i = 0; i < numMonsters; i++) {
            copy[i] = monsters[i];
        }
        return copy;
    }

    public int getNumMonsters() {
        return numMonsters;
    }

    public int getNumHostileMonstersAlive() {
        int total = 0;
        for (int i = 0; i < numMonsters; i++) {
            Monster monster = monsters[i];
            if (monster != null && monster.getHostility() && monster.getHealthPoints() > 0) {
                total++;
            }
        }
        return total;
    }

    public void removeDefeatedMonsters() {
        int write = 0;
        for (int i = 0; i < numMonsters; i++) {
            Monster monster = monsters[i];
            if (monster != null && monster.getHealthPoints() > 0) {
                monsters[write] = monster;
                write++;
            }
        }

        for (int i = write; i < numMonsters; i++) {
            monsters[i] = null;
        }
        numMonsters = write;
    }

    @Override
    public String toString() {
        return "Room '" + this.name + "' -> North: " + nameOrNone(this.north)
                + ", South: " + nameOrNone(this.south)
                + ", East: " + nameOrNone(this.east)
                + ", West: " + nameOrNone(this.west);
    }

    private String nameOrNone(Room room) {
        return room == null ? "None" : room.getName();
    }
}
