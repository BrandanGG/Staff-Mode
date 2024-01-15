package com.brandan.staffmode.commands;

import com.destroystokyo.paper.profile.PlayerProfile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.ban.ProfileBanList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import com.brandan.staffmode.utils.CalendarConversion;
import java.util.Arrays;
import java.util.Calendar;

public class Tempban implements CommandExecutor{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if ((args.length >= 2) && command.getName().equalsIgnoreCase("tempban")){
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            PlayerProfile profile = Bukkit.createProfile(target.getUniqueId());
            ProfileBanList list = Bukkit.getBanList(BanList.Type.PROFILE);
            if (args.length >= 3){
                String reason = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                Calendar cal = CalendarConversion.convertTime(args[1]);
                if (cal != null){
                    list.addBan(profile, reason, cal.getTime(), null);
                    if (target.isOnline()) {
                        Player onlinePlayer = target.getPlayer();
                        assert onlinePlayer != null;
                        onlinePlayer.kick(Component.text("You have been temporarily banned!\n", NamedTextColor.RED));
                    }
                } else {
                    sender.sendMessage(Component.text("Error: Invalid time format", NamedTextColor.RED));
                    return false;
                }
                sender.sendMessage(Component.text(target.getName() + " has been temporarily banned for: " + reason, NamedTextColor.GRAY));
            } else {
                sender.sendMessage(Component.text("Error: Usage is /tempban <player> <time> <reason>", NamedTextColor.RED));
            }
        } else {
            sender.sendMessage(Component.text("Error: Usage is /tempban <player> <time> <reason>", NamedTextColor.RED));
        }

        return false;
    }
}