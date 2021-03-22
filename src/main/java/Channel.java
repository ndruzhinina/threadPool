import java.util.concurrent.BlockingQueue;

public class Channel implements Runnable {
    private Thread thread = null;
    private BlockingQueue taskQueue;
    private boolean isOff = true;
    private int delay;
    private ChannelListener responseListener;
    private CommandExecutor commandExecutor;
    private String name;

    public Channel(BlockingQueue queue, String name) {
        taskQueue = queue;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSignalDelay(int delay) {
        this.delay = delay;
    }

    public void setResponseListener(ChannelListener responseListener) {
        this.responseListener = responseListener;
    }

    public void setRequestListener(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public void run() {
        isOff = false;
        this.thread = Thread.currentThread();
        while (!isOff()) {
            try {
                //System.out.println("Run channel " + _name);
                String command = (String) taskQueue.take();
                if (command != null) {
                    Thread.sleep(delay);
                    String commandResult = commandExecutor.execCommand(command);
                    Thread.sleep(delay);
                    responseListener.notify(commandResult);
                }
            } catch (Exception e) {

            }
        }
    }

    public synchronized void off() {
        isOff = true;
        this.thread.interrupt();
    }

    public synchronized boolean isOff() {
        return isOff;
    }
}