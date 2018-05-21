package Standards;

public enum Ethernet {
    IEE802_3C(10),
    IEE802_3U(100),
    IEE802_3Z(1000),
    IEE802_3AE(10000);

    int throughput;

    Ethernet(int throughput){
        this.throughput = throughput;
    }

    public int getThroughput() {
        return throughput;
    }
}
