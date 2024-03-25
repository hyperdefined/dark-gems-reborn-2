package hyperdefined.dgr2.tools;

import java.util.List;

import hyperdefined.dgr2.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class MagicalPickaxe extends ItemPickaxe{

	public MagicalPickaxe(int i,ToolMaterial gem5) {
		super(gem5);
		this.setUnlocalizedName("magical_pickaxe");
		this.setTextureName(Reference.MODID + ":" + "magical_pickaxe");
	}
	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack){
		return EnumRarity.epic;
	}
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List lores, boolean par4) {
		lores.add("Tier 5");
	}
}