package ptgms.industries.pl1;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Pl1 extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("joinas")) {
            if (sender.isOp()) {
                System.out.println(args.length);
                if (args.length == 0 || args.length >= 3) {
                    sender.sendMessage("You didn't give me the right amount of arguments! (/joinas (optional)COLOR NAME)");
                    return false;
                }
                ChatColor color;
                List<String> list = new ArrayList<>(Arrays.asList(args));
                if (args.length != 1) {
                    color = getColor(args[0]);
                    list.remove(0);
                } else {
                    color = ChatColor.WHITE;
                }
                String name = list.toArray(new String[0])[0];
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(color + name + ChatColor.YELLOW + " joined the game");
                }
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "Sorry, you don't have permission to use this command!");
            }
        }

        if (label.equalsIgnoreCase("sayas")) {
            if (sender.isOp()) {
                if (args.length <= 1) {
                    sender.sendMessage("Not enough arguments!");
                    return false;
                }
                ChatColor color;
                List<String> list = new ArrayList<>(Arrays.asList(args));
                switch(args[0]) {
                    case "red":
                        color = ChatColor.RED;
                        list.remove(0);
                        break;
                    case "blue":
                        color = ChatColor.BLUE;
                        list.remove(0);
                        break;
                    case "black":
                        color = ChatColor.BLACK;
                        list.remove(0);
                        break;
                    default:
                        color = ChatColor.WHITE;
                        break;
                }
                for (Player player : Bukkit.getOnlinePlayers()) {

                    String name = list.get(0);
                    list.remove(0);
                    String[] message = list.toArray(new String[0]);
                    StringBuilder finalMessage = new StringBuilder();
                    for (String s : message) {
                        finalMessage.append(s).append(" ");
                    }
                    if (finalMessage.toString().equals("")) {
                        sender.sendMessage("Not enough arguments!");
                        return false;
                    }
                    player.sendMessage(ChatColor.WHITE + "<" + color + name + ChatColor.WHITE + "> " + finalMessage);
                }
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "Sorry, you don't have permission to use this command!");
            }
        }
        return false;
    }

    ChatColor getColor(String color) {
        ChatColor colorReturn;
        switch (color) {
            case "red":
                colorReturn = ChatColor.RED;
                break;
            case "blue":
                colorReturn = ChatColor.BLUE;
                break;
            case "black":
                colorReturn = ChatColor.BLACK;
                break;
            case "yellow":
                colorReturn = ChatColor.YELLOW;
                break;
            case "purple":
                colorReturn = ChatColor.LIGHT_PURPLE;
                break;
            default:
                colorReturn = ChatColor.YELLOW;
                break;
        }
        return colorReturn;
    }
}
