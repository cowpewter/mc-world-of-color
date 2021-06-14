package com.cowpewter.worldofcolor.common.utils;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Stream;
import net.minecraft.item.DyeColor;
import net.minecraft.block.material.MaterialColor;

public class Color {
  public static final String[] ALL_COLOR_MODIFIERS = {
    "lightest",
    "pastel",
    "dark",
    "darkest"
  };

  public static String[] getAllNewColorNames() {
    ArrayList<String> colorList = new ArrayList<String>();
    Stream.of(DyeColor.values()).forEach(dyeColor -> {
      for (String modifier : Color.ALL_COLOR_MODIFIERS) {
        if ((modifier == "lightest" || modifier == "pastel") && dyeColor.getName() == "white") {
          continue;
        }
        if ((modifier == "dark" || modifier == "darkest") && dyeColor.getName() == "black") {
          continue;
        }
        colorList.add(modifier + "_" + dyeColor.getName());
      }
    });

    return colorList.stream().toArray(String[]::new);
  }

  public static String[] getAllColorNames() {
    String[] newColors = Color.getAllNewColorNames();
    ArrayList<String> vanillaColorList = new ArrayList<String>();
    Stream.of(DyeColor.values()).forEach(dyeColor -> {
      vanillaColorList.add(dyeColor.getName());
    });

    return Stream
      .concat(vanillaColorList.stream(), Arrays.stream(newColors))
      .toArray(String[]::new);
  }

  public static MaterialColor getMaterialForColor(String fullColorName) {
    Integer index = fullColorName.indexOf("_");
    String vanillaColor = fullColorName.substring(index+1);
    String colorName = "COLOR_" + vanillaColor.toUpperCase();

    // Ugh why won't something like this just work???
    // return MaterialColor.valueOf(colorName);

    switch (colorName) {
      case "COLOR_RED":
        return MaterialColor.COLOR_RED;
      case "COLOR_ORANGE":
        return MaterialColor.COLOR_ORANGE;
      case "COLOR_YELLOW":
        return MaterialColor.COLOR_YELLOW;
      case "COLOR_LIME":
        return MaterialColor.COLOR_LIGHT_GREEN;
      case "COLOR_GREEN":
        return MaterialColor.COLOR_GREEN;
      case "COLOR_LIGHT_BLUE":
        return MaterialColor.COLOR_LIGHT_BLUE;
      case "COLOR_BLUE":
        return MaterialColor.COLOR_BLUE;
      case "COLOR_PURPLE":
        return MaterialColor.COLOR_PURPLE;
      case "COLOR_MAGENTA":
        return MaterialColor.COLOR_MAGENTA;
      case "COLOR_PINK":
        return MaterialColor.COLOR_PINK;
      case "COLOR_BROWN":
        return MaterialColor.COLOR_BROWN;
      case "COLOR_LIGHT_GRAY":
        return MaterialColor.COLOR_LIGHT_GRAY;
      case "COLOR_GRAY":
        return MaterialColor.COLOR_GRAY;
      case "COLOR_WHITE":
        return MaterialColor.SNOW;
      case "COLOR_BLACK":
        return MaterialColor.COLOR_BLACK;
      default:
        return null;
    }
  }
}
