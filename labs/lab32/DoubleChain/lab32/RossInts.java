package lab32;


public class RossInts {
	int rossInt;
	
	public RossInts(int i) {
		put(i);
	}
	
	void put(int s) {
		rossInt = s * 10;
	}
	
	int get() {
		return rossInt;
	}
	public static void main(String[] args) {
		RossInts x = new RossInts(2);
		int num = x.get();
		System.out.println(num);
	}
	
}


