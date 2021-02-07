import java.util.concurrent.BlockingQueue;

public class Channel implements Runnable {
    private Thread thread = null;
    private BlockingQueue _taskQueue;
    private boolean _isOff = true;
    private int _delay;
    private ChannelListener _responseListener;
    private CommandExecutor _commandExecutor;
    private String _name;

    public Channel(BlockingQueue queue, String name) {
        _taskQueue = queue;
        _name = name;
    }

    public String getName() {
        return _name;
    }

    public void setSignalDelay(int delay) {
        _delay = delay;
    }

    public void setResponseListener(ChannelListener responseListener) {
        _responseListener = responseListener;
    }

    public void setRequestListener(CommandExecutor commandExecutor) {
        _commandExecutor = commandExecutor;
    }

    public void run(){
        _isOff = false;
        this.thread = Thread.currentThread();
        while(!isOff()) {
            try{
                String command = (String) _taskQueue.take();
                Thread.sleep(_delay);
                String commandResult = _commandExecutor.execCommand(command);
                Thread.sleep(_delay);
                _responseListener.notify(commandResult);
            } catch(Exception e) {

            }
        }
    }

    public synchronized void off() {
        _isOff = true;
        this.thread.interrupt();
    }

    public synchronized boolean isOff() {
        return _isOff;
    }
}