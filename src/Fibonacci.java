public class Fibonacci {
    
    public static BigInteger caculate(int n){
        if (n==0){
            return new BigInteger("0");
        }else{
            if (n==1){
                return new BigInteger("1");
            }
        }
        return caculate(n-1).plus(caculate(n-2));
    }

    public static void main(String[] args){
        System.out.println("Fibonacci of n: "+Fibonacci.caculate(10));
    }
}
