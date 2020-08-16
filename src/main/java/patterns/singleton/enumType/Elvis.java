package patterns.singleton.enumType;

/**
 * Preferred approach.
 * Provides the serialization machinery for free.
 * Provides ironclad guarantee against multiple instantiation.
 */
public enum Elvis {
    INSTANCE;

    public void leaveTheBuilding() {
        System.out.println("Elvis leaves the building with enum type.");
    }

}