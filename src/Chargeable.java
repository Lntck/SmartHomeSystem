/**
 * Interface for chargeable devices, allowing them to be charged and checked for charging status.
 */
public interface Chargeable {
    /**
     * Checks if the device is charging.
     *
     * @return true if the device is charging, false otherwise.
     */
    boolean isCharging();

    /**
     * Starts charging the device.
     *
     * @return true if charging started, false if the device is already charging.
     */
    boolean startCharging();

    /**
     * Stops charging the device.
     *
     * @return true if charging stopped, false if the device is not charging.
     */
    boolean stopCharging();
}
