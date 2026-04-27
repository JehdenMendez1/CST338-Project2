package dungeonfighter.util;

import dungeonfighter.game.Card;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for DeckValidator.
 * Verifies deck validation rules such as size limits and duplicate detection.
 *
 * @author Christian Meza
 * @since 4/25/26
 */
public class DeckValidatorTest
{
    private Card createCard(int id)
    {
        return new Card(id, "Card" + id, 10, 10, "", "", "");
    }

    @Test
    void validDeck_shouldReturnTrue()
    {
        List<Card> deck = List.of(
                createCard(1),
                createCard(2),
                createCard(3)
        );

        assertTrue(DeckValidator.isValidDeck(deck));
    }

    @Test
    void emptyDeck_shouldReturnFalse()
    {
        List<Card> deck = List.of();

        assertFalse(DeckValidator.isValidDeck(deck));
    }

    @Test
    void nullDeck_shouldReturnFalse()
    {
        assertFalse(DeckValidator.isValidDeck(null));
    }

    @Test
    void duplicateCards_shouldReturnFalse()
    {
        // Two cards with the same ID should fail the duplicate rule.
        List<Card> deck = List.of(
                createCard(1),
                createCard(1)
        );

        assertFalse(DeckValidator.isValidDeck(deck));
    }

    @Test
    void tooManyCards_shouldReturnFalse()
    {
        // Deck size exceeds the maximum allowed size of 8.
        List<Card> deck = List.of(
                createCard(1),
                createCard(2),
                createCard(3),
                createCard(4),
                createCard(5),
                createCard(6),
                createCard(7),
                createCard(8),
                createCard(9)
        );

        assertFalse(DeckValidator.isValidDeck(deck));
    }

    @Test
    void deckWithNullCard_shouldReturnFalse()
    {
        // A deck should not be valid if one of its card slots is null.
        List<Card> deck = new java.util.ArrayList<>();
        deck.add(createCard(1));
        deck.add(null);

        assertFalse(DeckValidator.isValidDeck(deck));
    }

    @Test
    void emptyDeck_shouldReturnMatchStartError()
    {
        assertEquals("Deck cannot be empty",
                DeckValidator.getMatchStartError(List.of()));
    }

    @Test
    void invalidDeck_shouldReturnMatchStartError()
    {
        // Duplicate cards make the deck invalid, so the match should not start.
        List<Card> deck = List.of(
                createCard(1),
                createCard(1)
        );

        assertEquals("Deck is invalid",
                DeckValidator.getMatchStartError(deck));
    }

    @Test
    void validDeck_shouldReturnNoMatchStartError()
    {
        List<Card> deck = List.of(
                createCard(1),
                createCard(2)
        );

        assertNull(DeckValidator.getMatchStartError(deck));
    }
}
