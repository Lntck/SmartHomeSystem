/**
 * Represents a camera device, extending SmartDevice and implementing Chargeable.
 */
public final class Camera extends SmartDevice implements Chargeable {
    private static final int MAX_CAMERA_ANGLE = 60;  // Maximum camera angle
    private static final int MIN_CAMERA_ANGLE = -60; // Minimum camera angle
    private boolean charging;  // Charging status
    private boolean recording;  // Recording status
    private int angle;  // Camera angle

    /**
     * Constructor to initialize a camera device.
     *
     * @param status    Power status of the camera.
     * @param charging  Charging status of the camera.
     * @param recording Recording status of the camera.
     * @param angle     Initial camera angle.
     */
    public Camera(Status status, boolean charging, boolean recording, int angle) {
        super(status);
        this.charging = charging;
        this.recording = recording;
        this.angle = angle;
    }

    /**
     * Gets the current angle of the camera.
     *
     * @return The current angle of the camera.
     */
    public int getAngle() {
        return angle;
    }

    /**
     * Sets the camera angle within the valid range.
     *
     * @param angle New angle for the camera.
     * @return true if the angle was successfully set, false if out of range.
     */
    public boolean setCameraAngle(int angle) {
        if (angle >= MIN_CAMERA_ANGLE && angle <= MAX_CAMERA_ANGLE) {
            this.angle = angle;
            return true;
        }
        return false;
    }

    /**
     * Starts recording if the camera is not already recording.
     *
     * @return true if recording started, false if already recording.
     */
    public boolean startRecording() {
        if (!recording) {
            this.recording = true;
            return true;
        }
        return false;
    }

    /**
     * Stops recording if the camera is recording.
     *
     * @return true if recording stopped, false if not recording.
     */
    public boolean stopRecording() {
        if (recording) {
            this.recording = false;
            return true;
        }
        return false;
    }

    /**
     * Checks if the camera is recording.
     *
     * @return true if the camera is recording, false otherwise.
     */
    public boolean isRecording() {
        return recording;
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
        return String.format("Camera %d is %s, the angle is %d, the charging status is %s, and the recording "
                        + "status is %s.", this.getDeviceId(), this.getStatus(), this.getAngle(),
                this.isCharging(), this.isRecording());
    }
}
