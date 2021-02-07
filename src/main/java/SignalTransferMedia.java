public class SignalTransferMedia {

    private double _delay;
    public SignalTransferMedia(double signalTransferSpeed, double distance) {
        _delay = distance / signalTransferSpeed;
    }

    public int getDelayMs() {
        return (int)Math.round(_delay * 1000);
    }
}
