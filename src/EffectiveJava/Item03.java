package EffectiveJava;

// Singleton with public final field
class Elvis {
    public static final Elvis INSTANCE = new Elvis();
    private Elvis() {}
    public static Elvis getInstance() {
        return INSTANCE;
    }

    public void leaveTheBuilding() {}

    // readResolve method to preserve singleton property
    private Object readResolve() {
        // Return the one true Elvis and let the garbage collector
        // take care of the Elvis impersonator
        return INSTANCE;
    }

    // Enum singleton - the preferred approach
    enum ElvisEnum {
        INSTANCE;

        public void leaveTheBuilding() {

        }
    }
}