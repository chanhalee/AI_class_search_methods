package MapTypes;

import SearchMethods.MonteCarlo.MonteCarloNodeAction;
import Util.SingleEntryContainer;

import java.util.ArrayList;

public class Map_Othello extends MonteCarloSearchMap{
	private static final int BLACK = -1;
	private static final int WHITE = 1;
	int size;
	int[][] map;
	int PlayerInThisTurn = BLACK;

	Map_Othello(int size){
		this.size = size;
		map = new int[size][size];
		cleansMap();
	}

	public Map_Othello playAction(MonteCarloNodeAction<Integer, Integer> action){
		SingleEntryContainer<Integer, Integer> localAction;
		localAction = action.getAction();
		putChip(localAction.getItem1(), localAction.getItem2());
		return this;
	}

	public ArrayList<MonteCarloNodeAction<Integer, Integer>> searchNextAction(){
		ArrayList<MonteCarloNodeAction<Integer, Integer>> result;
		int x;
		int y;

		y = -1;
		while(++y < size){
			x = -1;
			while(++x < size){
				if(map[x][y] == 0){

				}
			}
		}
	}
	private boolean seatAvailable(int x, int y){
		int diagonalTide = 0;
		int verticalTide = 0;
		int HorizontalTide = 0;

		int copy_x;
		int copy_y;
		if(map[x][y] != 0)
			return false;
		copy_x = x;
		copy_y = y;
		while(--copy_x >= 0){
			if(--copy_y >= 0 && map[copy_x][copy_y] != 0){ /////중단점!
				if(map[copy_x][copy_y] != 0){
					if(tide * map[copy_x][copy_y] < 0)
						return true;
					tide += map[copy_x][copy_y];
				}
				else
					break;
			}
			tide = 0;
			while(++copy_y <= size){
				if(map[copy_x][copy_y] != 0){
					if(tide * map[copy_x][copy_y] < 0)
						return true;
					tide += map[copy_x][copy_y];
				}
				else
					break;
			}
		}
		copy_x = x;
		while(++copy_x <= size){
			copy_y = y;
			if(--copy_y >= 0){
				if(map[copy_x][copy_y] != 0){
					if(tide * map[copy_x][copy_y] < 0)
						return true;
					tide += map[copy_x][copy_y];
				}
				else
					break;
			copy_y = y;
			tide = 0;
			if(++copy_y <= size){
				if(map[copy_x][copy_y] != 0){
					if(tide * map[copy_x][copy_y] < 0)
						return true;
					tide += map[copy_x][copy_y];
				}
				else
					break;
			}
		}
		return false;
	}


	void putChip(int x, int y){
		if(map[x][y] == 0)
			map[x][y] = playerMove();
		else
			throw new IllegalArgumentException("잘못된 행마" + x + ", " + y);
	}

	int playerMove(){
		PlayerInThisTurn *= -1;
		return PlayerInThisTurn * -1;
	}

	void cleansMap(){
		int i;
		int j;

		i = -1;
		while(++i < size){
			j = -1;
			while(++j < size){
				map[i][j] = 0;
			}
		}
		PlayerInThisTurn = BLACK;
	}

	@Override
	public void printRow(int col_number) {

	}

	@Override
	public void printMap() {

	}
}
