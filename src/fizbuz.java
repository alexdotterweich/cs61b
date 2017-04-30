public class fizbuz{
	public static void fizzbuzz(int x) {
		if ((x % 3) == 0 && (x % 5) == 0){
			System.out.println("Fizzbuzz");
		} 
		else if (x % 3 == 0) {
			System.out.println("Fizz");
		} 
		else if (x % 5 == 0){
			System.out.println("Buzz");
		} 
		else {
			System.out.println(x);
		}
	}
	public static void main(String[] args){
		for (int i = 1; i <= 20; i++){
			fizzbuzz(i);
		}
	}
}

