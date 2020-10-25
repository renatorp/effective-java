package stream;

import java.util.Arrays;
import java.util.EnumMap;

import java.util.Set;
import java.util.stream.Collectors;

public class GroupingBy {
	private static class Plant {
		enum LifeCycle {ANNUAL, PERENNIAL, BIENNIAL}

		final String name;
		final LifeCycle lifeCycle;

		public Plant(String name, LifeCycle lifeCycle) {
			this.name = name;
			this.lifeCycle = lifeCycle;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public static void main(String[] args) {
		Plant[] plants = new Plant[]{
			new Plant("p1", Plant.LifeCycle.ANNUAL),
			new Plant("p2", Plant.LifeCycle.BIENNIAL),
			new Plant("p3", Plant.LifeCycle.PERENNIAL),
			new Plant("p4", Plant.LifeCycle.ANNUAL)
		};

		EnumMap<Plant.LifeCycle, Set<Plant>> collect = Arrays.stream(plants)
			.collect(Collectors.groupingBy(p -> p.lifeCycle, () -> new EnumMap<>(Plant.LifeCycle.class), Collectors.toSet()));

		for (Plant p : collect.get(Plant.LifeCycle.ANNUAL)) {
			System.out.println(p.name);
		}
	}
}
