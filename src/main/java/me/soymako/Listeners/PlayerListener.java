package me.soymako.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.Event.Result;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import me.soymako.Utils.MakoChat;
import me.soymako.Utils.MakoInventories;
import me.soymako.makoscurse.Main;

public class PlayerListener implements Listener {

  @EventHandler
  public void onPlayerMovement(PlayerMoveEvent e){
   
    Player player = e.getPlayer();

    // [0] -> Under
    // [1] -> Above
    // [2] -> Left
    // [3] -> Left Up
    // [4] -> Right
    // [5] -> Right Up

    Block[] blocks = getBlocks(player);

    Block blockInSight = player.getTargetBlockExact((int) Main.setupWizard.serverData.getSightDistance());

    curseLogic(player, blocks, blockInSight);

  }

  boolean isCurse(Block block){
    if (block == null) return false;
    return Main.setupWizard.serverData.isBlockMaterialCurse(block);
  }

  void curseLogic(Player player, Block[] blocks, Block blockInSight){

    if (!Main.setupWizard.serverData.isRunning()) return;



    if (Main.setupWizard.serverData.getCurseOnTouch()) {
      for (Block block : blocks) {
        if (block == null) return;
        if (block.getType().equals(Material.AIR) || isCurse(block)) continue;
        block.setType(Main.setupWizard.serverData.getMaterial());
      }
    }

    if (blockInSight == null) return;
    if (blockInSight.getType().equals(Material.AIR) || isCurse(blockInSight)) return;


    if (Main.setupWizard.serverData.getCurseOnSight()) {
      blockInSight.setType(Main.setupWizard.serverData.getMaterial());
    }



    // player.getWorld().getBlockAt(blockInSight.getLocation()).setType(Main.setupWizard.serverData.getMaterial());
  }

  Block[] getBlocks(Player player){


    
    double targetDistance = Main.setupWizard.serverData.getCurseRelativeDistance();

    Location positionUnder = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() - targetDistance, player.getLocation().getZ());
    Location positionAbove = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + targetDistance, player.getLocation().getZ());
    Location positionAboveHead = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY()+ 1 + targetDistance, player.getLocation().getZ());

    Location positionLeft = new Location(player.getWorld(), player.getLocation().getX() - targetDistance, player.getLocation().getY(), player.getLocation().getZ());

    Location positionRight = new Location(player.getWorld(), player.getLocation().getX() + targetDistance, player.getLocation().getY(), player.getLocation().getZ());

    Block blockUnder = player.getWorld().getBlockAt(positionUnder.getBlockX(), positionUnder.getBlockY(), positionUnder.getBlockZ());

    Block blockAbove = player.getWorld().getBlockAt(positionAbove.getBlockX(), positionAbove.getBlockY(), positionAbove.getBlockZ());

    Block blockLeft = player.getWorld().getBlockAt(positionLeft.getBlockX(), positionLeft.getBlockY(), positionLeft.getBlockZ());
    Block blockLeftUp = player.getWorld().getBlockAt(positionLeft.getBlockX() -1, positionLeft.getBlockY() + 1, positionLeft.getBlockZ());

    Block blockRight = player.getWorld().getBlockAt(positionRight.getBlockX(), positionRight.getBlockY(), positionRight.getBlockZ());
    Block blockRightUp = player.getWorld().getBlockAt(positionRight.getBlockX() +1, positionRight.getBlockY() + 1, positionRight.getBlockZ());

    Block blockAboveHead = player.getWorld().getBlockAt(positionAboveHead.getBlockX(), positionAboveHead.getBlockY(), positionAboveHead.getBlockZ());

    // MakoChat.serverMessage("down: " + blockUnder.getType());
    // MakoChat.serverMessage("up: " + blockAbove.getType());
    // MakoChat.serverMessage("left: " + blockLeft.getType());
    // MakoChat.serverMessage("right: " + blockRight.getType());

    return new Block[]{blockUnder, blockAboveHead, blockAbove, blockLeft, blockLeftUp, blockRightUp, blockRight};

  }


  @EventHandler
  public void onPlayerInventoryAction(InventoryClickEvent e){
    if (e.getWhoClicked() instanceof Player) {
      Player player = (Player) e.getWhoClicked();
      Inventory inventory = e.getInventory();
      Result result = e.getResult();
      InventoryView view = e.getView();
      ItemStack itemOnCursor = e.getCurrentItem();

      e.setCancelled(MakoInventories.handleInventoryAction(player, inventory, result, view, itemOnCursor));
    }
  }


  @EventHandler
  public void onItemPickup(PlayerPickupItemEvent e){
    if (!Main.setupWizard.serverData.isRunning()) return;

    if (!Main.setupWizard.serverData.getCurseOnPickup()) return;
    ItemStack newItem = e.getItem().getItemStack();

    int amount = newItem.getAmount();
    ItemMeta meta = newItem.getItemMeta();

    ItemStack itemStack = new ItemStack(Main.setupWizard.serverData.getMaterial());
    itemStack.setAmount(amount);
    itemStack.setItemMeta(meta);


    e.setCancelled(true);

    e.getItem().remove();

    Player player = e.getPlayer();
    player.getInventory().addItem(itemStack);
  }


  @EventHandler
  public void onEntityDamaged(EntityDamageByEntityEvent e){
    if (e.getDamager() instanceof Player) {
      LivingEntity entity = (LivingEntity) e.getEntity();
      Player player = (Player) e.getDamager();
      if (Main.setupWizard.serverData.isRunning() && Main.setupWizard.serverData.getCurseEnemy()) {
        float maxForce = 20f;
        float force = (float) entity.getHealth();
        if (force > maxForce) force = maxForce;
        entity.getWorld().createExplosion(entity.getLocation(), force, true, true);
        
        createExplodingItems(entity.getLocation());

        entity.setHealth(0);

      }
    }
  }

  public void createExplodingItems(Location location){
    Entity[] items = new Entity[30];

    for (int i = 0; i < items.length; i++) {
      items[i] = location.getWorld().spawnEntity(location, EntityType.ITEM);
      Item item = (Item) items[i];
      item.setItemStack(new ItemStack(Main.setupWizard.serverData.getMaterial()));
    }

    for (Entity entity : items) {
        // Velocidad aleatoria entre -0.5 y 0.5 en X y Z, y un empuje hacia arriba en Y
        double x = (Math.random() - 0.5) * 1.2;
        double y = Math.random() * 0.7 + 0.4;
        double z = (Math.random() - 0.5) * 1.2;

        entity.setVelocity(new Vector(x,y,z));
    }    
  }
  
}
