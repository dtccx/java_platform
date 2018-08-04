package ccx.build;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BuildBlocks {
    public IBlockState blockState;
    public byte specialBlock;

	public static void build(World world, BlockPos position, Block block, String args, int height, int width, int length) {
		// TODO Auto-generated method stub
		int x = position.getX();
		int y = position.getY();
    		int z = position.getZ();
    		if (args.toLowerCase().equals("buildhouse")) {
    			buildHouse(world, position, block, height, width, length);
    		}
	}
	
	
	public static void buildHouse(World world, BlockPos position, Block block, int height, int width, int length) {
//		int height = 50;
//		int width = 40;
//		int length = 40;
		int x = position.getX();
		int y = position.getY();
    		int z = position.getZ();
		for (int i = x; i <= x + width; i++) {
			for (int j = y; j < y + length; j++) {
				for (int m = z; m < z + height; m++) {
					world.setBlockState(new BlockPos(i, j , m), block.getDefaultState());
				}
			}
		}
	}
	
	public static void buildRoad(BlockPos position) {
		
	}
}
