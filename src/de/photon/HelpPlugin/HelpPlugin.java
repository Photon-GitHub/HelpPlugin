package de.photon.HelpPlugin;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HelpPlugin extends JavaPlugin{

    @Override
    public void onEnable()
    {
        try
        {
            FileConfiguration cfg = getConfig();
            FileConfigurationOptions cfgOptions = cfg.options();
            cfgOptions.copyDefaults(true);
            saveConfig();
            this.getLogger().info(" enabled.");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable()
    {
        this.getLogger().info(" disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try
        {
            if(command.getName().equalsIgnoreCase("help") && sender.hasPermission("helpplugin.help"))
            {
                if(sender instanceof Player)
                {
                    Player p = (Player) sender;

                    String soundconf = getConfig().getString("Sound");
                    p.playSound(p.getLocation(), Sound.valueOf(soundconf), 10.0F, 1.0F);
                }

                for (String line : getConfig().getStringList("messages")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
                }
            }else if(command.getName().equalsIgnoreCase("HelpPlugin"))
            {
                if(args[0].equalsIgnoreCase("reload") && sender.hasPermission("helpplugin.reload"))
                {
                    this.reloadConfig();
                    sender.sendMessage(ChatColor.DARK_GREEN + "HelpPlugin reloaded.");
                }
            }
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
