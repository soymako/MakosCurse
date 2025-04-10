package me.soymako.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class MakoChat {

  public static String translate(String text){
    return ChatColor.translateAlternateColorCodes('&', text);
  }

  public static void serverMessage(String text){
    String message = translate("&6&l[&eMako'sCurse&6&l]&r " + text);
    for (Player p : Bukkit.getOnlinePlayers()){
      p.sendMessage(message);
    }
  }
  
  public static void serverMessage(String text, boolean showPrefix){
    String message = "";
    if (showPrefix) message = translate("&6&l[&eMako'sCurse&6&l]&r " + text);
    else message = translate(text);
    for (Player p : Bukkit.getOnlinePlayers()){
      p.sendMessage(message);
    }
  }

  public static void message(Player player, String message){
    player.sendMessage(translate(message));
  }

}
