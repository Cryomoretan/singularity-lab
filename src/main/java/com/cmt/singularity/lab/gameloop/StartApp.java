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

import com.cmt.singularity.Configuration;
import com.cmt.singularity.assertion.Assert;
import static com.cmt.singularity.lab.gameloop.Main.*;
import com.cmt.singularity.tasks.Task;
import com.cmt.singularity.tasks.TaskGroup;
import com.cmt.singularity.tasks.Tasks;

/**
 *
 * @author Benjamin Schiller
 */
public class StartApp implements Task
{

	private final static Assert assertion = Assert.getAssert(StartApp.class.getName());

	protected final Tasks tasks;
	protected final TaskGroup mainGroup;
	protected final Configuration configuration;

	public StartApp(Configuration configuration, Tasks tasks, TaskGroup mainGroup)
	{
		assertion.assertNotNull(configuration, "configuration != null");
		assertion.assertNotNull(tasks, "tasks != null");
		assertion.assertNotNull(mainGroup, "mainGroup != null");

		this.tasks = tasks;
		this.mainGroup = mainGroup;
		this.configuration = configuration;
	}

	@Override
	public void execute()
	{
		// Set up render group
		int renderWorkers = configuration.getInt(COFIGURATION_RENDER_WORKER_KEY, COFIGURATION_RENDER_WORKER_DEFAULT);
		tasks.createTaskGroup("Render", renderWorkers, 100, true);

		// Set up worker group
		int workerWorkers = configuration.getInt(COFIGURATION_WORKER_WORKER_KEY, COFIGURATION_WORKER_WORKER_KEY_DEFAULT);
		tasks.createTaskGroup("Worker", workerWorkers, 100, true);

		// End app task
		Task endApp = new EndApp(tasks);

		// Create and schedule first frame begin
		Task beginFrame = new BeginFrame(configuration, mainGroup, endApp);
		mainGroup.parallel(beginFrame);
	}
}
