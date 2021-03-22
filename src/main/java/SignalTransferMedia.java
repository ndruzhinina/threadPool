public class SignalTransferMedia {

    private double delay;

    public SignalTransferMedia(double signalTransferSpeed, double distance) {
        delay = distance / signalTransferSpeed;
    }

    public int getDelayMs() {
        return (int) Math.round(delay * 1000);
    }
}