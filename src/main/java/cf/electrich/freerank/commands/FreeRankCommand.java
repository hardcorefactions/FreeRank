package cf.electrich.freerank.commands;

import cf.electrich.freerank.Main;
import cf.electrich.freerank.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class FreeRankCommand implements CommandExecutor {
    public FreeRankCommand() {
        Main.getInstance().getCommand("freerank").setExecutor(this);
    }
    private final Main plugin = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&cOnly players."));
        } else {
            Player player = (Player) sender;
            SimpleDateFormat loadFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
            loadFormat.setTimeZone(TimeZone.getTimeZone(this.plugin.getConfig().getString("timezone")));
            try {
                long endDate = loadFormat.parse(this.plugin.getConfig().getString("date")).getTime();
                long diff = endDate - new Date().getTime();
                if (diff <= 0) {
                    player.sendMessage(CC.translate("&cCommand disabled."));
                } else {
                    long sec = diff / 1000;
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp u " + player.getName() + " parent addtemp " + this.plugin.getConfig().getString("rank-name") + " " + sec + "s");
                    player.sendMessage(CC.translate("&eEnjoy your new free rank! &c❤"));
                }
                return false;
            } catch (ParseException ignored) {
                Bukkit.getConsoleSender().sendMessage(CC.translate("&cFailed to load time. Is it in the correct format?"));
            }
        }
        return false;
    }
}
