public class Client implements Cloneable {
    private final String name; // Client's name
    private int rank;    // Client's rank (0-10)

    // Constructor
    public Client(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }

    // Equals method to compare clients by name
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Client client = (Client) obj;
        return name.equalsIgnoreCase(client.getName());
    }

    @Override
    public String toString() {
        return String.format("Client{name='%s', rank=%d}", name, rank);
    }

    @Override
    protected Client clone() throws CloneNotSupportedException {
        return (Client) super.clone();
    }

}
