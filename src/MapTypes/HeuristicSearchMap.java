package MapTypes;

public abstract class HeuristicSearchMap extends Map {
	abstract public int getDiff();
	abstract public int getDiff(HeuristicSearchMap target);
	abstract public int getG();
	abstract public HeuristicSearchMap[] expand();
}
