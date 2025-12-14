package com.cmt.singularity.lab.gameloop;

import com.cmt.singularity.Singularity;
import com.cmt.singularity.Configuration;
import com.cmt.singularity.tasks.Task;
import com.cmt.singularity.tasks.TaskGroup;
import com.cmt.singularity.tasks.Tasks;
import de.s42.log.LogManager;
import de.s42.log.Logger;

/**
 *
 * @author Benjamin Schiller
 */
public class Main
{

	@SuppressWarnings("unused")
	private final static Logger log = LogManager.getLogger(Main.class.getName());

	public static void main(String[] args)
	{
		log.info("Starting Singularity Lab Game Loop Demo");

		Configuration configuration = Configuration.create(args);

		Singularity singularity = Singularity.create(configuration);

		Tasks tasks = singularity.getTasks();

		// Create "main thread" task group
		TaskGroup mainGroup = tasks.createTaskGroup("Main", 1, 100, false);

		Task startApp = new StartApp(singularity);

		mainGroup.parallel(startApp);
	}
}
