package com.cowpewter.dyetasticcolors.common.utils;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.cowpewter.dyetasticcolors.DyetasticColors;

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

  public static String getColorNameFromRegistryName(String regName, String typeName) {
    return regName.replace(DyetasticColors.ID + ":", "").replace("_" + typeName, "");
  }

  public static DyeColor getDyeColorFromMaterialColor(MaterialColor materialColor) {
    // Java won't let me use a switch here
    // It won't let me switch on a MaterialColor and neither can I switch on the
    // .toString() of each color unless I save all 16 to individual variables.
    // So giant series of if statements, I guess
    if (materialColor == MaterialColor.COLOR_RED) {
      return DyeColor.RED;
    }
    if (materialColor == MaterialColor.COLOR_ORANGE) {
      return DyeColor.ORANGE;
    }
    if (materialColor == MaterialColor.COLOR_YELLOW) {
      return DyeColor.YELLOW;
    }
    if (materialColor == MaterialColor.COLOR_LIGHT_GREEN) {
      return DyeColor.LIME;
    }
    if (materialColor == MaterialColor.COLOR_GREEN) {
      return DyeColor.GREEN;
    }
    if (materialColor == MaterialColor.COLOR_LIGHT_BLUE) {
      return DyeColor.LIGHT_BLUE;
    }
    if (materialColor == MaterialColor.COLOR_BLUE) {
      return DyeColor.BLUE;
    }
    if (materialColor == MaterialColor.COLOR_PURPLE) {
      return DyeColor.PURPLE;
    }
    if (materialColor == MaterialColor.COLOR_MAGENTA) {
      return DyeColor.MAGENTA;
    }
    if (materialColor == MaterialColor.COLOR_PINK) {
      return DyeColor.PINK;
    }
    if (materialColor == MaterialColor.COLOR_BROWN) {
      return DyeColor.BROWN;
    }
    if (materialColor == MaterialColor.COLOR_LIGHT_GRAY) {
      return DyeColor.LIGHT_GRAY;
    }
    if (materialColor == MaterialColor.COLOR_GRAY) {
      return DyeColor.GRAY;
    }
    if (materialColor == MaterialColor.SNOW) {
      return DyeColor.WHITE;
    }
    if (materialColor == MaterialColor.COLOR_BLACK) {
      return DyeColor.BLACK;
    }

    // This method doesn't really need to support any other MaterialColors
    // This util is mostly for stained glass to tint beacons
    return DyeColor.WHITE;
  }

  public static MaterialColor getMaterialForColor(String fullColorName, Boolean isTerracotta) {
    Integer index = fullColorName.indexOf("_");
    String vanillaColor = fullColorName.substring(index+1);
    String colorName = "COLOR_" + vanillaColor.toUpperCase();

    switch (colorName) {
      case "COLOR_RED":
        return isTerracotta ? MaterialColor.TERRACOTTA_RED : MaterialColor.COLOR_RED;
      case "COLOR_ORANGE":
        return isTerracotta ? MaterialColor.TERRACOTTA_ORANGE : MaterialColor.COLOR_ORANGE;
      case "COLOR_YELLOW":
        return isTerracotta ? MaterialColor.TERRACOTTA_YELLOW : MaterialColor.COLOR_YELLOW;
      case "COLOR_LIME":
        return isTerracotta ? MaterialColor.TERRACOTTA_LIGHT_GREEN : MaterialColor.COLOR_LIGHT_GREEN;
      case "COLOR_GREEN":
        return isTerracotta ? MaterialColor.TERRACOTTA_GREEN : MaterialColor.COLOR_GREEN;
      case "COLOR_LIGHT_BLUE":
        return isTerracotta ? MaterialColor.TERRACOTTA_LIGHT_BLUE : MaterialColor.COLOR_LIGHT_BLUE;
      case "COLOR_BLUE":
        return isTerracotta ? MaterialColor.TERRACOTTA_BLUE : MaterialColor.COLOR_BLUE;
      case "COLOR_PURPLE":
        return isTerracotta ? MaterialColor.TERRACOTTA_PURPLE : MaterialColor.COLOR_PURPLE;
      case "COLOR_MAGENTA":
        return isTerracotta ? MaterialColor.TERRACOTTA_MAGENTA : MaterialColor.COLOR_MAGENTA;
      case "COLOR_PINK":
        return isTerracotta ? MaterialColor.TERRACOTTA_PINK : MaterialColor.COLOR_PINK;
      case "COLOR_BROWN":
        return isTerracotta ? MaterialColor.TERRACOTTA_BROWN : MaterialColor.COLOR_BROWN;
      case "COLOR_LIGHT_GRAY":
        return isTerracotta ? MaterialColor.TERRACOTTA_LIGHT_GRAY : MaterialColor.COLOR_LIGHT_GRAY;
      case "COLOR_GRAY":
        return isTerracotta ? MaterialColor.TERRACOTTA_GRAY : MaterialColor.COLOR_GRAY;
      case "COLOR_WHITE":
        return isTerracotta ? MaterialColor.TERRACOTTA_WHITE : MaterialColor.SNOW;
      case "COLOR_BLACK":
        return isTerracotta ? MaterialColor.TERRACOTTA_BLACK : MaterialColor.COLOR_BLACK;
      default:
        return null;
    }
  }
}
