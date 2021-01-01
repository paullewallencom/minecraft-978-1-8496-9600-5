package com.codisimus.enchanter;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Enchants the item that the command sender is holding
 */
public class EnchantCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String alias, String[] args) {
        //This command can only be executed by Players
        if (!(sender instanceof Player)) {
            return false;
        }

        //Cast the command sender to a Player
        Player player = (Player) sender;

        //Retrieve the ItemStack that the Player is holding
        ItemStack hand = player.getItemInHand();

        //Return if the Player is not holding an Item
        if (hand.getType() == Material.AIR) {
            return false;
        }

        //Add a level 10 Knockback enchantment
        Enchantment enchant = Enchantment.KNOCKBACK;
        hand.addUnsafeEnchantment(enchant, 10);

        //Add a level 1 Fire Aspect enchantment
        enchant = Enchantment.FIRE_ASPECT;
        hand.addUnsafeEnchantment(enchant, 1);

        player.sendMessage("Your item will now push people backwards and light them on fire!");
        return true;
    }

}
