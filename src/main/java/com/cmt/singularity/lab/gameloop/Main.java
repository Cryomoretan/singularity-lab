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

	public static void main(String[] args)
	{
		// Create initial configuration
		Configuration configuration = Configuration.create(args);

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
