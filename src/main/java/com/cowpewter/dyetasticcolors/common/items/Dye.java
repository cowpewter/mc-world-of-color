package com.cowpewter.dyetasticcolors.common.items;

import com.cowpewter.dyetasticcolors.common.utils.Color;
import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Dye extends NamedItem {
  public Dye(String name, Item.Properties props) {
    super(name, props);
  }

  public static ArrayList<NamedItem> generateAllColors() {
    ArrayList<NamedItem> items = new ArrayList<NamedItem>();

    String[] allColors = Color.getAllNewColorNames();
    for (String color : allColors) {
      items.add(new Dye(color + "_dye", new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    }

    return items;
  }
}
