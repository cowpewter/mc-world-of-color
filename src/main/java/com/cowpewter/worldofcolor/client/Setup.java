package com.cowpewter.worldofcolor.client;

import com.cowpewter.worldofcolor.WorldOfColor;
import com.cowpewter.worldofcolor.common.blocks.StainedGlass;
import com.cowpewter.worldofcolor.common.blocks.StainedGlassPane;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(WorldOfColor.ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Setup {
  @SubscribeEvent
  public static void clientSetup(FMLClientSetupEvent event) {
    for (String blockName : StainedGlass.generateAllBlockNames()) {
      Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(WorldOfColor.ID, blockName));
      if (block != null) {
        RenderTypeLookup.setRenderLayer(block, RenderType.translucent());
      }
    }
    for (String blockName : StainedGlassPane.generateAllBlockNames()) {
      Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(WorldOfColor.ID, blockName));
      if (block != null) {
        RenderTypeLookup.setRenderLayer(block, RenderType.translucent());
      }
    }
  }
}
