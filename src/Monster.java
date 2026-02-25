/**
 * Represents a monster with type, combat stats, and treasure inventory.
 */
public class Monster {
    public static final int MAX_TREASURES = 5;
    private static final int MAX_EXTRA_TREASURES = 4;

    private MonsterType monsterType;
    private String hairColour;
    private int healthPoints;
    private int powerPoints;
    private boolean isHostile;

    private final Treasure[] ownsTreasures;
    private int numTreasures;

    private final Treasure[] extraTreasures = new Treasure[MAX_EXTRA_TREASURES];
    private int numExtraTreasures = 0;

    private Weapon weapon;

    public Monster(MonsterType monsterType, String hairColour, boolean isHostile) {
        this(monsterType, hairColour, isHostile, null);
    }

    public Monster(MonsterType monsterType, String hairColour, boolean isHostile, Treasure treasure) {
        this.monsterType = monsterType;
        this.hairColour = hairColour;
        this.isHostile = isHostile;
        this.ownsTreasures = new Treasure[MAX_TREASURES];
        this.numTreasures = 0;

        if (treasure != null) {
            addTreasure(treasure);
        }
    }

    public void addTreasure(Treasure treasure) {
        if (treasure == null || numTreasures >= MAX_TREASURES) {
            return;
        }
        this.ownsTreasures[numTreasures] = treasure;
        numTreasures++;
    }

    public void collectExtraTreasure(Treasure treasure) {
        if (treasure == null || numExtraTreasures >= MAX_EXTRA_TREASURES) {
            return;
        }
        this.extraTreasures[numExtraTreasures] = treasure;
        numExtraTreasures++;
    }

    public void collectLootFromPlayer(Player player) {
        if (player == null) {
            return;
        }
        Treasure[] dropped = player.dropAllTreasures();
        for (Treasure treasure : dropped) {
            collectExtraTreasure(treasure);
        }
    }

    void setMonsterType(MonsterType monsterType) {
        this.monsterType = monsterType;
    }

    MonsterType getMonsterType() {
        return this.monsterType;
    }

    public void setHair(String newHairColour) {
        this.hairColour = newHairColour;
    }

    public String getHair() {
        return this.hairColour;
    }

    public void setHealthPoints(int newHealthPoints) {
        this.healthPoints = Math.max(newHealthPoints, 0);
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public void setPowerPoints(int newPowerPoints) {
        this.powerPoints = Math.max(newPowerPoints, 0);
    }

    public int getPowerPoints() {
        return this.powerPoints;
    }

    public boolean getHostility() {
        return this.isHostile;
    }

    public void setHostility(boolean isHostility) {
        this.isHostile = isHostility;
    }

    public int getNumTreasures() {
        return this.numTreasures;
    }

    public Treasure[] getOwnedTreasures() {
        Treasure[] copy = new Treasure[this.numTreasures];
        for (int i = 0; i < this.numTreasures; i++) {
            copy[i] = this.ownsTreasures[i];
        }
        return copy;
    }

    public int getTotalSpecialPoints() {
        int totalHealthPoints = 0;
        for (int i = 0; i < this.numTreasures; i++) {
            Treasure t = this.ownsTreasures[i];
            if (t != null) {
                totalHealthPoints = totalHealthPoints + t.getSpecialPowerPoints();
            }
        }
        return totalHealthPoints;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public void setWeapon(Weapon newWeapon) {
        this.weapon = newWeapon;
        setPowerPoints(weapon == null ? 0 : weapon.getDamagePoints());
    }

    public boolean attack(Player player) {
        if (player == null || this.healthPoints <= 0) {
            return false;
        }
        int remainingHealth = player.getHealthPoints() - this.powerPoints;
        player.setHealthPoints(Math.max(remainingHealth, 0));
        return player.getHealthPoints() == 0;
    }

    public Treasure[] dropAllTreasures() {
        Treasure[] droppedTreasures = new Treasure[this.numTreasures];
        for (int i = 0; i < this.numTreasures; i++) {
            droppedTreasures[i] = this.ownsTreasures[i];
            this.ownsTreasures[i] = null;
        }
        this.numTreasures = 0;
        return droppedTreasures;
    }

    @Override
    public String toString() {
        return this.monsterType + " (hair: " + (this.hairColour == null ? "none" : this.hairColour)
                + ", " + (this.isHostile ? "hostile" : "friendly")
                + ", HP: " + this.healthPoints
                + ", PP: " + this.powerPoints
                + ", treasures: " + this.numTreasures
                + ", extra treasures: " + this.numExtraTreasures
                + ")";
    }
}
