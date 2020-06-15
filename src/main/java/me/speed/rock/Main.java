package me.speed.rock;
 
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
 
public class Main extends JavaPlugin {
   
    public void onEnable() {
        Bukkit.getServer().getLogger().info("Rock Plugin Enabled");
        }  
         
        public void onDisable() {
        Bukkit.getServer().getLogger().info("Rock Plugin Disabled");
        }
         
        public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("rock")) {
        sender.sendMessage(ChatColor.BLUE +"You have thrown a rock, but you have also summoned a meteor!");
        }
        return true;
         
}  
}