package _10_overriding_equals;

import java.awt.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class EquivalenceRelation {

	static class ColorPoint extends Point {
		private final Color color;
		public ColorPoint(int x, int y, Color color) {
			super(x, y);
			this.color = color;
		}

		// Broken - violates symmetry!
		@Override public boolean equals(Object o) {
			if (!(o instanceof ColorPoint))
				return false;
			return super.equals(o) && ((ColorPoint) o).color == color;
		}
	}

	static class ColorPointV2 extends Point {
		private final Color color;
		public ColorPointV2(int x, int y, Color color) {
			super(x, y);
			this.color = color;
		}

		// Broken - violates transitivity!
		@Override public boolean equals(Object o) {
			if (!(o instanceof Point))
				return false;

			// If o is a normal Point, do a color-blind comparison
			if (!(o instanceof ColorPointV2))
				return o.equals(this);

			// o is a ColorPoint; do a full comparison
			return super.equals(o) && ((ColorPointV2) o).color == color;
		}
	}

	static class ColorPointV3 extends Point {
		private final Color color;
		public ColorPointV3(int x, int y, Color color) {
			super(x, y);
			this.color = color;
		}

		// Broken - violates Liskov substitution principle (page 43)
		@Override public boolean equals(Object o) {
			if (o == null || o.getClass() != getClass())
				return false;
			Point p = (Point) o;
			return p.x == x && p.y == y;
		}
	}

	static class CounterPoint extends ColorPointV3 {
		private static final AtomicInteger counter = new AtomicInteger();
		public CounterPoint(int x, int y, Color color) {
			super(x, y, color);
			counter.incrementAndGet();
		}
		public static int numberCreated() { return counter.get(); }
	}

	public static void main(String[] args) {
		Point point = new Point(1, 3);
		Point bluePoint = new ColorPoint(1, 3, Color.BLUE);

		// 1. violates symmetry
		System.out.println(point.equals(bluePoint) + " <> " + bluePoint.equals(point));


		Point yellowPoint = new ColorPointV2(1, 3, Color.YELLOW);
		Point redPoint = new ColorPointV2(1, 3, Color.RED);

		// 2. violates transitivity
		System.out.println(yellowPoint.equals(point) + " and " + point.equals(redPoint) + " <> " + yellowPoint.equals(redPoint));


		Point blackCounterPoint = new CounterPoint(1, 3, Color.BLACK);
		Point blackPoint = new ColorPointV3(1, 3, Color.BLACK);

		// 3.  violates Liskov substitution principle
		System.out.println(blackPoint.equals(blackCounterPoint) + "!!");
	}

}
