package telran.numbers;

import java.util.Arrays;
import java.util.stream.IntStream;

public class GroupsSumTasksThread extends GroupsSum {
	

	public GroupsSumTasksThread(int[][] groups) {
		super(groups);
	}

	@Override
	public long  getSum() {
		SumArrayTask[] tasks = getTasks();
		Thread[] threads = startTasks(tasks);
		waitingThreadsFinished(threads);
 		return sumOfGroups(tasks);
	}

	

	private void waitingThreadsFinished(Thread[] threads) {
		for(Thread thread: threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// no interrupts
			}
		}
		
	}

	private Thread[] startTasks( SumArrayTask[] tasks ) {
		Thread[] threads = new Thread[tasks.length];
		IntStream.range(0, threads.length).forEach( i -> { 
			threads[i] = new Thread(tasks[i]);
			threads[i].start();
			}
		);
		return threads;
	}

	protected SumArrayTask[] getTasks() {
		return Arrays.stream(groups).map( a-> new SumArrayTask(a))
				.toArray(SumArrayTask[]::new);
	}

}
