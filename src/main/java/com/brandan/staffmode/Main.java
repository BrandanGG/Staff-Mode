package com.brandan.staffmode;
import com.brandan.staffmode.commands.Ban;
import com.brandan.staffmode.commands.Tempban;
import com.brandan.staffmode.commands.Unban;
import com.brandan.staffmode.commands.VanishCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("unban").setExecutor(new Unban());
        getCommand("ban").setExecutor(new Ban());
        getCommand("tempban").setExecutor(new Tempban());
        getCommand("vanish").setExecutor(new VanishCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
