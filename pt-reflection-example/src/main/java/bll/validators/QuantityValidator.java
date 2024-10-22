package bll.validators;

import model.Product;

public class QuantityValidator implements Validator<Product>{
    @Override
    public void validate(Product product) {
        if(product.getQuantity() <= 0 ){
            throw new IllegalArgumentException("The quantity shouldn't be a negative number");
        }
    }
}
