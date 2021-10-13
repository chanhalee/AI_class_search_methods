package SearchMethods.MonteCarlo;

import MapTypes.MonteCarloSearchMap;
import SearchMethods.Search;
import Util.SingleEntryContainer;

import java.util.ArrayList;

public class Search_MonteCarlo extends Search {

	Search_MonteCarlo(String name) {
		super(name);
	}

	@Override
	public String search(String start, String target, int map_type) {

		return null;
	}
	private ArrayList<MonteCarloNodeAction> searchNextAct(MonteCarloSearchMap){
		ArrayList<MonteCarloNodeAction> result = new ArrayList<>();

	}

	private int rollout() {
		return 0;
	}
}

