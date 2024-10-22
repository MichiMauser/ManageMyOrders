package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.ClientAgeValidator;
import bll.validators.EmailValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import dao.OrderDAO;
import model.Client;
import model.Porder;

/**
 * Business logic layer class for managing clients.
 *
 * @author Technical University of Cluj-Napoca, Romania Distributed Systems
 *         Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @since Apr 03, 2017
 */
public class ClientBLL {

	private List<Validator<Client>> validators;
	private ClientDAO clientDAO = new ClientDAO();

	/**
	 * Constructs a new ClientBLL object and initializes validators.
	 */
	public ClientBLL() {
		validators = new ArrayList<Validator<Client>>();
		validators.add(new EmailValidator());
		validators.add(new ClientAgeValidator());
	}

	/**
	 * Finds a client by ID.
	 *
	 * @param id the ID of the client to find
	 * @return the client with the specified ID
	 * @throws IllegalArgumentException if the client with the specified ID is not found
	 */
	public Client findById(int id) {
		Client client = clientDAO.findById(id);
		if (client == null) {
			throw new IllegalArgumentException("The client with id = " + id + " was not found!");
		}
		return client;
	}

	/**
	 * Retrieves all clients.
	 *
	 * @return a list of all clients
	 * @throws IllegalArgumentException if no clients are found
	 */
	public List<Client> findAll() {
		List<Client> clientList = clientDAO.findAll();
		if (clientList.isEmpty()) {
			throw new IllegalArgumentException("No clients found!");
		}
		return clientList;
	}

	/**
	 * Inserts a new client.
	 *
	 * @param c the client to insert
	 * @throws IllegalAccessException if the client cannot be inserted
	 */
	public void insert(Client c) throws IllegalAccessException {
		for (Validator<Client> validator : validators) {
			validator.validate(c);
		}
		clientDAO.insert(c);
	}

	/**
	 * Updates an existing client.
	 *
	 * @param c the client to update
	 * @throws IllegalAccessException if the client cannot be updated
	 */
	public void update(Client c) throws IllegalAccessException {
		for (Validator<Client> validator : validators) {
			validator.validate(c);
		}
		clientDAO.update(c);
	}

	/**
	 * Deletes a client.
	 *
	 * @param c the client to delete
	 * @throws IllegalAccessException if the client cannot be deleted
	 */
	public void delete(Client c) throws IllegalAccessException {
		OrderDAO orderDAO = new OrderDAO();
		List<Porder> orders = orderDAO.findAll();
		int i = 0;
		while (i < orders.size()) {
			int orderClientId = orders.get(i).getClientId();
			int orderId = orders.get(i).getId();
			if (orderClientId == c.getId()) {
				orderDAO.delete(new Porder(orderId));
				orders.remove(i);
			} else {
				i++;
			}
		}
		clientDAO.delete(c);
	}
}