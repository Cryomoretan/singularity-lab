package com.cmt.singularity.lab.gameloop;

import com.cmt.singularity.Configuration;
import com.cmt.singularity.Singularity;
import com.cmt.singularity.tasks.Task;
import com.cmt.singularity.tasks.TaskGroup;
import com.cmt.singularity.tasks.Tasks;

/**
 *
 * @author Benjamin Schiller
 */
public class Main
{

	public static final String COFIGURATION_RENDER_WORKER_KEY = "com.cmt.singularity.lab.gameloop.renderWorkers";
	public static final int COFIGURATION_RENDER_WORKER_DEFAULT = 1;

	public static final String COFIGURATION_WORKER_WORKER_KEY = "com.cmt.singularity.lab.gameloop.workerWorkers";
	public static final int COFIGURATION_WORKER_WORKER_KEY_DEFAULT = 4;

	public static final String COFIGURATION_MAX_FRAME_COUNT_KEY = "com.cmt.singularity.lab.gameloop.maxFrameCount";
	public static final int COFIGURATION_MAX_FRAME_COUNT_DEFAULT = 0;

	public static void main(String[] args)
	{
		// Create initial configuration
		Configuration configuration = Configuration.create(args);

		// Set a custom fixed value for COFIGURATION_MAX_FRAME_COUNT_KEY
		// if absent (no arg -com.cmt.singularity.lab.gameloop.maxFrameCount=X)
		configuration.setIfAbsent(COFIGURATION_MAX_FRAME_COUNT_KEY, 12);

		// Create engine root
		Singularity singularity = Singularity.create(configuration);

		// Get the core tasks for task execution
		Tasks tasks = singularity.getTasks();

		// Create "main thread" task group
		TaskGroup mainGroup = tasks.createTaskGroup("Main", 1, 100, false);

		// Create and schedule the app loop
		Task startApp = new StartApp(configuration, tasks, mainGroup);
		mainGroup.parallel(startApp);
	}
}
