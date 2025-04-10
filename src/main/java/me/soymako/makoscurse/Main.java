package me.soymako.makoscurse;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

  public static Main instance;
  public static SetupWizard setupWizard = new SetupWizard();

  @Override
  public void onEnable() {
    instance = this;
    setupWizard.setup();
  }

}
