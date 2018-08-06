import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JLabel;

public class RR {
  ArrayList<Integer> p = new ArrayList<Integer>();
  ArrayList<Integer> t = new ArrayList<Integer>();

  public RR(int[] process, int[] arrivalTime, int[] burstTime, int quantum, int queue){
    int size = process.length;

    int[] wt = new int[size];
    int[] te = new int[size];
    int[] rt = burstTime;

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
    int j = 0, q = arrivalTime[0], counter = 0;

    while(j <= size){
      j++;
      for(int i = 0; i < size; i++){
        if(rt[i] == 0)  continue;
        if(rt[i] > quantum){
          //  System.out.println(q + " " +process[i]);
          counter = q;
          t.add(q);
          q = q + quantum;
          for(int k = counter; k < q; k++){
            System.out.println("P" +process[i]);
            p.add(process[i]);
          }
          rt[i] = rt[i] - quantum;
          te[i] = te[i] + 1;
        }else{
          //  System.out.println(q + " " +process[i]);
          counter = q;
          wt[i] = q - te[i] * quantum;
          t.add(q);
          q = q + rt[i];
          for(int k = counter; k < q; k++){
            System.out.println("P" +process[i]);
            p.add(process[i]);
          }
          rt[i] = rt[i] - rt[i];
        }
      }
    }
    t.add(q);

     MLFQFrame.processLabel = new JLabel[p.size()];
     GanttThread ppt = new GanttThread(p, t, queue);
  }
}
