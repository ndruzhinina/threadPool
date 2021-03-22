import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class SpaceStationTests {

    @Test
    public void knowsTwoCommands() {
        SpaceStation spaceStation = new SpaceStation(null, null);
        assertEquals(spaceStation.getKnownCommandList().size(), 2);
    }

    @Test
    public void gettimeCommandReturnsActualDate() {
        SpaceStation spaceStation = new SpaceStation(null, null);

        String cmdResult = spaceStation.execCommand("gettime");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime parsedCmdResult = LocalDateTime.parse(cmdResult, dtf);
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(parsedCmdResult, now);

        assertTrue(Math.abs(duration.getSeconds()) < 1);
    }

    @Test
    public void takephotoCommandReturnsHiResImage() {
        SpaceStation spaceStation = new SpaceStation(null, null);

        String cmdResult = spaceStation.execCommand("takephoto");
        String[] lines = cmdResult.split("\\r?\\n");

        assertTrue(lines.length > 20);
        assertTrue(lines[0].length() > 20);
        for (int i = 1; i < lines.length; i++) {
            assertTrue(lines[i].length() == lines[0].length());
        }
    }

    @Test
    public void getSignalDelayReturnsMediaDelay() {
        SignalTransferMedia media = new SignalTransferMedia(25, 100);
        SpaceStation spaceStation = new SpaceStation(null, media);

        assertEquals(spaceStation.getSignalDelay(), media.getDelayMs());
    }

    @Test
    public void getNameReturnsAssignedName() {
        SpaceStation spaceStation = new SpaceStation("Perseverance", null);

        assertEquals(spaceStation.getName(), "Perseverance");
    }
}
