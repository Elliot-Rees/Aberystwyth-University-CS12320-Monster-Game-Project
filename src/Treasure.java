/**
 * Represents a collectible treasure with a type, colour, and special power.
 *
 *
 * @author Elliot Rees
 * @version 4 (23/02/2026)
 */

public class Treasure {
      private TreasureType treasureType;
      private String treasureColour;
      private int specialPowerPoints;

      /**
       * Creates a treasure instance.
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
       * Updates this treasure's special power points.
       *
       * @param newPoints new special power value
       */
      public void setSpecialPowerPoints(int newPoints) {
            this.specialPowerPoints = newPoints;
      }

      /**
       * Returns the treasure colour.
       *
       * @return colour description
       */
      public String getTreasureColour() {
            return this.treasureColour;
      }

      /**
       * Updates the treasure colour.
       *
       * @param newColour new colour description
       */
      public void setTreasureColour(String newColour) {
            this.treasureColour = newColour;
      }

      /**
       * Returns the treasure type.
       *
       * @return treasure type
       */
      public TreasureType getTreasureType() {
            return this.treasureType;
      }

      /**
       * Updates the treasure type.
       *
       * @param newType new treasure type
       */
      public void setTreasureType(TreasureType newType) {
            this.treasureType = newType;
      }

      /**
       * Returns a readable summary of this treasure.
       *
       * @return formatted treasure description
       */
      public String toString() {
            return "Treasure is of type " + this.treasureType + " with " + this.treasureColour + " colour " + this.specialPowerPoints + " points.";
      }
}
