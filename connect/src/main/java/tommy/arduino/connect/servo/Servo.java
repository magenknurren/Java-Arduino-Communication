package tommy.arduino.connect.servo;

import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;

public class Servo {

	private SerialPort servoPort;

	public Servo(SerialPort servoPort) {
		this.servoPort = servoPort;
	}

	public boolean openPort() {
		servoPort.setComPortParameters(9600, 8, 1, 0);
		servoPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
		return servoPort.openPort();
	}

	public void setServo(int degree) throws IOException {
		String command = "set" + degree;
		if (degree == 0) {
			servoPort.getOutputStream().write("setZero".getBytes());
		} else {
			servoPort.getOutputStream().write(command.getBytes());
		}
		servoPort.getOutputStream().flush();
	}

	public void disconnect() {
		servoPort.closePort();

	}

}
