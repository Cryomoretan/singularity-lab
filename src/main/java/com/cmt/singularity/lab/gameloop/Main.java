package com.cmt.singularity.lab.gameloop;

import com.cmt.singularity.Configuration;
import com.cmt.singularity.Singularity;
import static com.cmt.singularity.lab.gameloop.MaxFrameCountConfigurationAccessor.getMaxFrameCount;
import static com.cmt.singularity.lab.gameloop.RenderWorkersConfigurationAccessor.getRenderWorkers;
import static com.cmt.singularity.lab.gameloop.WorkerWorkersConfigurationAccessor.getWorkerWorkers;
import com.cmt.singularity.tasks.Task;
import com.cmt.singularity.tasks.TaskGroup;
import com.cmt.singularity.tasks.TaskGroupLogConfigurationAccessor;
import static com.cmt.singularity.tasks.TaskGroupLogConfigurationAccessor.getTaskGroupLog;
import com.cmt.singularity.tasks.Tasks;
import de.s42.log.LogManager;
import de.s42.log.Logger;

/**
 *
 * Set a custom value for configurations via cmd i.e. with -com.cmt.singularity.lab.gameloop.maxFrameCount=X etc. - see
 * ConfigurationAccessors or use the ConfigurationAccessors setters in Java code
 *
 * @author Benjamin Schiller
 */
public class Main
{

	private final static Logger log = LogManager.getLogger(Main.class.getName());

	public static void main(String[] args)
	{
		// Create initial configuration
		Configuration configuration = Configuration.create(args);
		// Override a config value
		//setTaskGroupLog(configuration, true);

		// Print configuration
		log.debug("Configuration", TaskGroupLogConfigurationAccessor.KEY, getTaskGroupLog(configuration));
		log.debug("Configuration", MaxFrameCountConfigurationAccessor.KEY, getMaxFrameCount(configuration));
		log.debug("Configuration", RenderWorkersConfigurationAccessor.KEY, getRenderWorkers(configuration));
		log.debug("Configuration", WorkerWorkersConfigurationAccessor.KEY, getWorkerWorkers(configuration));

		// Create engine root
		Singularity singularity = Singularity.create(configuration);

		// Get the core tasks for task execution
		Tasks tasks = singularity.getTasks();

		// Create "main thread" task group
		TaskGroup mainGroup = tasks.createTaskGroup("Main", 1, 10, false);

		// Create and schedule the app loop
		Task startApp = new StartApp(configuration, tasks, mainGroup);
		mainGroup.parallel(startApp);
	}
}
