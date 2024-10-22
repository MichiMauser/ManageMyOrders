package bll;

import bll.validators.QuantityOrderValidator;
import bll.validators.Validator;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Porder;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Business logic layer for managing orders.
 */
public class OrderBLL {

    private List<Validator<Porder>> validators = new ArrayList<>();
    private OrderDAO orderDAO = new OrderDAO();

    /**
     * Constructs an OrderBLL object and adds validators.
     */
    public OrderBLL(){
        validators.add(new QuantityOrderValidator());
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id The ID of the order to retrieve.
     * @return The order with the specified ID.
     * @throws IllegalArgumentException if the order with the specified ID is not found.
     */
    public Porder findById(int id) {
        Porder porder = orderDAO.findById(id);
        if (porder == null) {
            throw new IllegalArgumentException("The order with id =" + id + " was not found!");
        }
        return porder;
    }

    /**
     * Retrieves all orders.
     *
     * @return A list of all orders.
     * @throws IllegalArgumentException if no orders are found.
     */
    public List<Porder> findAll(){
        List<Porder> porderList = orderDAO.findAll();
        if(porderList.isEmpty()){
            throw new IllegalArgumentException("No order found !");
        }
        return porderList;
    }

    /**
     * Makes an order.
     *
     * @param o The order to be made.
     * @throws IllegalAccessException if the order cannot be made.
     */
    public void makeOrder(Porder o) throws IllegalAccessException{
        ProductDAO productDAO = new ProductDAO();
        for(Validator<Porder> validator: validators){
            validator.validate(o);
        }
        Product p  = productDAO.findById(o.getProductId());
        p.setQuantity(p.getQuantity() - o.getOrdQuantity());
        productDAO.update(p);
        orderDAO.insert(o);
    }

    /**
     * Updates an order.
     *
     * @param o The order to be updated.
     * @throws IllegalAccessException if the order cannot be updated.
     */
    public void update(Porder o) throws IllegalAccessException{
        for(Validator<Porder> validator: validators){
            validator.validate(o);
        }
        orderDAO.update(o);
    }

    /**
     * Deletes an order.
     *
     * @param o The order to be deleted.
     * @throws IllegalAccessException if the order cannot be deleted.
     */
    public void delete(Porder o) throws IllegalAccessException{
        orderDAO.delete(o);
    }
}