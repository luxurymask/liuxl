package Practice;

public class Practice {
    //朴素Fibonacci
    public static int recursiveFibonacci(int n){
        if(n == 0){
            return 0;
        }else if(n == 1){
            return 1;
        }
        return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
    }
    
    //Fibonacci
    public static int bottomupFibonacci(int n){
        int result = 0;
        if(n == 1){
            result = 1;
        }
        for(int i = 2;i <= n;i++){
            result += i;
        }
        return result;
    }
    
    //矩阵Fibonacci
    public static int recursiveSquarlingFibonacci(int n) throws Exception{
        Matrix M = new Matrix(2);
        M.setElement(0, 0, 1);
        M.setElement(0, 1, 1);
        M.setElement(1, 0, 1);
        
        return Matrix.power(M, n).getElement(0, 1);
    }
    
    public static void main(String[] args) throws Exception{
        for(int i = 0;i <= 10;i++){
            System.out.print(recursiveFibonacci(i) + " ");
        }
        System.out.println("");
        for(int i = 0;i <= 10;i++){
            System.out.print(recursiveFibonacci(i) + " ");
        }
        System.out.println("");
        for(int i = 0;i <= 10;i++){
            System.out.print(recursiveSquarlingFibonacci(i) + " ");
        }
    }
}
