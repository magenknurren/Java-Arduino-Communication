package tommy.arduino.connect.servo;

import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;

public class Servo {

	private SerialPort servoPort;
	private boolean portOpen;

	public Servo(SerialPort servoPort) {
		this.servoPort = servoPort;
		initPort();
	}

	private void initPort() {
		servoPort.setComPortParameters(9600, 8, 1, 0);
		servoPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
		if (servoPort.openPort()) {
			System.out.println("connected!");
			portOpen = true;
		}
		;
	}

	public void setServoDegree(int deg) throws IOException {
		String command = "set" + deg;
		if (deg == 0) {
			servoPort.getOutputStream().write("setZero".getBytes());
		} else {
			servoPort.getOutputStream().write(command.getBytes());
		}
		System.out.println("setting servo to " + deg + " degree...");
		servoPort.getOutputStream().flush();
	}

	public boolean connected() {
		return portOpen;
	}

	public void disconnect() {
		if (servoPort.closePort()) {
			portOpen = false;
		}
	}

}
