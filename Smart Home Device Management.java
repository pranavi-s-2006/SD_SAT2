import java.util.*;

class SmartHome {

    static Map<String, Device> devices = new HashMap<>();
    static List<String> activity = new ArrayList<>();

    static class Device {
        String id, name, type, state;

        Device(String id, String name, String type) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.state = "OFF";
        }
    }

    static void registerDevice(String id, String name, String type) {
        devices.put(id, new Device(id, name, type));
        System.out.println("Device registered: " + name);
    }

    static void controlDevice(String id, String command) {
        Device d = devices.get(id);

        if (d == null) {
            System.out.println("Device not found");
            return;
        }

        if (!command.equals("ON") && !command.equals("OFF")) {
            System.out.println("Invalid command");
            return;
        }

        d.state = command;
        activity.add(id + " : " + command);

        System.out.println(d.name + " turned " + command);
    }

    static void getStatus(String id) {
        Device d = devices.get(id);

        if (d == null) {
            System.out.println("Device not found");
            return;
        }

        System.out.println("\nDevice ID   : " + d.id);
        System.out.println("Device Name : " + d.name);
        System.out.println("Device Type : " + d.type);
        System.out.println("State       : " + d.state);
    }

    static void showActivity() {
        System.out.println("\nDevice Activity:");
        for (String a : activity)
            System.out.println(a);
    }

    static void removeDevice(String id) {
        if (devices.remove(id) != null)
            System.out.println("\nDevice removed successfully");
        else
            System.out.println("\nDevice not found");
    }

    public static void main(String[] args) {

        registerDevice("D101", "Living Room Light", "LIGHT");

        getStatus("D101");

        controlDevice("D101", "ON");

        getStatus("D101");

        showActivity();

        removeDevice("D101");
    }
}