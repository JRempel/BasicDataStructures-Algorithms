package sort;

import java.util.ArrayList;

public class SelectSort {
	public static <T extends Comparable<T>> void SelectionSort(T[] input) {
		if (input.length == 1)
			return;
		else {
			T temp;
			int index;
			for (int i = 0; i < input.length; i++) {
				index = i;
				for (int j = i + 1; j < input.length; j++) {
					if (input[j].compareTo(input[i]) < 0) {
						index = j;
					}
				}
				temp = input[i];
				input[i] = input[index];
				input[index] = temp;
			}
		}
	}
	
	public static <T extends Comparable<T>> void SelectionSort(ArrayList<T> input) {
		if (input.size() <= 1)
			return;
		else {
			T temp;
			int index;
			for (int i = 0; i < input.size(); i++) {
				index = i;
				for (int j = i + 1; j < input.size(); j++) {
					if (input.get(j).compareTo(input.get(i)) < 0) {
						index = j;
					}
				}
				temp = input.get(i);
				input.set(i, input.get(index));
				input.set(index, temp);
			}
		}
	}
}
