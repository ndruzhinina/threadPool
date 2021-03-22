import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class ChannelTests {


    @Mock
    CommandExecutor commandExecutorMock;

    @Mock
    ChannelListener channelListenerMock;

    @Test
    public void sendsRequestAndReturnsResponse() {

        when(commandExecutorMock.execCommand(anyString())).thenReturn("response");

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1);
        Channel channel = new Channel(blockingQueue, "dummy");
        Channel channelSpy = spy(channel);
        when(channelSpy.isOff()).thenReturn(false).thenReturn(true);
        blockingQueue.offer("request");
        channelSpy.setSignalDelay(0);
        channelSpy.setRequestListener(commandExecutorMock);
        channelSpy.setResponseListener(channelListenerMock);
        channelSpy.run();

        verify(commandExecutorMock, times(1)).execCommand("request");
        verify(channelListenerMock, times(1)).notify("response");
    }

    @Test
    public void worksWhileIsOn() {
        int iterations = 10;
        when(commandExecutorMock.execCommand(anyString())).thenReturn("response");

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(iterations);
        Channel channel = new Channel(blockingQueue, "dummy");
        Channel channelSpy = spy(channel);

        OngoingStubbing stubbing = when(channelSpy.isOff());
        for (int i = 0; i < iterations; i++) {
            stubbing = stubbing.thenReturn(false);
            blockingQueue.offer("");
        }
        stubbing.thenReturn(true);

        channelSpy.setSignalDelay(0);
        channelSpy.setRequestListener(commandExecutorMock);
        channelSpy.setResponseListener(channelListenerMock);
        channelSpy.run();

        verify(commandExecutorMock, times(iterations)).execCommand(anyString());
        verify(channelListenerMock, times(iterations)).notify(anyString());
    }

    @Test
    public void offTurnsChannelOff() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1);
        Channel channel = new Channel(blockingQueue, "dummy");
        Channel channelSpy = spy(channel);
        when(channelSpy.isOff()).thenReturn(true).thenReturn(false);
        channelSpy.run();
        channelSpy.off();
        assertTrue(channel.isOff());
    }

    @Test
    public void getNameReturnsAssignedName() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1);
        Channel channel = new Channel(blockingQueue, "dummy");
        assertEquals(channel.getName(), "dummy");
    }
}
