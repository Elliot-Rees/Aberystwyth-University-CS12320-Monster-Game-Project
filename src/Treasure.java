/**
 * Represents a collectible treasure with a type, colour, and special power.
 *
 * @author Elliot Rees
 * @version 4 (23/02/2026)
 */

//Todo: Add comments to explain code
public class Treasure {
  private TreasureType treasureType;
  private String treasureColour;
  private int specialPowerPoints;

  /**
   * Creates treasure instance.
   *
   * @param treasureType category of treasure
   * @param treasureColour colour description
   * @param specialPowerPoints special power value granted by this treasure
   */
  public Treasure(TreasureType treasureType, String treasureColour, int specialPowerPoints) {
    this.treasureType = treasureType;
    this.treasureColour = treasureColour;
    this.specialPowerPoints = specialPowerPoints;
  }

  /**
   * Returns this treasure's special power points.
   *
   * @return special power points
   */
  public int getSpecialPowerPoints() {
    return  specialPowerPoints;
  }

  /**
   * Returns summary of treasure.
   *
   * @return formatted treasure description
   */
  public String toString() {
    return "Treasure is of type " + this.treasureType + " with " + this.treasureColour + " colour " + this.specialPowerPoints + " points.";
  }
}
