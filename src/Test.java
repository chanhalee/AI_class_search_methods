import SearchMethods.*;

public class Test {
	public static void main(String[] args) {
		Search_BFS bfs = new Search_BFS();
		Search_Beam beam = new Search_Beam();
		Search_ASTAR aStar = new Search_ASTAR();
		String start = "2 8 3 1 6 4 7 0 5";
		String target = "1 2 3 8 0 4 7 6 5";

		System.out.println(bfs.search(start, target, 1 ));
		System.out.println(beam.search(start, target, 1 , 2));
		System.out.println(aStar.search(start, target, 1 ));

	}
}
