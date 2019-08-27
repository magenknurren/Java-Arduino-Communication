package tommy.arduino.connect.main;

import java.applet.Applet;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;

import tommy.arduino.connect.servo.Servo;

public class App extends Applet {

	private static final long serialVersionUID = -1241022805899597942L;

	static SerialPort port;

	Button setToZero;
	Button setTo90;
	Button setTo180;
	private TextField textArea;

	private TextField stateArea;

	private Servo arduinoServo;

	public void init() {

		createUI();

		arduinoServo = initArduinoServo();

		if (arduinoServo != null && arduinoServo.openPort()) {
			stateArea.setText("connected");
			setButtonsActive(true);
		} else {
			stateArea.setText("could not connect!");
			setButtonsActive(false);
		}

	}

	private void createUI() {
		textArea = new TextField();
		stateArea = new TextField();
		setToZero = new Button();
		setToZero.setLabel("0 degree");
		setToZero.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					arduinoServo.setServo(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		setTo90 = new Button();
		setTo90.setLabel("90 degree");
		setTo90.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					arduinoServo.setServo(90);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		setTo180 = new Button();
		setTo180.setLabel("180 degree");
		setTo180.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					arduinoServo.setServo(180);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		add(setToZero);
		add(setTo90);
		add(setTo180);
		add(textArea);
		add(stateArea);
	}

	private void setButtonsActive(boolean b) {
		setToZero.setEnabled(b);
		setTo90.setEnabled(b);
		setTo180.setEnabled(b);
	}

	private Servo initArduinoServo() {
		SerialPort[] serialPorts = SerialPort.getCommPorts();
		String arduinoPort = "";

		for (SerialPort serialPort : serialPorts) {
			if ("Arduino Uno".equalsIgnoreCase(serialPort.getDescriptivePortName())) {
				arduinoPort = serialPort.getSystemPortName();
				port = SerialPort.getCommPort("//dev//" + arduinoPort);
				return new Servo(port);
			}
		}
		return null;
	}
}
