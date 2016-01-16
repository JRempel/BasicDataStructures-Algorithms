package sort;

import java.util.ArrayList;

public class SelectSort {
	public static <T extends Comparable<T>> void SelectionSort(T[] input) {
		if (input.length == 1)
			return;
		else {
			T temp;
			int index;
			// repeat the search & swap n-1 times
			for (int i = 0; i < input.length - 1; i++) {
				index = i;
				// scan for the smallest key index in the remaining items
				for (int j = i + 1; j < input.length; j++) {
					if (input[j].compareTo(input[i]) < 0) {
						index = j;
					}
				}
				// swap smallest item with item at current index
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
			// repeat the search & swap n-1 times
			for (int i = 0; i < input.size() - 1; i++) {
				index = i;
				// scan for the smallest key index in the remaining items
				for (int j = i + 1; j < input.size(); j++) {
					if (input.get(j).compareTo(input.get(i)) < 0) {
						index = j;
					}
				}
				// swap smallest item with item at current index
				temp = input.get(i);
				input.set(i, input.get(index));
				input.set(index, temp);
			}
		}
	}
}
