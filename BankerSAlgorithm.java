package banker.salgorithm;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BankerSAlgorithm {

    public static int RandInt(int min, int max) {
        Random rand = new Random();
        int RandNum = rand.nextInt((max - min) + 1) + min;
        return RandNum;
    }

    public static void main(String[] args) {
        int NoProcess;
        int Resources;
        Random l = new Random();
        int counterr =0;

        //int []available =new int[Resources];
        //int [][]max =new int[NoProcess][Resources];
        // int [][]allocate =new int[NoProcess][Resources];
        // boolean f=false;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Number of processes: ");
        //Scanner sc = new Scanner(System.in);

        NoProcess = sc.nextInt();
        //System.out.println(NoProcess);

        System.out.println("Enter Number of resources: ");
        Resources = sc.nextInt();

        int[] no_instance = new int[Resources];
        // int[] no_instance= {10,7,8};

        int[] available = new int[Resources];
        System.out.println("Enter No. of instance: ");
        for (int i = 0; i < Resources; i++) {
            no_instance[i] = sc.nextInt();
        }
        System.arraycopy(no_instance, 0, available, 0, no_instance.length);

        //int[][] max = {{2, 0, 1}, {3, 0, 0}, {1, 0, 2}};
        //int[][] allocate = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        Random rn = new Random();
        int[][] max = new int[NoProcess][Resources];
        int[][] allocate = new int[NoProcess][Resources];

        for (int i = 0; i < NoProcess; i++) {
            for (int j = 0; j < Resources; j++) {
                // max[i][j]=RandInt(0,(available[j])); 
                //ehna 3z
                //available[j]-1
                max[i][j] = rn.nextInt((available[j]) - 1);
            }
        }

        System.out.print("Maximum = ");

        for (int i = 0; i < NoProcess; i++) {
            System.out.print("[ ");
            for (int j = 0; j < Resources - 1; j++) {
                System.out.print(max[i][j] + " ");
            }
            System.out.print(max[i][Resources - 1] + " ]");
        }

        System.out.println(" ");

        for (int i = 0; i < NoProcess; i++) {
            for (int j = 0; j < Resources; j++) {
                allocate[i][j] = 0;
            }
        }
        //print allocation
         System.out.print("Allocation = ");

        for (int i = 0; i < NoProcess; i++) {
            System.out.print("[ ");
            for (int j = 0; j < Resources - 1; j++) {
                System.out.print(allocate[i][j] + " ");
            }
            System.out.print(allocate[i][Resources - 1] + " ]");
        }

        System.out.println(" ");
        //int[] request = {1, 0, 2};
        Banker b = new Banker(available, NoProcess);
        for (int i = 0; i < NoProcess; i++) {
            b.addProcess(i, max[i], allocate[i]);
        }
        boolean check = false;
        boolean[] bo = new boolean[NoProcess];
        Arrays.fill(bo, Boolean.FALSE);
        int[]Finish = new int[NoProcess];

        while (!check) {
            Random x = new Random();
            int index = RandInt(0, NoProcess - 1);
            //System.out.println("Enter Index of process that needs request ");
            //int index = sc.nextInt();
            if (!bo[index]) {
                int av[] = b.getAvailable();
                int ne[] = b.getneed(index);

                int request[] = new int[Resources];

                // System.out.println("Enter request ");
                for (int i = 0; i < Resources; i++) {
                    //request[i]= RandInt(0, ne[i]+1);
                    request[i] = rn.nextInt(ne[i]+1);
                    //request[i]=sc.nextInt();
                }
                System.out.print("The request of[" + index + "]" + "=" + "[");
                for (int i = 0; i < request.length; i++) {
                    System.out.print(request[i] + " ");
                }
                System.out.println("]");

                check = b.CheckReq(index, request);
                
                int ne1[]= b.getneed(index);
             int eng=0;
            for(int i=0;i<Resources;i++)
            {
                if(ne1[i]==0)
                {
                    eng++;
                }
            }
            if(eng==Resources)
            {
                int all[]=b.getallocation(index);
                int av1[]=b.getAvailable();
                for(int j=0;j<Resources;j++)
                {
                    av1[j]=av1[j]+all[j];
                   
                }
                b.setAvailable(av1);
                b.setallocation(index);
            }
            int []available5 = b.getAvailable();
                System.out.println("Available :");
                for(int i=0;i<Resources;i++)
                {
                System.out.println(available5[i]+" ");
                 }
            boolean y = x.nextBoolean();
            int index1 = RandInt(0, NoProcess - 1);
            int alloo[]=b.getallocation(index1);
            int release[] = new int[Resources];
            if (y)
            {
                for (int i = 0; i < Resources; i++) {
                    //request[i]= RandInt(0, ne[i]+1);
                    release[i] = BankerSAlgorithm.RandInt(0, alloo[i]);
                }
                System.out.println("The release =");
                for(int i=0;i<release.length;i++)
                {
                    System.out.println(release[i]+" ");
                }
                    int available3[] = b.getAvailable();
                    for(int k=0;k<Resources;k++)
                    {
                        available3[k]= available3[k]+release[k];
                        alloo[k]=alloo[k]- release[k];
                        
                    }
                    b.setallocation2(index1,alloo);
                    b.setAvailable(available3);
                    
                    
                    //request[i]=sc.nextInt();
                int []available6 = b.getAvailable();
               System.out.println("Available :");
               for(int i=0;i<Resources;i++)
            {
                System.out.println(available6[i]+" ");
            } 
             
            }
                //ayman comment
                //System.out.println(check);
                int nee[] = b.getneed(index);
                int sum = 0;
                for (int i = 0; i < Resources; i++) {
                    sum = sum + nee[i];
                }
                //ayman comment
                //System.out.println("sum of need of "+index+" = "+sum);
                if (sum == 0) {
                    bo[index] = true;
                    Finish[counterr]=index;
                    counterr++;
                }
                
                int count = 0;
                for (int i = 0; i < bo.length; i++) {
                    if (bo[i] == true) {
                        count++;
                    }
                }
                if (count == NoProcess) {
                    check = true;
                }
            }
            

        }
        for(int h=0;h<Finish.length;h++)
        {
        System.out.println("Process "+Finish[h]+" ");
        }
        // b.getState();
        //b.CheckReq(1, request);
        System.out.println("System exit");
    }
}