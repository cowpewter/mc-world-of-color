package com.cowpewter.dyetasticcolors.common.blocks;

import com.cowpewter.dyetasticcolors.DyetasticColors;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class NamedBlock extends Block implements INamedBlock {

  protected Item.Properties itemProps = new Item.Properties();
  protected Boolean hasItem = false;
  protected String name = "";

  NamedBlock(String name, AbstractBlock.Properties props) {
		super(props);
    this.name = name;
		this.setRegistryName(DyetasticColors.ID, name);
	}

  public Boolean hasItem() {
    return this.hasItem;
  }

  public INamedBlock setItemProperties(Item.Properties itemProps) {
    this.hasItem = true;
    this.itemProps = itemProps;
    return this;
  }

  public Item.Properties getItemProperties() {
    return this.itemProps;
  }

  public String getNameForBlockItem() {
    return this.name;
  }
}
