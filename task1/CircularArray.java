package task1;

public class CircularArray {

	public static void main(String[] args) {
		
		int arrLength = Integer.parseInt(args[0]);
		int hopLength = Integer.parseInt(args[1]);
		
		int[] carray = createCircularArray(arrLength);
		
		String path = "";
		
		int startInd = 0;
		int endInd = circularHopIndex(carray.length, startInd, hopLength);
		int temp = endInd;
		
		do {
			temp = endInd;
			path = path + carray[startInd];
			startInd = endInd;
			endInd = circularHopIndex(carray.length, startInd, hopLength);
			
		}while(temp != 0);
		
		System.out.println(path);
	}
	
	// Создает и возвращает массив заполненный от 1 до arrLength
	public static int[] createCircularArray(int arrLength) {
		
		int[] arr = new int[arrLength];
		
		for(int i = 0; i < arrLength; ++i) {
			
			arr[i] = i + 1;
		}
		
		return arr;
	}
	
	// Возвращает индекс следующего элемента после прыжка
	public static int circularHopIndex(int arrLength, int hopStart, int hopLength) {
		
		return (hopStart + (hopLength - 1)) % arrLength;
	}
}
