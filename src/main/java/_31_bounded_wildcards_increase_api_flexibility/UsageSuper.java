package _31_bounded_wildcards_increase_api_flexibility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class UsageSuper {

	public static void main(String[] args) {
		List<Number> numbers = new ArrayList<>();
		numbers.add(2.3);
		addIntegers(numbers);
		System.out.println(numbers);

		List<Integer> integers = new ArrayList<>();
		integers.add(10);
		addIntegers(integers);
		System.out.println(integers);

		MyComparableClass1 c1 = new MyComparableClass1(2);
		MyComparableClass1 c2 = new MyComparableClass1(70);
		MyComparableClass1 c3 = new MyComparableClass1(20);
		System.out.println(max(List.of(c1, c2, c3)));

		MyComparableClass2 cc1 = new MyComparableClass2(2);
		MyComparableClass2 cc2 = new MyComparableClass2(70);
		MyComparableClass2 cc3 = new MyComparableClass2(20);
		System.out.println(max(List.of(cc1, cc2, cc3)));
	}

	private static void addIntegers(Collection<? super Integer> existingNumbers) {
		existingNumbers.add(3);
		existingNumbers.add(4);
		existingNumbers.add(2);
		existingNumbers.add(5);
	}

	private static class MyComparableClass1 implements Comparable<MyComparableClass1> {
		private Integer property;

		public MyComparableClass1(Integer property) {
			this.property = property;
		}

		@Override
		public int compareTo(MyComparableClass1 o) {
			return property.compareTo(o.property);
		}

		@Override
		public String toString() {
			return property.toString();
		}
	}

	private static class MyComparableClass2 implements Comparable<Object> {
		private Integer property;


		public MyComparableClass2(Integer property) {
			this.property = property;
		}

		@Override
		public String toString() {
			return property.toString();
		}

		@Override
		public int compareTo(Object o) {
			return Integer.valueOf(this.hashCode()).compareTo(Integer.valueOf(o.hashCode())) ;
		}
	}

	private static <T extends Comparable<? super T>> T max(List<? extends T> list) {
		Iterator<? extends T> iterator = list.iterator();
		T max = iterator.next();

		while (iterator.hasNext()) {
			T next = iterator.next();
			if (next.compareTo(max) > 0) {
				max = next;
			}
		}

		return max;
	}

}
