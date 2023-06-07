import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Fish extends Thread {

    private String sex = ThreadLocalRandom.current().nextInt(1, 3) == 1 ? "MALE" : "FEMALE"; //Initial value of sex maybe MALE or FEMALE only

    private long birthTime = new Date().getTime(); //When object fish will be created, birthTime will initialize that time
    private long lifespan = (long) ThreadLocalRandom.current().nextInt(10 * 1000, 60 * 1000); // The average lifespan of fishes from 1 to 60 second;
    //Note! If you want to increase lifespan of fish, you need to change bound of random function


    public boolean getAlive() {
        return this.alive;
    }

    private boolean alive = true;


    //This is default function of Thread class.
    @Override
    public void run() {
        try {
            while (lifespan > new Date().getTime() - birthTime) {
                Thread.sleep(1); // we can delete this partition, this is for testing
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The fish with id " + super.getId() + " was died!");
        this.alive =false;
    }

    public Fish spawning(Fish fish) {
        if (!this.sex.equals(fish.getSex())) {
            return new Fish();
        }
        return null;
    }



    public String getSex() {
        return sex;
    }

    public long getBirthTime() {
        return birthTime;
    }

    public long getLifespan() {
        return lifespan;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "id=" + super.getId()+ ", " +
                "sex='" + sex + '\'' +
                ", birthTime=" + birthTime +
                ", lifespan=" + lifespan +
                ", isLive=" + alive +
                '}';
    }
}
