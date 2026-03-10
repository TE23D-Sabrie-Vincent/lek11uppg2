import java.util.*;
import java.nio.file.*;

public class App {
    public static void main(String[] args) throws Exception {

        Set<String> Vips = new HashSet<>();
        Set<String> Volontärer = new HashSet<>();
        Set<String> Bannlysta = new HashSet<>();
        Set<String> Eposter = new HashSet<>();

        try{
        // String vipsData = Files.readString(Path.of("vips.txt"));
        Bannlysta.addAll(Arrays.asList(Files.readString(Path.of("Bannlysta.txt")).split("\\r?\\n")));
        Vips.addAll(Arrays.asList(Files.readString(Path.of("Vips.txt")).split("\\r?\\n")));
        Eposter.addAll(Arrays.asList(Files.readString(Path.of("Eposter.txt")).split("\\r?\\n")));
        Volontärer.addAll(Arrays.asList(Files.readString(Path.of("Volontärer.txt")).split("\\r?\\n")));
        }   
        catch (Exception e) {
            System.out.println("Fel vid filinläsning: " + e.getMessage());
        }


        Set<String> superVolontärer = new HashSet<>(Volontärer);
        superVolontärer.retainAll(Vips); // Behåll bara de som också finns i VIP

        System.out.println("--- Supervoluntärer (Vip och voluntärer)---");
        for (String email : superVolontärer){
            System.out.println(email);
        }

        Eposter.removeAll(Bannlysta);

        System.out.println("\n--- Godkända gäster (Bannlysta borttagna) ---");
        System.out.println("Antal unika godkända gäster: " + Eposter.size());

        Set<String> totaltRegister = new HashSet<>();
        totaltRegister.addAll(Eposter);
        totaltRegister.addAll(Vips);
        totaltRegister.addAll(Volontärer);

        // Tar bort de bannlysta
        totaltRegister.removeAll(Bannlysta);

        System.out.println("\n--- Totalt antal unika behöriga på festivalen ---");
        System.out.println("Antal: " + totaltRegister.size());
    }
}
