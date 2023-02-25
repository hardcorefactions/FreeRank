package cf.electrich.freerank;

import cf.electrich.freerank.commands.FreeRankCommand;
import cf.electrich.freerank.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {
    private static Main instance;
    public String ConfigRoute;
    public static Main getInstance() {return instance;}

    public void log(String s) {
        Bukkit.getConsoleSender().sendMessage(CC.translate(s));
    }

    @Override
    public void onEnable() {
        instance = this;
        log("&7&m------------------------");
        log("&6&lFree Rank");
        log("");
        log("&7&oMaded by &c@compromissed&7.");
        log("&7&m------------------------");
        registerConfig();
        new FreeRankCommand();
    }
    public void registerConfig(){
        File config = new File(getDataFolder(), "config.yml");
        ConfigRoute = config.getPath();
        if (!config.exists()){
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
