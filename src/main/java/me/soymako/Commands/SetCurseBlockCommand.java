package me.soymako.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.soymako.makoscurse.Main;

public class SetCurseBlockCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player) sender;
      if (!player.isOp()) return false;
      ItemStack item = player.getInventory().getItemInMainHand();

      Material material = item.getType();

      if (material == Material.AIR || material == null){
        player.sendMessage("You're not holding a block!");
        player.sendMessage("Please hold a block in your main hand!");
        return false;
      }

      Main.setupWizard.serverData.setMaterial(material);

      return true;
    }
    return false;
  }


}
