package climbway;

import java.util.Scanner;

import pt.ulisboa.tecnico.learnjava.bank.domain.Account;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Sibs;

public class MVCPattern {

	public static void main(String[] args) throws BankException, AccountException, ClientException {
		Scanner s = new Scanner(System.in);

		User model = new User();

		UserView view = new UserView();

		UserController controller = new UserController(model, view);

		String input;

		Services services = new Services();

		Sibs sibs = new Sibs(100, services);

		Bank bank = new Bank("CGC");

		Client client1 = new Client(bank, "Maria", "Alves", "123456789", "967440681", "Street", 33);
		Client client2 = new Client(bank, "Jo�o", "Alves", "123456788", "966906844", "Street", 33);
		Client client3 = new Client(bank, "Pedro", "Casaleiro", "123456787", "964391860", "Street", 33);
		Client client4 = new Client(bank, "Patr�cia", "Matos", "123456700", "927338098", "Street", 33);
		Client client5 = new Client(bank, "Ana", "Pinto", "123456000", "967440682", "Street", 33);

		String Iban1 = bank.createAccount(Bank.AccountType.CHECKING, client1, 100, 0);
		String Iban2 = bank.createAccount(Bank.AccountType.CHECKING, client2, 100, 0);
		String Iban3 = bank.createAccount(Bank.AccountType.CHECKING, client3, 100, 0);
		String Iban4 = bank.createAccount(Bank.AccountType.CHECKING, client4, 100, 0);
		String Iban5 = bank.createAccount(Bank.AccountType.CHECKING, client5, 100, 0);

		Account account1 = services.getAccountByIban(Iban1);
		Account account2 = services.getAccountByIban(Iban2);
		Account account3 = services.getAccountByIban(Iban3);
		Account account4 = services.getAccountByIban(Iban4);
		Account account5 = services.getAccountByIban(Iban5);

		while (controller.isRunning()) {

			input = s.nextLine();

			controller.setUserInput(input);

			controller.updateView();
		}

		s.close();

	}

}
