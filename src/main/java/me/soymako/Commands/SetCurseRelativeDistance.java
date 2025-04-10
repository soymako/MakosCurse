package me.soymako.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.soymako.Utils.MakoChat;
import me.soymako.makoscurse.Main;

public class SetCurseRelativeDistance implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player){
      Player player = (Player) sender;
      if (!player.isOp()) return false;

      if (args.length > 0){

        String distanceArg = args[0];
        double distance = Double.parseDouble(distanceArg);

        Main.setupWizard.serverData.setCurseRelativeDistance(distance);

        MakoChat.message(player, "Changed curse relative distance to " + distance);
      }
    }


    return false;
  }


}
