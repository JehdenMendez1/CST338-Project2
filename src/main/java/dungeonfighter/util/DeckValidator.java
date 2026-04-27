package dungeonfighter.util;

import java.util.List;
import java.util.HashSet;

/**
 * Utility class for validating player decks.
 * Ensures decks follow game rules before gameplay.
 *
 * @author Christian Meza
 * @since 4/25/26
 */
public class DeckValidator {

    // Maximum number of cards allowed in a deck
    private static final int MAX_DECK_SIZE = 8;

    /**
     * Validates a deck based on basic game rules.
     *
     * @param deck List of card identifiers (can later be replaced with Card objects)
     * @return true if deck is valid, false otherwise
     */
    public static boolean isValidDeck(List<String> deck) {

        // Rule 1: Deck must not be null
        if (deck == null) return false;

        // Rule 2: Deck must contain at least one card
        if (deck.isEmpty()) return false;

        // Rule 3: Deck cannot exceed maximum allowed size
        if (deck.size() > MAX_DECK_SIZE) return false;

        // Rule 4: No duplicate cards allowed
        // Convert list to a set (removes duplicates automatically)
        HashSet<String> uniqueCards = new HashSet<>(deck);

        // If sizes differ, duplicates existed in the original list
        if (uniqueCards.size() != deck.size()) return false;

        // If all checks pass, the deck is valid
        return true;
    }
}
