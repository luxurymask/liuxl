package Practice;

public class Matrix {
    private int m;
    private int n;
    private int[][] elements;
    
    public Matrix(int m, int n){
        this.m = m;
        this.n = n;
        this.elements = new int[m][n];
    }
    
    public Matrix(int n){
        this.m = n;
        this.n = n;
        this.elements = new int[n][n];
    }
    
    public void setElement(int m, int n, int element){
        this.elements[m][n] = element;
    }
    
    public int getElement(int m, int n){
        return this.elements[m][n];
    }
    
    public static Matrix multiplication(Matrix A, Matrix B) throws Exception{
        if(A.n != B.m){
            throw new Exception("参数不合法。");
        }
        
        int publicNum = A.n;
        
        Matrix result = new Matrix(A.m, B.n);
        for(int i = 0;i < A.m;i++){
            for(int j = 0;j < B.n;j++){
                for(int k = 0;k < publicNum;k++){
                    result.elements[i][j] += A.elements[i][k] * B.elements[k][j];
                }
            }
        }
        return result;
    }
    
    public static Matrix power(Matrix A, int n) throws Exception{
        if(A.m != A.n){
            throw new Exception("参数不合法。");
        }
        
        if(n == 0){
            Matrix identity = new Matrix(A.m);
            for(int i = 0;i < n;i++){
                identity.elements[i][i] = 1;
            }
            return identity;
        }else if(n == 1){
            return A;
        }
        
        if(n % 2 == 0){
            return multiplication(power(A, n/2),power(A, n/2));
        }else{
            return multiplication(multiplication(power(A, (n - 1)/2), power(A, (n - 1)/2)), A);
        }
    }
}
