/**
 * Name: Your Name
 * Date: 2025-02-10
 * Assignment: SDC330 Performance Assessment - Database
 * Description:
 *     Represents a single address record stored in the SQLite database.
 */

public class Address {
    private int id;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zipcode;

    public Address(int id, String street1, String street2, String city, String state, String zipcode) {
        this.id = id;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Address ").append(id).append(":\n");
        sb.append(street1).append("\n");
        if (street2 != null && !street2.trim().isEmpty()) {
            sb.append(street2).append("\n");
        }
        sb.append(city).append(", ").append(state).append(" ").append(zipcode);
        return sb.toString();
    }
}
