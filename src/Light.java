/**
 * Represents a light device, extending SmartDevice and implementing Chargeable.
 */
public final class Light extends SmartDevice implements Chargeable {
    private boolean charging;  // Charging status
    private Brightnesslevel brightnessLevel;  // Brightness level
    private LightColor lightColor;  // Light color

    /**
     * Constructor to initialize a light device.
     *
     * @param status          Power status of the light.
     * @param charging        Charging status of the light.
     * @param brightnessLevel Initial brightness level of the light.
     * @param lightColor      Initial color of the light.
     */
    public Light(Status status, boolean charging, Brightnesslevel brightnessLevel, LightColor lightColor) {
        super(status);
        this.charging = charging;
        this.brightnessLevel = brightnessLevel;
        this.lightColor = lightColor;
    }

    /**
     * Gets the current color of the light.
     *
     * @return The color of the light.
     */
    public LightColor getLightColor() {
        return lightColor;
    }

    /**
     * Sets the color of the light.
     *
     * @param lightColor New color for the light.
     */
    public void setLightColor(LightColor lightColor) {
        this.lightColor = lightColor;
    }

    /**
     * Gets the current brightness level of the light.
     *
     * @return The brightness level of the light.
     */
    public Brightnesslevel getBrightnessLevel() {
        return brightnessLevel;
    }

    /**
     * Sets the brightness level of the light.
     *
     * @param brightnessLevel New brightness level for the light.
     */
    public void setBrightnessLevel(Brightnesslevel brightnessLevel) {
        this.brightnessLevel = brightnessLevel;
    }

    @Override
    public boolean isCharging() {
        return charging;
    }

    @Override
    public boolean startCharging() {
        if (!charging) {
            charging = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean stopCharging() {
        if (charging) {
            charging = false;
            return true;
        }
        return false;
    }

    @Override
    public String displayStatus() {
        return String.format("Light %d is %s, the color is %s, the charging status is %s, and the brightness "
                        + "level is %s.", this.getDeviceId(), this.getStatus(), this.getLightColor(),
                isCharging(), getBrightnessLevel());
    }
}
