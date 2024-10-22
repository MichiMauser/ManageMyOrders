package start;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import dao.ClientDAO;
import dao.OrderDAO;
import model.Client;
import model.Porder;
import model.Product;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException, IllegalAccessException {

		ClientBLL clientBll = new ClientBLL();
		ProductBLL productBLL = new ProductBLL();
		OrderBLL orderBLL = new OrderBLL();
		ClientDAO clientDAO = new ClientDAO();
		OrderDAO orderDAO = new OrderDAO();
		Client client1 = new Client("Tester1", "edi", 20);
		Client client3 = new Client("Tester5", "edi@yahoo.com", 45);
		Client client4 = new Client("Tester39", "edi@yahoo.com", 35);
		Product p = new Product("Lemn",10,5);
		Product p1 = new Product("Lemn",1,10);
		Product tester = new Product();
		Porder porder = new Porder(6,6,1);

		try {


			//System.out.println(productBLL.findById(6).getQuantity());
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}

		// obtain field-value pairs for object through reflection
		//ReflectionExample.retrieveProperties(client);

	}

}
