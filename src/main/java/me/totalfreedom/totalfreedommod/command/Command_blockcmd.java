package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.player.FPlayer;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "Become a badadmin and block commands for no reason!", usage = "/<command> <-a | purge | <player>>", aliases = "blockcommands,blockcommand,bc,bcmd")
public class Command_blockcmd extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length != 1)
        {
            return false;
        }

        if (args[0].equals("purge"))
        {
            FUtil.adminAction(sender.getName(), "I unblocked all of your commands! I should get Owner", true);
            int counter = 0;
            for (Player player : server.getOnlinePlayers())
            {
                FPlayer playerdata = plugin.pl.getPlayer(player);
                if (playerdata.allCommandsBlocked())
                {
                    counter += 1;
                    playerdata.setCommandsBlocked(false);
                }
            }
            msg("Unblocked commands for " + counter + " players.\nYou are now " + counter + " times cooler");
            return true;
        }

        if (args[0].equals("-a"))
        {
            FUtil.adminAction(sender.getName(), "HAHAHA I'M A BADADMIN I BLOCKED ALL OF YOUR GUYS COMMANDS", true);
            int counter = 0;
            for (Player player : server.getOnlinePlayers())
            {
                if (isAdmin(player))
                {
                    continue;
                }

                counter += 1;
                plugin.pl.getPlayer(player).setCommandsBlocked(true);
                msg(player, "Your commands have been blocked by a badadmin.", ChatColor.RED);
            }

            msg("Blocked commands for " + counter + " players.\nYou now have " + counter + " players who want to suspend you!");
            return true;
        }

        final Player player = getPlayer(args[0]);

        if (player == null)
        {
            msg(FreedomCommand.PLAYER_NOT_FOUND);
            return true;
        }

        FPlayer playerdata = plugin.pl.getPlayer(player);

        playerdata.setCommandsBlocked(!playerdata.allCommandsBlocked());

        FUtil.adminAction(sender.getName(), "Because I am a " (playerdata.allCommandsBlocked() ? "bad" : "good") + "admin I'm " + (playerdata.allCommandsBlocked() ? "B" : "Unb") + "locking all commands for " + player.getName(), true);
        msg((playerdata.allCommandsBlocked() ? "B" : "Unb") + "locked all commands, you " + (playerdata.allCommandsBlocked() ? "bad" : "good") + "admin");

        return true;
    }
}
