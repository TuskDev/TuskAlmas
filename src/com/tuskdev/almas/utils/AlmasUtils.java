package com.tuskdev.almas.utils;

import com.tuskdev.almas.Main;

public class AlmasUtils {

	public static void setAlmas(String nome, int almas) {
		Main.pl.getConfig().set("Almas." + nome, almas);
		Main.pl.saveConfig();
	}

	public static int getAlmas(String nome) {
		return Main.pl.getConfig().getInt("Almas." + nome);

	}

	public static void removeAlmas(String nome, int almas) {
		setAlmas(nome, getAlmas(nome) - almas);
		Main.pl.saveConfig();
	}

	public static void addAlmas(String nome, int almas) {
		setAlmas(nome, getAlmas(nome) + almas);
		Main.pl.saveConfig();
	}
}
