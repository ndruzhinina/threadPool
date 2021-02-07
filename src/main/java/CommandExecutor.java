import java.util.List;

public interface CommandExecutor {
    String execCommand(String cmd);

    int getSignalDelay();

    List<String> getKnownCommandList();

    String getName();
}
