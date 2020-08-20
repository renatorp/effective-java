package _10_overriding_equals;

import org.w3c.dom.css.Counter;

import java.awt.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class CompositionOverInheritance {

	// Adds a value component without violating the equals contract
	static class ColorPoint {

		private final Point point;
		private final Color color;

		public ColorPoint(int x, int y, Color color) {
			point = new Point(x, y);
			this.color = Objects.requireNonNull(color);
		}

		/**
		 * Returns the point-view of this color point.
		 */
		public Point asPoint() {
			return point;
		}

		@Override public boolean equals(Object o) {
			if (!(o instanceof ColorPoint))
				return false;
			ColorPoint cp = (ColorPoint) o;
			return cp.point.equals(point) && cp.color.equals(color);
		}
	}

	static class CounterPoint extends ColorPoint {
		private static final AtomicInteger counter = new AtomicInteger();
		public CounterPoint(int x, int y, Color color) {
			super(x, y, color);
			counter.incrementAndGet();
		}
		public static int numberCreated() { return counter.get(); }
	}
	public static void main(String[] args) {
		Point point = new Point(1,2);
		ColorPoint blackPoint = new ColorPoint(1,2, Color.BLACK);
		ColorPoint bluePoint = new ColorPoint(1,2,Color.BLUE);
		CounterPoint blackCounterPoint = new CounterPoint(1,2,Color.BLACK);

		// 1. does not violate symmetry
		System.out.println(point.equals(bluePoint) + " == " + bluePoint.equals(point));

		// 2. does not violate transitivity
		System.out.println(blackPoint.equals(point) + " and " + point.equals(bluePoint) + " == " + blackPoint.equals(bluePoint));

		// 3. does not violate Liskov substitution principle
		System.out.println(blackPoint.equals(blackCounterPoint) + "!!");
	}
}
