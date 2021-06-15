package com.cowpewter.dyetasticcolors.common.blocks;

import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.extensions.IForgeBlock;

public interface INamedBlock extends IForgeBlock, IItemProvider {
  public Boolean hasItem();
  public INamedBlock setItemProperties(Item.Properties itemProps);
  public Item.Properties getItemProperties();
  public String getNameForBlockItem();
}
