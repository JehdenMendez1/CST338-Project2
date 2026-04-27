package dungeonfighter.game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class CardTest {

    @Test
    void constructor_shouldSetAllFieldsCorrectly() {
        Card card = new Card(10, "Knight", 50, 60, "Slash", "Deals physical damage", "knight.png");
        assertEquals(10, card.getCardId());
        assertEquals("Knight", card.getName());
        assertEquals(50, card.getHealth());
        assertEquals(60, card.getMaxHealth());
        assertEquals("Slash", card.getAbility());
        assertEquals("Deals physical damage", card.getAbilityDescription());
        assertEquals("knight.png", card.getImageAsset());
        assertEquals(50.0 / 60.0, card.getHealthProgress());
        assertFalse(card.isDefeated());
    }
    @Test
    void invalidMaxHealthTest() {
        assertThrows(IllegalArgumentException.class,
                () -> new Card("Wrong", 0, "N/A", "N/A", "asset.png"));
    }
    @Test
    void takeDamage_shouldReduceHealth() {
        Card card = new Card("Knight", 50, "Slash", "Deals damage", "knight.png");
        card.takeDamage(20);
        assertEquals(30, card.getHealth());
        assertEquals(0.6, card.getHealthProgress(), 0.0001);
        assertFalse(card.isDefeated());
    }

    @Test
    void lethalDamage_shouldSetHealthToZero() {
        Card card = new Card("Knight", 50, "Slash", "Deals damage", "knight.png");
        card.takeDamage(80);
        assertEquals(0, card.getHealth());
        assertEquals(0.0, card.getHealthProgress(), 0.0001);
        assertTrue(card.isDefeated());
    }

    @Test
    void heal_shouldIncreaseHealth() {
        Card card = new Card("Knight", 50, "Slash", "Deals damage", "knight.png");
        card.takeDamage(30);
        card.heal(20);
        assertEquals(40, card.getHealth());
        card.heal(20);
        assertEquals(50, card.getHealth());
        assertEquals(1.0, card.getHealthProgress());
    }
}
