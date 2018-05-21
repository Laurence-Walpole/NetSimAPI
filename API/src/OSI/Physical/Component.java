package OSI.Physical;

import java.util.List;
/**
 * This class is used to describe a physical component in the network.
 * It provides a basis to creating components such as NICs and Cabling.
 *
 * @author Laurence Walpole
 * @version 1.0
 * @since 2018-05-21
 */
public class Component {
    private int maxThroughput;
    private List<Component> connectingComponents;
    private boolean active;

    /**
     * Defines the component
     * @param active Defines if the component is active.
     * @param maxThroughput The maximum throughput of the component, designated in MegaBytes per second.
     * @param connectingComponents The other components connected to this component.
     */
    public Component(boolean active, int maxThroughput, List<Component> connectingComponents){
        this.active = active;
        this.maxThroughput = maxThroughput;
        this.connectingComponents = connectingComponents;
    }
    /**
     * @return boolean This returns the active state of the component.
     */
    public boolean isActive() {
        return active;
    }
    /**
     * @return int This returns the maximum throughput of the component, defined in MegaBytes per second.
     */
    public int getMaxThroughput() {
        return maxThroughput;
    }
    /**
     * @return List<Component> This returns the connecting components of the component (the children).
     */
    public List<Component> getConnectingComponents() {
        return connectingComponents;
    }
    /**
     * @param newState The new desired state of the component.
     */
    public void changeActiveState(boolean newState){
        this.active = newState;
    }
    /**
     * @param throughput The maximum throughput of the component.
     */
    public void setMaxThroughput(int throughput){
        this.maxThroughput = throughput;
    }
    /**
     * @param component The component to be removed from the connected components.
     */
    public void removeComponent(Component component){
        this.connectingComponents.remove(component);
    }
    /**
     * @param component The component to be added to the connected components.
     */
    public void addComponent(Component component){
        this.connectingComponents.add(component);
    }
    /**
     * @param components Creates the original list of components.
     */
    public void setComponents(List<Component> components){
        this.connectingComponents = components;
    }
}
