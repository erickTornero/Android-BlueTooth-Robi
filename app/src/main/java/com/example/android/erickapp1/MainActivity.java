package com.example.android.erickapp1;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.bluetooth.BluetoothDevice;
import java.util.UUID;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "tag";
    TextView textMessage;
    Button btnTestBT;
    BluetoothAdapter bluetAdapt;
    ArrayAdapter <String> arrayAdapter;
    ListView listDevicesBT;
    AlertDialog dialogo;
    ConnectThread con;
    ListView hj;
    ArrayAdapter<String> adapterTwo;

    BluetoothDevice deviceBTcon;
    //Variable UUID: Conexión que identifica a la aplicación unica y
    //Generada aleatoriamente por una página web en este caso: http://www.marcnuri.com

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //This is the code write for the programmer
        bluetAdapt = BluetoothAdapter.getDefaultAdapter();

        textMessage = (TextView) findViewById(R.id.textMessage);
        btnTestBT = (Button) findViewById(R.id.btnTestBt);

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    //Metodo: Verifica si esta prendido bluetooth, en caso que no: intenta prenderlo
    public void startBluetooth(View v)
    {

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
                arrayAdapter.add(device.getName().toString()+"\n"+device.getAddress());
            }
        }
    }

    public void scanDevicesBt(View v)
    {

    }
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                arrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    };

    public void example(View v)
    {
        AlertDialog.Builder gdialog = new AlertDialog.Builder(this);
        View vista = this.getLayoutInflater().inflate(R.layout.devices_layout,null);
        hj = (ListView) vista.findViewById(R.id.listDevices);
        adapterTwo = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        hj.setAdapter(adapterTwo);
        Set<BluetoothDevice> pairedDevices = bluetAdapt.getBondedDevices();
        if(pairedDevices.size()>0)
        {
            //OBTIENE TODOS LOS DISPOSITIVOS SINCRONIZADOS
            for(BluetoothDevice device:pairedDevices)
            {
                adapterTwo.add(device.getName().toString()+"\n"+device.getAddress());
            }
        }
        gdialog.setView(vista);
        hj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object var =  hj.getItemAtPosition(position);
                String mac = var.toString();
                textMessage.setText(mac.substring(mac.length()-17));
                //deviceBTcon = bluetAdapt.getRemoteDevice(mac);

                dialogo.dismiss();
            }
        });
        dialogo = gdialog.create();
        dialogo.show();
    }

    public void permiso()
    {
        //IDENTIFICA SI LA VERSION DEL SISTEMA ES MAYOR O IGUAL A MASHMALLOW(version 6)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {

        }
    }
    public void conectar()
    {
        con = new ConnectThread(deviceBTcon);
        con.start();
    }

}