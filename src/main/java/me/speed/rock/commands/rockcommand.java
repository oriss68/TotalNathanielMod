package me.speed.rock.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class rockcommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(sender instanceof Player) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9You have thrown a rock, but you have also summoned a meteor!"));	
		}
		else {
			sender.sendMessage(ChatColor.BLUE +"You have thrown a rock, but you have also summoned a meteor!");
		}
		return true;
	}
	
}
