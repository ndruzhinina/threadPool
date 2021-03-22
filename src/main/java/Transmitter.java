import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Transmitter implements ChannelListener {

    private BlockingQueue commandQueue = null;
    private List<Channel> channels = new ArrayList<>();
    private boolean isStopped = false;
    private CommandExecutor station;
    private PrintStream outputStream;
    private int signalDelay;
    private final int NUM_CHANNELS = 3;
    private Object syncObj = new Object();

    public Transmitter(int commandQueueCapacity, PrintStream outputStream) {
        this.outputStream = outputStream;
        commandQueue = new ArrayBlockingQueue(commandQueueCapacity);

        for (int i = 0; i < NUM_CHANNELS; i++) {
            channels.add(new Channel(commandQueue, "Channel-" + i));
        }
    }

    public void on() {
        outputStream.println("Activating " + NUM_CHANNELS + " channels...");
        for (Channel channel : channels) {
            Thread thread = new Thread(channel);
            thread.setName(channel.getName());
            thread.start();
        }
    }

    public void connect(CommandExecutor station) {
        this.station = station;
        signalDelay = this.station.getSignalDelay();
        for (Channel channel : channels) {
            channel.setSignalDelay(signalDelay);
            channel.setResponseListener(this);
            channel.setRequestListener(this.station);
        }

        outputStream.println("Connected to: " + this.station.getName());
        outputStream.println("Delay: " + signalDelay + "ms");
        outputStream.println("Known commands:");
        outputStream.println(String.join(", ", this.station.getKnownCommandList()));
    }

    public void disconnect() {
        station = null;
        signalDelay = 0;
        outputStream.println("Disconnected");
    }

    public synchronized void sendCommand(String command) throws Exception {
        if (this.isStopped)
            throw new IllegalStateException("The transmitter is off");
        this.commandQueue.offer(command);
    }

    private void print(String msg) {
        String message = "Channel #" + Thread.currentThread().getName() + " >>> " + msg;
        outputStream.println(message);
    }

    public synchronized void off() {
        outputStream.println("Deactivating channels");
        this.isStopped = true;
        for (Channel channel : channels) {
            channel.off();
        }
    }

    public synchronized void waitAll() {
        while (this.commandQueue.size() > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void notify(String msg) {
        print(msg);
    }
}