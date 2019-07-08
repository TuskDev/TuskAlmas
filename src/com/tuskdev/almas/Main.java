package com.tuskdev.almas;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.tuskdev.almas.api.TopAPI;
import com.tuskdev.almas.comandos.ComandoAlmas;
import com.tuskdev.almas.comandos.CommandManager;
import com.tuskdev.almas.listeners.AlmasListeners;

public class Main extends JavaPlugin {
	public static Main pl;

	public static HashMap<String, Integer> ranks = new HashMap<>();

	static {
		Main.ranks = new HashMap<String, Integer>();

	}

	public void onEnable() {
		pl = this;
		pl.saveDefaultConfig();
		loadCommands();
		loadListeners();
		TopAPI.SaveHash();
		debug("&aVersao: " + getDescription().getVersion());
		debug("&aAuthor: " + getDescription().getAuthors());
	}

	public static void loadCommands() {
		CommandManager.registerCommands(ComandoAlmas.class);
	}

	public void loadListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new AlmasListeners(), this);
	}

	public void debug(String msg) {
		String msg2 = msg.replace("&", "§");
		getServer().getConsoleSender().sendMessage("§aImperioPlug-ins - " + msg2);
	}

	public void onDisable() {
		debug("&aPlugin Desativado");
	}
}
