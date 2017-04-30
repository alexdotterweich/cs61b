public class test{
	public static void stars(int x){
		String star = "*";
		while (x > 0){
			System.out.println(star);
			star += star;
			x--;
		}
	}
	
	public static void starsrecurse(int x){ 
		String star = "*";
		String current = "*";
		for (int i = 0; i < x; i++){
			current += star;
		}
		if (x > 0) {
			starsrecurse(x-1);
		} 
		System.out.println(current);
	}
	
	public static void main(String[] args){
//		
		starsrecurse(10);
	}
}