package com.tuskdev.almas.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemTeste {

	public ItemTeste(Player p, int quantia) {
		for (int i = 0; i < quantia; i++) {
			if (p.getInventory().firstEmpty() == -1) {
				p.getLocation().getWorld().dropItemNaturally(p.getLocation(), head(p));
			} else {
				p.getInventory().addItem(head(p));
			}
		}
	}
	
	private ItemStack head(Player p) {
		return new ItemBuilder(Material.SKULL_ITEM).durability(3).owner(p.getName())
				.name("§eCabeça de: §f"+p.getName())
				.lore("", "","§eValor: §f1 alma's","")
				.build();
	}
}
