package jp.example.joinspawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinSpawn extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("JoinSpawn enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("JoinSpawn disabled!");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String world = getConfig().getString("spawn.world");
        double x = getConfig().getDouble("spawn.x");
        double y = getConfig().getDouble("spawn.y");
        double z = getConfig().getDouble("spawn.z");
        float yaw = (float) getConfig().getDouble("spawn.yaw");
        float pitch = (float) getConfig().getDouble("spawn.pitch");

        Location loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
        event.getPlayer().teleport(loc);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command can only be used by players.");
            return true;
        }
        Player player = (Player) sender;

        if (!player.hasPermission("joinspawn.set")) {
            player.sendMessage("§cYou do not have permission.");
            return true;
        }

        if (command.getName().equalsIgnoreCase("setjp")) {
            if (args.length < 3) {
                player.sendMessage("§chow to use: /setjp <x> <y> <z> [yaw] [pitch]");
                return true;
            }

            try {
                double x = Double.parseDouble(args[0]);
                double y = Double.parseDouble(args[1]);
                double z = Double.parseDouble(args[2]);
                float yaw = args.length >= 4 ? Float.parseFloat(args[3]) : 0f;
                float pitch = args.length >= 5 ? Float.parseFloat(args[4]) : 0f;

                getConfig().set("spawn.world", player.getWorld().getName());
                getConfig().set("spawn.x", x);
                getConfig().set("spawn.y", y);
                getConfig().set("spawn.z", z);
                getConfig().set("spawn.yaw", yaw);
                getConfig().set("spawn.pitch", pitch);
                saveConfig();

                player.sendMessage("§aSet the spawn position for participants.: "
                        + String.format("X: %.2f Y: %.2f Z: %.2f Yaw: %.1f Pitch: %.1f", x, y, z, yaw, pitch));
            } catch (NumberFormatException e) {
                player.sendMessage("§cPlease enter the numbers correctly.");
            }
            return true;
        }
        return false;
    }
}
