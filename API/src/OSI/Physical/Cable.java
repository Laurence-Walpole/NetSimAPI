package OSI.Physical;

import Standards.Ethernet;

import java.util.List;
/**
 * This class is used to describe a cable component in the network.
 * It inherits attributes from the Component class.
 *
 * @author Laurence Walpole
 * @version 1.0
 * @since 2018-05-21
 */
public class Cable extends Component{
    private Ethernet cableStandard;
    private String cableColour;
    private int cableLength;

    /**
     * Defines the piece of cable used to connect two NICs.
     * @param standard Defines the IEEE standard of the cable.
     * @param colour Defines the colour of the cable.
     * @param length Defines the length of the piece of cable, measured in metres.
     * @param active Defines if the component is active.
     * @param connectingComponents The other components connected to this component.
     */
    public Cable(Ethernet standard, String colour, int length, boolean active, List<Component> connectingComponents){
        super(active, standard.getThroughput(), connectingComponents);
        this.cableStandard = standard;
        this.cableColour = colour;
        this.cableLength = length;
    }
    /**
     * @return String This returns the name of the standard this cable follows.
     */
    public String getCableStandard() {
        return cableStandard.name();
    }
    /**
     * @return String This returns the colour of the cable.
     */
    public String getCableColour() {
        return cableColour;
    }
    /**
     * @return boolean This returns the length of the cable.
     */
    public int getCableLength() {
        return cableLength;
    }
    /**
     * @return String Returns a formatted display of the cables basic information.
     */
    @Override
    public String toString(){
        return String.format("[Standard]: %s, [Colour]: %s, [Length]: %d, [Active]: %s, [Connection Count]: %d",
                getCableStandard(), getCableColour(), getCableLength(), isActive(), getConnectingComponents().size());
    }
}
