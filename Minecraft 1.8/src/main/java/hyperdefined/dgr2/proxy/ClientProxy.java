package hyperdefined.dgr2.proxy;

import hyperdefined.dgr2.Reference;
import hyperdefined.dgr2.init.BlockRegistry;
import hyperdefined.dgr2.init.ItemRegistry;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.ForgeVersion.Status;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
       	ItemRegistry.registerRenders();
    	BlockRegistry.registerRenders();
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
    
    public static class updater {
        public updater() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerJoin(LivingUpdateEvent event) {
    	if (event.entity.worldObj.isRemote && event.entity == FMLClientHandler.instance().getClientPlayerEntity()) {
        MinecraftForge.EVENT_BUS.unregister(this);
        Object o = Loader.instance().getIndexedModList().get(Reference.MODID);
        net.minecraftforge.common.ForgeVersion.CheckResult result = ForgeVersion.getResult(((ModContainer) o));
        if (result.status == Status.OUTDATED) {
        IChatComponent mess = getOutdatedMessage(result, "Dark Gems Reborn 2");
        (event.entity).addChatMessage(mess);
        		}
    		}
    	}
        public static IChatComponent getOutdatedMessage(net.minecraftforge.common.ForgeVersion.CheckResult result, String name) {
         String linkName = "[" + EnumChatFormatting.GREEN + name + " " + result.target + EnumChatFormatting.WHITE;
         String link = "" + result.url;
         String linkComponent = "{\"text\":\"" + linkName + "\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"" + link + "\"}}";

         String info = "\"" + EnumChatFormatting.RED + "New " + name + " version is available, please update! \n" + "\"";
         String mess = "[" + info + "," + linkComponent + ",\"]\"]";
         return IChatComponent.Serializer.jsonToComponent(mess);
        }
    }
}