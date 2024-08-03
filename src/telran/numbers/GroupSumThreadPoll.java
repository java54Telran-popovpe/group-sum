package telran.numbers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GroupSumThreadPoll extends GroupsSum {
	ExecutorService executorService;

	public GroupSumThreadPoll(int[][] groups) {
		super(groups);
		executorService = Executors.newFixedThreadPool( getNumberOfThreads());
	}

	private int getNumberOfThreads() {
		Runtime runtime = Runtime.getRuntime();
		return runtime.availableProcessors();
	}

	@Override
	public long getSum() {
		SumArrayTask[] tasks = getTasks();
		executeTask(tasks);
		executorService.shutdown();
		try {
			executorService.awaitTermination(1, TimeUnit.DAYS);
			
		} catch (InterruptedException e) {
			// no interrupts
		}
		return sumOfGroups(tasks);
	}

	private void executeTask(SumArrayTask[] tasks) {
		for(SumArrayTask task: tasks) {
			executorService.execute(task);
		}
		
	}

}
