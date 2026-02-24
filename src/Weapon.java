/**
 * Represents a weapon with a category and fixed damage value.
 *
 * @author Elliot Rees
 * @version 4 (23/02/2026)
 */
public class Weapon {
      private WeaponType weaponType;
      private int damagePoints;

      /**
       * Creates a weapon.
       *
       * @param weaponType weapon category
       * @param damagePoints damage points dealt by this weapon
       */
      public Weapon(WeaponType weaponType, int damagePoints){
            this.weaponType = weaponType;
            this.damagePoints = damagePoints;
      }

      /**
       * Updates weapon category.
       *
       * @param newType new weapon type
       */
      public void setWeaponType(WeaponType newType) {
            this.weaponType = newType;
      }

      /**
       * Returns weapon category.
       *
       * @return weapon type
       */
      public WeaponType getWeaponType(){
            return this.weaponType;
      }

      /**
       * Updates damage points.
       *
       * @param newPoints new damage value
       */
      public void setDamagePoints(int newPoints) {
            this.damagePoints = newPoints;
      }

      /**
       * Returns damage points.
       *
       * @return damage points
       */
      public int getDamagePoints(){
            return this.damagePoints;
      }

      /**
       * Returns a readable summary of this weapon.
       *
       * @return formatted weapon description
       */
      public String toString(){
            return "Weapon of type " + this.weaponType
                    + " with " + this.damagePoints + " damage points.";
      }

}
