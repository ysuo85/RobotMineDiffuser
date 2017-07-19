package eldp.robotminediffuser.services

import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothClass
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class RobotMessagingService : Service() {

    companion object {
        @JvmStatic val BT_DEVICE : String = "bt_device"
        @JvmStatic val SPP_UUID : String = "00001101-0000-1000-8000-00805F9B34FB"
        @JvmStatic var mState : RobotMessagingStates = RobotMessagingStates.StateNone
        @JvmStatic var DeviceName : String = ""
        @JvmStatic var device : BluetoothClass.Device? = null
    }

    val mBluetoothAdapter : BluetoothAdapter? = null
    private var mConnectThread : ConnectThread? = null
    private var mConnectedThread : ConnectedThread? = null
    private var mBinder: IBinder? = null

    override fun onCreate() {
        Log.d("RobotMessagingService", "Service started")
        super.onCreate()
    }

    override fun onBind(intent: Intent): IBinder? {
        return mBinder
    }

    enum class RobotMessagingStates {
        StateNone,
        StateListen,
        StateConnecting,
        StateConnected
    }

    @Synchronized private fun connected(mmDevice: BluetoothDevice, mmSocket: BluetoothSocket){
        if(mConnectThread != null){
            mConnectThread!!.cancel()
            mConnectThread = null
        }

        if(mConnectedThread != null){
 ///           mConnectedThread.cancel()
            mConnectedThread = null
        }

        mConnectedThread = ConnectedThread(mmSocket)
        mConnectedThread!!.start()
        mState = RobotMessagingStates.StateConnected
    }

    private class ConnectThread(device: BluetoothDevice, adapter : BluetoothAdapter) : Thread() {
        private var mmSocket : BluetoothSocket? = null
        private var mmDevice : BluetoothDevice? = null
        private var mmBluetoothAdapter : BluetoothAdapter? = null

        init {
            this.mmBluetoothAdapter = adapter
            this.mmDevice = device
            var tmp : BluetoothSocket? = null
            try{
                tmp = device.createRfcommSocketToServiceRecord(UUID.fromString(SPP_UUID))
            }
            catch (e : IOException) {
                e.printStackTrace()
            }
            mmSocket = tmp
        }

        override fun run(){
            name = "ConnectThread"
            mmBluetoothAdapter!!.cancelDiscovery()
            try{
                mmSocket?.connect()
            }
            catch(e : IOException){
                try{
                    mmSocket?.close()
                }
                catch(e1 : IOException){
                    e1.printStackTrace()
                }
  //              connectionFailed()
                return
            }
        }

        fun cancel(){
            try{
                mmSocket?.close()
            }
            catch(e : IOException)
            {
                Log.e("RobotMessagingService", "close() of connect socket failed")
            }
        }
    }

    private class ConnectedThread(socket : BluetoothSocket) : Thread() {
        private var mmSocket : BluetoothSocket? = null
        private var mmInStream : InputStream? = null
        private var mmOutStream : OutputStream? = null

        init {
            mmSocket = socket
            var tmpIn: InputStream? = null
            var tmpOut: OutputStream? = null
            try {
                tmpIn = socket.inputStream
                tmpOut = socket.outputStream
            } catch(e: IOException) {
                Log.e("RobotMessagingService", "temp socket not created", e)
            }
        }

        fun cancel(){
            try{
                mmSocket?.close()
            }
            catch(e : IOException)
            {
                Log.e("BluetoothConnection", "close() of connected socket failed")
            }
        }
    }
}
