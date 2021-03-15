package client;

public class Individual implements Client {
    private final String name;
    private final String inn;
    private final String describing;
    private final boolean isSanctioned;

    Individual(String name, String inn, String describing, boolean isSanctioned) {
        this.name = name;
        this.inn = inn;
        this.describing = describing;
        this.isSanctioned = isSanctioned;
    }

    public String info() {
        return name + " " + inn + " " + describing;
    }

}