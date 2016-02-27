package hackforgood.willgo.models;

import android.content.Context;
import android.os.AsyncTask;

import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

/**
 * Created by Jorge on 27/2/16.
 * Modify by Jorge.
 */
public class RetrieveData extends AsyncTask<RoadManager, Void, Road> {

    OnFragmentInteractionListener listener;
    Context context;
    public RetrieveData(OnFragmentInteractionListener listener,Context context)
    {
        this.listener=listener;
        this.context=context;
    }
    @Override
    protected Road doInBackground(RoadManager... roads) {
        //Your code
        RoadManager roadManager = new OSRMRoadManager(context);
        ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
        GeoPoint startPoint = new GeoPoint(40.9653275,-5.670977);
        waypoints.add(startPoint);
        GeoPoint endPoint = new GeoPoint(40.9650218,-5.6646976);

        waypoints.add(endPoint);

        Road road = roadManager.getRoad(waypoints);

        return road;


    }

    @Override
    protected void onPostExecute(Road road) {
        listener.onFragmentInteraction(road);
        super.onPostExecute(road);
    }
}




