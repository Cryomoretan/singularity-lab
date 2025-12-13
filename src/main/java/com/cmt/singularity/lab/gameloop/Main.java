package com.cmt.singularity.lab.gameloop;

import com.cmt.singularity.Singularity;
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
		log.debug("main:enter");

		log.info("Starting Singularity Lab Game Loop Demo");

		Singularity singularity = Singularity.create();

		Tasks tasks = singularity.getTasks();

		TaskGroup mainGroup = tasks.createTaskGroup("Main", 1, 100, false);
		TaskGroup renderGroup = tasks.createTaskGroup("Render", 1, 1000, true);
		TaskGroup workerGroup = tasks.createTaskGroup("Worker", 4, 1000, true);

		Task beginFrame = new BeginFrame(mainGroup);

		mainGroup.parallel(beginFrame);

		tasks.endGracefully();

		log.debug("main:exit");
	}
}
