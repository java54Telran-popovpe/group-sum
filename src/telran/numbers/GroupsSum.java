package telran.numbers;

import java.util.Arrays;

public abstract class GroupsSum {
	
	public GroupsSum(int[][] groups) {
		this.groups = groups;
	}
	
	protected int[][] groups;
	
	abstract public long getSum();
	
	protected  SumArrayTask[] getTasks() {
		return Arrays.stream(groups).map( a-> new SumArrayTask(a))
				.toArray(SumArrayTask[]::new);
	}
	
	protected long sumOfGroups(SumArrayTask[] tasks) {
		return Arrays.stream(tasks).mapToLong(SumArrayTask::getResult).sum();
	}
	
}
