package MapTypes;

import Util.SingleEntryContainer;

// --------------
//
// 최초 세팅되는 target 과 start 상태는 공백을 구분자로 한 각 행열의 요소에 대한 정보가 담긴 문자열을 input 받음.
// ex) "1 2 3 4 5 6 7 8 0"
//	[6][7][8]
//	[5][4][1]
//	[2][3][0]	** 0은 공백.
//
// * expand 시 이전상태로 돌아가는 것을 방지하기 위해 change-type를 저장하여 자신의 change-type의 맞은편(되돌리는 type)를 실행하지 못하게 함.
// 	위 처리를 하지 않을 경우 두 상태를 오가는 무한루프에 빠질 수 있음 (BFS처럼 g를 고려하지 않는 탐색에서)
//
//---------------
public class Map_EightPuzzle extends HeuristicSearchMap{
	private final int[][] map;
	private final int g;
	private final int diff;
	private final int changeType;
	private final Map_EightPuzzle target;

	public Map_EightPuzzle(String data, int g, Map_EightPuzzle target){ // 시작상태 생성시에만 작동하는 생성자
		map = new int[3][3];
		parseData(data);
		this.g = g;	// g는 루트노드와의 거리
		this.target = target;
		this.diff = setDiff(target);
		this.changeType = 0;
	}

	Map_EightPuzzle(int[][] map, int g, Map_EightPuzzle target, int changeType){ // expand시에만 작동하는 생성자
		this.map = map;
		this.g = g;
		this.target = target;
		this.diff = setDiff(target);
		this.changeType = changeType;
	}
	public Map_EightPuzzle(String data, int g){
		map = new int[3][3];
		parseData(data);
		this.g = g;	// g는 루트노드와의 거리
		this.diff = 0;
		target = null;
		this.changeType = 0;
	}

	public int getDiff(){
		return diff;
	}

	public int getDiff(HeuristicSearchMap compareWith){
		return setDiff(compareWith);
	}
	private int setDiff(HeuristicSearchMap target){
		Map_EightPuzzle targetE;
		int i = -1;
		int j;
		int count = 0;

		if(target instanceof Map_EightPuzzle) {
			targetE = (Map_EightPuzzle) target;
			while (++i < 3) {
				j = -1;
				while (++j < 3) {
					if (this.map[i][j] != targetE.whatIsIn(i, j))
						count++;
				}
			}
			return count;
		}
		return  -1;
	}

	public int getG(){
		return this.g;
	}

	public int whatIsIn(int row, int col){
		return map[row][col];
	}

	public Map_EightPuzzle[] expand(){ /// 중단점
		int counter = -1;
		EightPuzzle_Tile vacancy_from = findVacancy();
		EightPuzzle_Tile vacancy_to;
		Map_EightPuzzle[] result = new Map_EightPuzzle[4];

		if(vacancy_from.getItem1() < 2 && changeType != -10) { // change-type 0
			vacancy_to = new EightPuzzle_Tile(vacancy_from.getItem1() + 1, vacancy_from.getItem2());
			result[++counter] = new Map_EightPuzzle(generateSwitchedMap(vacancy_from, vacancy_to), this.g + 1, target, 10);
		}
		if(vacancy_from.getItem2() < 2 && changeType != -1) {
			vacancy_to = new EightPuzzle_Tile(vacancy_from.getItem1(), vacancy_from.getItem2() + 1);
			result[++counter] = new Map_EightPuzzle(generateSwitchedMap(vacancy_from, vacancy_to), this.g + 1, target, 1);
		}
		if(vacancy_from.getItem1() > 0 && changeType != 10) {
			vacancy_to = new EightPuzzle_Tile(vacancy_from.getItem1() -1, vacancy_from.getItem2());
			result[++counter] = new Map_EightPuzzle(generateSwitchedMap(vacancy_from, vacancy_to), this.g + 1, target, -10);
		}
		if(vacancy_from.getItem2() > 0 && changeType != 1) {
			vacancy_to = new EightPuzzle_Tile(vacancy_from.getItem1(), vacancy_from.getItem2() - 1);
			result[++counter] = new Map_EightPuzzle(generateSwitchedMap(vacancy_from, vacancy_to), this.g + 1, target, -1);
		}
		return result;
	}

	private int[][] generateSwitchedMap(EightPuzzle_Tile vacancy_from, EightPuzzle_Tile vacancy_to){
		int[][] result = new int[3][3];
		int i;
		int j;

		i = -1;
		while(++i < 3){
			j = -1;
			while (++j < 3){
				result[i][j] = this.map[i][j];
			}
		}
		switchSeat(result, vacancy_from, vacancy_to);
		return result;
	}

	private boolean switchSeat(int[][] map, EightPuzzle_Tile vacancy_from, EightPuzzle_Tile vacancy_to){
		if (map[vacancy_from.getItem1()][vacancy_from.getItem2()] != 0)
			return false;
		map[vacancy_from.getItem1()][vacancy_from.getItem2()] = map[vacancy_to.getItem1()][vacancy_to.getItem2()];
		map[vacancy_to.getItem1()][vacancy_to.getItem2()] = 0;
		return true;
	}

	private EightPuzzle_Tile findVacancy(){
		int i = -1;
		int j;

		while(++i < 3){
			j = -1;
			while (++j < 3){
				if (map[i][j] == 0)
					return new EightPuzzle_Tile(i, j);
			}
		}
		return new EightPuzzle_Tile(-1, -1);
	}

	private void parseData(String data){
		int i = -1;
		int j;

		String[] data_arr = data.trim().split(" ");
		while(++i < 3){
			j = -1;
			while (++j < 3){
				map[i][j] = Integer.parseInt(data_arr[3*i+j]);
			}
		}
	}

	public void printRow(int row_number){
		if(row_number < -1 || row_number > 2){
			System.out.println("잘못된 <EightPuzzle.printCol> 명령 (" + row_number + ")");
			return;
		}
		if (row_number == -1){
			System.out.print("g:" + this.g + " dif:" + this.diff + "    ");
			return;
		}
		for(int i : map[row_number])
			System.out.print("[" + (i == 0 ? " " : i) + "]");
		if(row_number == 1 && diff != 0)
			System.out.print(" -> ");
		else
			System.out.print("    ");
	}
	public void printMap(){
		int col_number = -1;

		System.out.println("g:" + this.g + "| dif:" + this.diff);
		while(++col_number < 3) {
			for (int i : map[col_number]) {
				System.out.print("[" + (i == 0 ? " " : i) + "]");
			}
			System.out.println();
		}
		System.out.println();
	}
}

class EightPuzzle_Tile extends SingleEntryContainer<Integer, Integer> {

	EightPuzzle_Tile(Integer item1, Integer item2) {
		super(item1, item2);
		try{
			if(item1 > 2 || item1 < 0 || item2 > 2 || item2 < 0)
				throw new EightPuzzle_TileNumberFormatException("맵생성 오류");
		} catch (EightPuzzle_TileNumberFormatException ne){
			ne.printStackTrace();
			System.out.println(ne.getMessage() + "(" + item1 +", "+ item2 + ")");
		}
	}
}

class EightPuzzle_TileNumberFormatException extends NumberFormatException{
	EightPuzzle_TileNumberFormatException(String msg) {
		super(msg);
	}
	EightPuzzle_TileNumberFormatException() {
		super("Maps.EightPuzzle_TileNumberFormatException");
	}

}