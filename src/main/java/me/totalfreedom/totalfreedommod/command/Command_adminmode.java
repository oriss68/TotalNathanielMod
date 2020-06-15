package me.totalfreedom.totalfreedommod.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import me.totalfreedom.totalfreedommod.config.ConfigEntry;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.TELNET_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "Takes away the right to join to OPs", usage = "/<command> [on | off]")
public class Command_adminmode extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length != 1)
        {
            return false;
        }

        if (args[0].equalsIgnoreCase("off"))
        {
            ConfigEntry.ADMIN_ONLY_MODE.setBoolean(false);
            FUtil.adminAction(sender.getName(), "YAY! NOW ALL PLAYERS CAN JOIN", true);
            return true;
        }
        else if (args[0].equalsIgnoreCase("on"))
        {
            ConfigEntry.ADMIN_ONLY_MODE.setBoolean(true);
            FUtil.adminAction(sender.getName(), "Aww, why the fuck you have to limit it to just admins?", true);
            for (Player player : server.getOnlinePlayers())
            {
                if (!isAdmin(player))
                {
                    player.kickPlayer("Server is now closed to non-admins, blame it on " + sender.getName());
                }
            }
            return true;
        }

        return false;
    }

    @Override
    public List<String> getTabCompleteOptions(CommandSender sender, Command command, String alias, String[] args)
    {
        if (args.length == 1 && plugin.al.isAdmin(sender) && !(sender instanceof Player))
        {
            return Arrays.asList("on", "off");
        }

        return Collections.emptyList();
    }
}
