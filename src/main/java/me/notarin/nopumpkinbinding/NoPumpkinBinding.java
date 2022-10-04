package me.notarin.nopumpkinbinding;

import com.tchristofferson.configupdater.ConfigUpdater;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;

public final class NoPumpkinBinding extends JavaPlugin implements Listener {

    FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        //The config needs to exist before using the updater
        File configFile = new File(getDataFolder(), "config.yml");

        try {
            ConfigUpdater.update(this, "config.yml", configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        reloadConfig();
        getServer().getPluginManager().registerEvents(this, this);
        if (config.getBoolean("startup-message")) {
            System.out.println("NoPumpkinBinding Started!");
        }
    }
    public void onDisable() {
        saveDefaultConfig();
    }
    @EventHandler
    public void pumpkin_enchant(PrepareAnvilEvent event) {
        AnvilInventory c = event.getInventory();
        ItemStack BaseItem = c.getItem(0);
        ItemStack Result = c.getItem(3);
        assert BaseItem != null;
        if (!(Result == null)) {
            boolean Check2 = Result.containsEnchantment(Enchantment.BINDING_CURSE);
            if (BaseItem.getType() == Material.CARVED_PUMPKIN && Check2) {
                System.out.println("Blocked pumpkin enchantment.");
                event.setResult(new ItemStack(Material.AIR));
            }
        }
    }
    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            World w = p.getWorld();
            if (command.getName().equalsIgnoreCase("getthispumpkinoffme")) {
                try {
                    ItemStack i = p.getInventory().getHelmet();
                    assert i != null;
                    if (i.getType().name().contains("CARVED_PUMPKIN") && i.containsEnchantment(Enchantment.BINDING_CURSE)) {
                        if (!config.getBoolean("drop")) {
                            System.out.println("Removing curse of binding.");
                            i.removeEnchantment(Enchantment.BINDING_CURSE);
                        }
                        else {
                            if (!config.getBoolean("break")) {
                                p.sendMessage("Dropping pumpkin.");
                                w.dropItem(p.getLocation(), i);
                            }
                            else {
                                p.sendMessage("Destroying pumpkin.");
                            }
                            i.setAmount(0);
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "You are not wearing a pumpkin with the Curse of Binding!");
                    }
                } catch (Exception e) {
                    p.sendMessage(ChatColor.RED + "You're not wearing anything on your head!");
                }
            }
        }
        return true;
    }
}
