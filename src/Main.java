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
                /*
                 * Processes the "StartCharging" command to start charging a device.
                 * Validates the device name, ID, and checks if the device is chargeable.
                 * Only devices that implement the Chargeable interface can start charging.
                 */
                case "StartCharging" -> {
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
                /*
                 * Processes the "StopCharging" command to stop charging a device.
                 * Validates the device name, ID, and checks if the device is chargeable.
                 * Only devices that implement the Chargeable interface can stop charging.
                 */
                case "StopCharging" -> {
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
                /*
                 * Processes the "SetTemperature" command to set the temperature of a heater.
                 * Validate the device name, ID, ensures the heater is on, checks temperature is within valid range.
                 */
                case "SetTemperature" -> {
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
                /*
                 * Processes the "SetBrightness" command to set the brightness of a light device.
                 * Validates the device name, ID, ensures the light is on, and checks for valid brightness levels.
                 */
                case "SetBrightness" -> {
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
                /*
                 * Processes the "SetColor" command to set the color of a light device.
                 * Validates the device name, ID, ensures the light is on, and checks for valid color options.
                 */
                case "SetColor" -> {
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
                /*
                 * Processes the "SetAngle" command to set the angle of a camera device.
                 * Validates the device name, ID, ensures the camera is on, and checks for a valid angle range.
                 */
                case "SetAngle" -> {
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
                /*
                 * Processes the "StartRecording" command to start recording with a camera.
                 * Validates the device name, ID, ensures the camera is on, and checks if it is already recording.
                 */
                case "StartRecording" -> {
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
                /*
                 * Processes the "StopRecording" command to stop recording with a camera.
                 * Validates the device name, ID, ensures the camera is on, and checks if it is recording.
                 */
                case "StopRecording" -> {
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
}
