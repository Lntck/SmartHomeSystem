/**
 * Interface for controllable devices, allowing them to be turned on/off and checked for status.
 */
public interface Controllable {
    /**
     * Turns off the device.
     *
     * @return true if the device was turned off, false if it was already off.
     */
    boolean turnOff();

    /**
     * Turns on the device.
     *
     * @return true if the device was turned on, false if it was already on.
     */
    boolean turnOn();

    /**
     * Checks if the device is on.
     *
     * @return true if the device is on, false otherwise.
     */
    boolean isOn();
}
