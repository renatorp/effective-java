package patterns.singleton.finalField;

public class Elvis {
    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {
        // Not safe. Can be called reflectively with AccessibleObject.setAccessible.
    }

    public void leaveTheBuilding() {
        System.out.println("Elvis leaves the building with final field.");
    }
}
