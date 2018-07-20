import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class CPUFrame extends JFrame {

  JFrame frame = this;
  private static JPanel mainPanel = new JPanel(null);
  static JPanel ganttPanel = new JPanel(null);
  JPanel tablePanel = new JPanel(null);
  JPanel actionPanel = new JPanel(null);
  JPanel queuePanel = new JPanel();
  JPanel historyPanel = new JPanel();

  JButton startButton = new JButton("START");
  JButton stopButton = new JButton("STOP");
  JButton restartButton = new JButton("RESTART");
  JButton resetButton = new JButton("RESET");
  JButton exitButton = new JButton("EXIT");


  JLabel algorithmLabel = new JLabel("SCHEDULING ALGORITHM: ");
  JLabel quantumLabel = new JLabel("QUANTUM: ");

  JTextField quantumTextField = new JTextField("0");

  JTable table;
  static JLabel[] processLabel;
  Handler handler = new Handler();

  String[] algorithmString = {"(FCFS) First Come First Serve", "(SJF) Shortest Job First", "(SRTF) Shortest Remaining Time First", "(NPP) Non-preemptive Priority", "(PP) Preemptive Priority", "(RR) Round Robin"};
  JComboBox algorithmDropbox = new JComboBox(algorithmString);

  public CPUFrame(){

    mainPanel.setBackground(new Color(60,60,60));
    add(mainPanel);


    addActionPanel();
    addQueuePanel();
    addHistoryPanel();
    addTablePanel();
    addGanttPanel();
    addListener();


  }
  public void addListener(){
    startButton.addActionListener(handler);
    stopButton.addActionListener(handler);
    resetButton.addActionListener(handler);
    restartButton.addActionListener(handler);
    exitButton.addActionListener(handler);
  }

  public static void addComponent(JPanel panel, JComponent component, int x1, int y1, int x2, int y2){
    component.setBounds(x1, y1, x2, y2);
    panel.add(component);
    panel.repaint();
    panel.revalidate();
  }

  public void addActionPanel(){

    actionPanel.setBounds(11, 12, 649, 100);
    actionPanel.setBackground(new Color(235,235,235));
    mainPanel.add(actionPanel);

    algorithmLabel.setFont(new Font("Calibri", Font.BOLD, 15));
    algorithmLabel.setForeground(new Color(100,100,100));
    quantumLabel.setFont(new Font("Calibri", Font.BOLD, 15));
    quantumLabel.setForeground(new Color(100,100,100));
    addComponent(actionPanel, algorithmDropbox, 220, 22, 245, 23);
    addComponent(actionPanel, algorithmLabel, 50, 18, 200, 30);
    addComponent(actionPanel, quantumLabel, 475, 18, 100, 30);
    addComponent(actionPanel, quantumTextField, 549, 22, 40, 23);
    addComponent(actionPanel, startButton, 50, 50, 100, 30);
    addComponent(actionPanel, stopButton, 160, 50, 100, 30);
    addComponent(actionPanel, restartButton, 270, 50, 100, 30);
    addComponent(actionPanel, resetButton, 380, 50, 100, 30);
    addComponent(actionPanel, exitButton, 490, 50, 100, 30);

  }

  public void addQueuePanel(){
    queuePanel.setBounds(11, 124, 649, 120);
    queuePanel.setBackground(new Color(235,235,235));
    mainPanel.add(queuePanel);
  }

  public void addHistoryPanel(){
    historyPanel.setBounds(11, 256, 649, 369);
    historyPanel.setBackground(new Color(235,235,235));
    mainPanel.add(historyPanel);
  }

  public void addTablePanel(){
    tablePanel.setBounds(671, 12, 683, 613);
    tablePanel.setBackground(new Color(235,235,235));
    mainPanel.add(tablePanel);

    String[] colHeadings = {"PROCESS","ARRIVAL TIME", "BURST TIME", "PRIORITY" };
    Object[][] rowData = {{"PROCESS","ARRIVAL TIME", "BURST TIME", "PRIORITY"},
    {"P1", null, "", ""},
    {"P2", null, "", ""},
    {"P3", null, "", ""},
    {"P4", null, "", ""},
    {"P5", null, "", ""},
    {"P6", null, "", ""},
    {"P7", null, "", ""},
    {"P8", null, "", ""},
    {"P9", null, "", ""},
    {"P10", null, "", ""},
    {"P11", null, "", ""},
    {"P12", null, "", ""},
    {"P13", null, "", ""},
    {"P14", null, "", ""},
    {"P15", null, "", ""},
    {"P16", null, "", ""},
    {"P17", null, "", ""},
    {"P18", null, "", ""},
    {"P19", null, "", ""},
    {"P20", null, "", ""}};



    table = new JTable(rowData, colHeadings);
    table.setFillsViewportHeight(true);
    table.setRowHeight(0, 53);

    for(int i = 1; i < 21; i++){
      table.setRowHeight(i, 28);
    }
    addComponent(tablePanel, table, 0,0, 683,613);


  }

  public void addGanttPanel(){
    ganttPanel.setBounds(11, 637, 1344, 119);
    ganttPanel.setBackground(new Color(235,235,235));
    mainPanel.add(ganttPanel);

  }

  public class Handler implements ActionListener {
    public void actionPerformed(ActionEvent event){
      if(event.getSource() == exitButton){
        System.exit(0);
      }
      if(event.getSource() == restartButton){

      }
      if(event.getSource() == resetButton){

      }

      if(event.getSource() == startButton){


        int i = 1;
        int j = 1;

        int size = 0;
        Object value = null;
        Object p = null;
        Object a = null;
        Object b = null;
        Object pr = null;

        while(true){

          if(i <= 20){

            value = table.getValueAt(i,1);
            if (value!=null)			{
              i++;
            }else{
              break;
            }
          }else{
            break;
          }
        }
        size = i -1;
        System.out.println("TOTAL SIZE: " + size);
        int[] process = new int[size];
        int[] arrivalTime = new int[size];
        int[] burstTime = new int[size];
        int[] priority = new int[size];

        while(true){
          if(j <= 20){
            a = table.getValueAt(j,1);
            b = table.getValueAt(j,2);
            pr = table.getValueAt(j,3);

            if (a!=null)			{
              process[j-1] = j;
              arrivalTime[j-1] = Integer.parseInt(a.toString());
              burstTime[j-1] = Integer.parseInt(b.toString());
              priority[j-1] = Integer.parseInt(pr.toString());
              System.out.println(process[j-1] + "\t" + arrivalTime[j-1] + "\t" + burstTime[j-1] + "\t" + priority[j-1]);
              j++;
            }else{
              break;
            }
          }else{
            break;
          }
        }
        String choice = String.valueOf(algorithmDropbox.getSelectedItem());
        int distance = 15;
        if(choice.equals("(PP) Preemptive Priority")){
          PP pp = new PP(process, arrivalTime, burstTime, priority, size);
        }else if(choice.equals("(NPP) Non-preemptive Priority")){
          System.out.print("ASDASDASD");
          NP np = new NP(process, arrivalTime, burstTime, priority, size);
        }else if(choice.equals("(RR) Round Robin")){
          RR rr = new RR(process, arrivalTime, burstTime, priority, size, Integer.parseInt(quantumTextField.getText()));
        }else if(choice.equals("(FCFS) First Come First Serve")){
          FCFS fcfs = new FCFS(process, arrivalTime, burstTime);
        }else if(choice.equals("(SJF) Shortest Job First")){
          SJF sjf = new SJF(process, arrivalTime, burstTime);
        }else if(choice.equals("(SRTF) Shortest Remaining Time First")){
          SRTF fcfs = new SRTF(process, arrivalTime, burstTime);
        }
      }
    }
  }
}
