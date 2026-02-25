/**
 * Dungeon composed of four interconnected rooms.
 */
public class Dungeon {
    private Room greatHall;
    private Room crypt;
    private Room westEnd;
    private Room eastEnd;
    private Room dragonLair;
    private Room currentRoom;

    public Dungeon() {
        buildRooms();
        connectRooms();
        populateMonsters();
        this.currentRoom = null;
    }

    public Room getGreatHall() {
        return this.greatHall;
    }

    public Room getCrypt() {
        return this.crypt;
    }

    public Room getWestEnd() {
        return this.westEnd;
    }

    public Room getEastEnd() {
        return this.eastEnd;
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public Room getDragonLair() {
        return this.dragonLair;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Room[] getAllRooms() {
        return new Room[]{this.greatHall, this.crypt, this.westEnd, this.eastEnd, this.dragonLair};
    }

    public Room getRoomByName(String roomName) {
        if (roomName == null) {
            return null;
        }
        for (Room room : getAllRooms()) {
            if (room.getName().equalsIgnoreCase(roomName.trim())) {
                return room;
            }
        }
        return null;
    }

    public boolean allHostileMonstersDefeated() {
        for (Room room : getAllRooms()) {
            if (room.getNumHostileMonstersAlive() > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean allMainDungeonHostilesDefeated() {
        Room[] mainRooms = {this.greatHall, this.crypt, this.westEnd, this.eastEnd};
        for (Room room : mainRooms) {
            if (room.getNumHostileMonstersAlive() > 0) {
                return false;
            }
        }
        return true;
    }

    private void buildRooms() {
        this.greatHall = new Room("Great Hall");
        this.crypt = new Room("Crypt");
        this.westEnd = new Room("West End");
        this.eastEnd = new Room("East End");
        this.dragonLair = new Room("Dragon Lair");
    }

    private void connectRooms() {
        this.greatHall.setEast(this.crypt);
        this.greatHall.setSouth(this.westEnd);

        this.crypt.setWest(this.greatHall);
        this.crypt.setSouth(this.eastEnd);

        this.westEnd.setNorth(this.greatHall);
        this.westEnd.setEast(this.eastEnd);

        this.eastEnd.setNorth(this.crypt);
        this.eastEnd.setWest(this.westEnd);
        this.eastEnd.setSouth(this.dragonLair);

        this.dragonLair.setNorth(this.eastEnd);
    }

    private void populateMonsters() {
        Treasure coin1 = new Treasure(TreasureType.COIN, "Golden", 50);
        Treasure coin2 = new Treasure(TreasureType.COIN, "Silver", 20);
        Treasure coin3 = new Treasure(TreasureType.COIN, "Iron", 15);
        Treasure growth = new Treasure(TreasureType.POTION, "Green", 30);

        Monster goblin1 = new Monster(MonsterType.GOBLIN, "Green", true, coin1);
        goblin1.setHealthPoints(16);
        goblin1.setPowerPoints(4);

        Monster goblin2 = new Monster(MonsterType.GOBLIN, "Black", true, coin2);
        goblin2.setHealthPoints(18);
        goblin2.setPowerPoints(5);

        Monster giant = new Monster(MonsterType.GIANT, "Blue", true, coin3);
        giant.setHealthPoints(28);
        giant.setPowerPoints(7);

        Monster orc = new Monster(MonsterType.HUMANOID, "Black", true, coin3);
        orc.setHealthPoints(22);
        orc.setPowerPoints(6);

        Monster wizard = new Monster(MonsterType.WIZARD, "White", false, growth);
        wizard.setHealthPoints(20);
        wizard.setPowerPoints(5);

        this.greatHall.addMonster(goblin1);
        this.greatHall.addMonster(goblin2);

        this.crypt.addMonster(giant);

        this.westEnd.addMonster(orc);
        this.westEnd.addMonster(wizard);

        Treasure hoardOne = new Treasure(TreasureType.COIN, "Gold", 80);
        Treasure hoardTwo = new Treasure(TreasureType.COIN, "Gold", 120);
        Treasure hoardThree = new Treasure(TreasureType.COIN, "Gold", 200);
        Treasure hoardFour = new Treasure(TreasureType.COIN, "Gold", 300);
        Treasure hoardFive = new Treasure(TreasureType.COIN, "Gold", 500);

        Monster dragon = new Monster(MonsterType.DRAGON, "Crimson", true, hoardOne);
        dragon.addTreasure(hoardTwo);
        dragon.addTreasure(hoardThree);
        dragon.addTreasure(hoardFour);
        dragon.addTreasure(hoardFive);
        dragon.setHealthPoints(49);
        dragon.setPowerPoints(8);
        this.dragonLair.addMonster(dragon);
    }
}
