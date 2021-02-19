package client;

public class Individual implements Client {
    public String name;
    private String inn;
    public String describing;
    public boolean isSanctioned;

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
