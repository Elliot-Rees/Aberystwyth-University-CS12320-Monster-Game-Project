import java.util.Scanner;

/**
 * Interactive dungeon application with room movement and monster fights.
 */
public class Application {
    private final Dungeon dungeon;
    private final Player player;
    private final Scanner scanner;
    private final DungeonMapRenderer mapRenderer;
    private boolean dragonLairUnlockAnnounced;
    private boolean dragonDefeatAnnounced;

    public Application() {
        this.dungeon = new Dungeon();
        this.player = new Player("Hero", 40, 7);
        this.scanner = new Scanner(System.in);
        this.mapRenderer = new DungeonMapRenderer();
        this.dragonLairUnlockAnnounced = false;
        this.dragonDefeatAnnounced = false;
    }

    public static void main(String[] args) {
        new Application().runMenu();
    }

    private void printMenu() {
        System.out.println();
        System.out.println("=== DUNGEON MENU ===");
        System.out.println("N/E/S/W - Move");
        System.out.println("P       - Show current room");
        System.out.println("L       - List all rooms");
        System.out.println("G       - Set current room by name");
        System.out.println("M       - Show dungeon map");
        System.out.println("X       - Enter room and declare monsters");
        System.out.println("F       - Fight hostile monsters in current room");
        System.out.println("D       - Show player information");
        System.out.println("Q       - Quit");
        System.out.print("Choose option: ");
    }

    private void runMenu() {
        boolean running = true;

        while (running) {
            printMenu();
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.isEmpty()) {
                System.out.println("Please enter an option.");
                continue;
            }

            switch (input) {
                case "N":
                case "E":
                case "S":
                case "W":
                    move(input.charAt(0));
                    break;
                case "P":
                    printCurrentRoom();
                    break;
                case "L":
                    listAllRooms();
                    break;
                case "G":
                    goToRoomByName();
                    break;
                case "M":
                    mapRenderer.print(dungeon, dungeon.getCurrentRoom());
                    break;
                case "X":
                    enterRoom();
                    break;
                case "F":
                    fightInCurrentRoom();
                    if (player.getHealthPoints() <= 0) {
                        running = false;
                    }
                    break;
                case "D":
                    displayPlayerInfo();
                    break;
                case "Q":
                    running = false;
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

            if (!dragonLairUnlockAnnounced && dungeon.allMainDungeonHostilesDefeated()) {
                dragonLairUnlockAnnounced = true;
                System.out.println("All hostile monsters in the main dungeon are defeated.");
                System.out.println("A hidden path opens: East End -> South -> Dragon Lair.");
            }

            if (!dragonDefeatAnnounced && dungeon.allHostileMonstersDefeated()) {
                dragonDefeatAnnounced = true;
                System.out.println("You defeated every hostile monster, including the dragon.");
                System.out.println("The dungeon is fully cleared.");
            }
        }

        scanner.close();
    }

    private void move(char direction) {
        Room currentRoom = dungeon.getCurrentRoom();
        if (currentRoom == null) {
            System.out.println("No room has been selected yet!");
            return;
        }

        Room nextRoom = null;
        switch (direction) {
            case 'N':
                nextRoom = currentRoom.getNorth();
                break;
            case 'E':
                nextRoom = currentRoom.getEast();
                break;
            case 'S':
                nextRoom = currentRoom.getSouth();
                break;
            case 'W':
                nextRoom = currentRoom.getWest();
                break;
            default:
                break;
        }

        if (nextRoom == null) {
            System.out.println("Can't go " + directionToWord(direction) + "!");
            return;
        }

        if (nextRoom == dungeon.getDragonLair() && !dungeon.allMainDungeonHostilesDefeated()) {
            System.out.println("A magical barrier blocks the Dragon Lair. Defeat all main-dungeon hostile monsters first.");
            return;
        }

        dungeon.setCurrentRoom(nextRoom);
        System.out.println("Moved to: " + nextRoom.getName());
    }

    private String directionToWord(char direction) {
        switch (direction) {
            case 'N': return "north";
            case 'E': return "east";
            case 'S': return "south";
            case 'W': return "west";
            default: return "that direction";
        }
    }

    private void printCurrentRoom() {
        Room currentRoom = dungeon.getCurrentRoom();
        if (currentRoom == null) {
            System.out.println("No room has been selected yet!");
        } else {
            System.out.println("Current room: " + currentRoom.getName());
        }
    }

    private void listAllRooms() {
        System.out.println("Rooms:");
        for (Room room : dungeon.getAllRooms()) {
            System.out.println("- " + room.getName());
        }
    }

    private void goToRoomByName() {
        System.out.print("Enter room name: ");
        String roomName = scanner.nextLine().trim();
        Room room = dungeon.getRoomByName(roomName);

        if (room == null) {
            System.out.println("Room not found. Check spelling and try again.");
            return;
        }

        if (room == dungeon.getDragonLair() && !dungeon.allMainDungeonHostilesDefeated()) {
            System.out.println("Dragon Lair is locked. Defeat all main-dungeon hostile monsters first.");
            return;
        }

        if (room == dungeon.getCurrentRoom()) {
            System.out.println("You are already in " + room.getName() + ".");
            return;
        }

        dungeon.setCurrentRoom(room);
        System.out.println("Starting room set to: " + room.getName());
    }

    private void enterRoom() {
        Room currentRoom = dungeon.getCurrentRoom();
        if (currentRoom == null) {
            System.out.println("No room has been selected yet!");
            return;
        }

        System.out.println("Entered room: " + currentRoom.getName());

        Monster[] monsters = currentRoom.getMonsters();
        if (monsters.length == 0) {
            System.out.println("No monsters in this room.");
            return;
        }

        System.out.println("Monsters in this room:");
        for (Monster monster : monsters) {
            String status = monster.getHostility() ? "Hostile" : "Friendly";
            System.out.println("- " + monster + " -> " + status);
        }
    }

    private void fightInCurrentRoom() {
        Room currentRoom = dungeon.getCurrentRoom();
        if (currentRoom == null) {
            System.out.println("No room has been selected yet!");
            return;
        }

        Monster[] monsters = currentRoom.getMonsters();
        if (monsters.length == 0) {
            System.out.println("No monsters to fight in this room.");
            return;
        }

        for (Monster monster : monsters) {
            if (!monster.getHostility() || monster.getHealthPoints() <= 0) {
                continue;
            }

            System.out.println("Fight started with: " + monster.getMonsterType());
            while (player.getHealthPoints() > 0 && monster.getHealthPoints() > 0) {
                boolean monsterDefeated = player.attack(monster);
                System.out.println(player.getName() + " hits for " + player.getPowerPoints()
                        + ". Monster HP: " + monster.getHealthPoints());

                if (monsterDefeated) {
                    System.out.println(monster.getMonsterType() + " defeated.");
                    break;
                }

                boolean playerDefeated = monster.attack(player);
                System.out.println(monster.getMonsterType() + " hits for " + monster.getPowerPoints()
                        + ". Player HP: " + player.getHealthPoints());

                if (playerDefeated) {
                    monster.collectLootFromPlayer(player);
                    System.out.println("You were defeated by " + monster.getMonsterType() + ". Game over.");
                    return;
                }
            }
        }

        currentRoom.removeDefeatedMonsters();
        System.out.println("Fight phase complete in " + currentRoom.getName() + ".");

        if (currentRoom == dungeon.getDragonLair() && currentRoom.getNumHostileMonstersAlive() == 0) {
            System.out.println("The dragon is slain. You claim the hoard of gold.");
        }
    }

    private void displayPlayerInfo() {
        System.out.println(player);
        if (player.getTreasureCount() == 0) {
            System.out.println("No treasures collected yet.");
            return;
        }

        System.out.println("Collected treasures:");
        for (Treasure treasure : player.getTreasures()) {
            System.out.println("- " + treasure);
        }
    }
}
