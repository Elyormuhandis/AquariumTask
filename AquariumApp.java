import java.util.*;
import java.util.stream.Collectors;

public class AquariumApp {

    public static void main(String[] args) throws InterruptedException {
        List<Fish> fishList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the our aquarium! \nEnter initial value of fish:");
        int quantityOfFish = scanner.nextInt();
        // This block for initialization
        for (int i = 0; i < quantityOfFish; i++) {
            Fish fish = new Fish();
            fish.start();
            fishList.add(fish);
        }
        // This block for simulate aquarium life
        while (!fishList.isEmpty()) {
            Thread.sleep(1000); // Every second we can show information about aquarium
            Random random = new Random(); // This object used for randomize values

            //This block for meeting of fish, here fish spawning, maybe created new fish
            int idx1=random.nextInt(fishList.size());
            if (random.nextInt(4) == 2) {
                int idx2=random.nextInt(fishList.size());
                if (idx1!=idx2){
                    Fish fish1 = fishList.get(idx1);
                    Fish fish2 = fishList.get(idx2);
                    Fish newFish = fish1.spawning(fish2);
                    if (newFish!=null){
                        newFish.start();
                        fishList.add(newFish);
                        System.out.println("New fish " + newFish);
                    }
                }
            }

            //This block for checking fish is alive or die
            fishList = fishList.stream().filter(Fish::getAlive).collect(Collectors.toList());

            //This block for information about aquarium
            System.out.println("The quantity of fish: " + fishList.size());
            System.out.println("The quantity of male fish: " + fishList.stream().filter(fish -> fish.getSex().equals("MALE")).count());
            System.out.println("The quantity of female fish: " + fishList.stream().filter(fish -> fish.getSex().equals("FEMALE")).count());
        }
        System.out.println("All the fish have died, the aquarium is empty.");
    }


}
