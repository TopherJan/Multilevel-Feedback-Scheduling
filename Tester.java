import java.util.Scanner;
import java.util.Random;

public class Tester {

  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
	
	/*
	NP
	#Test1
	arrivalTime = {41, 29, 3, 42, 0, 28, 26, 49, 25, 24};
	burstTime = {4, 43, 5, 6, 38, 43, 14, 4, 26, 3};
	priority = {7, 10, 9, 5, 5, 8, 5, 6, 8, 10};
	
	
	#Test2
	arrivalTime = {4, 1, 4, 0, 38, 33, 43, 20, 15, 32, 40, 2, 32, 3, 40};
	burstTime = {8, 45, 17, 8, 30, 21, 50, 45, 21, 8, 2, 31, 10, 41, 15};
	priority = {15, 1, 13, 6, 7, 11, 12, 15, 11, 1, 11, 14, 9, 1, 6};
	
	#Test3
	arrivalTime = {13, 35, 47, 20, 45, 31, 8, 0, 35, 7, 6, 48, 39, 21, 43, 44, 19, 15, 38, 11};
	burstTime = {37, 44,30, 20, 37, 42, 17, 10, 30, 11, 30, 7, 31, 26, 36, 2, 23, 49, 41, 18};
	priority = {4, 18, 19, 3, 6, 5, 17, 20, 12, 7, 2, 4, 7, 11, 4, 7, 5, 18, 20, 14}
	*/
	
	/*
	PP
	arrivalTime = {2, 36, 20, 47, 21, 0, 46, 32, 23, 12};
	burstTime = {45, 7, 28, 5, 17, 46, 45, 7, 28, 35};
	priority = {1,1,5,3,4,7,6,7,5,7};
	
	arrivalTime = {0, 37, 15, 15, 26, 20, 12, 18, 26, 19, 12, 1, 19, 31, 27};
	burstTime = {37, 2, 6, 20, 22, 36, 28, 14, 21, 10, 30, 1, 20, 36, 23};
	priority = {2,3,4,11,4,3,2,7,7,7,6,13,2,9,7};
	
	arrivalTime = {6, 16, 48, 0, 23, 37, 3, 42, 45, 39, 47, 30, 25, 19, 26, 45, 6, 29, 44, 48};
	burstTime = {5, 32, 48, 5, 43, 24, 32, 25, 29, 17, 9, 1, 35, 18, 22, 38, 15, 39, 35, 50};
	priority = {3, 19, 4, 14, 9, 19, 1, 4, 19, 5, 4, 10, 7, 5, 1, 6, 16, 19, 11, 16};
	*/
	/*
	RR
	arrivalTime = {33, 0, 40, 38, 8, 17, 6, 3, 27, 43};
	burstTime = {17, 36, 23, 16, 3, 35, 16, 21, 46, 43};
	
	arrivalTime = {14, 17, 2, 0, 36, 6, 4, 37, 13, 4, 48, 41, 13, 14,11};
	burstTime = {21, 27, 44, 14, 14, 2, 24, 47, 45, 38, 45, 3, 44, 26, 11};
	
	arrivalTime = {33, 36, 29, 27, 23, 45, 43, 14, 49, 13, 10, 0, 41, 43, 27, 22, 26, 1, 47, 33};
	burstTime = {16, 34, 29, 38, 23, 4, 38, 40, 38, 7, 36, 10, 18, 7, 23, 1, 6, 36, 48, 8};
	
	*/
	
	
	
	
	
	
	
		Random generator = new Random(); 
		
		System.out.print("Enter number of processes: ");
		int size = input.nextInt();
	
		System.out.print("Enter quantum time: ");
		int q = input.nextInt();
	
		int[] process = new int[size];	
		//int[] arrivalTime = new int[size];
		//int[] burstTime = new int[size];
		int[] priority = new int[size];
    
		System.out.println("\nORIGINAL CHART");
		System.out.println("Process \tArrival Time \tBurst Time \tPriority");	
		for(int i = 0; i < size; i++){
			process[i] = i+1;

			//arrivalTime[i] = generator.nextInt(50);
			//burstTime[i] = generator.nextInt(50) + 1;
			priority[i] = generator.nextInt(size) + 1;	  
		//	System.out.println(process[i] + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + priority[i]);	  
		}
		//	arrivalTime = {33, 0, 40, 38, 8, 17, 6, 3, 27, 43};
	// burstTime = {17, 36, 23, 16, 3, 35, 16, 21, 46, 43};
	
	int[] arrivalTime = {14, 17, 2, 0, 36, 6, 4, 37, 13, 4, 48, 41, 13, 14,11};
	int[] burstTime = {21, 27, 44, 14, 14, 2, 24, 47, 45, 38, 45, 3, 44, 26, 11};
	
	//int[] arrivalTime = {33, 36, 29, 27, 23, 45, 43, 14, 49, 13, 10, 0, 41, 43, 27, 22, 26, 1, 47, 33};
	//int[] burstTime = {16, 34, 29, 38, 23, 4, 38, 40, 38, 7, 36, 10, 18, 7, 23, 1, 6, 36, 48, 8};

		
	//	PP pp = new PP(process, arrivalTime, burstTime, priority, size);
		//NP np = new NP(process, arrivalTime, burstTime, priority, size);
		RR rr = new RR(process, arrivalTime, burstTime, priority, size, q);
  }

}