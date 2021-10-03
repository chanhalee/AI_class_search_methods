import SearchMethods.*;

public class Test {
	public static void main(String[] args) {
		Search_ASTAR bfs = new Search_ASTAR();
		String start = "2 8 3 1 6 4 7 0 5";
		String target = "1 2 3 8 0 4 7 6 5";

		System.out.println(bfs.search(start, target, 1 ));
	}
}
