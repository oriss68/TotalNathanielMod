package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.freeze.FreezeData;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "No more legs for you!", usage = "/<command> [target | purge]", aliases = "fr,zawarudo")
public class Command_freeze extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            boolean gFreeze = !plugin.fm.isGlobalFreeze();
            plugin.fm.setGlobalFreeze(gFreeze);

            if (!gFreeze)
            {
                FUtil.adminAction(sender.getName(), "Zero!", false);
                msg("Finally! They got their legs back!");
                return true;
            }

            FUtil.adminAction(sender.getName(), "ZA WARUDO!", false);
            for (Player player : server.getOnlinePlayers())
            {
                if (!isAdmin(player))
                {
                    player.sendTitle(ChatColor.RED + "ZA WARUDO!", ChatColor.YELLOW + "Well, you have to wait 9 seconds now.", 20, 100, 60);
                    msg(player, "You have been frozen becase " + sender.getName() + " abused their commands, please spam the chat to get unfrozen", ChatColor.RED);
                }
            }
            msg("ZA WARUDO");

            return true;
        }

        if (args[0].equals("purge"))
        {
            FUtil.adminAction(sender.getName(), "Gave everyone their legs back", false);
            for (Player player : server.getOnlinePlayers())
            {
                if (!isAdmin(player))
                {
                    player.sendTitle(ChatColor.GREEN + "Fine...", ChatColor.YELLOW + "Here's your legs", 20, 100, 60);
                }
            }
            plugin.fm.purge();
            return true;
        }

        final Player player = getPlayer(args[0]);

        if (player == null)
        {
            msg(FreedomCommand.PLAYER_NOT_FOUND, ChatColor.RED);
            return true;
        }

        final FreezeData fd = plugin.pl.getPlayer(player).getFreezeData();
        fd.setFrozen(!fd.isFrozen());

        msg(player.getName() + "'s legs have been " + (fd.isFrozen() ? "stolen" : "given back") + ".");
        msg(player, "Your legs have been " + (fd.isFrozen() ? "stolen" : "given back") + ".", ChatColor.AQUA);

        return true;
    }
}
