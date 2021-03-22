import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignalTransferMediaTests {

    @Test
    public void getDelayMsReturnsValueInMilliseconds() {
        SignalTransferMedia media = new SignalTransferMedia(25, 10);

        assertEquals(media.getDelayMs(), 400);
    }
}
