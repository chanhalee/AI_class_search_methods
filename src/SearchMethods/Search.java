package SearchMethods;

public abstract class Search {
	public String name;
	Search(String name){
		this.name = name;
	}

	abstract public String search(String start, String target, int map_type);

	public String search(String start, String target, int map_type, int openBufferSize){ return null;};
}