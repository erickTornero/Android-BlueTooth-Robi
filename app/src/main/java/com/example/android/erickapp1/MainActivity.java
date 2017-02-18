package com.example.android.erickapp1;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.bluetooth.BluetoothDevice;
public class MainActivity extends AppCompatActivity {
    TextView textMessage;
    Button btnTestBT;
    BluetoothAdapter bluetAdapt;
    ArrayAdapter <BluetoothDevice> arraySincrBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //This is the code write for the programmer
        textMessage = (TextView) findViewById(R.id.textMessage);
        btnTestBT = (Button) findViewById(R.id.btnTestBt);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

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
    public void  bluetoothSinc(View v)
    {

    }

}
