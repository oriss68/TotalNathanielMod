package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.NON_OP, source = SourceType.BOTH)
@CommandParameters(description = "Sit in nearest minecart. You know you could just click on the Minecart right?", usage = "/<command> [partialname]")
public class Command_cartsit extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        Player targetPlayer = playerSender;

        if (args.length == 1 && plugin.al.isAdmin(sender))
        {

            targetPlayer = getPlayer(args[0]);

            if (targetPlayer == null)
            {
                sender.sendMessage(FreedomCommand.PLAYER_NOT_FOUND);
                return true;
            }
        }

        if (senderIsConsole)
        {
            if (targetPlayer == null)
            {
                sender.sendMessage("Hello Telnet pussy! You have to define a player name: /cartsit <player>");
                return true;
            }
        }

        if (targetPlayer.isInsideVehicle())
        {
            targetPlayer.getVehicle().eject();
        }
        else
        {
            Minecart nearest_cart = null;
            for (Minecart cart : targetPlayer.getWorld().getEntitiesByClass(Minecart.class))
            {
                if (cart.isEmpty())
                {
                    if (nearest_cart == null)
                    {
                        nearest_cart = cart;
                    }
                    else
                    {
                        if (cart.getLocation().distanceSquared(targetPlayer.getLocation()) < nearest_cart.getLocation().distanceSquared(targetPlayer.getLocation()))
                        {
                            nearest_cart = cart;
                        }
                    }
                }
            }

            if (nearest_cart != null)
            {
                nearest_cart.addPassenger(targetPlayer);
                msg("You know you could just right click on the Minecart, right?");
            }
            else
            {
                msg("There are no Minecarts.. so sad.");
            }
        }

        return true;
    }
}
