
package hackforgood.willgo.models;


public class Coordinates {
	long lng; //Longitud
	long lat; //Latitud
	long date; //Elevation

	public Coordinates(long _lng, long _lat, long _date){
		this.lng= _lng;
		this.lat= _lat;
		this.date= _date;
	}

	public long getLng(){
		return this.lng;
	}
	public long getLat(){
		return this.lat;
	}
	public long getDate(){
		return this.date;
	}

}