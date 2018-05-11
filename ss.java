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
         
         public static int[] distance(int n,float c,int tree[][]){
             int conn[]=new int[n*n];
             int k=0;
             for(int i=0;i<n;i++){
                 int x=tree[i][0];
                 int y=tree[i][1];
                 for(int j=0;j<n;j++){
                     int x1=tree[j][0];
                     int y1=tree[j][1];
                     double dis = Math.sqrt(((x-x1)*(x-x1))+((y-y1)*(y-y1)));
                     if(dis>c){
                         conn[k++]=0;
                     }else if(dis==0){
                         conn[k++]=0;
                     }else if(dis<=c){
                         conn[k++]=1;
                     }
                 }
             }
             return conn;
         }
         
         public static void main(String []args){
            Scanner input = new Scanner(System.in);
            int n=input.nextInt(),flag=0,totcount=0,i,j;
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
            int dist[] = distance(n,c,tree);
            for(i=0;i<dist.length;i++){
                System.out.println(dist[i]);
            }
         }
         
       
    }