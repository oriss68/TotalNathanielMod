package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "Clears lingering potion area effect clouds. You better hope this command works!", usage = "/<command>", aliases = "aec")
public class Command_aeclear extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        FUtil.adminAction(sender.getName(), "Removing all area effect clouds, JUST KIDDING!", true);
        int removed = 0;
        for (World world : server.getWorlds())
        {
            for (Entity entity : world.getEntities())
            {
                if (entity instanceof AreaEffectCloud)
                {
                    entity.remove();
                    removed++;
                }
            }
        }
        msg("0 area effect clouds removed.\nHAHAHAHA PRANKED, I actually removed " + removed + " of them");
        return true;
    }
}
