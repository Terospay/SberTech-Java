package garage;

public class Owner {
    private final int age;
    private final int ownerId;

    public Owner(int age, int ownerId) {
        this.age = age;
        this.ownerId = ownerId;
    }

    public int getAge() {
        return age;
    }

    public int getOwnerId() {
        return ownerId;
    }
}
