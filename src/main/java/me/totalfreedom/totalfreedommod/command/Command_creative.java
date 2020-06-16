package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.OP, source = SourceType.BOTH)
@CommandParameters(description = "Become Creative", usage = "/<command> <-a | [partialname]>", aliases = "gmc")
public class Command_creative extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            if (isConsole())
            {
                sender.sendMessage("When you're a telnet pussy, you must define a target player.");
                return true;
            }

            playerSender.setGameMode(GameMode.CREATIVE);
            msg("You're now creative");
            return true;
        }

        checkRank(Rank.SUPER_ADMIN);

        if (args[0].equals("-a"))
        {
            for (Player targetPlayer : server.getOnlinePlayers())
            {
                targetPlayer.setGameMode(GameMode.CREATIVE);
            }

            FUtil.adminAction(sender.getName(), "Everyone is now creative", false);
            msg("You're now creative");
            return true;
        }

        Player player = getPlayer(args[0]);

        if (player == null)
        {
            sender.sendMessage(FreedomCommand.PLAYER_NOT_FOUND);
            return true;
        }

        msg("Making " + player.getName() + " creative");
        msg(player, sender.getName() + " made you creative");
        player.setGameMode(GameMode.CREATIVE);

        return true;
    }
}
