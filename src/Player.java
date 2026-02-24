import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Player's health increases when they defeat a monster and loot treasure
 *
 * @author Elliot Rees
 * @version 1 (23/02/2026)
 */

//Todo: Add comments to explain code
public class Player {
  private String name;
  private int healthPoints;
  private int powerPoints;
  private final List<Treasure> treasures;

  /**
   * Creates a player with name, initial health, and power.
   *
   * @param name player name
   * @param healthPoints initial health points
   * @param powerPoints initial power points
   */
  public Player(String name, int healthPoints, int powerPoints) {
    this.name = name;
    this.healthPoints = healthPoints;
    this.powerPoints = powerPoints;
    this.treasures = new ArrayList<>();
  }

  /**
   * Returns the player name.
   *
   * @return player name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Updates the player name.
   *
   * @param name new player name
   */
  public void setName(String name) { // Not used yet Ignore warning
    this.name = name;
  }

  /**
   * Returns player health points.
   *
   * @return health points
   */
  public int getHealthPoints() {
    return this.healthPoints;
  }

  /**
   * Updates player health points.
   *
   * @param healthPoints new health value
   */
  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
  }

  /**
   * Returns player power points.
   *
   * @return power points
   */
  public int getPowerPoints() {
    return this.powerPoints;
  }

  /**
   * Updates player power points.
   *
   * @param powerPoints new power value
   */
  public void setPowerPoints(int powerPoints) { // Not used yet Ignore warning
    this.powerPoints = powerPoints;
  }

  /**
   * Returns an unmodifiable view of owned treasures.
   *
   * @return read-only treasure list
   */
  public List<Treasure> getTreasures() { // Not used yet Ignore warning
    return Collections.unmodifiableList(this.treasures);
  }

  /**
   * Attacks the monster. If the monster is defeated, the player
   * loots all monster treasures and gains health from looted item(s).
   *
   * @param monster monster to attack
   * @return {@code true} if the monster is defeated after this attack
   */
  public boolean attack(Monster monster) {
    if (monster == null || this.healthPoints <= 0) {
      return false;
    }
    int remainingHealth = monster.getHealthPoints() - this.powerPoints;
    monster.setHealthPoints(Math.max(remainingHealth, 0));
    boolean monsterDefeated = monster.getHealthPoints() == 0;
    if (monsterDefeated) {
      lootMonster(monster);
    }
    return monsterDefeated;
  }

  /**
   * Adds a treasure to the player and increases health by its special power.
   *
   * @param treasure treasure to collect
   */
  public void collectTreasure(Treasure treasure) {
    if (treasure == null) {
      return;
    }
    this.treasures.add(treasure);
    this.healthPoints += treasure.getSpecialPowerPoints();
  }

  /**
   * Returns a summary of the player state.
   *
   * @return formatted player details
   */
  @Override // Provides own version of method that already exists in parent object
  public String toString() {
    return "Player " + this.name
        + " has " + this.healthPoints + " health points, "
        + this.powerPoints + " power points, and "
        + this.treasures.size() + " treasure(s).";
  }

  /**
   * Loots all treasure from a defeated monster.
   *
   * @param monster defeated monster
   */
  private void lootMonster(Monster monster) {
    Treasure[] lootedTreasures = monster.dropAllTreasures();
    for (Treasure treasure : lootedTreasures) {
      collectTreasure(treasure);
    }
  }
}
