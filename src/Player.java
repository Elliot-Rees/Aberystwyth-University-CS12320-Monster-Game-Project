import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a player with combat stats and a treasure inventory.
 */
public class Player {
    private String name;
    private int healthPoints;
    private int powerPoints;
    private final List<Treasure> treasures;

    public Player(String name, int healthPoints, int powerPoints) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.powerPoints = powerPoints;
        this.treasures = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = Math.max(healthPoints, 0);
    }

    public int getPowerPoints() {
        return this.powerPoints;
    }

    public void setPowerPoints(int powerPoints) {
        this.powerPoints = Math.max(powerPoints, 0);
    }

    public List<Treasure> getTreasures() {
        return Collections.unmodifiableList(this.treasures);
    }

    public int getTreasureCount() {
        return this.treasures.size();
    }

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

    public void collectTreasure(Treasure treasure) {
        if (treasure == null) {
            return;
        }
        this.treasures.add(treasure);
        this.healthPoints += treasure.getSpecialPowerPoints();
    }

    public Treasure[] dropAllTreasures() {
        Treasure[] dropped = this.treasures.toArray(new Treasure[0]);
        this.treasures.clear();
        return dropped;
    }

    @Override
    public String toString() {
        return "Player " + this.name
                + " has " + this.healthPoints + " health points, "
                + this.powerPoints + " power points, and "
                + this.treasures.size() + " treasure(s).";
    }

    private void lootMonster(Monster monster) {
        Treasure[] lootedTreasures = monster.dropAllTreasures();
        for (Treasure treasure : lootedTreasures) {
            collectTreasure(treasure);
        }
    }
}
