package org.wildstang.hardware.roborio.outputs;

import org.wildstang.framework.io.outputs.I2COutput;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

/**
 * Controls an I2C output.
 */
public class WsI2COutput extends I2COutput {

    private I2C i2c;
    private MessageHandler messageSender;

    /**
     * Constructs the I2C output from config.
     * @param name Descriptive name of the I2C output.
     * @param port I2C hardware port number.
     * @param p_address I2C address, normally static by device.
     */
    public WsI2COutput(String name, Port port, int p_address) {
        super(name);

        i2c = new I2C(port, p_address);

        // Fire up the message sender thread.
        messageSender = new MessageHandler(i2c);
        Thread t = new Thread(messageSender);
        // This is safe because there is only one instance of the subsystem in the subsystem container.
        t.start();
    }

    /**
     * Sets I2C output state to current value.
     */
    @Override
    protected void sendDataToOutput() {
        synchronized (messageSender) {
            byte[] data = getValue();
            if (data != null) {
                messageSender.setSendData(data, data.length);
                messageSender.notify();
            }
        }

        // Reset so we only send when we have new data
        setValue(null);
    }

    /**
     * Handles I2C messages.
     * Note: Offload to a thread avoid blocking main thread with I2C sends.
     */
    private static class MessageHandler implements Runnable {

        static byte[] rcvBytes;
        byte[] sendData;
        int sendSize = 0;
        boolean running = true;
        boolean dataToSend = false;
        private I2C i2c;

        /**
         * Message handler requires a WPILib I2C instance from WsI2COutput.
         * @param p_i2c I2C instance to send messages.
         */
        public MessageHandler(I2C p_i2c) {
            i2c = p_i2c;
        }

        /**
         * Run function for runnable handles I2C transactions.
         */
        @Override
        public void run() {
            while (running) {
                synchronized (this) {
                    try {
                        // blocking sleep until someone calls notify.
                        this.wait();
                        // Need at least a byte and someone has to have called setSendData.
                        if (sendSize >= 0 && dataToSend) {
                            // set receive size to 0 to avoid sending an i2c read request.
                            i2c.transaction(sendData, sendSize, rcvBytes, 0);
                            dataToSend = false;
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }
        }

        /**
         * Sets the next data to send and flags that there is new data.
         * @param data Byte array of data to send.
         * @param size Size of the Byte array in bytes.
         */
        public void setSendData(byte[] data, int size) {
            sendData = data;
            sendSize = size;
            dataToSend = true;
        }
    }
}
