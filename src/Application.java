/**
 * Entry point for the combined dungeon and battle demo.
 * First displays dungeon room graph, then runs the battle simulation.
 * @author Elliot Rees
 * @version 6 (24/02/2026)
 */

// Todo: Add comments explaining code

public class Application {
      /**
       * Starts the program.
       * public keyword redundant hence removed
       * @param args command-line arguments (unused for now. Throws error when removed ¯\_(ツ)_/¯ Ignore error)
       */

      static void main(String[] args) {
            displayRoomGraph();
            System.out.println();
            runBattleSimulation();
      }

      /**
       * Displays all rooms and their directional links, plus current room.
       */
      private static void displayRoomGraph() {
            Dungeon dungeon = new Dungeon();

            System.out.println("== DUNGEON ROOMS ==");
            for (Room room : dungeon.getAllRooms()) {
                  System.out.println(room);
            }

            System.out.println();
            Room current = dungeon.getCurrentRoom();
            if (current != null) {
                  System.out.println("Current room: " + current.getName());
            } else {
                  System.out.println("Current room: None");
            }
      }

      /**
       * Runs a battle between a player and a monster.
       */
      private static void runBattleSimulation() {
            Player hero = new Player("Hero", 20, 6);
            Weapon sword = new Weapon(WeaponType.SWORD, 6);

            Monster goblin = new Monster(MonsterType.GOBLIN, "Green", true);
            goblin.setHealthPoints(18);
            goblin.setPowerPoints(4);
            goblin.addTreasure(new Treasure(TreasureType.GEM, "Green", 5));

            System.out.println("== BATTLE STARTS ==");
            System.out.println(
                    "Player name '" + hero.getName() + "' with " + hero.getHealthPoints()
                            + " health points, and " + hero.getPowerPoints()
                            + " power points. Possesses Weapon of type " + sword.getWeaponType()
                            + " with " + sword.getDamagePoints() + " damage points and loot none."
            );
            System.out.println(
                    "The scary monster is of type " + goblin.getMonsterType()
                            + " with " + goblin.getHair() + " hair and "
                            + goblin.getHealthPoints() + " health points."
            );
            System.out.println();

            while (hero.getHealthPoints() > 0 && goblin.getHealthPoints() > 0) {
                  boolean monsterDefeated = hero.attack(goblin);
                  System.out.println(
                          hero.getName() + " attacks the monster for " + hero.getPowerPoints()
                                  + " damage. Monster HP now: " + goblin.getHealthPoints()
                  );
                  if (monsterDefeated) {
                        break;
                  }

                  goblin.attack(hero);
                  System.out.println(
                          "Monster attacks " + hero.getName() + " for " + goblin.getPowerPoints()
                                  + " damage. " + hero.getName() + " HP now: " + hero.getHealthPoints()
                  );
            }

            System.out.println();
            System.out.println("== BATTLE ENDS ==");
            if (hero.getHealthPoints() > 0) {
                  System.out.println("Player wins! Looting monster's treasure...");
            } else {
                  System.out.println("Monster wins! Player was defeated.");
            }
      }
}
