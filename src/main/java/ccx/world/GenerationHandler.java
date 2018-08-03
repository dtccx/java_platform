package ccx.world;

import java.util.Random;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class GenerationHandler implements IWorldGenerator {
	private final WorldGenMinable houseRoof;
	private final WorldGenMinable houseBase;

	public GenerationHandler() {
		houseRoof = new WorldGenMinable(Blocks.GOLD_ORE.getDefaultState(), 20, BlockMatcher.forBlock(Blocks.AIR));
		houseBase = new WorldGenMinable(Blocks.GOLD_BLOCK.getDefaultState(), 20, BlockMatcher.forBlock(Blocks.AIR));
	}

	@Override
	public void generate(final Random random, final int chunkX, final int chunkZ, final World world, final IChunkGenerator chunkGenerator, final IChunkProvider chunkProvider) {
		final BlockPos chunkPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
		final BlockPos chunkPos2 = new BlockPos(chunkX * 16, 10, chunkZ * 16);

		switch (world.provider.getDimensionType()) {
			case NETHER:
				for (int i = 0; i < 16; i++) {
					houseBase.generate(world, random, chunkPos.add(random.nextInt(16), random.nextInt(108) + 10, random.nextInt(16)));
					System.out.println("NETHERNETHERNETHERNETHER");
				}
				break;
			case THE_END:
				for (int i = 0; i < 16; i++) {
					houseBase.generate(world, random, chunkPos.add(i, 0, i));
					System.out.println("THE_ENDTHE_ENDTHE_END");
				}
				break;
			case OVERWORLD:
				
					houseBase.generate(world, random, chunkPos.add(16, 10, 16));
					houseRoof.generate(world, random, chunkPos2.add(16, 20, 16));
					System.out.println("O OOOOO");
				
				break;
		}
	}
}