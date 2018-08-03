package ccx.build;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BuildBlocks {
    public IBlockState blockState;
    public BlockPos position;
    public byte specialBlock;
    
    public void build(World worldIn, boolean onGeneration) {
        //if (specialBlock != PRESERVEGROUNDDEPTH && specialBlock != PRESERVEGROUNDSURFACE && specialBlock != CLEARTREE) {
        	if (true) {
            if (blockState != null) {
                worldIn.setBlockState(position, blockState);
                //String soundName = blockState.getBlock().stepSound.getPlaceSound();
                //worldIn.playSound(position.getX() + 0.5D, position.getY() + 0.5D, position.getZ() + 0.5D, soundName, 0.3F, 0.6F);
            }
        }
        	
        	int i = position.getX();
        	int j = position.getY();
        	int k = position.getZ();
        	BlockPos bpos = new BlockPos(i, j, k);

        	worldIn.setBlockState(bpos, Blocks.GOLD_BLOCK.getDefaultState(), 0);
        	
    }

}
