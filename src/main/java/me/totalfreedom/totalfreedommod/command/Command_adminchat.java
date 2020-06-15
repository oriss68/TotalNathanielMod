package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.player.FPlayer;
import me.totalfreedom.totalfreedommod.rank.Rank;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(
        description = "Talk privately with other administrators, like a shy person",
        usage = "/<command> [message...]",
        aliases = "o,ac")
public class Command_adminchat extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            if (senderIsConsole)
            {
                msg("Stop being a telnet pussy and join the game!");
                return true;
            }

            FPlayer userinfo = plugin.pl.getPlayer(playerSender);
            userinfo.setAdminChat(!userinfo.inAdminChat());
            msg("Toggled your dumb admin chat thing " + (userinfo.inAdminChat() ? "on" : "off") + ".");
        }
        else
        {
            plugin.cm.adminChat(sender, StringUtils.join(args, " "));
        }

        return true;
    }
}
