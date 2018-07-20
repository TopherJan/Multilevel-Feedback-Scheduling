import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JLabel;


public class RR {

  public RR(int[] process, int[] arrivalTime, int[] burstTime, int[] priority, int size, int q, int queue){
  /*
    Scanner input = new Scanner(System.in);

	System.out.print("Enter number of processes: ");
	int size = input.nextInt();


	int[] process = new int[size];
	int[] arrivalTime = new int[size];
	int[] burstTime = new int[size];

	System.out.println("\nORIGINAL CHART");
	System.out.println("Process \tArrival Time \tBurst Time");
	for(int i = 0; i < size; i++){
	  process[i] = i+1;
      System.out.print(process[i] + "\t\t");
	  arrivalTime[i] = input.nextInt();
      burstTime[i] = input.nextInt();
	}
	*/
	int temp = 0;
	for (int i = 0; i < size; i++){
        for (int j = i + 1; j < size; j++){
		   	if(arrivalTime[i] > arrivalTime[j]){
				temp = process[i];
				process[i] = process[j];
				process[j] = temp;
				temp = arrivalTime[i];
				arrivalTime[i] = arrivalTime[j];
				arrivalTime[j] = temp;
				temp = burstTime[i];
				burstTime[i] = burstTime[j];
				burstTime[j] = temp;
			}
        }
	}

	ArrayList<Integer> arr = new ArrayList();
	ArrayList<Integer> arr2 = new ArrayList();
	ArrayList<Integer> p = new ArrayList();
	ArrayList<Integer> t = new ArrayList();

	//System.out.println("\nSORTED CHART");
	for(int i = 0; i < size; i++){
	 //	System.out.println("P" + process[i] + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i]);
		arr.add(burstTime[i]);
		arr2.add(process[i]);
	}


	//System.out.print("Enter time slice: ");
	//int q = input.nextInt();

	System.out.println("\n\nRR GANTT CHART");

	int time = arrivalTime[0];
	int counter = 0;

	t.add(time);
	while(!arr.isEmpty()){

		p.add(arr2.get(0));

		if(arr.get(0) >= q){
			arr.set(0, arr.get(0) - q);
			time += q;
		}else{
			time += arr.get(0);
			arr.set(0, 0);
		}

		if(arr.get(0) != 0){
			arr.add(arr.get(0));
			arr.remove(0);
			arr2.add(arr2.get(0));
			arr2.remove(0);
		}else{
		    arr.remove(0);
			arr2.remove(0);
		}

		t.add(time);
		counter++;
	}

	for(int i = 0; i < counter; i++){
		System.out.print("\t|  P" + p.get(i));
	}

	System.out.print("  |\n");


	for(int i = 0; i < counter+1; i++){
		System.out.print("\t" + t.get(i));
	}

	MLFQFrame.processLabel = new JLabel[t.size()];
	GanttThread ppt = new GanttThread(p, t, queue);



  }

}
