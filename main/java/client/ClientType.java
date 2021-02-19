package client;

public enum ClientType {
    INDIVIDUAL{
        public Object createClient(String name, String inn, String describing, boolean isSanctioned) {
            return new Individual(name, inn, describing, isSanctioned);
        }
    },
    LEGAL_ENTITY {
        public Object createClient(String name, String inn, String describing, boolean isSanctioned) {
            return new LegalEntity(name, inn, describing, isSanctioned);
        }
    },
    HOLDING {
        public Object createClient(String name, String inn, String describing, boolean isSanctioned) {
            return new Holding(name, inn, describing, isSanctioned);
        }
    };

    public abstract Object createClient(String name, String inn, String describing, boolean isSanctioned);
}
