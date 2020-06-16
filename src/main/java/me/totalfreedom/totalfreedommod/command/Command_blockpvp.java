package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.player.FPlayer;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "PvP can be annoying sometimes, so let's block it!", usage = "/<command> [[-s] <player> [reason] | list | purge | all]", aliases = "pvpblock,pvpmode")
public class Command_blockpvp extends FreedomCommand
{

    @Override
    public boolean run(final CommandSender sender, final Player playerSender, final Command cmd, final String commandLabel, String[] args, final boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            return false;
        }

        if (args[0].equals("list"))
        {
            msg("PVP is blocked for players:");
            int count = 0;
            for (Player player : server.getOnlinePlayers())
            {
                final FPlayer info = plugin.pl.getPlayer(player);
                if (info.isPvpBlocked())
                {
                    msg(" - " + player.getName());
                    ++count;
                }
            }

            if (count == 0)
            {
                msg(" - none");
            }
            return true;
        }

        if (args[0].equals("purge"))
        {
            FUtil.adminAction(sender.getName(), "All players are now strong again", true);
            int count = 0;
            for (Player player : server.getOnlinePlayers())
            {
                final FPlayer info = plugin.pl.getPlayer(player);
                if (info.isPvpBlocked())
                {
                    info.setPvpBlocked(false);
                    ++count;
                }
            }

            msg("Made " + count + " players strong.");
            return true;
        }

        if (args[0].equals("all"))
        {
            FUtil.adminAction(sender.getName(), "YOU ARE ALL WEAKLINGS, YOU CAN'T FIGHT ANYMORE", true);
            int counter = 0;
            for (Player player : server.getOnlinePlayers())
            {
                if (!plugin.al.isAdmin(player))
                {
                    final FPlayer playerdata = plugin.pl.getPlayer(player);
                    playerdata.setPvpBlocked(true);
                    ++counter;
                }
            }

            msg("Made " + counter + " players weak.");
            return true;
        }

        final boolean smite = args[0].equals("-s");
        if (smite)
        {
            args = ArrayUtils.subarray(args, 1, args.length);
            if (args.length < 1)
            {
                return false;
            }
        }

        final Player p = getPlayer(args[0]);
        if (p == null)
        {
            sender.sendMessage(FreedomCommand.PLAYER_NOT_FOUND);
            return true;
        }

        String reason = null;
        if (args.length > 1)
        {
            reason = StringUtils.join(args, " ", 1, args.length);
        }

        final FPlayer pd = plugin.pl.getPlayer(p);
        if (pd.isPvpBlocked())
        {
            FUtil.adminAction(sender.getName(), "Making " + p.getName() + " strong again", true);
            pd.setPvpBlocked(false);
            msg("Making " + p.getName() + " strong again");
            msg(p, "You are now strong again", ChatColor.GREEN);
        }
        else
        {

            FUtil.adminAction(sender.getName(), "Making " + p.getName() + " weak", true);
            pd.setPvpBlocked(true);
            if (smite)
            {
                Command_smite.smite(sender, p, reason);
            }

            msg(p, "You are now weak", ChatColor.RED);
            msg("Made " + p.getName() + " weak");
        }
        return true;
    }
}
