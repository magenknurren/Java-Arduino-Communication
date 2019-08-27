package tommy.arduino.connect.main;

import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;

import tommy.arduino.connect.servo.Servo;

public class App extends Applet{
	
	private static final long serialVersionUID = -1241022805899597942L;
	
	static SerialPort port;

	public void init() {
    	
		final Servo arduinoServo = initArduinoServo();

		
		Button setToZero = new Button();
		setToZero.setLabel("0 degree");
		setToZero.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(arduinoServo.connected()) {
					try {
						arduinoServo.setServoDegree(0);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		Button setTo90 = new Button();
		setTo90.setLabel("90 degree");
		setTo90.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(arduinoServo.connected()) {
					try {
						arduinoServo.setServoDegree(90);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		Button setTo180 = new Button();
		setTo180.setLabel("180 degree");
		setTo180.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(arduinoServo.connected()) {
					try {
						arduinoServo.setServoDegree(180);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
    	
    	
    	add(setToZero);
    	add(setTo90);
    	add(setTo180);
    	
    }



	private Servo initArduinoServo() {
		SerialPort[] serialPorts = SerialPort.getCommPorts();
		String arduinoPort = "";
		
    	for (SerialPort serialPort : serialPorts) {
    		if("Arduino Uno".equalsIgnoreCase(serialPort.getDescriptivePortName())) {
    			System.out.println( "found Arduino on port: " + serialPort.getSystemPortName());
    			arduinoPort = serialPort.getSystemPortName();
    			port = SerialPort.getCommPort("//dev//" + arduinoPort);
    			return new Servo(port);
    		}
		}
    	return null;
	}
}
