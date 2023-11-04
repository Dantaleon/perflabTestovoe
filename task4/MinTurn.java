package task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MinTurn {

	public static void main(String[] args) {
		
		String arrayFilepath = args[0];
		List<Integer> list = new ArrayList<Integer>();
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(arrayFilepath));
			
			String line = reader.readLine();
			while(line != null) {
				
				list.add(Integer.parseInt(line));
				line = reader.readLine();
			}
			
			reader.close();
	
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		int[] array = new int[list.size()];
		for(int i = 0; i < list.size(); ++i) {
			
			array[i] = list.get(i);
		}
		
		boolean isFall = isArrayGoDown(array);
		int operations = 0;
		while (isFall) {
			
			array[findMaxElem(array)]--;
			
			operations++;
			
			isFall = isArrayGoDown(array);
		}
		// к этому моменту у нас массив из чисел, отличающихся не более чем на единицу
		// осталось подсчитать, что выгоднее будет изменять (чего меньше)
		
		int mins = countElemInArr(array, array[findMinElem(array)]);
		int maxes = countElemInArr(array, array[findMaxElem(array)]);
		
		int change = 1;
		if (mins < maxes) {
			
			operations = changeElems(array, array[findMinElem(array)], change, operations);
		}
		else if (maxes < mins) {
			
			operations = changeElems(array, array[findMaxElem(array)], -change, operations);
		}
		
		System.out.println(operations);
	}
	
	// Возвращает решение о том, стоит ли понижать массив
	// (наибольший элемент)
	public static boolean isArrayGoDown(int[] array) {
		
		boolean isDown = false;
		int max = array[findMaxElem(array)];
		
		for (int i = 0; i < array.length; ++i) {
			
			if ((max - array[i]) > 1) {
				isDown = true;
				break;
			}
		}
		
		return isDown;
	}
	
	// Меняет определенные элементы массива на величину change
	// и накапливает операции (затем возвращает их)
	public static int changeElems(int[] array, int elem, int change, int operations) {
		
		for (int i = 0; i < array.length; ++i) {
			
			if (array[i] == elem) {
				
				array[i] += change;
				operations++;
			}
		}
		return operations;
	}
	
	
	// Возвращает индекс макс элемента
	public static int findMaxElem(int[] array) {
		
		int ind = 0;
		int max = array[ind];
		
		for (int i = 1; i < array.length; ++i) {
			
			if (array[i] > max) {
				max = array[i];
				ind = i;
			}
		}
		
		
		return ind;
	}
	
	// Возвращает индекс мин элемента
	public static int findMinElem(int[] array) {
		
		int ind = 0;
		int min = array[ind];
		
		for (int i = 1; i < array.length; ++i) {
			
			if (array[i] < min) {
				min = array[i];
				ind = i;
			}
		}
		
		return ind;
	} 
	
	// Возвращает кол-во определенных элементов массива
	public static int countElemInArr(int[] array, int elem) {
		
		int count = 0;
		
		for (int i = 0; i < array.length; ++i) {
			
			if (array[i] == elem) count++;
		}
		
		return count;
	}
}
