package MapTypes;

public abstract class Map {
	abstract public int getDiff();
	abstract public int getDiff(Map target);
	abstract public int getG();
	abstract public Map[] expand();
	abstract public void printCol(int col_number);
	abstract public void printMap();
}
