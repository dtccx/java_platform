package ccx;

import ccx.world.GenerationHandler;
import ccx.world.WorldGenerate;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegistryHandler {
	public static void preInitRegistries()
	{	
		/*
		 * Here is an example of how to use world generation to generate world
		 * When you load a map.
		 * You can try this without command if you are interested.
		 */
		
		//GameRegistry.registerWorldGenerator(new WorldGenerate(), 100);
		//GameRegistry.registerWorldGenerator(new VillageGenerator(), 0);
		//GameRegistry.registerWorldGenerator(new GenerationHandler(), 0);
		
	}
	
	public static void initRegistries()
	{
		
	}
	
	public static void postInitRegistries()
	{
		
	}
}
