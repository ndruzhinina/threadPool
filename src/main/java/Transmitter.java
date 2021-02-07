import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Transmitter implements ChannelListener {

    private BlockingQueue _commandQueue = null;
    private List<Channel> _channels = new ArrayList<>();
    private boolean _isStopped = false;
    private CommandExecutor _station;
    private PrintStream _outputStream;
    private int _signalDelay;
    private final int NUM_CHANNELS = 3;
    private Object _syncObj = new Object();

    public Transmitter(int commandQueueCapacity, PrintStream outputStream){
        _outputStream = outputStream;
        _commandQueue = new ArrayBlockingQueue(commandQueueCapacity);

        for(int i = 0; i < NUM_CHANNELS; i++){
            _channels.add(new Channel(_commandQueue, "Channel-" + i));
        }
    }

    public void on() {
        _outputStream.println("Activating " + NUM_CHANNELS + " channels...");
        for(Channel channel: _channels) {
            Thread thread = new Thread(channel);
            thread.setName(channel.getName());
            thread.start();
        }
    }

    public void connect(CommandExecutor station) {
        _station = station;
        _signalDelay = _station.getSignalDelay();
        for(Channel channel : _channels){
            channel.setSignalDelay(_signalDelay);
            channel.setResponseListener(this);
            channel.setRequestListener(_station);
        }

        _outputStream.println("Connected to: " + _station.getName());
        _outputStream.println("Delay: " + _signalDelay + "ms");
        _outputStream.println("Known commands:");
        _outputStream.println(String.join(", ", _station.getKnownCommandList()));
    }

    public void disconnect() {
        _station = null;
        _signalDelay = 0;
        _outputStream.println("Disconnected");
    }

    public synchronized void sendCommand(String command) throws Exception{
        if(this._isStopped)
            throw new IllegalStateException("The transmitter is off");
        this._commandQueue.offer(command);
    }

    private void print(String msg) {
        String message = "Channel #" + Thread.currentThread().getName()  + " >>> " + msg;
        _outputStream.println(message);
    }

    public synchronized void off() {
        _outputStream.println("Deactivating channels");
        this._isStopped = true;
        for(Channel channel : _channels){
            channel.off();
        }
    }

    public synchronized void waitAll() {
        while(this._commandQueue.size() > 0) {
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