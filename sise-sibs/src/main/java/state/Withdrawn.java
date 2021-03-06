package state;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.domain.TransferOperation;

public class Withdrawn implements State {

	@Override
	public void process(TransferOperation wrapper, Services services) throws AccountException {
		services.deposit(wrapper.getTargetIban(), wrapper.getValue());
		if (services.sameBank(wrapper.getSourceIban(), wrapper.getTargetIban())) {
			wrapper.setState(new Completed());
		} else {
			wrapper.setState(new Deposited());
		}

	}

	@Override
	public void cancel(TransferOperation wrapper, Services services) throws AccountException {
		services.deposit(wrapper.getSourceIban(), wrapper.getValue());
		wrapper.setState(new Cancelled());
	}

}
