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

// usage
// /unban <player>
//I hope this works?
// TODO: add /unban handling -- test -- add errors


public class Unban implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player && sender.hasPermission("staffmode.unban")){
            if ((args.length == 1) && (command.getName().equalsIgnoreCase("unban"))){
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                PlayerProfile profile = Bukkit.createProfile(target.getUniqueId());
                ProfileBanList list = Bukkit.getBanList(BanList.Type.PROFILE);
                list.pardon(profile);
                sender.sendMessage(Component.text("Player " + target.getName() + " is unbanned.", NamedTextColor.GREEN));
            }else{
                sender.sendMessage(Component.text("Error: Usage is /unban <player>", NamedTextColor.RED));
            }
        }

        return false;
    }
}
