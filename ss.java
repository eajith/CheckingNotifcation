    import java.util.*;
    import java.lang.*;
    import java.math.*;
    
    public class HelloWorld{
          public static int check(int n,int tree[][]){
             int count=0;
             int flag=-3;
            for(int i=0;i<n;i++){
                if(tree[i][2]>tree[i][3]){
                    count++;
                    flag=i;
                }
                if(count==2){
                    return -1;
                }
            }
            if(count==1){
                return flag;
            }else{
                return -2;
            }
         }
         
        public static int[][] distance(int n,float c,int tree[][]){
             int conn[][]=new int[n][n];
             int k=0;
             for(int i=0;i<n;i++){
                 int x=tree[i][0];
                 int y=tree[i][1];
                 for(int j=0;j<n;j++){
                     int x1=tree[j][0];
                     int y1=tree[j][1];
                     double dis = Math.sqrt(((x-x1)*(x-x1))+((y-y1)*(y-y1)));
                     if(dis>c){
                         conn[i][j]=0;
                     }else if(dis==0){
                         conn[i][j]=0;
                     }else if(dis<=c){
                         conn[i][j]=1;
                     }
                 }
             }
             return conn;
        }
        
        public static bool present(int k,int d[],int n){
            for(int i=0;i<n;i++){
                if(k==d[i])
                    return false;
            }
            return true;
        }
        
        public static int end(int endpoint,int dist[][],int n,float c,int tree[][],int totcount){
            int x=0,y=0,z=0,r=0,i,j,k,l;
            int tempTree[][]=new int[n][4];
            for ( int i = 0 ; i < tree.length; i++){
                System.arraycopy(tree[i], 0, tempTree[i], 0, tree[i].length);
            }
            int tempEndPoint[] = new int[n];
            tempEndpoint[x++]=endpoint;
            int connectedNode[]= new int[n];
            int donevertices[] =new int[n];
            int newendpoint[]= new int[n];
            donevertices[y++]=endpoint;
            int reachedCount=tempTree[endpoint][2];
            int notReached= totcount - reachedCount;
            for(i=0;i<n;i++){
                if(dist[endpoint][i]==1){
                    connectedNode[z++]=i;
                }else{
                    continue;
                }
            }
            int remainingCapacity=0;
            for(i in connectedNode){
                tempTree[i][3]= tempTree[i][3]-tempTree[i][2];
                remainingCapacity=remainingCapacity+tempTree[i][3];
                reachedCount=reachedCount+tempTree[i][2];
                notReached=notReached-tempTree[i][2];
                donevertices[y++]=i;
                tempTree[i][2]=0;
                newendpoint[r++]=i;
            }
            int needCapactiy=0;
            for(i=0;i<n;i++){
                if(present(i,donevertices,y)){
                    needCapacity+=tempTree[i][3];
                }
            }
            if(remainingCapacity < needCapacity){
                return -1;
            }else if(reachedCount==totcount){
                return -2;
            }else{
                int tempNewEnd[]=new int[n],p=0;
                while(reachedCount!=totcount && r!=0){
                    int tempEndPoint[]=new int[n];
                    for(i in newendpoint){
                        for(j=0;j<n;j++){
                            if(dist[i][j]==1){
                                connectedNode[z++]=j;
                            }else{
                                continue;
                            }
                        }
                        for(k in connectedNode){
                            if(present(k,donevertices,y)){
                                if(tempTree[i][3]>=tempTree[k][2]){
                                    tempTree[k][3]-=tempTree[k][2];
                                    remainingCapacity+=tempTree[k][3];
                                    reachedCount+=tempTree[k][2];
                                    notReached-=tempTree[k][2];
                                    donevertices[y++]=k;
                                    tempTree[k][2]=0;
                                    tempNewEnd[p++]=k;
                                    needCapacity=0;
                                }else{
                                    return -1;
                                }
                            }
                            for(l=0;l<n;l++){
                                if(l,donevertices,y){
                                    needCapacity+=tempTree[l][3];
                                }
                            }
                            if(remainingCapacity<needCapacity){
                                return -1;
                            }else if(reachedCount==totcount){
                                return -2;
                            }
                        }
                        
                    }
                    
                }
            }
        }
         
         
         public static void main(String []args){
            Scanner input = new Scanner(System.in);
            int n=input.nextInt(),flag=0,totcount=0,i,j;
            String s="";
            float c = input.nextFloat();
            int tree[][]=new int[n][4];
            for(i=0;i<n;i++){
                for(j=0;j<4;j++){
                    tree[i][j]=input.nextInt();
                }
            }
            int checking = check(n,tree);
            System.out.println(checking);
            for(i=0;i<n;i++){
                totcount+= tree[i][2];
            }
            int dist[][] = distance(n,c,tree);
            for(i=0;i<dist.length;i++){
                for(j=0;j<n;j++){
                    System.out.print(dist[i][j]+" ");
                }
                System.out.println();
            }
            if(checking == -1){
                System.out.println("-1");
            }else if(checking == -2){
                for(i=0;i<n;i++){
                    int status = end(i,dist,n,c,tree,totcount);
                    if(status == -2){
                        flag = 1;
                        s = s+" "+ i;
                    }
                }
                if(flag==-1){
                    System.out.println("-1");
                }else if(flag==0){
                    System.out.println(s);
                }
            }else{
                int status2 = end(checking,dist,n,c,tree,totcount);
                System.out.println(checking);
            }
            
         }
         
       
    }
