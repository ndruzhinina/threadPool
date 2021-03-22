import java.util.Scanner;

public class Simulator {

    public static void main(String[] args) throws Exception {

        final int lightSpeed = 299792458;
        final long earthToMoonDistance = 384000000;
        SignalTransferMedia media = new SignalTransferMedia(lightSpeed, earthToMoonDistance);
        Transmitter transmitter = new Transmitter(3, System.out);

        SpaceStation moonStation = new SpaceStation("Moon Station 1", media);
        transmitter.on();
        transmitter.connect(moonStation);

        Scanner scanner = new Scanner(System.in);
        String command;
        while (!(command = scanner.nextLine().toLowerCase()).equals("exit")) {
            transmitter.sendCommand(command);
        }

        transmitter.waitAll();
        transmitter.disconnect();
        transmitter.off();
    }
}