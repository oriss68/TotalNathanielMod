package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Grief by using a bow", usage = "/<command>", aliases = "ea")
public class Command_explosivearrows extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        boolean onList = plugin.it.explosivePlayers.contains(playerSender);
        if (onList)
        {
            plugin.it.explosivePlayers.remove(playerSender);
            msg("Grief bow off", ChatColor.RED);
        }
        else
        {
            plugin.it.explosivePlayers.add(playerSender);
            msg("GRIEFER BOW ON", ChatColor.GREEN);
        }

        return true;
    }
}
