import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransmitterTests {

    @Mock
    CommandExecutor commandExecutorMock;

    @Test
    public void executesCommand() throws Exception {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final String utf8 = StandardCharsets.UTF_8.name();

        PrintStream ps = new PrintStream(baos, true, utf8);

        Transmitter transmitter = new Transmitter(3, ps);

        when(commandExecutorMock.getKnownCommandList()).thenReturn(Arrays.asList("dummyCommand"));
        when(commandExecutorMock.execCommand(anyString())).thenReturn("dummyResult");
        when(commandExecutorMock.getName()).thenReturn("dummyName");
        when(commandExecutorMock.getSignalDelay()).thenReturn(0);

        transmitter.on();
        transmitter.connect(commandExecutorMock);
        transmitter.sendCommand("whatever");
        transmitter.waitAll();

        Thread.sleep(10);

        transmitter.disconnect();
        transmitter.off();
        assertTrue(baos.toString().indexOf("dummyResult") > 0);
    }
}
