package dungeonfighter.util;

import dungeonfighter.game.Card;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Utility class for validating player decks.
 * Ensures decks follow game rules before gameplay.
 *
 * @author Christian Meza
 * @since 4/25/26
 */
public class DeckValidator
{
    // Maximum number of cards allowed in a deck
    private static final int MAX_DECK_SIZE = 8;

    /**
     * Validates a deck based on basic game rules.
     *
     * @param deck List of Card objects in the player's deck
     * @return true if deck is valid, false otherwise
     */
    public static boolean isValidDeck(List<Card> deck)
    {
        // Rule 1: Deck must not be null
        if (deck == null)
        {
            return false;
        }

        // Rule 2: Deck must contain at least one card
        if (deck.isEmpty())
        {
            return false;
        }

        // Rule 3: Deck cannot exceed maximum allowed size
        if (deck.size() > MAX_DECK_SIZE)
        {
            return false;
        }

        // Rule 4: Deck cannot contain null cards
        for (Card card : deck)
        {
            if (card == null)
            {
                return false;
            }
        }

        // Rule 5: No duplicate cards allowed based on card ID
        Set<Integer> uniqueCardIds = new HashSet<>();

        for (Card card : deck)
        {
            if (!uniqueCardIds.add(card.getCardId()))
            {
                return false;
            }
        }

        // If all checks pass, the deck is valid
        return true;
    }

    /**
     * Returns an error message explaining why a match cannot start.
     *
     * @param deck List of Card objects in the player's deck
     * @return an error message if invalid, or null if the match can start
     */
    public static String getMatchStartError(List<Card> deck)
    {
        // If the deck doesn't exist or has no cards, we can't start a match
        if (deck == null || deck.isEmpty())
        {
            return "Deck cannot be empty";
        }

        // If the deck fails validation, return a general error message
        if (!isValidDeck(deck))
        {
            return "Deck is invalid";
        }

        // If everything passes, return null to show there are no errors
        return null;
    }
}