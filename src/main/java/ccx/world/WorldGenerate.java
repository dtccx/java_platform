package ccx.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import ccx.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenerate implements IWorldGenerator {

	//public static final WorldGenStructure ARMOURY = new WorldGenStructure("armoury");
	
	private final WorldGenMinable HOUSECC;
	
	//private final WorldGenerator ALUMINIUM = new WorldGenAluminiumTree();
	public WorldGenerate() {
		HOUSECC = new WorldGenMinable(Blocks.IRON_ORE.getDefaultState(), 20, BlockMatcher.forBlock(Blocks.DIRT));
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) 
	{
		final BlockPos chunkPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
		switch(world.provider.getDimension())
		{
		case 2:
			runGenerator(HOUSECC, world, random, chunkX, chunkZ, 5, BlockInit.DIRT);
			System.out.println("Generating 222222222");
			break;
			
		case 1:
			runGenerator(HOUSECC, world, random, chunkX, chunkZ, 5, BlockInit.DIRT);
			System.out.println("Generating 111111111");
			break;
			
		case 0:
			
			//generateStructure(ARMOURY, world, random, chunkX, chunkZ, 20, BlockInit.DIRT, BiomeCopper.class);
			runGenerator(HOUSECC, world, random, chunkX, chunkZ, 5, null);
			System.out.println("Generating 00000000\n");
			//runGenerator(ALUMINIUM, world, random, chunkX, chunkZ, 5, Blocks.GRASS, BiomeForest.class);
			
			for (int i = 0; i < 16; i++) {
				HOUSECC.generate(world, random, chunkPos.add(random.nextInt(16), random.nextInt(128), random.nextInt(16)));
				System.out.println("HOUSECC.generate(world, random)");
			}
			
			break;
			
		case -1:
			
		}
	}
	
	
	//example
//	private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes)
//	{
//		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
//		
//		int x = (chunkX * 16) + random.nextInt(15) + 8;
//		int z = (chunkZ * 16) + random.nextInt(15) + 8;
//		int y = calculateGenerationHeight(world, x, z, topBlock);
//		BlockPos pos = new BlockPos(x,y,z);
//		
//		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
//		
//		if(world.getWorldType() != WorldType.FLAT)
//		{
//			if(random.nextInt(chance) == 0)
//				{
//					generator.generate(world, random, pos);
//					System.out.println("Generating");
//				}
//		}\
//		
//	}
//	
	private void runGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes)
	{
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
		
		int x = (chunkX * 16) + random.nextInt(15) + 8;
		int z = (chunkZ * 16) + random.nextInt(15) + 8;
		
		int y = calculateGenerationHeight(world, x, z, topBlock);
		System.out.println(x + " YYY " + y + " ZZZ " + z);
		BlockPos pos = new BlockPos(x,y,z);
		
		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
		System.out.println("Before Gener");
//		if(world.getWorldType() != WorldType.FLAT)
//		{

				if(random.nextInt(chance) == 0)
				{
					generator.generate(world, random, pos);
					System.out.println("Generating Ho uses");
				}
			
//		}
	}
	
	private static int calculateGenerationHeight(World world, int x, int z, Block topBlock)
	{
		int y = world.getHeight();
		boolean foundGround = false;
		
		while(!foundGround && y-- >= 0)
		{
			Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			foundGround = block == topBlock;
		}
		
		return y;
	}
	
	
	

}
