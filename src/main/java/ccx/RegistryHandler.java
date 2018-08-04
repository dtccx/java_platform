package ccx;

import ccx.world.GenerationHandler;
import ccx.world.VillageGenerator;
import ccx.world.WorldGenerate;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegistryHandler {
	public static void preInitRegistries()
	{	
		// register world generation
		//GameRegistry.registerWorldGenerator(new WorldGenerate(), 100);
		//GameRegistry.registerWorldGenerator(new VillageGenerator(), 0);
		//GameRegistry.registerWorldGenerator(new GenerationHandler(), 0);
		
		
//		BiomeInit.registerBiomes();
//		DimensionInit.registerDimensions();
//		
//		EntityInit.registerEntities();
//		RenderHandler.registerEntityRenders();
	}
	
	public static void initRegistries()
	{
//		SoundsHandler.registerSounds();
		//NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, null);
	}
	
	public static void postInitRegistries()
	{
//		WorldType COPPER = new WorldTypeCopper();
//		WorldType CUSTOM = new WorldTypeCustom();
	}
}
