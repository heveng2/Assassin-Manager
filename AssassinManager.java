import java.util.*;

public class AssassinManager {
    // Reference of head of kill ring linked list
    private AssassinNode killRingHead = null;
    // Reference of head of grave yard linked list
    private AssassinNode graveYardHead = null;

    /**
     * Constructor to populate kill ring linked list from input names.
     *
     * @param names - list of names to add to kill ring linked list
     */
    public AssassinManager(List<String> names) {
        // Add the names to the kill ring linked list
        for (int i = names.size() - 1; i >= 0; i--) {
            killRingHead = new AssassinNode(names.get(i), killRingHead);
        }
    }

    /**
     * Prints kill ring linked list
     */
    public void printKillRing() {
        // Store head of kill ring linked list
        AssassinNode temp = killRingHead;
        // If there is only one person
        if (temp != null && temp.next == null) {
            System.out.println("    " + temp.name + " is stalking " + temp.name);
        } else {
            // Traverse through the list
            while (temp.next != null) {
                System.out.println("    " + temp.name + " is stalking " + temp.next.name);
                temp = temp.next;
            }
        }
    }

    /**
     * Print grave yard linked list
     */
    public void printGraveyard() {
        // Store head of grave yard linked list
        AssassinNode temp = graveYardHead;
        // Traverse through the list
        while (temp != null) {
            // print name and killer
            System.out.println("    " + temp.name + " was killed by " + temp.killer);
            temp = temp.next;
        }
    }

    /**
     * Checks whether the given name is in the current kill ring.
     *
     * @param name
     * @return
     */
    public boolean killRingContains(String name) {
        // Store head of kill ring linked list
        AssassinNode temp = killRingHead;

        // Traverse through the list
        while (temp != null) {
            // If name found
            if (name.equalsIgnoreCase(temp.name)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * Checks whether the given name is in the grave yard
     *
     * @param name
     * @return
     */
    public boolean graveyardContains(String name) {
        // Store head of grave yard linked list
        AssassinNode temp = graveYardHead;

        // Traverse through the list
        while (temp != null) {
            // If name found
            if (name.equalsIgnoreCase(temp.name)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * Checks whether the game is over
     *
     * @return
     */
    public boolean gameOver() {
        return killRingHead.next == null;
    }

    /**
     * Print out name of the winner, if game is over
     *
     * @return
     */
    public String winner() {
        // If game is over
        if (gameOver()) {
            // Return name of winner
            return killRingHead.name;
        }
        return "";
    }

    /**
     * Kills the person with the given name and add grave yard linked list.
     *
     * @param name
     */
    public void kill(String name) {
        // If game is not over and name exists in kill ring
        if (!gameOver() && killRingContains(name)) {
            // Store head of kill ring head
            AssassinNode current = killRingHead;
            // To keep track of person who is going to be killed
            AssassinNode victim = killRingHead;

            // If name found
            if (current.name.equalsIgnoreCase(name)) {
                // If killer is at the end
                while (current.next != null) {
                    current = current.next;
                }
                victim.killer = current.name;
                killRingHead = killRingHead.next;
            } else {
                while (!current.next.name.equalsIgnoreCase(name)) {
                    current = current.next;
                }
                // Set killer name
                current.next.killer = current.name;
                victim = current.next;
                // Remove the victim from list
                current.next = current.next.next;
            }

            // Add victim to the yard linked list
            if (graveYardHead == null) {
                graveYardHead = victim;
                graveYardHead.next = null;
            } else {
                victim.next = graveYardHead;
                graveYardHead = victim;
            }
        }
    }
}
