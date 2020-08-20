package _10_overriding_equals;

public class EqualsRecipe {

	// Class with a typical equals method
	static final class PhoneNumber {
		private final short areaCode, prefix, lineNum;

		public PhoneNumber(int areaCode, int prefix, int lineNum) {
			this.areaCode = rangeCheck(areaCode, 999, "area code");
			this.prefix = rangeCheck(prefix, 999, "prefix");
			this.lineNum = rangeCheck(lineNum, 9999, "line num");
		}

		private static short rangeCheck(int val, int max, String arg) {
			if (val < 0 || val > max)
				throw new IllegalArgumentException(arg + ": " + val);
			return (short) val;
		}

		@Override public boolean equals(Object o) {
			// Use the == operator to check if the argument is a reference to this object.
			if (o == this)
				return true;

			// Use the instanceof operator to check if the argument has the correct type
			if (!(o instanceof PhoneNumber))
				return false;

			// Cast the argument to the correct type
			PhoneNumber pn = (PhoneNumber)o;

			// For each “significant” field in the class, check if that field of the argument
			//matches the corresponding field of this object
			return pn.lineNum == lineNum && pn.prefix == prefix
				&& pn.areaCode == areaCode;

			/* For primitive fields whose type is not float or double, use the == operator for
				comparisons; for object reference fields, call the equals method recursively;
				for float fields, use the static Float.compare(float, float) method; and
				for double fields, use Double.compare(double, double). The special treatment of float and double fields is made necessary by the existence of
				Float.NaN, -0.0f and the analogous double values
			*/

			/*
				Some object reference fields may legitimately contain null. To avoid the
				possibility of a NullPointerException, check such fiel
			*/

			/*  when field comparisons are more complex than simple equality tests, you may want
				to store a canonical form of the field so the equals method can do a cheap exact comparison on canonical forms rather than a more costly nonstandard comparison. This technique is most appropriate for immutable classes (Item 17); if
				the object can change, you must keep the canonical form up to date.
			*/

			/*
				The performance of the equals method may be affected by the order in which
				fields are compared. For best performance, you should first compare fields that
				are more likely to differ, less expensive to compare, or, ideally, both. You must
				not compare fields that are not part of an object’s logical state, such as lock
				fields used to synchronize operations. You need not compare derived fields,
				which can be calculated from “significant fields,” but doing so may improve
				the performance of the equals method. If a derived field amounts to a summary description of the entire object, comparing this field will save you the expense of comparing the actual data if the comparison fails.
			*/
		}

	}
}
