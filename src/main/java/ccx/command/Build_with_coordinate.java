package ccx.command;

import java.util.ArrayList;
import java.util.List;

import ccx.build.BuildBlocks;
import net.minecraft.block.Block;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class Build_with_coordinate implements ICommand {

	private final List aliases;
	protected ResourceLocation fullEntityName;
	protected Entity conjuredEntity;

	public Build_with_coordinate() {
		aliases = new ArrayList();
		aliases.add("build");
	}

	@Override
	public boolean isUsernameIndex(String[] var1, int var2) {
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
		return "build";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "build <text>";
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

		if (world.isRemote) {
			System.out.println("Not processing on Client side");
		} else {
			System.out.println("Processing on Server side");
			if (args.length != 6) {
				//build x1 y1 z1 x2 y2 z2
				sender.sendMessage(new TextComponentString("Invalid argument"));
				return;
			}

			sender.sendMessage(new TextComponentString("build: [" + args[0] + "]"));

			//Block BlockName = Block.getBlockFromName(args[0]);
//			if (BlockName == null) {
//				sender.sendMessage(new TextComponentString("Invalid Blockname, enter valid as 'GOLD_BLOCK'"));
//			}

			if (args[0].length() != 0) {
				int x1 = Integer.valueOf(args[0]);
				int y1 = Integer.valueOf(args[1]);
				int z1 = Integer.valueOf(args[2]);
				int x2 = Integer.valueOf(args[3]);
				int y2 = Integer.valueOf(args[4]);
				int z2 = Integer.valueOf(args[5]);
				BuildBlocks.buildBlocks(world, x1, y1, z1, x2, y2, z2);
				// world.setBlockState(sender.getPosition(), BlockName.getDefaultState());
			} else {
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
