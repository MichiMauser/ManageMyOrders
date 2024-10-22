package bll.validators;

import model.Client;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ClientAgeValidator implements Validator<Client> {
	private static final int MIN_AGE = 18;
	private static final int MAX_AGE = 75;


	@Override
	public void validate(Client client) {
		if(client.getAge() < MIN_AGE || client.getAge() > MAX_AGE){
			throw new IllegalArgumentException("Client Age limit is not respected!");
		}
	}
}
