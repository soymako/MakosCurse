package me.soymako.Utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.soymako.makoscurse.Main;
import net.md_5.bungee.api.ChatColor;

public class MakoInventories {

  public static void openConfigInventory(Player player){
    Inventory inventory = Bukkit.createInventory(player, 9, MakoChat.translate("&c&lConfiguration"));

    ItemStack[] content = {config_GetCurseOnSightOption(), config_GetCurseOnTouchOption(), config_GetCurseOnPickupOption(), config_GetCurseEnemyOption()};

    inventory.setContents(content);

    player.openInventory(inventory);
  }


  public static boolean handleInventoryAction(Player player, Inventory inventory, Result result, InventoryView view, ItemStack itemClicked){
    if (itemClicked == null) return false;
    if (itemClicked.getType() == Material.AIR) return false;
    String title = ChatColor.stripColor(view.getTitle());



    if (title.equalsIgnoreCase("configuration")){
      if (itemClicked.equals(MakoInventories.config_GetCurseOnSightOption())){
        Main.setupWizard.serverData.setCurseOnSight(!Main.setupWizard.serverData.getCurseOnSight());
        MakoChat.message(player, "cursor in sight: " + Main.setupWizard.serverData.getCurseOnSight());
      }
      if (itemClicked.equals(MakoInventories.config_GetCurseOnTouchOption())){
        Main.setupWizard.serverData.setCurseOnTouch(!Main.setupWizard.serverData.getCurseOnTouch());
        MakoChat.message(player, "cursor on touch: " + Main.setupWizard.serverData.getCurseOnTouch());
      }
      if (itemClicked.equals(MakoInventories.config_GetCurseOnPickupOption())){
        Main.setupWizard.serverData.setCurseOnPickup(!Main.setupWizard.serverData.getCurseOnPickup());
        MakoChat.message(player, "cursor on pick up: " + Main.setupWizard.serverData.getCurseOnPickup());
      }
      if (itemClicked.equals(MakoInventories.config_GetCurseEnemyOption())){
        Main.setupWizard.serverData.setCurseEnemy(!Main.setupWizard.serverData.getCurseEnemy());
        MakoChat.message(player, "cursor on enemy: " + Main.setupWizard.serverData.getCurseEnemy());
      }
      return true;
    }


    return false;

  }


  public static ItemStack config_GetCurseOnSightOption(){
    ItemStack item = new ItemStack(Material.ENDER_EYE);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(MakoChat.translate("&6&lToggle Curse on Sight"));
    List<String> lore = new ArrayList<String>();
    lore.add(MakoChat.translate(getLorePrefix() + "Toggles the Curse on Sight Option"));
    lore.add(MakoChat.translate(getLorePrefix() + "This means that when you look at a block"));
    lore.add(MakoChat.translate(getLorePrefix() + "It will be replaced with the curse block"));
    lore.add(MakoChat.translate(getLorePrefix() + "All the block data will be removed"));
    lore.add(MakoChat.translate(getLorePrefix() + "Unless is a chest, furnace, etc..."));
    meta.setLore(lore);
    item.setItemMeta(meta);
    meta.setCustomModelData(99);

    return item;
  }

  public static ItemStack config_GetCurseOnTouchOption(){
    ItemStack item = new ItemStack(Material.ENDER_EYE);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(MakoChat.translate("&6&lToggle Curse on Touch"));
    List<String> lore = new ArrayList<String>();
    lore.add(MakoChat.translate(getLorePrefix() + "Toggles the Curse on Touch Option"));
    lore.add(MakoChat.translate(getLorePrefix() + "This means that when you touch a block"));
    lore.add(MakoChat.translate(getLorePrefix() + "It will be replaced with the curse block"));
    lore.add(MakoChat.translate(getLorePrefix() + "All the block data will be removed"));
    lore.add(MakoChat.translate(getLorePrefix() + "Unless is a chest, furnace, etc..."));
    meta.setLore(lore);
    item.setItemMeta(meta);


    return item;
  }

  public static ItemStack config_GetCurseOnPickupOption(){
    ItemStack item = new ItemStack(Material.SADDLE);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(MakoChat.translate("&6&lToggle Curse on Pickup"));
    List<String> lore = new ArrayList<String>();
    lore.add(MakoChat.translate(getLorePrefix() + "Toggles the Curse on Pickup Option"));
    lore.add(MakoChat.translate(getLorePrefix() + "This means that when you pick up an Item"));
    lore.add(MakoChat.translate(getLorePrefix() + "It will be replaced with the curse block"));
    lore.add(MakoChat.translate(getLorePrefix() + "All the Item data will be removed"));
    meta.setLore(lore);
    item.setItemMeta(meta);


    return item;
  }


  public static ItemStack config_GetCurseEnemyOption(){
    ItemStack item = new ItemStack(Material.ENDER_EYE);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(MakoChat.translate("&6&lToggle Curse on Enemy"));
    List<String> lore = new ArrayList<String>();
    lore.add(MakoChat.translate(getLorePrefix() + "Toggles the Curse on Enemy Option"));
    lore.add(MakoChat.translate(getLorePrefix() + "This means that when you attack an Entity"));
    lore.add(MakoChat.translate(getLorePrefix() + "It'll be affected by the curse"));
    lore.add(MakoChat.translate(getLorePrefix() + "Exploding, curse block items will be"));
    lore.add(MakoChat.translate(getLorePrefix() + "sended flying in all directions"));
    lore.add(MakoChat.translate(getImportantLorePrefix() + "The explosion WILL damage nearby entities"));
    lore.add(MakoChat.translate(getImportantLorePrefix() + "The explosion WILL burn nearby blocks"));

    meta.setLore(lore);

    item.setItemMeta(meta);
    return item;
  }


  public static String getLorePrefix(){
    return MakoChat.translate("&f&l|>&f ");
  }

  public static String getImportantLorePrefix(){
    return MakoChat.translate("&c&l|> &c");
  }

  
}
