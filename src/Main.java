import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Main application class for managing smart devices.
 */
public class Main {
    public static final int MAX_DEVICE_ID = 9;
    public static final int MIN_DEVICE_ID = 0;
    public static final int INITIAL_CAMERA_ANGLE = 45;
    public static final int INITIAL_HEATER_TEMP = 20;

    public static void main(String[] args) {
        // Create an array of smart devices
        SmartDevice[] objects = new SmartDevice[] {
                new Light(Status.ON, false, Brightnesslevel.LOW, LightColor.YELLOW),
                new Light(Status.ON, false, Brightnesslevel.LOW, LightColor.YELLOW),
                new Light(Status.ON, false, Brightnesslevel.LOW, LightColor.YELLOW),
                new Light(Status.ON, false, Brightnesslevel.LOW, LightColor.YELLOW),
                new Camera(Status.ON, false, false, INITIAL_CAMERA_ANGLE),
                new Camera(Status.ON, false, false, INITIAL_CAMERA_ANGLE),
                new Heater(Status.ON, INITIAL_HEATER_TEMP),
                new Heater(Status.ON, INITIAL_HEATER_TEMP),
                new Heater(Status.ON, INITIAL_HEATER_TEMP),
                new Heater(Status.ON, INITIAL_HEATER_TEMP)
        };

        // Assign unique device IDs to each device in the array
        for (int i = 0; i < objects.length; i++) {
            objects[i].setDeviceId(i);
        }

        Scanner scanner = new Scanner(System.in);
        String operation;
        while (true) {
            operation = scanner.nextLine();
            Scanner sc = new Scanner(operation);

            switch (sc.next()) {
                /*
                 * Process the "end" command to terminate the program.
                 * Ensures no additional input follows the command.
                 */
                case "end" -> {
                    if (sc.hasNext()) {
                        System.out.println("Invalid command");
                    } else {
                        return; // Ends the program
                    }
                }
                /*
                 * Processes the "DisplayAllStatus" command to display the status of all devices.
                 * Ensures no additional input follows the command.
                 */
                case "DisplayAllStatus" -> {
                    if (sc.hasNext()) {
                        System.out.println("Invalid command");
                    } else {
                        for (SmartDevice device : objects) {
                            System.out.println(device.displayStatus()); // Displays the status of each device
                        }
                    }
                }
                /*
                 * Processes the "TurnOn" command to turn on a specified device.
                 * Validates the device name, ID, and ensures the device is off before turning it on.
                 */
                case "TurnOn" -> {
                    if (sc.hasNext(Pattern.compile("(\\D+)"))) {
                        String deviceName = sc.next();
                        if (sc.hasNext(Pattern.compile("(\\d+)$"))) {
                            int deviceId = sc.nextInt();
                            if ((deviceId <= MAX_DEVICE_ID && deviceId >= MIN_DEVICE_ID)
                                    && objects[deviceId].getClass().getSimpleName().equals(deviceName)) {
                                if (objects[deviceId].turnOn()) {
                                    System.out.printf("%s %d is on\n", deviceName, deviceId);
                                } else {
                                    System.out.printf("%s %d is already on\n", deviceName, deviceId);
                                }
                            } else {
                                System.out.println("The smart device was not found");
                            }
                        } else {
                            System.out.println("Invalid command");
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                }

                /*
                 * Processes the "TurnOff" command to turn off a specified device.
                 * Validates the device name, ID, and ensures the device is on before turning it off.
                 */
                case "TurnOff" -> {
                    if (sc.hasNext(Pattern.compile("(\\D+)"))) {
                        String deviceName = sc.next();
                        if (sc.hasNext(Pattern.compile("(\\d+)$"))) {
                            int deviceId = sc.nextInt();
                            if ((deviceId <= MAX_DEVICE_ID && deviceId >= MIN_DEVICE_ID)
                                    && objects[deviceId].getClass().getSimpleName().equals(deviceName)) {
                                if (objects[deviceId].turnOff()) {
                                    System.out.printf("%s %d is off\n", deviceName, deviceId);
                                } else {
                                    System.out.printf("%s %d is already off\n", deviceName, deviceId);
                                }
                            } else {
                                System.out.println("The smart device was not found");
                            }
                        } else {
                            System.out.println("Invalid command");
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                }
                case "StartCharging" -> {
                    /*
                     * Processes the "StartCharging" command to start charging a device.
                     * Validates the device name, ID, and checks if the device is chargeable.
                     * Only devices that implement the Chargeable interface can start charging.
                     */
                    if (sc.hasNext(Pattern.compile("(\\D+)"))) {
                        String deviceName = sc.next();
                        if (sc.hasNext(Pattern.compile("(\\d+)$"))) {
                            int deviceId = sc.nextInt();
                            if ((deviceId <= MAX_DEVICE_ID && deviceId >= MIN_DEVICE_ID)
                                    && objects[deviceId].getClass().getSimpleName().equals(deviceName)) {
                                if (Pattern.compile("(Camera|Light)").matcher(deviceName).matches()) {
                                    if (((Chargeable) objects[deviceId]).startCharging()) {
                                        System.out.printf("%s %d is charging\n", deviceName, deviceId);
                                    } else {
                                        System.out.printf("%s %d is already charging\n", deviceName, deviceId);
                                    }
                                } else {
                                    System.out.printf("%s %d is not chargeable\n", deviceName, deviceId);
                                }
                            } else {
                                System.out.println("The smart device was not found");
                            }
                        } else {
                            System.out.println("Invalid command");
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                }

                case "StopCharging" -> {
                    /*
                     * Processes the "StopCharging" command to stop charging a device.
                     * Validates the device name, ID, and checks if the device is chargeable.
                     * Only devices that implement the Chargeable interface can stop charging.
                     */
                    if (sc.hasNext(Pattern.compile("(\\D+)"))) {
                        String deviceName = sc.next();
                        if (sc.hasNext(Pattern.compile("(\\d+)$"))) {
                            int deviceId = sc.nextInt();
                            if ((deviceId <= MAX_DEVICE_ID && deviceId >= MIN_DEVICE_ID)
                                    && objects[deviceId].getClass().getSimpleName().equals(deviceName)) {
                                if (Pattern.compile("(Camera|Light)").matcher(deviceName).matches()) {
                                    if (((Chargeable) objects[deviceId]).stopCharging()) {
                                        System.out.printf("%s %d stopped charging\n", deviceName, deviceId);
                                    } else {
                                        System.out.printf("%s %d is not charging\n", deviceName, deviceId);
                                    }
                                } else {
                                    System.out.printf("%s %d is not chargeable\n", deviceName, deviceId);
                                }
                            } else {
                                System.out.println("The smart device was not found");
                            }
                        } else {
                            System.out.println("Invalid command");
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                }

                case "SetTemperature" -> {
                    /*
                     * Processes the "SetTemperature" command to set the temperature of a heater.
                     * Validate the device name, ID, ensures the heater is on, checks temperature is within valid range.
                     */
                    if (sc.hasNext(Pattern.compile("(\\D+)"))) {
                        String deviceName = sc.next();
                        if (sc.hasNext(Pattern.compile("(\\d+)"))) {
                            int deviceId = sc.nextInt();
                            if (sc.hasNext(Pattern.compile("(\\d+)$"))) {
                                int temperature = sc.nextInt();
                                if ((deviceId <= MAX_DEVICE_ID && deviceId >= MIN_DEVICE_ID)
                                        && objects[deviceId].getClass().getSimpleName().equals(deviceName)) {
                                    if (Pattern.compile("(Heater)").matcher(deviceName).matches()) {
                                        if (objects[deviceId].isOn()) {
                                            if (((Heater) objects[deviceId]).setTemperature(temperature)) {
                                                System.out.printf("%s %d temperature is set to %d\n", deviceName,
                                                        deviceId, temperature);
                                            } else {
                                                System.out.printf("Heater %d temperature should be in the range "
                                                        + "[15, 30]\n", deviceId);
                                            }
                                        } else {
                                            System.out.printf("You can't change the status of the %s %d while it is "
                                                    + "off\n", deviceName, deviceId);
                                        }
                                    } else {
                                        System.out.printf("%s %d is not a heater\n", deviceName, deviceId);
                                    }
                                } else {
                                    System.out.println("The smart device was not found");
                                }
                            } else {
                                System.out.println("Invalid command");
                            }
                        } else {
                            System.out.println("Invalid command");
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                }

                case "SetBrightness" -> {
                    /*
                     * Processes the "SetBrightness" command to set the brightness of a light device.
                     * Validates the device name, ID, ensures the light is on, and checks for valid brightness levels.
                     */
                    if (sc.hasNext(Pattern.compile("(\\D+)"))) {
                        String deviceName = sc.next();
                        if (sc.hasNext(Pattern.compile("(\\d+)"))) {
                            int deviceId = sc.nextInt();
                            if (sc.hasNext(Pattern.compile("(\\D+)$"))) {
                                String brightnessLevel = sc.next();
                                if ((deviceId <= MAX_DEVICE_ID && deviceId >= MIN_DEVICE_ID)
                                        && objects[deviceId].getClass().getSimpleName().equals(deviceName)) {
                                    if (Pattern.compile("(Light)").matcher(deviceName).matches()) {
                                        if (objects[deviceId].isOn()) {
                                            if (Pattern.compile("(LOW|MEDIUM|HIGH)")
                                                    .matcher(brightnessLevel).matches()) {
                                                ((Light) objects[deviceId])
                                                        .setBrightnessLevel(Brightnesslevel.valueOf(brightnessLevel));
                                                System.out.printf("%s %d brightness level is set to %s\n", deviceName,
                                                        deviceId, brightnessLevel);
                                            } else {
                                                System.out.println("The brightness can only be one of \"LOW\", "
                                                        + "\"MEDIUM\", or \"HIGH\"");
                                            }
                                        } else {
                                            System.out.printf("You can't change the status of the %s %d while it is "
                                                    + "off\n", deviceName, deviceId);
                                        }
                                    } else {
                                        System.out.printf("%s %d is not a light\n", deviceName, deviceId);
                                    }
                                } else {
                                    System.out.println("The smart device was not found");
                                }
                            } else {
                                System.out.println("Invalid command");
                            }
                        } else {
                            System.out.println("Invalid command");
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                }

                case "SetColor" -> {
                    /*
                     * Processes the "SetColor" command to set the color of a light device.
                     * Validates the device name, ID, ensures the light is on, and checks for valid color options.
                     */
                    if (sc.hasNext(Pattern.compile("(\\D+)"))) {
                        String deviceName = sc.next();
                        if (sc.hasNext(Pattern.compile("(\\d+)"))) {
                            int deviceId = sc.nextInt();
                            if (sc.hasNext(Pattern.compile("(\\D+)$"))) {
                                String color = sc.next();
                                if ((deviceId <= MAX_DEVICE_ID && deviceId >= MIN_DEVICE_ID)
                                        && objects[deviceId].getClass().getSimpleName().equals(deviceName)) {
                                    if (Pattern.compile("(Light)").matcher(deviceName).matches()) {
                                        if (objects[deviceId].isOn()) {
                                            if (Pattern.compile("(YELLOW|WHITE)").matcher(color).matches()) {
                                                ((Light) objects[deviceId]).setLightColor(LightColor.valueOf(color));
                                                System.out.printf("%s %d color is set to %s\n", deviceName,
                                                        deviceId, color);
                                            } else {
                                                System.out.println("The light color can only "
                                                        + "be \"YELLOW\" or \"WHITE\"");
                                            }
                                        } else {
                                            System.out.printf("You can't change the status of the %s %d while it is "
                                                    + "off\n", deviceName, deviceId);
                                        }
                                    } else {
                                        System.out.printf("%s %d is not a light\n", deviceName, deviceId);
                                    }
                                } else {
                                    System.out.println("The smart device was not found");
                                }
                            } else {
                                System.out.println("Invalid command");
                            }
                        } else {
                            System.out.println("Invalid command");
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                }

                case "SetAngle" -> {
                    /*
                     * Processes the "SetAngle" command to set the angle of a camera device.
                     * Validates the device name, ID, ensures the camera is on, and checks for a valid angle range.
                     */
                    if (sc.hasNext(Pattern.compile("(\\D+)"))) {
                        String deviceName = sc.next();
                        if (sc.hasNext(Pattern.compile("(\\d+)"))) {
                            int deviceId = sc.nextInt();
                            if (sc.hasNext(Pattern.compile("(-?\\d+)$"))) {
                                int angle = sc.nextInt();
                                if ((deviceId <= MAX_DEVICE_ID && deviceId >= MIN_DEVICE_ID)
                                        && objects[deviceId].getClass().getSimpleName().equals(deviceName)) {
                                    if (Pattern.compile("(Camera)").matcher(deviceName).matches()) {
                                        if (objects[deviceId].isOn()) {
                                            if (((Camera) objects[deviceId]).setCameraAngle(angle)) {
                                                System.out.printf("%s %d angle is set to %d\n", deviceName,
                                                        deviceId, angle);
                                            } else {
                                                System.out.printf("Camera %d angle should be in the range [-60, 60]\n",
                                                        deviceId);
                                            }
                                        } else {
                                            System.out.printf("You can't change the status of the %s %d while it is "
                                                    + "off\n", deviceName, deviceId);
                                        }
                                    } else {
                                        System.out.printf("%s %d is not a camera\n", deviceName, deviceId);
                                    }
                                } else {
                                    System.out.println("The smart device was not found");
                                }
                            } else {
                                System.out.println("Invalid command");
                            }
                        } else {
                            System.out.println("Invalid command");
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                }

                case "StartRecording" -> {
                    /*
                     * Processes the "StartRecording" command to start recording with a camera.
                     * Validates the device name, ID, ensures the camera is on, and checks if it is already recording.
                     */
                    if (sc.hasNext(Pattern.compile("(\\D+)"))) {
                        String deviceName = sc.next();
                        if (sc.hasNext(Pattern.compile("(\\d+)$"))) {
                            int deviceId = sc.nextInt();
                            if ((deviceId <= MAX_DEVICE_ID && deviceId >= MIN_DEVICE_ID)
                                    && objects[deviceId].getClass().getSimpleName().equals(deviceName)) {
                                if (Pattern.compile("(Camera|Light)").matcher(deviceName).matches()) {
                                    if (objects[deviceId].isOn()) {
                                        if (((Camera) objects[deviceId]).startRecording()) {
                                            System.out.printf("%s %d started recording\n", deviceName, deviceId);
                                        } else {
                                            System.out.printf("%s %d is already recording\n", deviceName, deviceId);
                                        }
                                    } else {
                                        System.out.printf("You can't change the status of the %s %d while it is off\n",
                                                deviceName, deviceId);
                                    }
                                } else {
                                    System.out.printf("%s %d is not a camera\n", deviceName, deviceId);
                                }
                            } else {
                                System.out.println("The smart device was not found");
                            }
                        } else {
                            System.out.println("Invalid command");
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                }

                case "StopRecording" -> {
                    /*
                     * Processes the "StopRecording" command to stop recording with a camera.
                     * Validates the device name, ID, ensures the camera is on, and checks if it is recording.
                     */
                    if (sc.hasNext(Pattern.compile("(\\D+)"))) {
                        String deviceName = sc.next();
                        if (sc.hasNext(Pattern.compile("(\\d+)$"))) {
                            int deviceId = sc.nextInt();
                            if ((deviceId <= MAX_DEVICE_ID && deviceId >= MIN_DEVICE_ID)
                                    && objects[deviceId].getClass().getSimpleName().equals(deviceName)) {
                                if (Pattern.compile("(Camera|Light)").matcher(deviceName).matches()) {
                                    if (objects[deviceId].isOn()) {
                                        if (((Camera) objects[deviceId]).stopRecording()) {
                                            System.out.printf("%s %d stopped recording\n", deviceName, deviceId);
                                        } else {
                                            System.out.printf("%s %d is not recording\n", deviceName, deviceId);
                                        }
                                    } else {
                                        System.out.printf("You can't change the status of the %s %d while it is off\n",
                                                deviceName, deviceId);
                                    }
                                } else {
                                    System.out.printf("%s %d is not a camera\n", deviceName, deviceId);
                                }
                            } else {
                                System.out.println("The smart device was not found");
                            }
                        } else {
                            System.out.println("Invalid command");
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                }
                default -> System.out.println("Invalid command");
            }
        }
    }

    /**
     * Enumeration for brightness levels of a light device.
     */
    public enum Brightnesslevel {
        HIGH,      // High brightness level
        MEDIUM,    // Medium brightness level
        LOW        // Low brightness level
    }

    /**
     * Enumeration for light colors.
     */
    public enum LightColor {
        WHITE,     // White light color
        YELLOW     // Yellow light color
    }

    /**
     * Enumeration for the status of a smart device.
     */
    public enum Status {
        OFF,       // Device is off
        ON         // Device is on
    }

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

    /**
     * Abstract class representing a smart device, providing functionality for controlling the device's power status.
     */
    public abstract static class SmartDevice implements Controllable {
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

    /**
     * Represents a light device, extending SmartDevice and implementing Chargeable.
     */
    public static final class Light extends SmartDevice implements Chargeable {
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

    /**
     * Represents a heater device, extending SmartDevice.
     */
    public static final class Heater extends SmartDevice {
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

    /**
     * Represents a camera device, extending SmartDevice and implementing Chargeable.
     */
    public static final class Camera extends SmartDevice implements Chargeable {
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
}
