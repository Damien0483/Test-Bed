public class Person {
    private String fullName;
    private int idNumber;
    private Address homeAddress;

    public Person(String fullName, int idNumber, Address homeAddress) {
        setFullName(fullName);
        setIdNumber(idNumber);
        setHomeAddress(homeAddress);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Override
    public String toString() {
        return "Name: " + fullName +
               ", ID: " + idNumber +
               ", Address: " + homeAddress.toString();
    }
}
