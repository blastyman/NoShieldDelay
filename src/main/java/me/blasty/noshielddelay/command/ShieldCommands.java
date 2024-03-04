package me.blasty.noshielddelay.command;

import me.blasty.noshielddelay.NoShieldDelay;
import me.blasty.noshielddelay.listener.ShieldListener;
import me.vaperion.blade.annotation.argument.Name;
import me.vaperion.blade.annotation.argument.Sender;
import me.vaperion.blade.annotation.command.Async;
import me.vaperion.blade.annotation.command.Command;
import me.vaperion.blade.annotation.command.Description;
import me.vaperion.blade.annotation.command.Permission;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import static net.kyori.adventure.text.Component.text;

public class ShieldCommands {

    @Async
    @Command(value = {"shield maxdelay", "shield maxd"})
    @Permission("shield.admin")
    @Description("Customise the max delay for shields.")
    public static void shieldMaxDelayCommand(@Sender Player sender, @Name("value") int value) {
        if (value < 0) {
            sender.sendMessage(text("Make sure maximum shield delay is not negative.", NamedTextColor.RED));
            return;
        }

        NoShieldDelay.getInstance().getConfig().set("max-shield-delay", value);
        NoShieldDelay.getInstance().saveConfig();

        ShieldListener.MAX_SHIELD_DELAY = value;
        sender.sendMessage(text("Successfully set max shield delay to ", NamedTextColor.GREEN)
                .append(text(value, NamedTextColor.YELLOW).append(text(".", NamedTextColor.GREEN))));
    }

    @Async
    @Command(value = {"shield mindelay", "shield mind"})
    @Permission("shield.admin")
    @Description("Customise the min delay for shields.")
    public static void shieldMinDelayCommand(@Sender Player sender, @Name("value") int value) {
        if (value < 0) {
            sender.sendMessage(text("Make sure minimum shield delay is not negative.", NamedTextColor.RED));
            return;
        }

        NoShieldDelay.getInstance().getConfig().set("min-shield-delay", value);
        NoShieldDelay.getInstance().saveConfig();

        ShieldListener.MIN_SHIELD_DELAY = value;
        sender.sendMessage(text("Successfully set minimum shield delay to ", NamedTextColor.GREEN)
                .append(text(value, NamedTextColor.YELLOW).append(text(".", NamedTextColor.GREEN))));
    }
}
