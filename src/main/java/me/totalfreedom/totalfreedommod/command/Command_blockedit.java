package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.player.FPlayer;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "Playing Minecraft? Well what about NOMINE-CRAFT", usage = "/<command> [[-s] <player> [reason] | list | purge | all]")
public class Command_blockedit extends FreedomCommand
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
            msg("The following have block modification abilities restricted:");
            int count = 0;
            for (Player player : server.getOnlinePlayers())
            {
                final FPlayer info = plugin.pl.getPlayer(player);
                if (info.isEditBlocked())
                {
                    msg("- " + player.getName());
                    ++count;
                }
            }

            if (count == 0)
            {
                msg("- none");
            }
            return true;
        }

        if (args[0].equals("purge"))
        {
            FUtil.adminAction(sender.getName(), "Fine, here's the real version of Minecraft.", true);
            int count = 0;
            for (final Player player : this.server.getOnlinePlayers())
            {
                final FPlayer info = plugin.pl.getPlayer(player);
                if (info.isEditBlocked())
                {
                    info.setEditBlocked(false);
                    ++count;
                }
            }
            msg("Gave Minecraft back to " + count + " players.");
            return true;
        }

        if (args[0].equals("all"))
        {
            FUtil.adminAction(sender.getName(), "MINECRAFT? MORE LIKE NOMINE-CRAFT!", true);
            int counter = 0;
            for (final Player player : this.server.getOnlinePlayers())
            {
                if (!plugin.al.isAdmin(player))
                {
                    final FPlayer playerdata = plugin.pl.getPlayer(player);
                    playerdata.setEditBlocked(true);
                    ++counter;
                }
            }

            msg("Blocked block modification abilities for " + counter + " players.");
            return true;
        }

        final boolean smite = args[0].equals("-s");
        if (smite)
        {
            args = (String[])ArrayUtils.subarray(args, 1, args.length);
            if (args.length < 1)
            {
                return false;
            }
        }

        final Player player2 = getPlayer(args[0]);
        if (player2 == null)
        {
            sender.sendMessage(FreedomCommand.PLAYER_NOT_FOUND);
            return true;
        }

        String reason = null;
        if (args.length > 1)
        {
            reason = StringUtils.join(args, " ", 1, args.length);
        }

        final FPlayer pd = plugin.pl.getPlayer(player2);
        if (pd.isEditBlocked())
        {
            FUtil.adminAction(sender.getName(), "Fine " + player2.getName() + ", you can have Minecraft back", true);
            pd.setEditBlocked(false);
            msg("Gave Minecraft back to " + player2.getName());
            msg(player2, "Here's your Minecraft", ChatColor.RED);
        }
        else
        {

            FUtil.adminAction(sender.getName(), player2.getName() + ", MINECRAFT? MORE LIKE NOMINE-CRAFT", true);
            pd.setEditBlocked(true);

            if (smite)
            {
                Command_smite.smite(sender, player2, reason);
            }

            msg(player2, "Say goodbye to Minecraft!", ChatColor.RED);
            msg("Took away Minecraft from " + player2.getName());
        }
        return true;
    }
}
