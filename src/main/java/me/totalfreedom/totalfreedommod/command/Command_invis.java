package me.totalfreedom.totalfreedommod.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.GameMode;
import org.bukkit.potion.PotionEffectType;

@CommandPermissions(level = Rank.OP, source = SourceType.BOTH)
@CommandParameters(description = "Shows (optionally clears) invisible players", usage = "/<command> [clear|smite]")
public class Command_invis extends FreedomCommand
{
    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        boolean clear = false;
        boolean smite = false;

        if (args.length >= 1)
        {
            if (args[0].equalsIgnoreCase("clear"))
            {
                if(!plugin.al.isAdmin(sender))
                    return noPerms();
                else 
                {
                    FUtil.adminAction(sender.getName(), "Oh, you were invisible? Well too bad! Now you can be seen!", true);
                    clear = true;
                }
            } else if (args[0].equalsIgnoreCase("smite")){
                if(!plugin.al.isAdmin(sender))
                    return noPerms();
                else
                {
                    FUtil.adminAction(sender.getName(), "IT'S TIME TO STRIKE LIGHTING ON THE INVISIBLE PEOPLE", true);
                    smite = true;
                }
            }
            else
                return false;
        }

        List<String> players = new ArrayList<String>();
        int clears = 0;

        for (Player player : server.getOnlinePlayers())
        {
            if (player.hasPotionEffect(PotionEffectType.INVISIBILITY) && !plugin.al.vanished.contains(player))
            {
                players.add(player.getName());
                if (clear && !plugin.al.isAdmin(player))
                {
                    player.removePotionEffect((PotionEffectType.INVISIBILITY));
                    clears++;
                }
                else if(smite && !plugin.al.isAdmin(player))
                {
                    // Deop
                    player.setOp(false);

                    // Set gamemode to survival
                    player.setGameMode(GameMode.SURVIVAL);

                    // Clear inventory
                    player.getInventory().clear();

                    // Strike with lightning effect
                    final Location targetPos = player.getLocation();
                    final World world = player.getWorld();
                    for (int x = -1; x <= 1; x++)
                    {
                        for (int z = -1; z <= 1; z++)
                        {
                            final Location strike_pos = new Location(world, targetPos.getBlockX() + x, targetPos.getBlockY(), targetPos.getBlockZ() + z);
                            world.strikeLightning(strike_pos);
                        }
                    }

                    // Kill
                    player.setHealth(0.0);

                    clears++;
                }
            }
        }

        if (players.isEmpty())
        {
            msg("There are no invisible players");
            return true;
        }
        
        if (clear)
            msg(clears + " players, have been EXPOSED");
        else if(smite)
            msg(clears + " people have been smitted on!");
        else
            msg("Invisible players (" + players.size() + "): " + StringUtils.join(players, ", "));
            
        return true;
    }

    @Override
    public List<String> getTabCompleteOptions(CommandSender sender, Command command, String alias, String[] args)
    {
        if (args.length == 1 && plugin.al.isAdmin(sender))
            return Arrays.asList("clear","smite");

        return Collections.emptyList();
    }
}
