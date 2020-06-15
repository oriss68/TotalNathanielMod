package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "Someone has a crash item and they can't join? Well, I prefer you not use this command and make them suffer", usage = "/<command> <player>")
public class Command_autoclear extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            return false;
        }

        boolean enabled = plugin.lp.CLEAR_ON_JOIN.contains(args[0]);

        if (enabled)
        {
            plugin.lp.CLEAR_ON_JOIN.remove(args[0]);
        }
        else
        {
            plugin.lp.CLEAR_ON_JOIN.add(args[0]);
        }

        msg(args[0] + " will " + (enabled ? "no longer" : "now") + " say good bye to their inventory");

        return true;
    }
}
