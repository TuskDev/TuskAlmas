package com.tuskdev.almas.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.tuskdev.almas.Main;
import com.tuskdev.almas.utils.AlmasUtils;

public class AlmasListeners implements Listener {

	@EventHandler
	public static void death(PlayerDeathEvent e) {
		Player p = e.getEntity();
		Random rnd = new Random();
		int n = rnd.nextInt(100);
		int chance = Main.pl.getConfig().getInt("Configuracoes.Porcentagem");
		if (n < chance) {
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
			SkullMeta meta = (SkullMeta) skull.getItemMeta();
			meta.setOwner(p.getName());
			List<String> lore = new ArrayList<String>();
			lore.add("");
			lore.add("§eValor: §f1 alma's");
			lore.add("");
			meta.setDisplayName("§eCabeça de: §f" + p.getName());
			meta.setLore(lore);
			skull.setItemMeta(meta);
			p.getWorld().dropItem(p.getLocation(), skull);
		}
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
			return;
		if (e.getInventory().getName().equalsIgnoreCase(Main.pl.getConfig().getString("Configuracoes.TopGuiName"))) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasDisplayName()
				&& p.getItemInHand().getItemMeta().getDisplayName().contains("§eCabeça de: §f")) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void interact(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack item = p.getItemInHand();
		if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)
				|| (e.getAction() == Action.LEFT_CLICK_AIR) || (e.getAction() == Action.LEFT_CLICK_BLOCK)) {
			if (!item.hasItemMeta())
				return;
			if (item.getType() == Material.SKULL_ITEM
					&& item.getItemMeta().getDisplayName().contains("§eCabeça de: §f")) {
				if (p.getItemInHand().getAmount() == 1) {
					p.setItemInHand(null);
					p.updateInventory();
					AlmasUtils.addAlmas(p.getName(), 1);
					p.sendMessage("");
					p.sendMessage("§eAlma's aumentada em 1");
					p.sendMessage("§eVoce ganhou 10 moedas, as quais poderam ser usadas em breve");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "money add " + p.getName() + " 10");
					p.sendMessage("");
					return;
				}
				if (p.getItemInHand().getAmount() > 1) {
					p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
					p.updateInventory();
					AlmasUtils.addAlmas(p.getName(), 1);
					p.sendMessage("");
					p.sendMessage("§eAlma's aumentada em 1");
					p.sendMessage("§eVoce ganhou 10 moedas, as quais poderam ser usadas em breve");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "money add " + p.getName() + " 10");
					p.sendMessage("");
					return;
				}
			}
		}
	}
}
