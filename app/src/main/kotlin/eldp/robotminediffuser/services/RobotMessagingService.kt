package eldp.robotminediffuser.services

import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.*
import android.util.Log
import android.widget.Toast
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import eldp.robotminediffuser.data.ArduinoMessage
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class RobotMessagingService : Service() {
    private val TAG = "ROBOT_MESSAGING_SERVICE"
    private val ARDUINO_BT_NAME = "Makeblock"
    private var mHandler: Handler ?= null // handler that gets info from Bluetooth service
    private val mMessenger: Messenger
    private val mBinder : IBinder
    private var mRobotConnection : ConnectedThread ?= null
    private val mBluetoothAdapter : BluetoothAdapter
    private val mapper = jacksonObjectMapper()

    private val mReceiver : BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context : Context, intent : Intent){
            val action = intent.action
            if(BluetoothDevice.ACTION_FOUND == action){
                val device : BluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                Log.d(TAG, "FOUND BLUETOOTH DEVICE: ${device.name}")
                if(device.name.equals(ARDUINO_BT_NAME)){
                    Log.d(TAG, "BLUETOOTH DEVICE FOUND")
                    ConnectThread(device).start()
                }
            }
        }
    }

    init {
        mMessenger = Messenger(IncomingHandler())
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        mBinder = LocalBinder()
    }

    override fun onCreate() {
        super.onCreate()
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        mHandler = Handler()
        registerReceiver(mReceiver, filter)
        Log.d(TAG, "ROBOTMESSAGINGSERVICE CREATED")

        if(mBluetoothAdapter == null){
            Toast.makeText(applicationContext, "Bluetooth unsupported", Toast.LENGTH_SHORT).show()
        }
        else{
            if(!mBluetoothAdapter.isEnabled){
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivity(enableBtIntent)
            }

            // Attempt to obtain a list of all presently paired devices
            val pairedDevices = mBluetoothAdapter.bondedDevices
            var deviceFound = false
            if(pairedDevices.size > 0){
                for(device : BluetoothDevice in pairedDevices){
                    val name = device.name
                    // Find the device with matching name and attempt to connect to it
                    Log.d(TAG, "PAIRED DEVICE: $name")
                    Log.d(TAG, "LOOKING FOR: $ARDUINO_BT_NAME")
                    if(device.name.equals(ARDUINO_BT_NAME)){
                        Log.d(TAG, "BLUETOOTH DEVICE FOUND")
                        Toast.makeText(applicationContext, "Bluetooth device found...", Toast.LENGTH_SHORT).show()
                        ConnectThread(device).start()
                        deviceFound = true
                        break
                    }
                }
            }
            if(!deviceFound){
                mBluetoothAdapter.startDiscovery()
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.d(TAG, "ROBOTMESSAGINGSERVICE STARTED")
        return START_STICKY
    }

    override fun onBind(intent : Intent?): IBinder {
        Toast.makeText(applicationContext, "Binding activity to service...", Toast.LENGTH_SHORT).show()
        return mBinder
    }

    override fun onDestroy() {
        super.onDestroy()
        mRobotConnection!!.cancel()
        unregisterReceiver(mReceiver)
    }

    fun sendCommand(command : ArduinoMessage){
        mRobotConnection?.write(mapper.writeValueAsBytes(command))
    }

    inner class LocalBinder : Binder() {
        fun getService() : RobotMessagingService {
            return this@RobotMessagingService
        }
    }

    // Defines several constants used when transmitting messages between the
    // service and the UI.
    private interface MessageConstants {
        companion object {
            val MESSAGE_READ = 0
            val MESSAGE_WRITE = 1
            val MESSAGE_TOAST = 2
        }
    }

    private inner class IncomingHandler : Handler() {
        override fun handleMessage(msg: Message?) {
            Toast.makeText(applicationContext, msg!!.data.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private inner class ConnectThread(mmDevice: BluetoothDevice) : Thread() {
        private val mmSocket: BluetoothSocket

        init {
            // Use a temporary object that is later assigned to mmSocket
            // because mmSocket is final.
            var tmp: BluetoothSocket? = null

            try {
                // Get a BluetoothSocket to connect with the given BluetoothDevice.
                // MY_UUID is the app's UUID string, also used in the server code.
                tmp = mmDevice.createRfcommSocketToServiceRecord(mmDevice.uuids[0].uuid)
            } catch (e: IOException) {
                Log.e(TAG, "Socket's create() method failed", e)
            }
            mmSocket = tmp!!
        }

        override fun run() {
            // Cancel discovery because it otherwise slows down the connection.
            mBluetoothAdapter.cancelDiscovery()

            try {
                // Connect to the remote device through the socket. This call blocks
                // until it succeeds or throws an exception.
                mmSocket.connect()
                Log.d(TAG, "Bluetooth connection established")
            } catch (connectException: IOException) {
                // Unable to connect; close the socket and return.
                try {
                    mmSocket.close()
                } catch (closeException: IOException) {
                    Log.e(TAG, "Could not close the client socket", closeException)
                }
                return
            }

            // The connection attempt succeeded. Perform work associated with
            // the connection in a separate thread.
            mRobotConnection = ConnectedThread(mmSocket)
            mRobotConnection!!.start()
        }

        // Closes the client socket and causes the thread to finish.
        fun cancel() {
            try {
                mmSocket.close()
            } catch (e: IOException) {
                Log.e(TAG, "Could not close the client socket", e)
            }
        }
    }

    private inner class ConnectedThread(private val mmSocket: BluetoothSocket) : Thread() {
        private val mmInStream: InputStream
        private val mmOutStream: OutputStream
        private var mmBuffer: ByteArray? = null // mmBuffer store for the stream

        init {
            var tmpIn: InputStream? = null
            var tmpOut: OutputStream? = null

            // Get the input and output streams; using temp objects because
            // member streams are final.
            try {
                tmpIn = mmSocket.inputStream
                Log.d(TAG,"Read stream established")
            } catch (e: IOException) {
                Log.e(TAG, "Error occurred when creating input stream", e)
            }

            try {
                tmpOut = mmSocket.outputStream
                Log.d(TAG, "Write stream established")
            } catch (e: IOException) {
                Log.e(TAG, "Error occurred when creating output stream", e)
            }

            mmInStream = tmpIn!!
            mmOutStream = tmpOut!!
        }

        override fun run() {
            mmBuffer = ByteArray(1024)
            var numBytes: Int // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs.
            while (true) {
                try {
                    // Read from the InputStream.
                    numBytes = mmInStream.read(mmBuffer)
                    // Send the obtained bytes to the UI activity.
                    val readMsg = mHandler!!.obtainMessage(
                            MessageConstants.MESSAGE_READ, numBytes, -1,
                            mmBuffer)
                    readMsg.sendToTarget()
                } catch (e: IOException) {
                    Log.d(TAG, "Input stream was disconnected", e)
                    break
                }
            }
        }

        // Call this from the main activity to send data to the remote device.
        fun write(bytes: ByteArray) {
            try {
//                var encodedBytes : ByteArray = kotlin.ByteArray(bytes.size + 1)
//                val dataSize : Byte = bytes.size.toByte()
//                Log.d(TAG,"Data size: ${dataSize}")
//                encodedBytes[0] = dataSize
//                for (i in bytes.indices){
//                    encodedBytes[i+1] = bytes[i]
//                }

                mmOutStream.write(bytes)

                // Share the sent message with the UI activity.
                val writtenMsg = mHandler!!.obtainMessage(
                        MessageConstants.MESSAGE_WRITE, -1, -1, mmBuffer)
                writtenMsg.sendToTarget()
            } catch (e: IOException) {
                Log.e(TAG, "Error occurred when sending data", e)

                // Send a failure message back to the activity.
                val writeErrorMsg = mHandler!!.obtainMessage(MessageConstants.MESSAGE_TOAST)
                val bundle = Bundle()
                bundle.putString("toast",
                        "Couldn't send data to the other device")
                writeErrorMsg.data = bundle
                mHandler!!.sendMessage(writeErrorMsg)
            }
        }

        // Call this method from the main activity to shut down the connection.
        fun cancel() {
            try {
                mmSocket.close()
            } catch (e: IOException) {
                Log.e(TAG, "Could not close the connect socket", e)
            }
        }
    }
}
