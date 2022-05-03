/**
 * @author Tyler Wolf 
 * 
 * 	I accept and acknowledge the academic integrity agreement
 * 
 * 	Unit 5 
 * 	Date: 05/03/2022 
 */
public class lgstar {
    public static int lgstar(int N) {
        double num = N;
        int iterations = 0;

        while(num > 1) {
            num = log2(num);
            iterations++;
        }
        return iterations;
    }

    public static int lgstar(int N, int iterations) {
        double num = N;

        for(int i =0; i < iterations; i++){
            num = log2(num);
        }   

        if(num < 1) return true;
        else return false;
    }    

    private float log2(int num) {
        return (int)(Math.log(num) / Math.log(2));
    }
}