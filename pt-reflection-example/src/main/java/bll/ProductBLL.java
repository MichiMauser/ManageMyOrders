package bll;

import bll.validators.PriceValidator;
import bll.validators.QuantityValidator;
import bll.validators.Validator;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Porder;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Business logic layer for managing products.
 */
public class ProductBLL {
    private List<Validator<Product>> validators = new ArrayList<>();
    private ProductDAO productDAO = new ProductDAO();

    /**
     * Constructs a ProductBLL object and adds validators.
     */
    public ProductBLL() {
        validators.add(new PriceValidator());
        validators.add(new QuantityValidator());
    }

    /**
     * Finds a product by its ID.
     *
     * @param id The ID of the product to find.
     * @return The product with the specified ID.
     * @throws IllegalArgumentException if the product with the specified ID is not found.
     */
    public Product findById(int id) {
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new IllegalArgumentException("The product with id =" + id + " was not found!");
        }
        return product;
    }

    /**
     * Finds all products.
     *
     * @return A list of all products.
     * @throws IllegalArgumentException if no products are found.
     */
    public List<Product> findAll(){
        List<Product> productList = productDAO.findAll();
        if(productList.isEmpty()){
            throw new IllegalArgumentException("No products found !");
        }
        return productList;
    }

    /**
     * Inserts a new product.
     *
     * @param p The product to insert.
     * @throws IllegalAccessException if the insertion operation is not allowed.
     */
    public void insert(Product p) throws IllegalAccessException {

        for (Validator<Product> validator : validators) {
            validator.validate(p);
        }
        productDAO.insert(p);
    }

    /**
     * Updates a product.
     *
     * @param p The product to update.
     * @throws IllegalAccessException if the update operation is not allowed.
     */
    public void update(Product p) throws IllegalAccessException {
        for (Validator<Product> validator : validators) {
            validator.validate(p);
        }
        productDAO.update(p);
    }

    /**
     * Deletes a product.
     *
     * @param p The product to delete.
     * @throws IllegalAccessException if the deletion operation is not allowed.
     */
    public void delete(Product p) throws IllegalAccessException {

        OrderDAO orderDAO = new OrderDAO();
        List<Porder> orders = orderDAO.findAll();
        int i = 0;
        while (i < orders.size()) {
            int orderProductId = orders.get(i).getProductId();
            int orderId = orders.get(i).getId();
            if (orderProductId == p.getId()) {
                orderDAO.delete(new Porder(orderId));
                orders.remove(i);
            } else {
                i++;
            }
        }
        productDAO.delete(p);

    }
}