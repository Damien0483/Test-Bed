import java.security.SecureRandom;

public class Producer implements Runnable {

    private static final SecureRandom generator = new SecureRandom();
    private final BlockingBuffer sharedLocation;

    // New private properties
    private String name;
    private int sleepTime;
    private int startProducing;
    private int stopProducing;

    // Constructor (must call setters)
    public Producer(BlockingBuffer sharedLocation, String name, int sleepTime,
                    int startProducing, int stopProducing) {

        this.sharedLocation = sharedLocation;

        setName(name);
        setSleepTime(sleepTime);
        setStartProducing(startProducing);
        setStopProducing(stopProducing);
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getSleepTime() { return sleepTime; }
    public void setSleepTime(int sleepTime) { this.sleepTime = sleepTime; }

    public int getStartProducing() { return startProducing; }
    public void setStartProducing(int startProducing) { this.startProducing = startProducing; }

    public int getStopProducing() { return stopProducing; }
    public void setStopProducing(int stopProducing) { this.stopProducing = stopProducing; }

    @Override
    public void run() {
        for (int count = startProducing; count <= stopProducing; count++) {
            try {
                Thread.sleep(sleepTime * 1000);

                // Pass name into blockingPut
                sharedLocation.blockingPut(count, name);

                System.out.printf("%s produced value %d%n", name, count);

            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.printf("%s done producing%nTerminating %s%n", name, name);
    }
}
