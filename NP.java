import java.util.*;
import javax.swing.JLabel;

public class NP {

  public NP(int[] process, int[] arrivalTime, int[] burstTime, int[] priority, int size){
	/*Scanner input = new Scanner(System.in);
	
	System.out.print("Number of processes: ");
	int size = input.nextInt();
	
	int[] process = new int[size];
	int[] arrivalTime = new int[size];
	int[] burstTime = new int[size];
	int[] priority = new int[size];	
  
  

    for(int i = 0; i < size; i++){
		System.out.print("Arrival time for P" + (i+1) + ": ");
        arrivalTime[i] = input.nextInt();	  
        process[i] = i+1;		
	}
	
    for(int i = 0; i < size; i++){
		System.out.print("Burst time for P" + (i+1) + ": ");
        burstTime[i] = input.nextInt();	  	
	}

    for(int i = 0; i < size; i++){
		System.out.print("Priority for P" + (i+1) + ": ");
        priority[i] = input.nextInt();	  	
	}


	System.out.println("\nORIGINAL CHART");
	System.out.println("Process \tArrival Time \tBurst Time \tPriority");	
	for(int i = 0; i < size; i++){
	 	System.out.println("P" + process[i] + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + priority[i]);
	}
*/	
	int temp;
	for (int i = 0; i < size; i++){
        for (int j = i + 1; j < size; j++){
            if (arrivalTime[i] > arrivalTime[j]){
               temp = arrivalTime[i];
               arrivalTime[i] = arrivalTime[j];
               arrivalTime[j] = temp;
			   temp = priority[i];
               priority[i] = priority[j];
               priority[j] = temp;
			   temp = burstTime[i];
			   burstTime[i] = burstTime[j];
			   burstTime[j] = temp;			   
   			   temp = process[i];
			   process[i] = process[j];
			   process[j] = temp;			   
            }
			
        }
    }
	
	
	for (int i = 0; i < size; i++){
        for (int j = i + 1; j < size; j++){
			if(arrivalTime[i] == arrivalTime[j]){
			   	if(priority[i] > priority[j]){
					temp = arrivalTime[i];
               arrivalTime[i] = arrivalTime[j];
               arrivalTime[j] = temp;
			   temp = priority[i];
               priority[i] = priority[j];
               priority[j] = temp;
			   temp = burstTime[i];
			   burstTime[i] = burstTime[j];
			   burstTime[j] = temp;			   
   			   temp = process[i];
			   process[i] = process[j];
			   process[j] = temp;		
					
				}
			}
        }
    }
	
	/*
	System.out.println("\nSORTED CHART");
	System.out.println("Process \tArrival Time \tBurst Time \tPriority");
	for(int i = 0; i < size; i++){
	 	System.out.println("P" + process[i] + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + priority[i]);
	}


   
	
	
	int time = arrivalTime[0];
	
	for(int i = 0; i < size; i++){
	 	System.out.print("\t|   P" + process[i]);

	}

    System.out.print("   |\n");

	System.out.print("\t" + time);
	for(int i = 0; i < size; i++){	
		time += burstTime[i];
		System.out.print("\t" + time);
		
	}*/
	/*
	System.out.print("P" + process[0]);
	int counter = 0;
	int[] store = new int[size];
	store[0] = 0;
	int mark = 0;
    int initialTime = burstTime[0];
	int ctr = 0;
	ArrayList<Integer> used = new ArrayList();
	while(size-1 != ctr){
		counter =0;
		ArrayList<Integer> arr = new ArrayList();
		
		for(int i = ctr; i < size; i++){
			if(initialTime >= arrivalTime[i]){
				
			arr.add(priority[i]);
	//		System.out.println(arr.get(counter));
			counter++;
		}
	}
	Collections.sort(arr);
	//System.out.println("After Sorting:");
	for(int x: arr){
//			System.out.println(x);
	}
	

	for(int i = 1; i < size; i++){
      if(arr.get(0) == priority[i]){
			initialTime+=burstTime[i];	
			store[ctr] = i;
		 	System.out.print("\tP" + process[i]); 
	  }
		
    }

     ctr++;	
	}
	
	System.out.println("USED");
	for(int i = 0; i < size; i++){
		
//	  System.out.println(store[i]);	
	}*/
	
	for (int i = 1; i < size; i++){
        for (int j = i + 1; j < size; j++){
	
			   	if(priority[i] > priority[j]){
					temp = arrivalTime[i];
               arrivalTime[i] = arrivalTime[j];
               arrivalTime[j] = temp;
			   temp = priority[i];
               priority[i] = priority[j];
               priority[j] = temp;
			   temp = burstTime[i];
			   burstTime[i] = burstTime[j];
			   burstTime[j] = temp;			   
   			   temp = process[i];
			   process[i] = process[j];
			   process[j] = temp;		
					
	
			}
        }
    }
	
	ArrayList<Integer> p = new ArrayList();
	ArrayList<Integer> a = new ArrayList();
	ArrayList<Integer> b = new ArrayList();
	ArrayList<Integer> pr = new ArrayList();
	ArrayList<Integer> t = new ArrayList();
	int counter = 0;
	int[] pros = new int[size];
	
	//System.out.println("\nSORTED 2.0 CHART");
	//System.out.println("Process \tArrival Time \tBurst Time \tPriority");

	for(int i = 0; i < size; i++){
	// 	System.out.println("P" + process[i] + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + priority[i]);
		p.add(process[i]);
		a.add(arrivalTime[i]);
		b.add(burstTime[i]);
		
	}
	
	int totalTime = Collections.min(a);
	t.add(totalTime);
//	System.out.println("\nOPS");
	while(!p.isEmpty()){
		
		if(totalTime >= a.get(0)){
			pros[counter] = p.get(0);
			totalTime += b.get(0);
			p.remove(0);
			a.remove(0);
			b.remove(0);
			counter++;
			t.add(totalTime);
		}
	
		}
	
	
    ArrayList<Integer> process_ID = new ArrayList();
   	System.out.println();
	System.out.println("\nNP GANTT CHART");
	
	
	for(int i = 0; i < size; i++){
	 	System.out.print("\t|  P" + pros[i]);
	}

    System.out.print("  |\n");


	for(int i = 0; i < size+1; i++){	
		System.out.print("\t" + t.get(i));	
	}
	
		
	
	for(int i = 0; i <= t.size()-2; i++){
	  int limit = t.get(i+1) - t.get(i);
	  System.out.print(limit);
	  
	  for(int j = 0; j < limit; j++){
		process_ID.add(pros[i]);  
	  }
	}
	
	
	CPUFrame.processLabel = new JLabel[process_ID.size()];
	GanttThread ppt = new GanttThread(process_ID, t);
	
	
	
  }




}