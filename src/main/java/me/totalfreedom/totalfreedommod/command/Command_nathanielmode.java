package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.player.FPlayer;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.NON_OP, blockHostConsole = true, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Activate your Nathaniel_428 godly powers", usage = "/<command>", aliases = "nathaniel,nathaniel428,nathaniel428mode")

public class Command_nathanielmode extends FreedomCommand
{
    @Override
    protected boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if(!playerSender.getName().equals("Nathaniel_428")){
            msg("Only Nathaniel_428 can run this command", ChatColor.RED);
            return true;
        }
        FPlayer fplayer = plugin.pl.getPlayer(playerSender);
        fplayer.setNathanielMode(!fplayer.isNathanielMode());
        FUtil.adminAction(playerSender.getName(), (fplayer.isNathanielMode() ? "Enabling" : "Disabling") + " my godly powers", false);
        return true;
    }
}
