/**
 * Abstract class representing a smart device, providing functionality for controlling the device's power status.
 */
public abstract class SmartDevice implements Controllable {
    private Status status;  // Device power status
    private int deviceId;   // Unique identifier for the device
    private int numberOfDevices = 0;  // Counter for the number of devices created

    /**
     * Constructor to initialize the smart device with a given status.
     *
     * @param status Initial power status of the device.
     */
    public SmartDevice(Status status) {
        this.status = status;
        numberOfDevices++;
    }

    /**
     * Abstract method to display the status of the device.
     *
     * @return A string representing the device status.
     */
    public abstract String displayStatus();

    /**
     * Gets the unique identifier of the device.
     *
     * @return Device ID.
     */
    public final int getDeviceId() {
        return deviceId;
    }

    /**
     * Sets the unique identifier of the device.
     *
     * @param deviceId Device ID.
     */
    public final void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * Gets the current power status of the device.
     *
     * @return Current status of the device.
     */
    public final Status getStatus() {
        return status;
    }

    /**
     * Sets the power status of the device.
     *
     * @param status New power status for the device.
     */
    public final void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Turns off the device if it is on.
     *
     * @return true if the device was turned off, false if it was already off.
     */
    public final boolean turnOff() {
        if (status == Status.ON) {
            status = Status.OFF;
            return true;
        }
        return false;
    }

    /**
     * Turns on the device if it is off.
     *
     * @return true if the device was turned on, false if it was already on.
     */
    public final boolean turnOn() {
        if (status == Status.OFF) {
            status = Status.ON;
            return true;
        }
        return false;
    }

    /**
     * Checks if the device is currently on.
     *
     * @return true if the device is on, false otherwise.
     */
    public final boolean isOn() {
        return status == Status.ON;
    }

    /**
     * Placeholder method to check if the device status can be accessed.
     *
     * @return true if the device is on, false otherwise.
     */
    public final boolean checkStatusAccess() {
        return status == Status.ON;
    }
}
