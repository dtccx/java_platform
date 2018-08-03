package ccx.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;

public class HouseBlock extends Block {
//	public static final PropertyEnum TYPE = PropertyEnum<T>create("type", EnumType.class);

	public HouseBlock(String unlocalizedName, Material material, float hardness, float resistance) {
		super(material);
		// TODO Auto-generated constructor stub
		this.setUnlocalizedName(unlocalizedName);
		
	}

}
