package dungeonfighter.game;

public class Card {
    private int cardId;
    private final String name;
    private int health;
    private final int maxHealth;
    private final String ability;
    private final String abilityDescription;
    private final String imageAsset;

    public Card(String name, int maxHealth, String ability, String abilityDescription, String imageAsset) {
        this(0, name, maxHealth, maxHealth, ability, abilityDescription, imageAsset);
    }

    public Card(int cardId, String name, int health, int maxHealth, String ability,
                String abilityDescription, String imageAsset) {
        if (maxHealth <= 0) {
            throw new IllegalArgumentException("maxHealth must be greater than 0");
        }
        if (health < 0) {
            throw new IllegalArgumentException("health cannot be negative");
        }
        if (health > maxHealth) {
            throw new IllegalArgumentException("health cannot be greater than maxHealth");
        }
        this.cardId = cardId;
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = health;
        this.ability = ability;
        this.abilityDescription = abilityDescription;
        this.imageAsset = imageAsset;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
    public int getMaxHealth() {
        return maxHealth;
    }

    public String getAbility() {
        return ability;
    }

    public String getAbilityDescription() {
        return abilityDescription;
    }

    public String getImageAsset() {
        return imageAsset;
    }
    public void takeDamage(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        health = Math.max(0, health - amount);
    }

    public void heal(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Heal cannot be negative");
        }
        health = Math.min(maxHealth, health + amount);
    }

    public boolean isDefeated() {
        return health <= 0;
    }

    public double getHealthProgress() {
        return health / (double) maxHealth;
    }
}
