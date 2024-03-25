package hyperdefined.dgr2.blocks;

import hyperdefined.dgr2.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlueOre extends Block{

	public BlueOre(int par1, int par2) {
		super(Material.rock);
		this.setStepSound(soundTypeStone);
		this.setHardness(5f);
		this.setResistance(7f);
		this.setHarvestLevel("pickaxe", 20);
		this.setBlockTextureName(Reference.MODID + ":" + "blue_ore");
		this.setBlockName("blue_ore");
	}
}