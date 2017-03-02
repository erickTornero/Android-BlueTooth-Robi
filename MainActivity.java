package com.example.android.erickapp1;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.bluetooth.BluetoothDevice;
import android.util.Log;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "tag";
    TextView textMessage;
    Button btnTestBT;
    BluetoothAdapter bluetAdapt;
    ArrayAdapter <String> arrayAdapter;
    ListView listDevicesBT;
    String []lista = {"itemA","itemB","itemC","itemD"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //This is the code write for the programmer
        textMessage = (TextView) findViewById(R.id.textMessage);
        btnTestBT = (Button) findViewById(R.id.btnTestBt);

        listDevicesBT = (ListView) findViewById(R.id.ListBtDevices);

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista);
        listDevicesBT.setAdapter(arrayAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    //Metodo: Verifica si esta prendido bluetooth, en caso que no: intenta prenderlo
    public void startBluetooth(View v)
    {
        bluetAdapt = BluetoothAdapter.getDefaultAdapter();
        if(bluetAdapt ==null)
        {

        }
        if(!bluetAdapt.isEnabled())
        {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent,1); //REQUEST_ENABLE_BT=1
            textMessage.setText("No estaba activo bluetooth");
        }
        else
        {
            textMessage.setText("Bluetooth activo :)");
        }

    }
    //Lectura de dispositivos sincronizados mediante Bluetooth
    public void  bluetoothSinc(View v)
    {
        Set<BluetoothDevice> pairedDevices = bluetAdapt.getBondedDevices();
        if(pairedDevices.size()>0)
        {
            //OBTIENE TODOS LOS DISPOSITIVOS SINCRONIZADOS
            for(BluetoothDevice device:pairedDevices)
            {
                //arrayAdapter.add(device.getName().toString()+""/*"\n"+device.getAddress()*/);
                /*Log.v(TAG, "PairedDevices: " + device.getName() + "  "
                        + device.getAddress());*/
                textMessage.setText(textMessage.getText()+"\n"+device.getName());
            }
        }
    }

    public void scanDevicesBt(View v)
    {

    }
   /* private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                arrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    };
    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);*/
}
