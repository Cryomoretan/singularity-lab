// <editor-fold desc="The MIT License" defaultstate="collapsed">
/*
 * The MIT License
 *
 * Copyright 2025 Cryomoretan GmbH.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
//</editor-fold>
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
public class StartApp implements Task
{

	@SuppressWarnings("unused")
	private final static Logger log = LogManager.getLogger(StartApp.class.getName());

	protected Singularity singularity;

	public StartApp()
	{
	}

	public StartApp(Singularity singularity)
	{
		this.singularity = singularity;
	}

	@Override
	public void execute()
	{
		Tasks tasks = singularity.getTasks();

		TaskGroup mainGroup = tasks.getTaskGroupByName("Main").orElseThrow();

		// Set up task groups
		TaskGroup renderGroup = tasks.createTaskGroup("Render", 1, 1000, true);
		TaskGroup workerGroup = tasks.createTaskGroup("Worker", 4, 1000, true);

		// End app task
		Task endApp = new EndApp(singularity);

		// Initiate first frame begin
		Task beginFrame = new BeginFrame(mainGroup, endApp);

		mainGroup.parallel(beginFrame);
	}

	public Singularity getSingularity()
	{
		return singularity;
	}

	public void setSingularity(Singularity singularity)
	{
		this.singularity = singularity;
	}
}
