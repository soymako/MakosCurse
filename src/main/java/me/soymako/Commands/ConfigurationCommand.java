package me.soymako.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.soymako.Utils.MakoInventories;

public class ConfigurationCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player){
      Player player = (Player) sender;
      if (!player.isOp()) return false;
      MakoInventories.openConfigInventory(player);
    }


    return false;
  }
  
}
