package ccx;

import ccx.command.Command_cmd;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityMoveHelper.Action;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommandHandler {
	public void interact(PlayerInteractEvent event) {
//		Action action = event.;
		EntityPlayer palyer = event.getEntityPlayer();
		World world = palyer.world;
		Block block = world.getBlockState(event.getPos()).getBlock();
		
		
		
	}
	
	
	public static void registercmd(FMLServerStartingEvent event) {
		event.registerServerCommand(new Command_cmd());
	}
	
}
