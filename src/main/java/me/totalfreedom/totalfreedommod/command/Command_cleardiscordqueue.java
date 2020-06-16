package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SENIOR_ADMIN, source = SourceType.ONLY_CONSOLE)
@CommandParameters(description = "Does some Discord queue stuff", usage = "/<command>")
public class Command_cleardiscordqueue extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        plugin.dc.clearQueue();
        msg("Cleared the Discord queue, I don't have a cool joke for this");
        return true;
    }
}
