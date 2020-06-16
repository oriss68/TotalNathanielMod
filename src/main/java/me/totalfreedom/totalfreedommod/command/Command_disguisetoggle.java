package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.libsdisguises.BlockedDisguises;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "YOU NO DISGUISE", usage = "/<command>", aliases = "dtoggle")
public class Command_disguisetoggle extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (!plugin.ldb.isEnabled())
        {
            msg("Get LibsDisguises you loser.");
            return true;
        }

        FUtil.adminAction(sender.getName(), (BlockedDisguises.disabled ? "HELLO" : "GOODBYE") + " LIBSDISGUISES", false);

        if (plugin.ldb.isDisguisesEnabled())
        {
            plugin.ldb.undisguiseAll(true);
            plugin.ldb.setDisguisesEnabled(false);
        }
        else
        {
            plugin.ldb.setDisguisesEnabled(true);
        }

        msg("People are now saying " + (BlockedDisguises.disabled ? "goodbye" : "hello") + " to LibsDisguises");

        return true;
    }
}
