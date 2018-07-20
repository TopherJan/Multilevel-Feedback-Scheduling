import java.util.ArrayList;
import javax.swing.JLabel;

public class SRTF {
	private int[] arrivalTime;
	private int[] processID;
	private int[] burstTime;
	private int numOfProcesses;
	private ArrayList<Integer> process_ID = new ArrayList<Integer>();
	private ArrayList<Integer> arrival_time = new ArrayList<Integer>();

	public SRTF(int[] processID, int[] arrivalTime, int[] burstTime){
		this.processID = processID;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		numOfProcesses = processID.length;

		int[] completionTime = new int[numOfProcesses];
		int[] fullBurst = burstTime.clone();
		int[] flag = new int[numOfProcesses];

		for(int i = 0; i < numOfProcesses; i++){
			flag[i] = 0;
		}
		int st=0, tot=0;
		ArrayList<Integer> list = new ArrayList<Integer>();

		int temp = 0;

		System.out.println("\nGantt Chart\n");
		while(true){
	    	int min = 50, c = numOfProcesses;
	    	if (tot == numOfProcesses){
	    		System.out.print(" -P" +list.get(0) +"-");
	    		System.out.print(" |" +st +"|");
					process_ID.add(list.get(0));
					arrival_time.add(st);
	    		break;
	    	}

	    	for (int i = 0; i < numOfProcesses; i++){
	    		if ((arrivalTime[i] <= st) && (flag[i] == 0) && (burstTime[i] <= min)){
	    			min=burstTime[i];
	    			c=i;
	    		}
	    	}

	    	if (c==numOfProcesses){
	    		st++;
	    	}else{
					process_ID.add(processID[c]);
					arrival_time.add(st);
	    		// System.out.println(st +" P" +processID[c]);
	    		if(!list.contains(processID[c])){
	    			if(list.isEmpty()){
	    				list.add(processID[c]);
	    			}else{
	    				temp = list.get(0);
		    			list.remove(0);
		    			list.add(processID[c]);
		    			System.out.print(" -P" +temp +"-");
	    			}
    				System.out.print(" |" +st +"|");
    			}
	    		burstTime[c]--;
	    		st++;

	    		if (burstTime[c]==0){
	    			completionTime[c]= st;
	    			flag[c]=1;
	    			tot++;
	    		}
	    	}
	    }

	    System.out.println("\n\npid  arrival burst");
		for(int i=0;i<numOfProcesses;i++){
			System.out.println(processID[i]+"\t"+arrivalTime[i]+"\t"+fullBurst[i]);
		}
		
		
		CPUFrame.processLabel = new JLabel[process_ID.size()];
		GanttThread ppt = new GanttThread(process_ID, arrival_time);

	}
}
