import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MLFQFrame extends JFrame implements ActionListener {
	private JPanel mainPanel = new JPanel(null);
	private JPanel tablePanel = new JPanel();
	static JPanel ganttPanel = new JPanel(null);
	private JPanel algoPanel = new JPanel(null);
	private JPanel infoPanel = new JPanel(null);

	JButton startButton = new JButton("START");
	JButton stopButton = new JButton("RESET");
	JButton exitButton = new JButton("EXIT");

	JLabel q1Label1 = new JLabel("Q1", SwingConstants.CENTER);
	JLabel q2Label1 = new JLabel("Q2", SwingConstants.CENTER);
	JLabel q3Label1 = new JLabel("Q3", SwingConstants.CENTER);

	static JPanel qPanel[] = new JPanel[3];
	JTextField quanTextField[] = new JTextField[3];

	private String[] rowLabelString = { "ID", "ARRIVAL", "BURST", "PRIORITY" };
	private String[] colLabelString = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" };

	int tableRow = 21;
	int tableColumn = 4;
	int[] processID;
	int[] arrivalTime;
	int[] burstTime;
	int[] priority;
	int queues[];
	int quantum[];

	JPanel processNumPanel = new JPanel();
	JLabel processNumLabel = new JLabel("Number of Processes: ");
	JTextField processNumField = new JTextField("");
	JButton enterButton = new JButton("ENTER");
	JButton clearButton = new JButton("CLEAR");

	JLabel queueNumLabel = new JLabel("Number of Queues: ");
	JTextField queueNumField = new JTextField("");
	JButton queueEnterButton = new JButton("ENTER");
	JButton queueClearButton = new JButton("CLEAR");

	JTextField[][] panelHolder = new JTextField[tableRow][tableColumn];
	JLabel[][] label = new JLabel[tableRow][tableColumn];

	String[] algorithmString = {"(FCFS) First Come First Serve", "(SJF) Shortest Job First", "(SRTF) Shortest Remaining Time First", "(NPP) Non-preemptive Priority", "(PP) Preemptive Priority","(RR) Round Robin" };
	JComboBox algorithmDropbox[] = new JComboBox[3];

	int numOfProcesses = 0, numOfQueues = 0;

	static JLabel[] processLabel;

	public MLFQFrame() {
		add(mainPanel);
		mainPanel.setBackground(new Color(220, 220, 220));
		createTable();
		createGantt();
		createAlgo();
		createInfo();
	}

	public void createTable() {
		processNumPanel.setBounds(20, 10, 370, 30);
		mainPanel.add(processNumPanel);

		processNumPanel.setLayout(new BoxLayout(processNumPanel, BoxLayout.LINE_AXIS));
		processNumPanel.add(processNumLabel);
		processNumPanel.add(processNumField);
		processNumPanel.add(enterButton);
		processNumPanel.add(clearButton);

		processNumField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e){
				char c = e.getKeyChar();
				if(((c < '0')||(c > '9')) && (c != KeyEvent.VK_BACK_SPACE)){
					e.consume();
				}
			}
		});
		enterButton.addActionListener(this);
		clearButton.addActionListener(this);

		tablePanel.setBounds(20, 40, 370, 720);
		mainPanel.add(tablePanel);

		tablePanel.setLayout(new GridLayout(tableRow, tableColumn, 3, 3));
		int rowCtr = 0;
		int columnCtr = 0;

		for (int m = 0; m < tableRow; m++) {
			for (int n = 0; n < tableColumn; n++) {
				if (m == 0) {
					label[m][n] = new JLabel(rowLabelString[rowCtr], SwingConstants.CENTER);
					label[m][n].setOpaque(true);
					label[m][n].setBackground(new Color(180, 180, 180));
					tablePanel.add(label[m][n]);
					rowCtr++;
				} else if (n == 0) {
					label[m][n] = new JLabel(colLabelString[columnCtr], SwingConstants.CENTER);
					label[m][n].setOpaque(true);
					label[m][n].setBackground(new Color(180, 180, 180));
					tablePanel.add(label[m][n]);
					columnCtr++;
				} else {
					panelHolder[m][n] = new JTextField();
					panelHolder[m][n].setEnabled(false);
					panelHolder[m][n].addKeyListener(new KeyAdapter() {
						public void keyTyped(KeyEvent e){
							char c = e.getKeyChar();
							if(((c < '0')||(c > '9')) && (c != KeyEvent.VK_BACK_SPACE)){
								e.consume();
							}
						}
					});
					tablePanel.add(panelHolder[m][n]);
				}
			}
		}
	}

	public void createGantt() {
		ganttPanel.setBounds(400, 20, 940, 390);
		ganttPanel.setBackground(new Color(200, 200, 200));
		mainPanel.add(ganttPanel);

		for (int i = 0; i < algorithmDropbox.length; i++) {
			int ctr = i;
			algorithmDropbox[i] = new JComboBox(algorithmString);
			quanTextField[i] = new JTextField();
			quanTextField[i].setText("Quantum");

			quanTextField[i].addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e){
					char c = e.getKeyChar();
					if(((c < '0')||(c > '9')) && (c != KeyEvent.VK_BACK_SPACE)){
						e.consume();
					}
				}
			});

			quanTextField[i].addFocusListener(new FocusListener() {
				private boolean showingPlaceholder = true;
				int i = ctr;

				public void focusGained(FocusEvent e) {
					if (showingPlaceholder) {
						showingPlaceholder = false;
						quanTextField[i].setText("");
					}
				}

				public void focusLost(FocusEvent arg0) {
					if (quanTextField[i].getText().isEmpty()) {
						quanTextField[i].setText("Quantum");
						showingPlaceholder = true;
					}
				}
			});

			qPanel[i] = new JPanel();
		}

		JLabel q1Label = new JLabel("Q1", SwingConstants.CENTER);
		JLabel q2Label = new JLabel("Q2", SwingConstants.CENTER);
		JLabel q3Label = new JLabel("Q3", SwingConstants.CENTER);

		ganttPanel.add(q1Label);
		q1Label.setOpaque(true);
		q1Label.setBackground(new Color(90, 90, 90));
		q1Label.setBounds(0, 0, 30, 128);

		ganttPanel.add(q2Label);
		q2Label.setOpaque(true);
		q2Label.setBackground(new Color(90, 90, 90));
		q2Label.setBounds(0, 130, 30, 128);

		ganttPanel.add(q3Label);
		q3Label.setOpaque(true);
		q3Label.setBackground(new Color(90, 90, 90));
		q3Label.setBounds(0, 260, 30, 128);

		qPanel[0].setBackground(Color.DARK_GRAY);
		qPanel[0].setBounds(30, 0, 2000, 128);
		ganttPanel.add(qPanel[0]);

		qPanel[1].setBackground(Color.DARK_GRAY);
		qPanel[1].setBounds(30, 130, 2000, 128);
		ganttPanel.add(qPanel[1]);

		qPanel[2].setBackground(Color.DARK_GRAY);
		qPanel[2].setBounds(30, 260, 2000, 128);
		ganttPanel.add(qPanel[2]);

		JScrollPane pane1 = new JScrollPane(qPanel[0]);
		ganttPanel.add(pane1);

		JScrollPane pane2 = new JScrollPane(qPanel[1]);
		ganttPanel.add(pane2);

		JScrollPane pane3 = new JScrollPane(qPanel[2]);
		ganttPanel.add(pane3);

		queueEnterButton.addActionListener(this);
		queueClearButton.addActionListener(this);
	}

	public void createAlgo() {
		algoPanel.setBounds(400, 423, 405, 310);
		algoPanel.setBackground(new Color(200, 200, 200));
		mainPanel.add(algoPanel);

		queueNumLabel.setBounds(15,0,130,30);
		queueNumField.setBounds(125,0,85,30);
		queueEnterButton.setBounds(215,0,90,30);
		queueClearButton.setBounds(305,0,90,30);

		queueNumField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e){
				char c = e.getKeyChar();
				if(((c < '0')||(c > '9')) && (c != KeyEvent.VK_BACK_SPACE)){
					e.consume();
				}
			}
		});

		algoPanel.add(queueNumLabel);
		algoPanel.add(queueNumField);
		algoPanel.add(queueEnterButton);
		algoPanel.add(queueClearButton);

		q1Label1.setBounds(0, 30, 405, 25);
		q1Label1.setOpaque(true);
		q1Label1.setBackground(new Color(90, 90, 90));
		algoPanel.add(q1Label1);

		algoPanel.add(algorithmDropbox[0]);
		algorithmDropbox[0].setBounds(10, 60, 200, 25);
		algorithmDropbox[0].setEnabled(false);

		algoPanel.add(quanTextField[0]);
		quanTextField[0].setBounds(300, 60, 100, 25);
		quanTextField[0].setEnabled(false);

		q2Label1.setBounds(0, 110, 405, 25);
		q2Label1.setOpaque(true);
		q2Label1.setBackground(new Color(90, 90, 90));
		algoPanel.add(q2Label1);

		algoPanel.add(algorithmDropbox[1]);
		algorithmDropbox[1].setBounds(10, 140, 200, 25);
		algorithmDropbox[1].setEnabled(false);

		algoPanel.add(quanTextField[1]);
		quanTextField[1].setBounds(300, 140, 100, 25);
		quanTextField[1].setEnabled(false);

		q3Label1.setBounds(0, 190, 405, 25);
		q3Label1.setOpaque(true);
		q3Label1.setBackground(new Color(90, 90, 90));
		algoPanel.add(q3Label1);

		algoPanel.add(algorithmDropbox[2]);
		algorithmDropbox[2].setBounds(10, 220, 200, 25);
		algorithmDropbox[2].setEnabled(false);

		algoPanel.add(quanTextField[2]);
		quanTextField[2].setBounds(300, 220, 100, 25);
		quanTextField[2].setEnabled(false);

		algoPanel.add(startButton);
		startButton.setBounds(10, 260, 120, 40);

		algoPanel.add(stopButton);
		stopButton.setBounds(140, 260, 120, 40);

		algoPanel.add(exitButton);
		exitButton.setBounds(270, 260, 120, 40);

		startButton.addActionListener(this);
		exitButton.addActionListener(this);
		stopButton.addActionListener(this);
	}

	public static void addComponent(JPanel panel, JComponent component, int x1, int y1, int x2, int y2){
		component.setBounds(x1, y1, x2, y2);
		panel.add(component);
		panel.repaint();
		panel.revalidate();
	}

	public void createInfo() {
		infoPanel.setBounds(815, 423, 523, 310);
		infoPanel.setBackground(Color.DARK_GRAY);
		mainPanel.add(infoPanel);
	}

	public void enterQueue(int num){
		for (int m = 0; m < num; m++) {
			algorithmDropbox[m].setEnabled(true);
			quanTextField[m].setEnabled(true);
		}
	}

	public void resetQueue(){
		for(int m = 0; m < 3; m++){
			algorithmDropbox[m].setSelectedIndex(0);
			quanTextField[m].setText("");
			algorithmDropbox[m].setEnabled(false);
			quanTextField[m].setEnabled(false);
		}
		queueNumField.setText("");
		queueNumField.setEnabled(true);
	}

	public void enterProcess(int num){
		for (int m = 1; m <= num; m++) {
			for (int n = 1; n < tableColumn; n++) {
				panelHolder[m][n].setEnabled(true);
			}
		}
	}

	public void resetTable(){
		for (int i = 1; i < tableRow; i++) {
			for (int j = 1; j < tableColumn; j++) {
				panelHolder[i][j].setText("");
				panelHolder[i][j].setEnabled(false);
			}
		}
		processNumField.setText("");
		processNumField.setEnabled(true);
	}

	public void getInfo(){
		processID = new int[numOfProcesses];
		arrivalTime = new int[numOfProcesses];
		burstTime = new int[numOfProcesses];
		priority = new int[numOfProcesses];
		queues = new int[numOfQueues];
		quantum = new int[numOfQueues];

		int number;
		for(int m = 1; m <= numOfProcesses; m++){
			for(int n = 1; n < tableColumn; n++){
				processID[m-1] = m;
				try{
					number = Integer.parseInt(panelHolder[m][n].getText());
				}catch(NumberFormatException e){
					number = 0;
				}
				if(n == 1){
					arrivalTime[m-1] = number;
				}else if (n == 2){
					burstTime[m-1] = number;
				}else{
					priority[m-1] = number;
				}
			}
		}

		for(int i = 0; i < numOfQueues; i++){
			queues[i] = algorithmDropbox[i].getSelectedIndex();
			try{
				quantum[i] = Integer.parseInt(quanTextField[i].getText());
			}catch(NumberFormatException e){
				quantum[i] = 0;
			}
		}

		new FCFS(processID, arrivalTime, burstTime);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == startButton) {
			getInfo();
		}

		if (event.getSource() == exitButton) {
			System.exit(0);
		}

		if(event.getSource() == stopButton){
			resetTable();
			resetQueue();
		}

		if(event.getSource() == enterButton){
			try{
				numOfProcesses = Integer.parseInt(processNumField.getText());
				processNumField.setEnabled(false);
				enterProcess(numOfProcesses);
			}catch(NumberFormatException e){
				numOfProcesses = 0;
			}
		}

		if(event.getSource() == clearButton){
			resetTable();
		}

		if(event.getSource() == queueEnterButton){
			try{
				numOfQueues = Integer.parseInt(queueNumField.getText());
				queueNumField.setEnabled(false);
				enterQueue(numOfQueues);
			}catch(NumberFormatException e){
				numOfQueues = 0;
			}
		}

		if(event.getSource() == queueClearButton){
			resetQueue();
		}
	}
}
