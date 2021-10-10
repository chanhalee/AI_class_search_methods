package SearchMethods;

import MapTypes.Map;
import MapTypes.Map_EightPuzzle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

public class Search_Beam extends Search{
	private int buffer_size;

	public Search_Beam() {
		super("Beam-Search");
	}

	@Override
	public String search(String start, String target, int map_type) {
		this.buffer_size = 3;

		if (map_type == 1) {
			return search_EightPuzzle(start, target);
		}
		else return "잘못된 맵형식 번호";
	}
	@Override
	public String search(String start, String target, int map_type, int openBufferSize) {
		this.buffer_size = openBufferSize;
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
		Stack<Map> openTemp = new Stack<>();
		Map ultimateAns;
		while(!open.isEmpty()){
			for(Map candidate : open) {
				closed.push(candidate);
				if(candidate.getDiff() == 0)
					break;
				for (Map m : candidate.expand()) {
					if (m != null)
						openTemp.push(m);
				}
			}
			if(closed.peek().getDiff() == 0)
				break;
			openTemp.sort(new Beam_SortComparator());
			open.clear();
			while(open.size() < buffer_size && !openTemp.isEmpty())
				open.push(openTemp.pop());
			open.sort(new Beam_SortComparator());
			openTemp.clear();
		}
		ultimateAns = closed.peek();
		if(ultimateAns instanceof Map_EightPuzzle)
			printTraceEight(closed);
		else
			printTraceEight(closed);
		return "expand: " + (closed.size() - 1) + ", expense: " + ((ultimateAns == null)? "" :ultimateAns.getG()) //closed 엔 최초의 상태가 더해져 비용은 closed 사이즈 -1
				+", open stack size: " + open.size();
	}

	void printTrace(Stack<Map> closed){
		for(Map m : closed){
			m.printMap();
		}
	}
	void printTraceEight(Stack<Map> closed){ //**** caution *** eight puzzle only!!!
		int row;
		int index;
		ArrayList<Map> shortPath = new ArrayList<>(closed.lastElement().getG()+1);

		row = -2;
		System.out.println("\n<" + name + ">"+ "\nclosed trace");
		while (++row < 3) {
			index = -1;
			while (++index < closed.size()) {
				closed.elementAt(index).printRow(row);
			}
			System.out.println();
		}
		index = -1;
		shortPath.addAll(closed);
		while( ++index < closed.size())
			shortPath.set(closed.elementAt(index).getG(), closed.elementAt(index));
		while( --index > closed.lastElement().getG())
			shortPath.remove(index);
		row = -2;
		System.out.println("Path that I've found");
		while (++row < 3) {
			index = -1;
			while (++index < shortPath.size()) {
				shortPath.get(index).printRow(row);
			}
			System.out.println();
		}
	}
}

class Beam_SortComparator implements Comparator<Map>{

	@Override
	public int compare(Map o1, Map o2) {
		return o2.getDiff() - o1.getDiff();
	}
}