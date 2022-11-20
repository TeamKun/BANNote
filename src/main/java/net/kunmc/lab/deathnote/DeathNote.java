package net.kunmc.lab.deathnote;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class DeathNote extends JavaPlugin {

    public static DeathNote INSTANCE;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(this.getCommand("bn")).setExecutor(new Commnads());
        Objects.requireNonNull(this.getCommand("bn")).setTabCompleter(new Commnads());
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);

        INSTANCE = this;
    }

}
