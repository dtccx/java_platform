package ccx.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockRegistry {
	public static Block houseBlock;
	
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		houseBlock = new HouseBlock(null, Material.ROCK, 0, 0);
		houseBlock.setRegistryName("houseBlock");
		houseBlock.setUnlocalizedName(houseBlock.getRegistryName().toString());
		
		//version before 1.12
//		GameRegistry.register(houseBlock);
//		GameRegistry.register(houseBlock.setRegistryName(houseBlock.getUnlocalizedName()));
		
		final IForgeRegistry<Block> registry = event.getRegistry();
		registry.register(houseBlock);
	}
}
