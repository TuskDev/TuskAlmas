package com.tuskdev.almas.comandos;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.tuskdev.almas.Main;
import com.tuskdev.almas.api.AlmasAPI;
import com.tuskdev.almas.api.TopAPI;
import com.tuskdev.almas.utils.ItemBuilder;
import com.tuskdev.almas.utils.ItemTeste;
import com.tuskdev.almas.utils.ScrollerInventory;

public class ComandoAlmas {

	@Command(aliases = { "almas" }, usage = "/almas", description = "Almas Comandos")
	public void onRegister(final Player p, final String[] args) {
		if (args.length == 0) {
			p.sendMessage("§eSuas Almas: §f" + AlmasAPI.getAlmas(p.getName()));
			return;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("top")) {
				ArrayList<ItemStack> items = new ArrayList<>();
				Main.ranks.clear();
				TopAPI.SaveHash();
				for (Entry<String, Integer> bla : TopAPI.entriesSortedByValues(Main.ranks)) {

					items.add(new ItemBuilder(Material.SKULL_ITEM).durability(3).owner(bla.getKey())
							.name("§eCabeça de: §f" + bla.getKey()).lore("§eValor: §f" + bla.getValue() + " alma's")
							.build());
				}
				ScrollerInventory scroll = new ScrollerInventory(items,
						Main.pl.getConfig().getString("Configuracoes.TopGuiName"), p);
				ScrollerInventory.users.put(p.getUniqueId(), scroll);
				return;
			}
		}
		if (p.isOp()) {
			if (args.length == 0) {
				p.sendMessage("§6Tusk Almas - comandos");
				p.sendMessage("§e/almas set <Player> <Quantidade> - §eseta as almas");
				p.sendMessage("§e/almas remover <Player> <Quantidade> - §fremover as almas");
				p.sendMessage("§e/almas add <Player> <Quantidade> - §fadiciona almas");
				p.sendMessage("§e/almas ver <Player> - §fvê almas do player");
				p.sendMessage("§e/almas top - §fvê jogadores top almas do servidor");
				return;
			}

			if (args[0].equalsIgnoreCase("set")) {
				if (args.length < 3) {
					p.sendMessage("§eUtilize: §7/almas set <Player> <Quantidade>");
				} else {
					Player target = Bukkit.getPlayerExact(args[1]);
					int Kills = Integer.parseInt(args[2]);
					if (target == null) {
						p.sendMessage("§cEste Jogador Não existe!");
						return;
					} else {
						AlmasAPI.setAlmas(target.getName(), Kills);
						p.sendMessage("§aAlmas de §7" + target.getName() + " §aForam alteradas para §7"
								+ AlmasAPI.getAlmas(target.getName()));
						Main.ranks.clear();
						TopAPI.SaveHash();
					}
				}
			}
			if (args[0].equalsIgnoreCase("remover")) {
				if (args.length < 3) {
					p.sendMessage("§eUtilize: §7/almas remover <Player> <Quantidade>");
				} else {
					Player target = Bukkit.getPlayerExact(args[1]);
					int Kills = Integer.parseInt(args[2]);
					if (target == null) {
						p.sendMessage("§cEste Jogador Não existe!");
						return;
					} else {
						AlmasAPI.removeKills(p.getName(), Kills);
						p.sendMessage("§aAlmas de §7" + target.getName() + " §aForam alteradas para §7"
								+ AlmasAPI.getAlmas(target.getName()));
						Main.ranks.clear();
						TopAPI.SaveHash();
					}
				}
			}
			if (args[0].equalsIgnoreCase("teste")) {
				if (args.length < 2) {
					p.sendMessage("§eUtilize: §7/almas teste <jogador> <quantia>");
					return;
				}
				Player p2 = Bukkit.getServer().getPlayer(args[1]);
				if (p2 == null) {
					p.sendMessage("§cEste usuário não está online.");
					return;
				}
				try {
					int quantia = Integer.parseInt(args[2]);
					new ItemTeste(p, quantia);
					p.sendMessage("§eEnviados " + quantia + " ItemTeste para " + p.getName() + ".");
				} catch (Exception e) {
					p.sendMessage("§cVocê inseriu uma quantia inválida.");

				}
			}
			if (args[0].equalsIgnoreCase("add")) {
				if (args.length < 3) {
					p.sendMessage("§eUtilize: §7/almas add <Player> <Quantidade>");
				} else {
					Player target = Bukkit.getPlayerExact(args[1]);
					int Kills = Integer.parseInt(args[2]);
					if (target == null) {
						p.sendMessage("§cEste Jogador Não existe!");
						return;
					} else {
						AlmasAPI.AddKills(p.getName(), Kills);
						p.sendMessage("§aAlmas de §7" + target.getName() + " §aForam alteradas para §7"
								+ AlmasAPI.getAlmas(target.getName()));
						Main.ranks.clear();
						TopAPI.SaveHash();
					}
				}
			}
			if (args[0].equalsIgnoreCase("ver")) {
				if (args.length < 2) {
					p.sendMessage("§eUtilize: §7/almas ver <Player>");
				} else {
					Player target = Bukkit.getPlayerExact(args[1]);
					if (target == null) {
						p.sendMessage("§cEste Jogador Não existe!");
						return;
					} else {
						p.sendMessage("§aAlmas de §7" + target.getName() + " §aQuantia: §7"
								+ AlmasAPI.getAlmas(target.getName()));
					}
				}
			}
		}
		return;
	}
}
