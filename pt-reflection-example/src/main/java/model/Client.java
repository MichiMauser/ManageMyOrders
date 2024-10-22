package model;

/**
 * Represents a client entity.
 */
public class Client {
    private int Id;
    private String FullName;
    private String Email;
    private int Age;

    /**
     * Constructs a client with default values.
     */
    public Client() {
    }

    /**
     * Constructs a client with specified parameters.
     *
     * @param FullName The full name of the client.
     * @param Email    The email of the client.
     * @param Age      The age of the client.
     */
    public Client(String FullName, String Email, int Age) {
        this.Email = Email;
        this.FullName = FullName;
        this.Age = Age;
    }

    /**
     * Constructs a client with the specified ID.
     *
     * @param Id The ID of the client.
     */
    public Client(int Id) {
        this.Id = Id;
    }

    /**
     * Gets the ID of the client.
     *
     * @return The ID of the client.
     */
    public int getId() {
        return Id;
    }

    /**
     * Gets the full name of the client.
     *
     * @return The full name of the client.
     */
    public String getFullName() {
        return FullName;
    }

    /**
     * Gets the email of the client.
     *
     * @return The email of the client.
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Sets the full name of the client.
     *
     * @param fullName The full name of the client.
     */
    public void setFullName(String fullName) {
        FullName = fullName;
    }

    /**
     * Sets the email of the client.
     *
     * @param email The email of the client.
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * Sets the age of the client.
     *
     * @param age The age of the client.
     */
    public void setAge(int age) {
        Age = age;
    }

    /**
     * Gets the age of the client.
     *
     * @return The age of the client.
     */
    public int getAge() {
        return Age;
    }

    /**
     * Sets the ID of the client.
     *
     * @param id The ID of the client.
     */
    public void setId(int id) {
        this.Id = id;
    }

    /**
     * Returns a string representation of the client.
     *
     * @return A string representation of the client.
     */
    @Override
    public String toString() {
        return "Client{" +
                "Id=" + Id +
                ", FullName='" + FullName + '\'' +
                ", Email='" + Email + '\'' +
                ", Age=" + Age +
                '}';
    }
}