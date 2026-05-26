package com.cmt.singularity.lab.vulkan;

import com.cmt.singularity.Singularity;
import com.cmt.singularity.StandardSingularity;
import com.cmt.singularity.assertion.Assert;
import com.cmt.singularity.compute.Compute;
import com.cmt.singularity.compute.Task;
import de.s42.log.LogManager;
import de.s42.log.Logger;

/**
 *
 * Simple Singularity Vulkan Demo
 *
 * https://vulkan.org/learn
 *
 * https://howtovulkan.com/
 *
 * https://shader-slang.org/
 *
 * https://github.com/LWJGL/lwjgl3-demos/tree/main/src/org/lwjgl/demo/vulkan
 *
 * https://github.com/Naitsirc98/Vulkan-Tutorial-Java
 *
 * https://www.lwjgl.org/ https://vulkan-tutorial.com/
 *
 * https://github.com/club-doki7/vulkan4j
 *
 * @author Benjamin Schiller
 */
public class Main
{

	@SuppressWarnings("unused")
	private final static Logger log = LogManager.getLogger(Main.class.getName());

	@SuppressWarnings("unused")
	private final static Assert assertion = Assert.getAssert(Main.class.getName());

	public static void main(String[] args)
	{

		// Create engine root
		Singularity singularity = new StandardSingularity();

		// @todo Override a config value in code
		// @todo Load args into config
		// @todo Print configuration
		// Get compute for task execution
		Compute compute = singularity.getCompute();

		// Start App
		Task startApp = new StartApp(compute);
		compute.getMainGroup().sequential(startApp);
	}
}
