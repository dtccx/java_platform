package ccx.command;

import java.util.ArrayList;
import java.util.List;

import ccx.build.BuildBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class Command_cmd implements ICommand{
	private final List aliases;
	  
    protected ResourceLocation fullEntityName; 
    protected Entity conjuredEntity; 
  
    public Command_cmd() 
    { 
        aliases = new ArrayList(); 
        aliases.add("conjure"); 
        aliases.add("conj"); 
    } 

    @Override 
    public boolean isUsernameIndex(String[] var1, int var2) 
    { 
        // TODO Auto-generated method stub 
        return false;
    }

	@Override
	public int compareTo(ICommand o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "cmd";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "cmd <text>";
	}

	@Override
	public List<String> getAliases() {
		// TODO Auto-generated method stub
		return this.aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		// TODO Auto-generated method stub
		 World world = sender.getEntityWorld(); 
		    
	        if (world.isRemote) 
	        { 
	            System.out.println("Not processing on Client side"); 
	        } 
	        else 
	        { 
	            System.out.println("Processing on Server side"); 
	            if(args.length <= 3) 
	            { 
	                sender.sendMessage(new TextComponentString("Invalid argument")); 
	                return; 
	            } 
	    
	            sender.sendMessage(new TextComponentString("cmd: [" + args[0]  
	                  + "]")); 
	     
	            //fullEntityName = new ResourceLocation(args[0]); 
	            Block BlockName = Block.getBlockFromName(args[0]);
	            if (BlockName == null) {
	            		sender.sendMessage(new TextComponentString("Invalid Blockname, enter valid as 'GOLD_BLOCK'"));
	            }
//	            if (EntityList.isRegistered(fullEntityName))
	            if (args[1].length() != 0)
	            { 
	            		int height = Integer.valueOf(args[2]);
	            		int width = Integer.valueOf(args[3]);
	            		int length = Integer.valueOf(args[4]);
	                //conjuredEntity = EntityList.createEntityByID(41, world);  
	                
//	                conjuredEntity.setPosition(sender.getPosition().getX(),       
//	                		sender.getPosition().getY(), sender.getPosition().getZ()); 
//	                world.spawnEntity(conjuredEntity); 
	                BuildBlocks.build(world, sender.getPosition(), BlockName, args[1], height, width, length);
	                //world.setBlockState(sender.getPosition(), BlockName.getDefaultState());
	            } 
	            else 
	            { 
	                sender.sendMessage(new TextComponentString("enter valid function as 'buildhouse / buildroad'")); 
	            } 
	        } 
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		// TODO Auto-generated method stub
		return null;
	}
}
