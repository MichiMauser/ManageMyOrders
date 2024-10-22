package model;

/**
 * Represents a purchase order entity.
 */
public class Porder {
    private int Id;
    private int ClientId;
    private int ProductId;
    private int OrdQuantity;

    /**
     * Constructs a purchase order with specified parameters.
     *
     * @param clientId    The ID of the client placing the order.
     * @param productId   The ID of the product being ordered.
     * @param ordQuantity The quantity of the product being ordered.
     */
    public Porder(int clientId, int productId, int ordQuantity) {
        ClientId = clientId;
        ProductId = productId;
        OrdQuantity = ordQuantity;
    }

    /**
     * Constructs a purchase order with default values.
     */
    public Porder() {
    }

    /**
     * Constructs a purchase order with the specified ID.
     *
     * @param Id The ID of the purchase order.
     */
    public Porder(int Id) {
        this.Id = Id;
    }

    /**
     * Sets the ID of the purchase order.
     *
     * @param id The ID to set.
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * Sets the ID of the client placing the order.
     *
     * @param clientId The client ID to set.
     */
    public void setClientId(int clientId) {
        ClientId = clientId;
    }

    /**
     * Sets the ID of the product being ordered.
     *
     * @param productId The product ID to set.
     */
    public void setProductId(int productId) {
        ProductId = productId;
    }

    /**
     * Sets the quantity of the product being ordered.
     *
     * @param ordQuantity The quantity to set.
     */
    public void setOrdQuantity(int ordQuantity) {
        OrdQuantity = ordQuantity;
    }

    /**
     * Gets the ID of the purchase order.
     *
     * @return The ID of the purchase order.
     */
    public int getId() {
        return Id;
    }

    /**
     * Gets the ID of the client placing the order.
     *
     * @return The client ID.
     */
    public int getClientId() {
        return ClientId;
    }

    /**
     * Gets the ID of the product being ordered.
     *
     * @return The product ID.
     */
    public int getProductId() {
        return ProductId;
    }

    /**
     * Gets the quantity of the product being ordered.
     *
     * @return The quantity of the product.
     */
    public int getOrdQuantity() {
        return OrdQuantity;
    }
}