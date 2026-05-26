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
package com.cmt.singularity.lab.vulkan;

import com.cmt.singularity.assertion.Assert;
import com.cmt.singularity.compute.*;
import com.cmt.singularity.vulkan.CreateInstance;
import com.cmt.singularity.vulkan.GlfwInit;
import de.s42.log.LogManager;
import de.s42.log.Logger;

/**
 *
 * @author Benjamin Schiller
 */
public class StartApp implements Task
{

	private final static Logger log = LogManager.getLogger(StartApp.class.getName());

	private final static Assert assertion = Assert.getAssert(StartApp.class.getName());

	protected final Compute compute;
	protected final ComputeGroup mainGroup;

	public StartApp(Compute compute, ComputeGroup mainGroup)
	{
		assertion.assertNotNull(compute, "tasks != null");
		assertion.assertNotNull(mainGroup, "mainGroup != null");

		this.compute = compute;
		this.mainGroup = mainGroup;
	}

	@Override
	public void execute()
	{
		log.debug("Starting App");

		// Prepare Vulkan
		// Create and schedule the init phase
		Task glfwInit = new GlfwInit();
		Task createInstance = new CreateInstance();
		TaskBarrier initDone = mainGroup.sequentialBefore(
			glfwInit,
			createInstance
		);

		// Wait for init done and end app
		Task endGracefully = new EndGracefully(compute);
		mainGroup.sequentialAfter(initDone,
			endGracefully
		);

	}
}
