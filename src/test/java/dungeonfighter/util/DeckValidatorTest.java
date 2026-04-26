package dungeonfighter.util;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeckValidatorTest {

    @Test
    void validDeck_shouldReturnTrue() {
        List<String> deck = List.of("Card1", "Card2", "Card3");
        assertTrue(DeckValidator.isValidDeck(deck));
    }

    @Test
    void emptyDeck_shouldReturnFalse() {
        List<String> deck = List.of();
        assertFalse(DeckValidator.isValidDeck(deck));
    }

    @Test
    void nullDeck_shouldReturnFalse() {
        assertFalse(DeckValidator.isValidDeck(null));
    }

    @Test
    void duplicateCards_shouldReturnFalse() {
        List<String> deck = List.of("Card1", "Card1");
        assertFalse(DeckValidator.isValidDeck(deck));
    }

    @Test
    void tooManyCards_shouldReturnFalse() {
        List<String> deck = List.of(
                "C1","C2","C3","C4","C5","C6","C7","C8","C9"
        );
        assertFalse(DeckValidator.isValidDeck(deck));
    }
}
