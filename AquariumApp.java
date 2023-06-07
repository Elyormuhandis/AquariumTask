import java.util.*;
import java.util.stream.Collectors;

public class AquariumApp {

    public static void main(String[] args) throws InterruptedException {
        List<Fish> fishes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the our aquarium! \nEnter initial value of fishes:");
        int quantityOfFishes = scanner.nextInt();
        // This block for initialization
        for (int i = 0; i < quantityOfFishes; i++) {
            Fish fish = new Fish();
            fish.start();
            fishes.add(fish);
        }
        // This block for simulate aquarium life
        while (!fishes.isEmpty()) {
            Thread.sleep(1000); // Every second we can show information about aquarium
            Random random = new Random(); // This object used for randomize values

            //This block for meeting of fishes, here fishes can spawning and maybe created new fish
            int idx1=random.nextInt(fishes.size()-1);
            if (random.nextInt(4) == 2 && !fishes.isEmpty()) {
                int idx2=random.nextInt(fishes.size()-1);
                if (idx1!=idx2){
                    Fish fish1 = fishes.get(idx1);
                    Fish fish2 = fishes.get(idx2);
                    Fish newFish = fish1.spawning(fish2);
                    if (newFish!=null){
                        newFish.start();
                        fishes.add(newFish);
                        System.out.println("New fish " + newFish);
                    }
                }
            }

            //This block for checking fish is alive or die
            fishes = fishes.stream().filter(fish -> fish.getAlive()).collect(Collectors.toList());

            //This block for information about aquarium
            System.out.println("The quantity of fishes: " + fishes.size());
            System.out.println("The quantity of male fishes: " + fishes.stream().filter(fish -> fish.getSex().equals("MALE")).count());
            System.out.println("The quantity of female fishes: " + fishes.stream().filter(fish -> fish.getSex().equals("FEMALE")).count());
        }
        System.out.println("All the fish have died, the aquarium is empty.");
    }


}
