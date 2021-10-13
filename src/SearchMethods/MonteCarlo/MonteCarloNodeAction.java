package SearchMethods.MonteCarlo;

import Util.SingleEntryContainer;

public class MonteCarloNodeAction<T, S> {
	SingleEntryContainer<T, S> action;

	public MonteCarloNodeAction(T x, S y) {
		action = new SingleEntryContainer<T, S>(x, y);
	}

	public SingleEntryContainer<T, S> getAction(){
		return action;
	}


}
