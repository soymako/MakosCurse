package me.soymako.Data;

import java.io.File;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;

import me.soymako.makoscurse.Main;

public class ServerData {

  File file;
  YamlConfiguration data;

  public void setup(){
    file = new File(Main.instance.getDataFolder(), "server.yml");
    data = YamlConfiguration.loadConfiguration(file);
    saveData();
  }

  public YamlConfiguration getData(){
    return data;
  }

  void saveData(){
    if (!file.exists()){
      Main.instance.getLogger().info("[MakosCurse] File is null");
    }
    if (data == null){
      Main.instance.getLogger().info("[MakosCurse] Data is null");
    }
    try {
      data.save(file);
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }


  
  public void setMaterial(Material material){
    data.set("material", material.name());
    saveData();
  }

  public Material getMaterial(){
    return Material.getMaterial(data.getString("material", "NULL"));
  }

  public boolean isRunning(){
    return data.getBoolean("running", false);
  }

  public void setRunning(boolean running){
    data.set("running", running);
    saveData();
  }

  public boolean isBlockMaterialCurse(Block block){
    if (block == null) return false;
    Material material = getMaterial();
    return block.getType() == material;
  }


  public void setSightDistance(double distance){
    data.set("sight_distance", distance);
    saveData();
  }

  public void setCurseRelativeDistance(double distance){
    data.set("curse_relative_distance", distance);
    saveData();
  }

  public double getSightDistance(){
    return data.getDouble("sight_distance", 3);
  }

  public double getCurseRelativeDistance(){
    return data.getDouble("curse_relative_distance", .1);
  }


  public void setCurseOnSight(boolean allow){
    data.set("config.curse_on_sight", allow);
    saveData();
  }

  public boolean getCurseOnSight(){
    return data.getBoolean("config.curse_on_sight", false);
  }

  public void setCurseOnTouch(boolean allow){
    data.set("config.curse_on_touch", allow);
    saveData();
  }

  public boolean getCurseOnTouch(){
    return data.getBoolean("config.curse_on_touch", false);
  }

  public void setCurseOnPickup(boolean allow){
    data.set("config.curse_on_pickup", allow);
    saveData();
  }

  public boolean getCurseOnPickup(){
    return data.getBoolean("config.curse_on_pickup", false);
  }

  public void setCurseEnemy(boolean allow){
    data.set("config.curse_enemy", allow);
    saveData();
  }

  public boolean getCurseEnemy(){
    return data.getBoolean("config.curse_enemy", false);
  }

}
