package me.soymako.makoscurse;


import me.soymako.Commands.ConfigurationCommand;
import me.soymako.Commands.CurseStartCommand;
import me.soymako.Commands.SetCurseBlockCommand;
import me.soymako.Commands.SetCurseRelativeDistance;
import me.soymako.Commands.SetSightDistanceCommand;
import me.soymako.Data.ServerData;
import me.soymako.Listeners.PlayerListener;

public class SetupWizard {

  public ServerData serverData = new ServerData();

  public void setup(){
    setupData();

    registerCommands();
    registerListeners();

  }

  public void setupData(){
    serverData.setup();
  }

  void registerCommands(){
    Main.instance.getCommand("set_curse_block").setExecutor(new SetCurseBlockCommand());
    Main.instance.getCommand("curse_start").setExecutor(new CurseStartCommand());
    Main.instance.getCommand("set_sight_distance").setExecutor(new SetSightDistanceCommand());
    Main.instance.getCommand("set_curse_relative_distance").setExecutor(new SetCurseRelativeDistance());
    Main.instance.getCommand("configuration").setExecutor(new ConfigurationCommand());
  }

  void registerListeners(){
    Main.instance.getServer().getPluginManager().registerEvents(new PlayerListener(), Main.instance);
  }

  

  
}
