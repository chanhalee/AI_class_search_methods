package SearchMethods;

import MapTypes.Map;
import MapTypes.Map_EightPuzzle;

import java.util.Comparator;
import java.util.Stack;

public class Search_ASTAR extends Search{

	public Search_ASTAR() {
		super("Best-First-Search.Search");
	}



	@Override
	public String search(String start, String target, int map_type) {
		if (map_type == 1) {
			return search_EightPuzzle(start, target);
		}
		else return "잘못된 맵형식 번호";
	}
	String search_EightPuzzle(String start_s, String target_s){
		Stack<Map> open;
		Stack<Map> closed;
		Map_EightPuzzle target;

		open = new Stack<>();
		closed = new Stack<>();
		target = new Map_EightPuzzle(target_s, Integer.MAX_VALUE);
		open.push(new Map_EightPuzzle(start_s, 0, target));
		return search_process(open, closed);
	}
	String search_process(Stack<Map> open, Stack<Map> closed){
		Map candidate = null;

		while(!open.isEmpty()){
			candidate = open.pop();
			for(Map m : candidate.expand()) {
				if (m != null)
					open.push(m);
			}
			closed.push(candidate);
			if(candidate.getDiff() == 0)
				break;
			open.sort(new ASTAR_SortComparator());
		}
		printTrace(closed);
		return "expand: " + closed.size() + ", expense: " + ((candidate == null)? "" :candidate.getG())
				+", open stack size: " + open.size();
	}

	void printTrace(Stack<Map> closed){
		for(Map m : closed){
			m.printMap();
		}
	}
}

class ASTAR_SortComparator implements Comparator<Map>{

	@Override
	public int compare(Map o1, Map o2) {
		if(o1 == null && o2 == null)
			return 0;
		if(o1 == null)
			return - (o2.getDiff() + o2.getG());
		if(o2 == null)
			return (o1.getDiff() + o1.getG());
		return (o2.getDiff() + o2.getG()) - (o1.getDiff() + o1.getG());
	}
}