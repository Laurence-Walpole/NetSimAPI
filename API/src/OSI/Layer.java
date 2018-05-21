package OSI;

public enum Layer {
    ONE("Physical"),
    TWO("Data Link"),
    THREE("Network"),
    FOUR("Transport"),
    FIVE("Session"),
    SIX("Presentation"),
    SEVEN("Application");

    String layerName;

    Layer(String layerName){
        this.layerName = layerName;
    }

    public String getLayerName() {
        return layerName;
    }
}
