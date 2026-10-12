import java.util.*;

class SmartHome {

    static class Device {
        String id;
        String name;
        String type;
        String status;
        String state;

        Device(String id, String name, String type) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.status = "ONLINE";
            this.state = "OFF";
        }

        void display() {
            System.out.println("Device ID   : " + id);
            System.out.println("Device Name : " + name);
            System.out.println("Device Type : " + type);
            System.out.println("Status      : " + status);
            System.out.println("State       : " + state);
        }
    }

    static Map<String, Device> devices = new HashMap<>();
    static List<String> activity = new ArrayList<>();
    static Queue<String> messageQueue = new LinkedList<>();

    static void registerDevice(String id, String name, String type) {
        if (devices.containsKey(id)) {
            System.out.println("Device already exists");
            return;
        }

        Device device = new Device(id, name, type);
        devices.put(id, device);
        activity.add(id + " : Device registered");
        System.out.println("Device registered successfully");
    }

    static void getDevice(String id) {
        Device device = devices.get(id);

        if (device == null) {
            System.out.println("Device not found");
            return;
        }

        device.display();
    }

    static void controlDevice(String id, String command) {
        Device device = devices.get(id);

        if (device == null) {
            System.out.println("Device not found");
            return;
        }

        if (!command.equals("ON") && !command.equals("OFF")) {
            System.out.println("Invalid command");
            return;
        }

        device.state = command;
        activity.add(id + " : State changed to " + command);
        messageQueue.add(id + " : " + command);

        System.out.println("Command executed successfully");
        System.out.println("Device " + id + " is now " + command);
    }

    static void updateStatus(String id, String status) {
        Device device = devices.get(id);

        if (device == null) {
            System.out.println("Device not found");
            return;
        }

        if (!status.equals("ONLINE") && !status.equals("OFFLINE")) {
            System.out.println("Invalid status");
            return;
        }

        device.status = status;
        activity.add(id + " : Status changed to " + status);

        System.out.println("Status updated successfully");
    }

    static void getStatus(String id) {
        Device device = devices.get(id);

        if (device == null) {
            System.out.println("Device not found");
            return;
        }

        System.out.println("Device ID : " + device.id);
        System.out.println("Status    : " + device.status);
        System.out.println("State     : " + device.state);
    }

    static void showDevices() {
        if (devices.isEmpty()) {
            System.out.println("No devices available");
            return;
        }

        System.out.println("\nRegistered Devices");

        for (Device device : devices.values()) {
            System.out.println(
                device.id + " | " +
                device.name + " | " +
                device.type + " | " +
                device.status + " | " +
                device.state
            );
        }
    }

    static void showActivity(String id) {
        System.out.println("\nActivity for " + id);

        boolean found = false;

        for (String item : activity) {
            if (item.startsWith(id)) {
                System.out.println(item);
                found = true;
            }
        }

        if (!found)
            System.out.println("No activity found");
    }

    static void processMessages() {
        System.out.println("\nMessage Queue");

        if (messageQueue.isEmpty()) {
            System.out.println("No messages");
            return;
        }

        while (!messageQueue.isEmpty()) {
            String message = messageQueue.poll();
            System.out.println("Processing: " + message);
        }
    }

    static void removeDevice(String id) {
        Device device = devices.remove(id);

        if (device == null) {
            System.out.println("Device not found");
            return;
        }

        activity.add(id + " : Device removed");
        System.out.println("Device removed successfully");
    }

    static void searchDevice(String type) {
        boolean found = false;

        System.out.println("\nDevices of type " + type);

        for (Device device : devices.values()) {
            if (device.type.equalsIgnoreCase(type)) {
                System.out.println(device.id + " - " + device.name);
                found = true;
            }
        }

        if (!found)
            System.out.println("No matching device found");
    }

    public static void main(String[] args) {

        System.out.println("SMART HOME DEVICE MANAGEMENT");

        registerDevice(
            "D101",
            "Living Room Light",
            "LIGHT"
        );

        registerDevice(
            "D102",
            "Bedroom Fan",
            "FAN"
        );

        registerDevice(
            "D103",
            "Living Room AC",
            "AC"
        );

        showDevices();

        System.out.println("\nDevice Details");
        getDevice("D101");

        System.out.println("\nChecking Status");
        getStatus("D101");

        System.out.println("\nControlling Device");
        controlDevice("D101", "ON");

        System.out.println("\nUpdated Status");
        getStatus("D101");

        System.out.println("\nUpdating Device Status");
        updateStatus("D101", "ONLINE");

        System.out.println("\nSearching Devices");
        searchDevice("LIGHT");

        System.out.println("\nDevice Activity");
        showActivity("D101");

        processMessages();

        System.out.println("\nRemoving Device");
        removeDevice("D103");

        System.out.println("\nFinal Device List");
        showDevices();
    }
}