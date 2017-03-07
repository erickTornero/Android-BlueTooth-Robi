package com.example.android.erickapp1;

/**
 * Created by emprendedor01 on 6/03/2017.
 */
import android.bluetooth.BluetoothSocket;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothAdapter;

import java.io.IOException;
import java.util.UUID;

public class ConnectThread extends Thread
{
    private final BluetoothDevice mmDevice;
    private final BluetoothSocket mmSocket;
    private BluetoothAdapter mBluetoothAdapter;
    private UUID MY_UUID = UUID
            .fromString("00001101-0000-1000-8000-00805F9B34FB");;
    public ConnectThread(BluetoothDevice device)
    {
        BluetoothSocket tmp = null;
        mmDevice = device;

        try
        {
            tmp = device.createRfcommSocketToServiceRecord(MY_UUID);

        }
        catch(IOException e)
        {

        }
        mmSocket=tmp;
    }
    public void run()
    {
        mBluetoothAdapter.cancelDiscovery();
        try
        {
            mmSocket.connect();
        }
        catch(IOException connectException)
        {
            try
            {
                mmSocket.close();
            }
            catch(IOException closeException){}
            return;
        }

    }
    public void cancel()
    {
        try
        {
            mmSocket.close();
        }
        catch(IOException e)
        {

        }
    }

}
