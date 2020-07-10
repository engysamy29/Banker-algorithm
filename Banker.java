package banker.salgorithm;

public class Banker {

    private int process;
    private int resource_type;
    private int[] Available;
    private int[][] Maximum;
    private int[][] Allocate;
    private int[][] Need;
    private boolean safe_state = false;
    private int[] Work;
    int[] temp;
    private boolean[] finish;
    
   public int[] getAvailable()
   {
       return Available;
   }
   public void  setAvailable(int []av)
   {
      Available=av;
   }
   public  int[] getneed(int index){
        int []need= new int[resource_type];
        for (int i=0;i<resource_type;i++)
        {
            need[i]=Need[index][i];
        }
        return need;
    }
   public  int[] getallocation(int index){
        int []allo= new int[resource_type];
        for (int i=0;i<resource_type;i++)
        {
            allo[i]=Allocate[index][i];
        }
        return allo;
    }
    public  void setallocation(int index){
        //int []allo= new int[resource_type];
        for (int i=0;i<resource_type;i++)
        {
            Allocate[index][i]=0;
        }
        
    }
    public  void setallocation2(int index,int []all){
        //int []allo= new int[resource_type];
        for (int i=0;i<resource_type;i++)
        {
            Allocate[index][i]=all[i];
        }
        
    }
    public Banker(int[] resources, int process) {

        this.process = process;
        resource_type = resources.length;
        Available = new int[resource_type];
        System.arraycopy(resources, 0, Available, 0, resource_type);
        Maximum = new int[process][resource_type];
        Allocate = new int[process][resource_type];
        Need = new int[process][resource_type];
        Work = new int[resource_type];
        temp = new int[resource_type];
        finish = new boolean[process];
        for (int i = 0; i < process; i++) {
            finish[i] = false;
        }
    }
    public void addProcess(int NoProcess, int[] Max, int[] allocate) {
        Maximum[NoProcess] = new int[resource_type];
        Allocate[NoProcess] = new int[resource_type];
        Need[NoProcess] = new int[resource_type];

        //Malena el arrays 
        System.arraycopy(Max, 0, Maximum[NoProcess], 0, Max.length);
        System.arraycopy(allocate, 0, Allocate[NoProcess], 0, Max.length);
        for (int i = 0; i < resource_type; i++) {
            Need[NoProcess][i] = Maximum[NoProcess][i] - Allocate[NoProcess][i];
        }
    }

   /* public void getState() {
        System.out.print("Available = [ ");
        for (int i = 0; i < resource_type - 1; i++) {
            System.out.print(Available[i] + " ");
        }
        System.out.println(Available[resource_type - 1] + " ]");

        System.out.print("Allocation = ");
        for (int i = 0; i < process; i++) {
            System.out.print("[ ");
            for (int j = 0; j < resource_type - 1; j++) {
                System.out.print(Allocate[i][j] + " ");
            }
            System.out.print(Allocate[i][resource_type - 1] + " ]");
        }

        System.out.println(" ");

        System.out.print("Maximum = ");
        for (int i = 0; i < process; i++) {
            System.out.print("[ ");
            for (int j = 0; j < resource_type - 1; j++) {
                System.out.print(Maximum[i][j] + " ");
            }
            System.out.print(Maximum[i][resource_type - 1] + " ]");
        }

        System.out.println(" ");

        System.out.print("Need = ");
        for (int i = 0; i < process; i++) {
            System.out.print("[ ");
            for (int j = 0; j < resource_type - 1; j++) {
                System.out.print(Need[i][j] + " ");
            }
            System.out.print(Need[i][resource_type - 1] + " ]");
        }

        System.out.println(" ");

        System.arraycopy(Available, 0, Work, 0, resource_type);

        for (int i = 0; i < process; i++) {

            if (finish[i] == false) {

                safe_state = false;

                System.out.println("Ayman " + i);
                System.out.println("Safe state: " + safe_state);
                int counter = 0;

                for (int k = 0; k < resource_type; k++) {
                    if (Need[i][k] <= Work[k]) {
                        counter++;
                    }
                    System.out.println("process" + i + " Counter" + counter);

                }
                if (counter == resource_type) {
                    for (int j = 0; j < resource_type; j++) {
                        Work[j] = Work[j] + Allocate[i][j];
                    }
                    finish[i] = true;
                }
                System.out.print("Work = [ ");
                for (int j = 0; j < resource_type - 1; j++) {
                    System.out.print(Work[j] + " ");
                }
                System.out.println(Work[resource_type - 1] + " ]");

            }

            if (i == 0) {
                System.arraycopy(Work, 0, temp, 0, resource_type);
                System.out.println("Ayman Hesham");
                System.out.println(finish[i]);
            }

            if (i == process - 1) {
                int count = 0;
                for (int k = 0; k < resource_type; k++) {
                    if (temp[k] == Work[k]) {
                        count++;
                    }
                }

                if (count != resource_type) {
                    i = -1;
                } else {
                    int con = 0;
                    for (int j = 0; j < process; j++) {
                        if (finish[j] == true) {
                            con++;
                        }
                    }
                    if (con == process) {
                        safe_state = true;
                    }
                }
            }
        }
        if (safe_state) {
            System.out.println("Safe state, No deadlock");
        } else {
            System.out.println("Not safe state, deadlock");
        }
    }*/

    public boolean getState2(int[][] Allocation, int[] Available, int[][] Maximum, int[][] Need) {
        boolean state = false;
        //ayman comments
       /* System.out.print("Available = [ ");
        for (int i = 0; i < resource_type - 1; i++) {
            System.out.print(Available[i] + " ");
        }
        System.out.println(Available[resource_type - 1] + " ]");

        System.out.print("Allocation = ");
        for (int i = 0; i < process; i++) {
            System.out.print("[ ");
            for (int j = 0; j < resource_type - 1; j++) {
                System.out.print(Allocation[i][j] + " ");
            }
            System.out.print(Allocation[i][resource_type - 1] + " ]");
        }

        System.out.println(" ");

        System.out.print("Maximum = ");
        for (int i = 0; i < process; i++) {
            System.out.print("[ ");
            for (int j = 0; j < resource_type - 1; j++) {
                System.out.print(Maximum[i][j] + " ");
            }
            System.out.print(Maximum[i][resource_type - 1] + " ]");
        }

        System.out.println(" ");

        System.out.print("Need = ");
        for (int i = 0; i < process; i++) {
            System.out.print("[ ");
            for (int j = 0; j < resource_type - 1; j++) {
                System.out.print(Need[i][j] + " ");
            }
            System.out.print(Need[i][resource_type - 1] + " ]");
        }
        
        System.out.println(" ");*/

        System.arraycopy(Available, 0, Work, 0, resource_type);
        
        for (int i = 0; i < process; i++) {

            if (finish[i] == false) {

                safe_state = false;

                //System.out.println("Ayman " + i);
              //  System.out.println("Safe state: " + safe_state);
                int counter = 0;

                for (int k = 0; k < resource_type; k++) {
                    
                    if (Need[i][k] <= Work[k]) {
                        counter++;
                    }
                    //ayman comment
                    //System.out.println("process" + i + " Counter" + counter);

                }
                if (counter == resource_type) {
                    for (int j = 0; j < resource_type; j++) {
                       // Available[j] = Available[j] + Allocation[i][j];
                       Work[j] = Work[j] + Allocation[i][j];
                    }
                    finish[i] = true;
                }
                //ayman comment
               /* System.out.print("Work = [ ");
                for (int j = 0; j < resource_type - 1; j++) {
                    System.out.print(Work[j] + " ");
                }
                System.out.println(Work[resource_type - 1] + " ]");*/
            }

            if (i == 0) {
                System.arraycopy(Work, 0, temp, 0, resource_type);
              //  System.out.println("Ayman Hesham");
                //System.out.println(finish[i]);
            }

            if (i == process - 1) {
                int count = 0;
                for (int k = 0; k < resource_type; k++) {
                    if (temp[k] == Work[k]) {
                        count++;
                    }
                }
                
                    //    System.out.println("Counter "+ count);

                if (count != resource_type) {
                    i = -1;
                } else {
                    int con = 0;
                    //ayman comment
                    for (int j = 0; j < process; j++) {
                        if (finish[j] == true) {
                            con++;
                            //ayman comment
                            //System.out.println("Finish of processes " + j + finish[j]);
                        }
                    }
                  // System.out.println("Counter "+ con);

                    if (con == process) {
                        safe_state = true;
                    }
                }
            }
        }
        
        if (safe_state) {
            System.out.println("Safe state, No deadlock");
            state = true;
        } else {
            System.out.println("Not safe state, deadlock");
           
        }
        return state;
    }

    public boolean CheckReq(int ProcessNo, int[] request) {
        int[][] Allocate2 = new int[process][resource_type];
        int[][] Need2 = new int[process][resource_type];
        int[] Available2 = new int[resource_type];
        int[][] Sim_Allocation = new int[process][resource_type];
        int[][] Sim_Max = new int[process][resource_type];
        int[][] Sim_Need = new int[process][resource_type];
        boolean error=false;
        boolean f=false;
        int counter = 0, count = 0;

        for (int i = 0; i < resource_type; i++) {
            if (request[i] <= Need[ProcessNo][i]) {
                counter++;
            }
        }
        for (int j = 0; j < resource_type; j++) {
            if (request[j] <= Available[j]) {
                count++;
            }
        }
        if(count<resource_type)
        {
            System.out.println("Request denied");
            return f;
        }
        
        if (counter == resource_type && count == resource_type) {
            //Process el bt-request
            //System.arraycopy(Allocate, 0, Allocate2, 0, resource_type);
            for(int i=0;i<process;i++)
            {
                for(int j=0;j<resource_type;j++)
                {
                    Allocate2[i][j]=Allocate[i][j];
                }
            }
           // System.arraycopy(Need, 0, Need2, 0, resource_type);
            for(int i=0;i<process;i++)
            {
                for(int j=0;j<resource_type;j++)
                {
                    Need2[i][j]=Need[i][j];
                }
            }
            System.arraycopy(Available, 0, Available2, 0, resource_type);

            for (int i = 0; i < resource_type; i++) {

                Allocate2[ProcessNo][i] = Allocate2[ProcessNo][i] + request[i];
                //ayman comment
                System.out.println();
                Need2[ProcessNo][i] = Need2[ProcessNo][i] - request[i];
                Available2[i] = Available2[i] - request[i];
            }
            //Na2lna el 2D arrays el kobar 3shan a3raf a3'ayar fihom
            for (int k = 0; k < Allocate.length; k++) {
                System.arraycopy(Allocate[k], 0, Sim_Allocation[k], 0, Allocate[k].length);
            }
            for (int k = 0; k < Need.length; k++) {
                System.arraycopy(Need[k], 0, Sim_Need[k], 0, Need[k].length);
            }
            for (int k = 0; k < Maximum.length; k++) {
                System.arraycopy(Maximum[k], 0, Sim_Max[k], 0, Maximum[k].length);
            }

            for (int m = 0; m < resource_type; m++) {

                //Bnzawed 7agat el request fl copy bta3et el 2D array
                Sim_Allocation[ProcessNo][m] = Allocate2[ProcessNo][m];
                Sim_Need[ProcessNo][m] = Need2[ProcessNo][m];
                Sim_Max[ProcessNo][m] = Maximum[ProcessNo][m];
            }

            boolean x=true;
            x = getState2(Sim_Allocation, Available2, Sim_Max, Sim_Need);
            //ayman comment
          //  System.out.println("State= " + x);

            //3'ayart el 7a2i2y
            if (x) {
                //System.arraycopy(Available2, 0, Available, 0, resource_type);
                for(int i=0;i<Available.length;i++)
                {
                    Available[i]=Available[i]-request[i];
                }
                for (int k = 0; k < Sim_Allocation.length; k++) {
                    System.arraycopy(Sim_Allocation[k], 0, Allocate[k], 0, Sim_Allocation[k].length);
                }
                for (int k = 0; k < Sim_Need.length; k++) {
                    System.arraycopy(Sim_Need[k], 0, Need[k], 0, Sim_Need[k].length);
                }
                for (int k = 0; k < Sim_Max.length; k++) {
                    System.arraycopy(Sim_Max[k], 0, Maximum[k], 0, Sim_Max[k].length);
                }
               // System.out.println("Ay 7aga");
            } else {
                System.out.println("Request Denied");
            }
        } else {
            System.out.println("Request greater than need");
            error=true;
        }
        return error;
    }
}
