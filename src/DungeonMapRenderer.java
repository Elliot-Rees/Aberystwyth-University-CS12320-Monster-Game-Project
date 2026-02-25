/**
 * Renders the dungeon layout and marks player position.
 */
public class DungeonMapRenderer {

    public void print(Dungeon dungeon, Room currentRoom) {
        print(dungeon, currentRoom, false);
    }

    public void print(Dungeon dungeon, Room currentRoom, boolean useUnicode) {
        String you = currentRoom == null ? "" : " [YOU]";
        String tl = dungeon.getGreatHall().getName() + (currentRoom == dungeon.getGreatHall() ? you : "");
        String tr = dungeon.getCrypt().getName() + (currentRoom == dungeon.getCrypt() ? you : "");
        String bl = dungeon.getWestEnd().getName() + (currentRoom == dungeon.getWestEnd() ? you : "");
        String br = dungeon.getEastEnd().getName() + (currentRoom == dungeon.getEastEnd() ? you : "");
        String dragon = dungeon.getDragonLair().getName() + (currentRoom == dungeon.getDragonLair() ? you : "");

        System.out.println("+----------------------+      +----------------------+");
        System.out.printf("| %-20s | ---- | %-20s |%n", tl, tr);
        System.out.println("+----------------------+      +----------------------+");
        System.out.println("           |                           |");
        System.out.println("           |                           |");
        System.out.println("+----------------------+      +----------------------+");
        System.out.printf("| %-20s | ---- | %-20s |%n", bl, br);
        System.out.println("+----------------------+      +----------------------+");
        System.out.println("                                        |");
        System.out.println("                                        |");
        System.out.println("                             +----------------------+");
        System.out.printf("                             | %-20s |%n", dragon);
        System.out.println("                             +----------------------+");

        if (currentRoom == null) {
            System.out.println("No room selected yet.");
        }
    }
}
