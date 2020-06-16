package me.totalfreedom.totalfreedommod.command;

import java.util.Collections;
import java.util.List;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.OP, source = SourceType.BOTH)
@CommandParameters(description = "Clear your inventory.", usage = "/<command> [player]", aliases = "ci,clear")
public class Command_clearinventory extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {

        if (args.length < 1)
        {
            if (senderIsConsole)
            {
                return false;
            }

            playerSender.getInventory().clear();
            msg("GOOD BYE INVENTORY!");
        }
        else
        {
            if (plugin.al.isAdmin(sender))
            {
                if (args[0].equals("-a"))
                {
                    FUtil.adminAction(sender.getName(), "NO MORE ITEMS FOR YOU!", true);
                    for (Player player : server.getOnlinePlayers())
                    {
                        player.getInventory().clear();
                    }
                    msg("Everyone will be kissing their items goodbye!");
                }
                else
                {
                    Player player = getPlayer(args[0]);

                    if (player == null)
                    {
                        msg(PLAYER_NOT_FOUND);
                        return true;
                    }

                    player.getInventory().clear();
                    msg("Cleared " + player.getName() + "'s inventory, now " + player.getName() + " will file an abuse report on you");
                    player.sendMessage(sender.getName() + " has cleared your inventory, maybe it's time to file an admin abuse report");
                }
            }
            else
            {
                return noPerms();
            }
        }

        return true;
    }

    @Override
    public List<String> getTabCompleteOptions(CommandSender sender, Command command, String alias, String[] args)
    {
        if (args.length == 1 && plugin.al.isAdmin(playerSender))
        {
            List<String> players = FUtil.getPlayerList();
            players.add("-a");
            return players;
        }

        return Collections.emptyList();
    }
}
