package me.soymako.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.soymako.Utils.MakoChat;
import me.soymako.makoscurse.Main;

public class CurseStartCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player){
      Player player = (Player) sender;
      if (!player.isOp()) return false;
      Main.setupWizard.serverData.setRunning(!Main.setupWizard.serverData.isRunning());
      boolean isRunning = Main.setupWizard.serverData.isRunning();
      MakoChat.message(player, "isRunning: " + String.valueOf(isRunning));
    }
    return false;
  }  
}
