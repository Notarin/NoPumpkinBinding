package me.notarin.nopumpkinbinding;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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

public final class NoPumpkinBinding extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();
        getServer().getPluginManager().registerEvents(this, this);
        if (config.getBoolean("startup-message")) {
            System.out.println("NoPumpkinBinding Started!");
        }
    }
    @EventHandler
    public void pumpkin_enchant(PrepareAnvilEvent event) {
        AnvilInventory c = event.getInventory();
        ItemStack BaseItem = c.getItem(0);
        ItemStack Result = c.getItem(3);
        assert BaseItem != null;
        assert Result != null;
        boolean Check2 = Result.containsEnchantment(Enchantment.BINDING_CURSE);
        if (BaseItem.getType() == Material.CARVED_PUMPKIN && Check2) {
            System.out.println("Blocked pumpkin enchantment.");
            event.setResult(new ItemStack(Material.AIR));
        }
    }
    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("getthispumpkinoffme")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                try {
                    ItemStack i = p.getInventory().getHelmet();
                    assert i != null;
                    if (i.getType().name().contains("CARVED_PUMPKIN") && i.containsEnchantment(Enchantment.BINDING_CURSE)) {
                        System.out.println("Removing curse of binding.");
                        i.removeEnchantment(Enchantment.BINDING_CURSE);
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
