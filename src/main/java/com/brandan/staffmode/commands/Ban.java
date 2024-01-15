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

import java.time.Duration;
import java.util.Arrays;

public class Ban implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if ((args.length >= 1) && command.getName().equalsIgnoreCase("ban")) {
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            PlayerProfile profile = Bukkit.createProfile(target.getUniqueId());
            ProfileBanList list = Bukkit.getBanList(BanList.Type.PROFILE);
            if (args.length >= 2) { // if command is /ban {player} [reason]
                String reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                list.addBan(profile, reason, (Duration) null, null);
                //if player is online
                if (target.isOnline()) {
                    Player onlinePlayer = target.getPlayer();
                    assert onlinePlayer != null;
                    onlinePlayer.kick(Component.text(reason, NamedTextColor.RED));
                }
                sender.sendMessage(Component.text(target.getName() + " has been banned for: " + reason, NamedTextColor.GREEN));
            } else { // if command is just /ban {player}
                list.addBan(profile, null, (Duration) null, null);
                //if player is online
                if (target.isOnline()) {
                    Player onlinePlayer = target.getPlayer();
                    assert onlinePlayer != null;
                    onlinePlayer.kick(Component.text("You have been banned!", NamedTextColor.RED));
                }
                sender.sendMessage(Component.text(target.getName() + " has been banned", NamedTextColor.GREEN));
            }
        } else {
            sender.sendMessage(Component.text("Usage: /ban <player> <reason>", NamedTextColor.RED));
        }
        return false;
    }
}