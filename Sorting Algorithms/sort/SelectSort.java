package sort;

import java.util.Comparator;
import java.util.List;

public class SelectSort {
	public static <T extends Comparable<T>> void SelectionSort(T[] input) {
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

	public static <T extends Comparable<T>> void SelectionSort(List<T> input) {
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

	public static <T> void SelectionSort(List<T> input, Comparator<? super T> c) {
		T temp;
		int index;
		// repeat the search & swap n-1 times
		for (int i = 0; i < input.size() - 1; i++) {
			index = i;
			// scan for the smallest key index in the remaining items
			for (int j = i + 1; j < input.size(); j++) {
				if (c.compare(input.get(j), input.get(i)) < 0) {
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
