package com.cowpewter.dyetasticcolors.common.items;

import com.cowpewter.dyetasticcolors.DyetasticColors;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class NamedItem extends Item {
  NamedItem(String name, Item.Properties props) {
    super(props);
    this.setRegistryName(new ResourceLocation(DyetasticColors.ID, name));
  }
}
