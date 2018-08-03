package ccx.world;

import java.util.HashSet;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class VillageGenerator implements IWorldGenerator {
    private static HashSet<Integer> coordsTried = new HashSet<>();

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == 0) {
            BlockPos pos1 = new BlockPos(chunkX * 16, 0, chunkZ * 16);
            try {
                generateVillageAt(random, world.getHeight(pos1), world);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handles the actual generation of a village.
     * @param rand A random, currently unused
     * @param pos The position to generate the center of the village on (roughly)
     * @param world The world we are generating in
     */
    private void generateVillageAt(Random rand, BlockPos pos, World world) {

        //If we shouldn't generate, don't.
//        if (!(MillConfig.generateVillages || MillConfig.generateLoneBuildings)) {
//            return;
//        }

        //Don't try and generate a village client-side
        if (world.isRemote) {
            return;
        }

        // moved this check here and corrected a bug - Too close to spawn point.
//        if (world.getSpawnPoint().distanceSq(pos) < MillConfig.spawnDistance * MillConfig.spawnDistance) {
//            return;
//        }

        // Avoid checking same chunk again
        if (coordsTried.contains(pos.getX() + (pos.getZ() << 16))) {
            return;
        }

        BlockPos villageCenter;

        // check that all 11 x 11 chunks are loaded (they seldom are).
        if (areChunksLoaded(world, pos)) {
            villageCenter = pos;
        } else {
            // check surrounding chunks in a 13 x 13 area - this could be optimized I think
            villageCenter = anyAreaCloseByLoaded(world, pos);
            if (villageCenter == null) {
                return;
            }
        }

        //TODO: Use configured radius?
        int villageRadius = 60;

        //Get the four corners of the area we are generating in.
        BlockPos corner1 = villageCenter.add(villageRadius, 0, 0).subtract(new Vec3i(0, 0, villageRadius));
        BlockPos corner2 = villageCenter.add(villageRadius, 0, villageRadius);
        BlockPos corner3 = villageCenter.subtract(new Vec3i(villageRadius, 0, villageRadius));
        BlockPos corner4 = villageCenter.subtract(new Vec3i(villageRadius, 0, 0)).add(0, 0, villageRadius);

//        //If any of these are in the ocean, OR the center is in a river, we don't want to generate here.
//        if (world.getBiomeForCoordsBody(corner1).getBiomeForId() == BiomeGenBase.ocean.biomeID
//                || Biome.getIdForBiome(world.getBiomeForCoordsBody(corner2)) == BiomeGenBase.ocean.biomeID
//                || world.getBiomeForCoordsBody(corner3).biomeID == BiomeGenBase.ocean.biomeID
//                || world.getBiomeForCoordsBody(corner4).biomeID == BiomeGenBase.ocean.biomeID
//                || world.getBiomeForCoordsBody(villageCenter).biomeID == BiomeGenBase.ocean.biomeID
//                || world.getBiomeForCoordsBody(corner1).biomeID == BiomeGenBase.deepOcean.biomeID
//                || world.getBiomeForCoordsBody(corner2).biomeID == BiomeGenBase.deepOcean.biomeID
//                || world.getBiomeForCoordsBody(corner3).biomeID == BiomeGenBase.deepOcean.biomeID
//                || world.getBiomeForCoordsBody(corner4).biomeID == BiomeGenBase.deepOcean.biomeID
//                || world.getBiomeForCoordsBody(villageCenter).biomeID == BiomeGenBase.deepOcean.biomeID) {
//            return; //Water is a big no-no
//        }

        //Now check that all the corners are in the same biome as the center.
        //We allow 2 of the 4 corners to be a different biome, but not 3 - i.e. we guarantee that 3 of 5 points are in the same biome.
        int biomeMismatches = 0;

        if (Biome.getIdForBiome(world.getBiomeForCoordsBody(villageCenter)) != Biome.getIdForBiome(world.getBiomeForCoordsBody(corner1))) {
            biomeMismatches++;
        }
        if (Biome.getIdForBiome(world.getBiomeForCoordsBody(villageCenter)) != Biome.getIdForBiome(world.getBiomeForCoordsBody(corner2))) {
            biomeMismatches++;
        }
        if (Biome.getIdForBiome(world.getBiomeForCoordsBody(villageCenter)) != Biome.getIdForBiome(world.getBiomeForCoordsBody(corner3))) {
            biomeMismatches++;
        }
        if (Biome.getIdForBiome(world.getBiomeForCoordsBody(villageCenter)) != Biome.getIdForBiome(world.getBiomeForCoordsBody(corner4))) {
            biomeMismatches++;
        }

        if (biomeMismatches > 2) {
            return; //Biome mismatch
        }

        //Unused so far, but for players generating the village - TODO: could be used to fun effect with announcements?
        EntityPlayer generatingPlayer = world.getClosestPlayer((double)villageCenter.getX(), (double)villageCenter.getY(), (double)villageCenter.getZ(), 10.0, true);

        // remember that this chunk has been checked for later
        coordsTried.add(villageCenter.getX() + (villageCenter.getZ() << 16));

        // check if other villages are too close
        //if (!VillageTracker.get(world).isTooCloseToOtherVillage(villageCenter, MillConfig.minVillageDistance)) {
        	if (true) {
            BlockPos nPos = new BlockPos(villageCenter.getX(), 10, villageCenter.getZ());

            System.out.println("Attempting to generate a village at or near " + nPos.getX() + ", " + nPos.getZ());
            //VillageTracker.get(world).registerVillagePos(nPos);
            world.setBlockState(nPos, Blocks.DIRT.getDefaultState());
        }
    }

    /**
     * Checks if an 11x11-chunk area centered on the given position is all loaded.
     *
     * @param world The world to be checking in
     * @param pos The position that is the center of the 11x11-chunk area you want to test.
     * @return True if all the chunks are loaded, otherwise false.
     */
    private boolean areChunksLoaded(World world, BlockPos pos) {
        BlockPos bPos1 = new BlockPos(pos.getX() - 5 * 16, pos.getY(), pos.getZ() - 5 * 16);
        BlockPos bPos2 = new BlockPos(pos.getX() + 5 * 16, pos.getY(), pos.getZ() + 5 * 16);

        return world.isAreaLoaded(bPos1, bPos2);
    }

    /**
     * Checks if sufficient chunks to generate a village are loaded within a 13-chunk radius loosely centered on the given position.
     *
     * @param world The world to check for loaded chunks in.
     * @param pos The position that is roughly the center of the area to check
     * @return The center of any 11x11 area that is loaded - i.e. where a village should be generated.
     */
    private BlockPos anyAreaCloseByLoaded(World world, BlockPos pos) {
        for (int chunkX = -6; chunkX < 7; chunkX++) {
            for (int chunkZ = -6; chunkZ < 7; chunkZ++) {
                final int worldX = pos.getX() + chunkX * 16;
                final int worldZ = pos.getZ() + chunkZ * 16;
                if (!coordsTried.contains(worldX + (worldZ << 16))) {
                    BlockPos newPos = new BlockPos(worldX, pos.getY(), worldZ);
                    if (areChunksLoaded(world, newPos)) {
                        return newPos;
                    }
                }
            }
        }
        return null;
    }

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		// TODO Auto-generated method stub
		
	}
}
