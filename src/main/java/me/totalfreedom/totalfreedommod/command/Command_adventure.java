package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.OP, source = SourceType.BOTH)
@CommandParameters(description = "Adventure mode, more like nomine-craft mode because you can't break blocks", usage = "/<command> <[partialname] | -a>", aliases = "gma")
public class Command_adventure extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            if (isConsole())
            {
                sender.sendMessage("Yyou must define a target player, telnet pussy!");
                return true;
            }

            playerSender.setGameMode(GameMode.ADVENTURE);
            msg("LETS GO ON AN ADVENTURE");
            return true;
        }

        checkRank(Rank.SUPER_ADMIN);

        if (args[0].equals("-a"))
        {
            for (Player targetPlayer : server.getOnlinePlayers())
            {
                targetPlayer.setGameMode(GameMode.ADVENTURE);
            }

            FUtil.adminAction(sender.getName(), "I'm going to abuse my admin powers and change everyone's gamemode to Adventure", false);
            msg("LETS GO ON AN ADVENTURE");
            return true;
        }

        Player player = getPlayer(args[0]);

        if (player == null)
        {
            sender.sendMessage(FreedomCommand.PLAYER_NOT_FOUND);
            return true;
        }

        msg("Setting " + player.getName() + " to game mode adventure.");
        msg(player, sender.getName() + " set your game mode to adventure. You should file a suspension request on them");
        player.setGameMode(GameMode.ADVENTURE);
        return true;
    }
}
