import OSI.Physical.Cable;
import OSI.Physical.Component;
import OSI.Physical.NIC;
import Standards.Ethernet;
import Standards.MAC;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Component> componentList = new ArrayList<>();
        MAC m = new MAC("");
        NIC t = new NIC(m, 100, false, componentList);
        componentList.add(t);
        Cable c = new Cable(Ethernet.IEE802_3AE, "Black", 5, false, componentList);
        System.out.println(c);
        System.out.println(t);
    }

}
