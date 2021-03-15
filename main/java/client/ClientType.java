package client;

public enum ClientType {
    INDIVIDUAL{
        public Client createClient(String name, String inn, String describing, boolean isSanctioned) {
            return new Individual(name, inn, describing, isSanctioned);
        }
    },
    LEGAL_ENTITY {
        public Client createClient(String name, String inn, String describing, boolean isSanctioned) {
            return new LegalEntity(name, inn, describing, isSanctioned);
        }
    },
    HOLDING {
        public Client createClient(String name, String inn, String describing, boolean isSanctioned) {
            return new Holding(name, inn, describing, isSanctioned);
        }
    };

    public abstract Client createClient(String name, String inn, String describing, boolean isSanctioned);
}
