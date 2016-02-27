
package hackforgood.willgo.models;


import java.util.ArrayList;

public class Path {
	int id;
	String name;
	ArrayList<Coordinates> point = new ArrayList<>();

	public Path(int _id, String _name, ArrayList<Coordinates> _point){
		this.id= _id;
		this.name= _name;
		this.point= _point;
	}

	public int getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public ArrayList<Coordinates> getPoint(){
		return this.point;
	}

}

