package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.OP, source = SourceType.BOTH)
@CommandParameters(description = "Shows the list of all the good people, you should put 'purge' at the end of the command", usage = "/<command> [purge]")
public class Command_banlist extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length > 0)
        {
            if (args[0].equalsIgnoreCase("purge"))
            {
                checkRank(Rank.SENIOR_ADMIN);

                FUtil.adminAction(sender.getName(), "Purging the ban list, because I'm a good admin", true);
                int amount = plugin.bm.purge();
                msg("Purged " + amount + " player bans. You're now " + amount + " times cooler");

                return true;

            }

            return false;
        }

        msg(plugin.bm.getAllBans().size() + " player bans ("
                + plugin.bm.getUsernameBans().size() + " usernames, "
                + plugin.bm.getIpBans().size() + " IPs)");

        return true;
    }
}
