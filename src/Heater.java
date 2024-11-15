/**
 * Represents a heater device, extending SmartDevice.
 */
public final class Heater extends SmartDevice {
    private int temperature;  // Current temperature setting
    private static final int MAX_HEATER_TEMP = 30;  // Maximum temperature
    private static final int MIN_HEATER_TEMP = 15;  // Minimum temperature

    /**
     * Constructor to initialize a heater device.
     *
     * @param status      Power status of the heater.
     * @param temperature Initial temperature setting for the heater.
     */
    public Heater(Status status, int temperature) {
        super(status);
        this.temperature = temperature;
    }

    /**
     * Gets the current temperature of the heater.
     *
     * @return Current temperature setting of the heater.
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * Sets the temperature for the heater within the valid range.
     *
     * @param temperature New temperature setting for the heater.
     * @return true if the temperature was successfully set, false if out of range.
     */
    public boolean setTemperature(int temperature) {
        if (temperature >= MIN_HEATER_TEMP && temperature <= MAX_HEATER_TEMP) {
            this.temperature = temperature;
            return true;
        }
        return false;
    }

    @Override
    public String displayStatus() {
        return String.format("Heater %d is %s and the temperature is %d.", this.getDeviceId(),
                this.getStatus(), this.getTemperature());
    }
}
