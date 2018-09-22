package multipleThread;

import javax.swing.*;

import java.awt.Container;
import java.awt.*;
import java.awt.event.*;
import java.io.Console;


public class Counter1 extends JApplet{
	private int count = 0;
	private JButton
	start = new JButton("Start"),
	onOff= new JButton("Toggle");
	
	private JTextField t= new JTextField(10);
	private boolean runFlag=true;
	public void inti() {
		Container cp= getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(t);
		start.addActionListener(new StartL());
		cp.add(start);
		onOff.addActionListener(new OnOffL());
		cp.add(onOff);
	}
	
	public void go() {
		while(true) {
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {
				System.err.println("Interrupted");
			}
			if(runFlag) {
				t.setText(Integer.toString(count++));
			}
		}
	}
	
	class StartL implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			go();
		}
	}
	class OnOffL implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			runFlag=!runFlag;
		}
		
	}
	
}
