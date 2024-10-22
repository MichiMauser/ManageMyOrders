package model;

/**
 * Represents a product entity.
 */
public class Product {
    private int Id;
    private String Name;
    private double Price;
    private int Quantity;

    /**
     * Constructs a product with default values.
     */
    public Product() {
    }

    /**
     * Constructs a product with the specified ID.
     *
     * @param Id The ID of the product.
     */
    public Product(int Id) {
        this.Id = Id;
    }

    /**
     * Constructs a product with specified parameters.
     *
     * @param Name     The name of the product.
     * @param Price    The price of the product.
     * @param Quantity The quantity of the product.
     */
    public Product(String Name, double Price, int Quantity) {
        this.Name = Name;
        this.Price = Price;
        this.Quantity = Quantity;
    }

    /**
     * Sets the ID of the product.
     *
     * @param id The ID to set.
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * Sets the name of the product.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Sets the price of the product.
     *
     * @param price The price to set.
     */
    public void setPrice(double price) {
        Price = price;
    }

    /**
     * Sets the quantity of the product.
     *
     * @param quantity The quantity to set.
     */
    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    /**
     * Gets the ID of the product.
     *
     * @return The ID of the product.
     */
    public int getId() {
        return Id;
    }

    /**
     * Gets the name of the product.
     *
     * @return The name of the product.
     */
    public String getName() {
        return Name;
    }

    /**
     * Gets the price of the product.
     *
     * @return The price of the product.
     */
    public double getPrice() {
        return Price;
    }

    /**
     * Gets the quantity of the product.
     *
     * @return The quantity of the product.
     */
    public int getQuantity() {
        return Quantity;
    }
}