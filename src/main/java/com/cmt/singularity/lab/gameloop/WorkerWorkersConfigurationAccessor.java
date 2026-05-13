// <editor-fold desc="The MIT License" defaultstate="collapsed">
/*
 * The MIT License
 *
 * Copyright 2026 Cryomoretan GmbH.
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
import com.cmt.singularity.ConfigurationAccessor;
import com.cmt.singularity.assertion.Assert;

/**
 *
 * @author Benjamin Schiller
 */
public final class WorkerWorkersConfigurationAccessor implements ConfigurationAccessor
{

	public final static Assert assertion = Assert.getAssert(WorkerWorkersConfigurationAccessor.class.getName());

	/**
	 * Key in config for workerWorkers
	 */
	public static final String KEY = "com.cmt.singularity.lab.gameloop.workerWorkers";

	/**
	 * Default in config for workerWorkers
	 */
	public static final int DEFAULT = 4;

	public static int getWorkerWorkers(Configuration configuration)
	{
		assertion.assertNotNull(configuration, "configuration != null");

		return configuration.getInt(KEY, DEFAULT);
	}

	public static void setWorkerWorkers(Configuration configuration, int workerWorkers)
	{
		assertion.assertNotNull(configuration, "configuration != null");

		configuration.set(KEY, workerWorkers);
	}

	@SuppressWarnings("unused")
	private WorkerWorkersConfigurationAccessor()
	{
		// NEVER INSTANTIATED
	}
}
