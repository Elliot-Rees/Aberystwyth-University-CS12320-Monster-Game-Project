/**
 * Represents a monster with a type, appearance, combat stats, and inventory.
 * A monster can own up to {@link #MAX_TREASURES} (set to 5 for testing)  treasures and optionally carry a {@link Weapon}.
 * @author Elliot Rees
 * @version 4 (23/02/2026)
 */

//Todo: Add comments to explain code

public class Monster {

  private MonsterType monsterType;
  private String hairColour;
  private int healthPoints;
  private int powerPoints;
  private boolean isHostile;
  private final Treasure [] ownsTreasures; // Set final to remove warning (Only locks array reference not contents)
  private int numTreasures;
  public static final int MAX_TREASURES = 5;
  private Weapon weapon;

  /**
   * Creates monster with attributes.
   *
   * @param monsterType the category of monster
   * @param hairColour the hair colour, or {@code null} if none
   * @param isHostile whether the monster is hostile
   */
  public Monster(MonsterType monsterType, String hairColour, boolean isHostile){
    this.monsterType = monsterType;
    this.hairColour = hairColour;
    this.isHostile = isHostile;
    this.ownsTreasures = new Treasure[MAX_TREASURES];
  }

  /**
   * Adds a treasure to the inventory if capacity allows.
   *
   * @param treasure the treasure to add
   */
  public void addTreasure(Treasure treasure){
    if(numTreasures < MAX_TREASURES) {
      this.ownsTreasures[numTreasures] = treasure;
      numTreasures++;
    }
  }

  void setMonsterType(MonsterType monsterType){ // Not used yet Ignore warning
    this.monsterType = monsterType;
  }

  MonsterType getMonsterType(){
    return this.monsterType;
  }

  /**
   * Updates the monster's hair colour.
   *
   * @param newHairColour new hair colour value
   */
  public void setHair(String newHairColour){ // Not used yet Ignore warning
    this.hairColour = newHairColour;
  }

  /**
   * Returns the monster's hair colour.
   *
   * @return hair colour, or {@code null} if not set
   */
  public String getHair(){
    return this.hairColour;
  }

  /**
   * Sets current health points.
   *
   * @param newHealthPoints new health value
   */
  public void setHealthPoints(int newHealthPoints){
    this.healthPoints = newHealthPoints;
  }

  /**
   * Returns current health points.
   *
   * @return health points
   */
  public int getHealthPoints(){
    return this.healthPoints;
  }

  /**
   * Sets current power points.
   *
   * @param newPowerPoints new power value
   */
  public void setPowerPoints(int newPowerPoints){
    this.powerPoints = newPowerPoints;
  }

  /**
   * Returns power points.
   *
   * @return power points
   */
  public int getPowerPoints(){
    return this.powerPoints;
  }

  /**
   * Returns true when hostile
   *
   * @return {@code true} when hostile
   */
  public boolean getHostility() { // Not Used Ignore warning
    return this.isHostile;
  }

  /**
   * Sets hostility status.
   *
   * @param isHostility new hostility state
   */
  public void setHostility(boolean isHostility) { // Not used yet Ignore warning
    this.isHostile = isHostility;
  }

  /**
   * Returns the number of treasures currently owned.
   *
   * @return owned treasure count
   */
  public int getNumTreasures() { // Not used yet Ignore warning
    return this.numTreasures;
  }

  /**
   * Returns array of owned treasures.
   *
   * @return treasure array
   */
  public Treasure[] getOwnedTreasures() {
    return this.ownsTreasures;
  }

  /**
   * Sums special power points across all non-null treasures.
   *
   * @return total special power points from treasure
   */
  public int getTotalSpecialPoints() { // Not used yet Ignore warning
    Treasure [] treasures = getOwnedTreasures();
    int totalHealthPoints = 0;
    for(Treasure t:treasures) {
      if (t != null) {
        totalHealthPoints = totalHealthPoints + t.getSpecialPowerPoints();
      }
    }
    return totalHealthPoints;
  }

  /**
   * Returns the equipped weapon.
   *
   * @return weapon, or {@code null} if none equipped
   */
  public Weapon getWeapon() { // Not used yet Ignore warning
    return this.weapon;
  }

  /**
   * Equips a weapon and synchronizes power points with its damage.
   *
   * @param newWeapon weapon to equip
   */
  public void setWeapon(Weapon newWeapon) { // Not used yet Ignore warning
    this.weapon = newWeapon;
    setPowerPoints(weapon == null ? 0 : weapon.getDamagePoints());
  }

  /**
   * Attacks the supplied player by reducing their health points by this
   * monster's current power points.
   *
   * @param player the player to attack
   * @return {@code true} if the player is defeated after the attack
   */
  public boolean attack(Player player) { // Output not used yet ignore warning
    if (player == null || this.healthPoints <= 0) {
      return false;
    }
    int remainingHealth = player.getHealthPoints() - this.powerPoints;
    player.setHealthPoints(Math.max(remainingHealth, 0));
    return player.getHealthPoints() == 0;
  }

  /**
   * Drops all currently owned treasures and clears this monster's inventory.
   *
   * @return an array containing all dropped treasures
   */
  public Treasure[] dropAllTreasures() {
    Treasure[] droppedTreasures = new Treasure[this.numTreasures];
    for (int i = 0; i < this.numTreasures; i++) {
      droppedTreasures[i] = this.ownsTreasures[i];
      this.ownsTreasures[i] = null;
    }
    this.numTreasures = 0;
    return droppedTreasures;
  }

  /**
   * Builds a readable summary of this monster and its possessions.
   *
   * @return formatted monster description
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("The monster of type ");
    sb.append(monsterType);
    sb.append(" with ").append(this.hairColour == null ? "no" : this.hairColour);
    sb.append(" hair and ").append(this.isHostile ? "very hostile" : "very friendly");
    sb.append(" with ").append(this.healthPoints).append(" health points");
    sb.append(" and ").append(this.powerPoints).append(" power points.");
    sb.append("\nIt has ").append(this.numTreasures).append("/").append(MAX_TREASURES).append(" treasures.\n");

    if (numTreasures > 0) {
      for (int i = 0; i < numTreasures; i++) {
        Treasure t = this.ownsTreasures[i];
        sb.append("Treasure ").append(i+1).append(":");
        sb.append(t == null ? "No treasure " : t.toString());
        if (i < numTreasures - 1) sb.append("\n");
      }
    }
    sb.append("\nIt also has a ").append(this.weapon == null ? "no weapon." : this.weapon.toString());
    return sb.toString();
  }
}
