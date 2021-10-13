package SearchMethods.MonteCarlo;

import Util.SingleEntryContainer;

class MonteCarloNode extends SingleEntryContainer<int[], MonteCarloNodeAction> {
	MonteCarloNode(int[] data, MonteCarloNodeAction move) {
		super(data, move);
	}
}
