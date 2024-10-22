package bll.validators;

import dao.ClientDAO;
import dao.ProductDAO;
import model.Porder;
import model.Product;

public class QuantityOrderValidator implements Validator<Porder>{


    @Override
    public void validate(Porder porder) {

        ClientDAO clientDAO = new ClientDAO();
        ProductDAO productDAO = new ProductDAO();
        Product product = new Product();
        if(porder.getOrdQuantity() <= 0){
             throw new IllegalArgumentException("The quantity can't be lower than 1");
        }
        if(clientDAO.findById(porder.getClientId()) == null){
            throw new IllegalArgumentException("This customer doesn't exist");
        }
        if(productDAO.findById(porder.getProductId()) == null){
            throw new IllegalArgumentException("This product doesn't exist");
        }

        product = productDAO.findById(porder.getProductId());
        if(product.getQuantity() < porder.getOrdQuantity()){
            throw new IllegalArgumentException("Not enough stock: available stock "+ product.getQuantity()+" the order: "+ porder.getOrdQuantity());
        }
    }
}
