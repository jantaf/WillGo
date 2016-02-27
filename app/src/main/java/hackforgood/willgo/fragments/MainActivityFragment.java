package hackforgood.willgo.fragments;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Set;

import hackforgood.willgo.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private BluetoothAdapter myBluetooth = null;
    private Set <BluetoothDevice> pairedDevices;
    public MainActivityFragment() {
    }

    public static MainActivityFragment newInstance() {
        MainActivityFragment fragment = new MainActivityFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_main, container, false);
        Button bChair=(Button)v.findViewById(R.id.buttonSilla);
        final EditText editTextFrom=(EditText)v.findViewById(R.id.editTextFrom);
        final EditText editTextTo=(EditText)v.findViewById(R.id.editTextTo);
        editTextFrom.setText("Espacio I+D+I, Salamanca");
        editTextTo.setText("Plaza Mayor, Salamanca");
        bChair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment, MapFragment.newInstance(editTextFrom.getText().toString(), editTextTo.getText().toString()))
                        .addToBackStack("Main")
                        .commit();
            }
        });
        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        checkBluetooth();


        return v;
    }

    private void checkBluetooth()
    {
        if(myBluetooth == null)
        {
            //Show a mensag. that thedevice has no bluetooth adapter
            Log.d("bluetooth", "Bluetooth Device Not Available");

        }
        else
        {
            if (myBluetooth.isEnabled())
            {
                Log.d("bluetooth","enabled");
                pairedDevicesList();
            }
            else
            {
                //Ask to the user turn the bluetooth on
                Log.d("bluetooth", "available");
                Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnBTon,1);
            }
        }
    }
    private void pairedDevicesList()
    {
        pairedDevices = myBluetooth.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size()>0)
        {
            for(BluetoothDevice bt : pairedDevices)
            {
                list.add(bt.getName() + "\n" + bt.getAddress()); //Get the device's name and the address
                Log.d("device",bt.getName()+" "+bt.getAddress());
            }
        }
        else
        {
            //Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
            Log.d("bluetooth","No Paired Bluetooth Devices Found.");
        }

        //final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        //devicelist.setAdapter(adapter);
        //devicelist.setOnItemClickListener(myListClickListener); //Method called when the device from the list is clicked

    }


}
