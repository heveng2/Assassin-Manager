import java.util.ArrayList;
import java.util.List;

public class AssassinManagerTest {

    public static void main(String[] args) {
        testAssassinManager();
    }

    public static void testAssassinManager() {
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        
        AssassinManager manager = new AssassinManager(names);
        
        // Test printKillRing and printGraveyard after initialization
        System.out.println("Initial state:");
        manager.printKillRing();
        manager.printGraveyard();
        
        // Test killRingContains and graveyardContains
        System.out.println("Checking if 'Alice' is in the kill ring: " + manager.killRingContains("Alice"));
        System.out.println("Checking if 'Bob' is in the graveyard: " + manager.graveyardContains("Bob"));
        
        // Test killing a player
        manager.kill("Alice");
        System.out.println("After killing 'Alice':");
        manager.printKillRing();
        manager.printGraveyard();
        
        // Additional tests as needed
    }
}
