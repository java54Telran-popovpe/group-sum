package telran.numbers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.stream.Stream;

import telran.numbers.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class GroupsSumTest {
	private static final int N_GROUPS = 850;
	private static final int GROUP_LENGTH = 200_000;
	int[][] groups = {
			{1,2},
			{3,4},
			{5,6}
	};
	int[][] largeGroups = getLargeGroups(N_GROUPS, GROUP_LENGTH); 
	@Disabled
	@Test
	void goupsSumTaskThreadTest() {
		GroupsSum gs = new GroupsSumTasksThread(groups);
		assertEquals(21, gs.getSum());
	}
	@Disabled
	@Test
	void goupsSumThreadPoolTest() {
		GroupsSum gs = new GroupSumThreadPoll(groups);
		assertEquals(21, gs.getSum());
	}	
	
	private int[][] getLargeGroups(int nGroups, int groupLength) {
		// TODO Auto-generated method stub - one pipeline (with additional func)using method generate
		return Stream.generate(()-> new Random().ints(GROUP_LENGTH).toArray())
				.limit(N_GROUPS).toArray(i -> new int[i][]);
	}

	@Test
	void groupsSumTaskThreadPerformance() {
		new GroupsSumTasksThread(largeGroups).getSum();
	}
	@Test
	void groupsSumTaskThreadPoolsPerformance() {
		new GroupSumThreadPoll(largeGroups).getSum();
	}
}
