package model;

/**
 * Represents a log entity.
 */
public class Log {
    private int BillNr;
    private double Amount;
    private String ClientName;
    private String ProductName;

    /**
     * Constructs a log with default values.
     */
    public Log() {
    }

    /**
     * Constructs a log with specified parameters.
     *
     * @param ClientName  The name of the client.
     * @param ProductName The name of the product.
     * @param Amount      The amount of the transaction.
     */
    public Log(String ClientName, String ProductName, double Amount) {
        this.ClientName = ClientName;
        this.ProductName = ProductName;
        this.Amount = Amount;
    }

    /**
     * Gets the bill number.
     *
     * @return The bill number.
     */
    public int getBillNr() {
        return BillNr;
    }

    /**
     * Gets the amount of the transaction.
     *
     * @return The amount of the transaction.
     */
    public double getAmount() {
        return Amount;
    }

    /**
     * Gets the name of the client.
     *
     * @return The name of the client.
     */
    public String getClientName() {
        return ClientName;
    }

    /**
     * Gets the name of the product.
     *
     * @return The name of the product.
     */
    public String getProductName() {
        return ProductName;
    }

    /**
     * Sets the bill number.
     *
     * @param billNr The bill number to set.
     */
    public void setBillNr(int billNr) {
        BillNr = billNr;
    }

    /**
     * Sets the amount of the transaction.
     *
     * @param amount The amount of the transaction to set.
     */
    public void setAmount(double amount) {
        Amount = amount;
    }

    /**
     * Sets the name of the client.
     *
     * @param clientName The name of the client to set.
     */
    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    /**
     * Sets the name of the product.
     *
     * @param productName The name of the product to set.
     */
    public void setProductName(String productName) {
        ProductName = productName;
    }
}