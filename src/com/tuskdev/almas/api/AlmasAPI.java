package com.tuskdev.almas.api;

import com.tuskdev.almas.Main;

public class AlmasAPI {

	public static void setAlmas(String nome, int almas) {
		Main.pl.getConfig().set("Almas." + nome, almas);
		Main.pl.saveConfig();
	}

	public static int getAlmas(String nome) {
		return Main.pl.getConfig().getInt("Almas." + nome);

	}

	public static void removeKills(String nome, int almas) {
		setAlmas(nome, getAlmas(nome) - almas);
		Main.pl.saveConfig();
	}

	public static void AddKills(String nome, int almas) {
		setAlmas(nome, getAlmas(nome) + almas);
		Main.pl.saveConfig();
	}

	public static String setColor(String msg) {
		return msg.replace("&", "§");
	}
}
