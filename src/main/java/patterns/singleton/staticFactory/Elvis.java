package patterns.singleton.staticFactory;

import java.io.Serializable;

public class Elvis implements Serializable {
    private static transient final Elvis INSTANCE = new Elvis();

    private Elvis() {
        // Not safe. Can be called reflectively with AccessibleObject.setAccessible.
    }

    /**
     * It's clear that is a singleton.
     * Gives you the flexibility to change your mind about whether the class is a singleton without changing its api.
     * The factory method returns the sole instance, but it could be modified to return, say, a separate instance for each thread that invokes it.
    */
    public static Elvis getInstance() {
        return INSTANCE;
    }

    public void leaveTheBuilding() {
        System.out.println("Elvis leaves the building with static factory.");
    }

    /**
     * To maintain the singleton guarantee, declare all instance fields transient and provide a readResolve method.
     * Otherwise, each time a serialized instance is deserialized, a new instance will be created.
     *
     * readResolve is used for replacing the object read from the stream. The only use I've ever seen for this is enforcing singletons; when an object is read, replace it with the singleton instance.
     * This ensures that nobody can create another instance by serializing and deserializing the singleton.
     */
    public Object readResolve() {
       return INSTANCE;
    }

}
