package hackforgood.willgo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.Polyline;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

import hackforgood.willgo.R;
import hackforgood.willgo.models.OnFragmentInteractionListener;
import hackforgood.willgo.models.RetrieveData;


public class MapFragment extends Fragment implements OnFragmentInteractionListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;
    MapView map;

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        map = (MapView) v.findViewById(R.id.map);
        map.setBuiltInZoomControls(true);
        IMapController mapController = map.getController();
        mapController.setZoom(19);
        map.setTileSource(TileSourceFactory.MAPNIK);
        String[] coord = mParam1.split(",");
        GeoPoint startPoint = new GeoPoint(40.9653275, -5.670977);
        addOptionsClick(getContext());

        map.setMultiTouchControls(true);
        mapController.setCenter(startPoint);


        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new RetrieveData(this,getContext()).execute();


    }

    // TODO: Rename method, update argument and hook method into UI event
   /* public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    @Override
    public void onFragmentInteraction(Road road) {
        Polyline roadOverlay = RoadManager.buildRoadOverlay(road, getContext());
        map.getOverlays().add(roadOverlay);
        map.invalidate();
    }


    public OnItemGestureListener<OverlayItem> createListenerMap() {
        OnItemGestureListener<OverlayItem> myOnItemGestureListener = new OnItemGestureListener<OverlayItem>() {

            @Override
            public boolean onItemLongPress(int arg0, OverlayItem arg1) {
                // TODO Auto-generated method stub
                Log.d("Longclick", "Longclick");
                return false;
            }

            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                Log.d(item.getTitle(), item.getSnippet());

                return true;
            }

        };
        return myOnItemGestureListener;
    }

    public void addOptionsClick(Context ctx)
    //public void addMarkers(MapView map)
    {

        ArrayList<OverlayItem> anotherOverlayItemArray;
        anotherOverlayItemArray = new ArrayList<OverlayItem>();

        anotherOverlayItemArray.add(new OverlayItem("Espacio I+D", "Salamanca", new GeoPoint(40.9653275, -5.670977)));
        anotherOverlayItemArray.add(new OverlayItem("Plaza Mayor", "Salamanca", new GeoPoint(40.9650218, -5.6646976)));
        //code for new marker custom with image
        /*Drawable newMarker = this.getResources().getDrawable(R.drawable.muletas);
        OverlayItem itm=new OverlayItem("Plaza Mayor","Salamanca",new GeoPoint(40.9650218,-5.6646976));
        itm.setMarker(newMarker);
        anotherOverlayItemArray.add(itm);*/

        ItemizedOverlayWithFocus<OverlayItem> anotherItemizedIconOverlay = new ItemizedOverlayWithFocus<OverlayItem>(ctx, anotherOverlayItemArray, createListenerMap());
        map.getOverlays().add(anotherItemizedIconOverlay);


    }


}


