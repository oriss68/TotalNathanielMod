package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "GOOD BYE NICKNAMES", usage = "/<command>")
public class Command_denick extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (!plugin.esb.isEnabled())
        {
            msg("Download Essentials to use this command loser");
            return true;
        }

        FUtil.adminAction(sender.getName(), "GOOD BYE NICKNAMES", false);

        for (Player player : server.getOnlinePlayers())
        {
            plugin.esb.setNickname(player.getName(), null);
        }

        return true;
    }
}
