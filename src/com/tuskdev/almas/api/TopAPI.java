package com.tuskdev.almas.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import org.bukkit.event.Listener;

import com.tuskdev.almas.Main;

public class TopAPI implements Listener {

	static ArrayList<String> arrayOrganizado = new ArrayList<String>();
	static ArrayList<String> arrayOrganizado2 = new ArrayList<String>();


	public static void SaveHash() {
		for (String path : Main.pl.getConfig().getConfigurationSection("Lista.").getKeys(false)) {
			Main.getRanks().put(path, Main.pl.getConfig().getInt("Lista." + path));
		}
	}

	public static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
		SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
				int res = e2.getValue().compareTo(e1.getValue());
				return res != 0 ? res : 1;
			}
		});
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

	public static String getTop(int i) {
		arrayOrganizado.clear();
		for (Entry<String, Integer> bla : entriesSortedByValues(Main.getRanks())) {
			arrayOrganizado.add(bla.getKey());
		}
		try {
			return arrayOrganizado.get(i - 1);
		} catch (Exception e) {
			return "Nenhum";
		}
	}
	public static String getTop1() {
		arrayOrganizado2.clear();
		for (Entry<String, Integer> bla : entriesSortedByValues(Main.getRanks())) {
			arrayOrganizado2.add(bla.getKey());
		}
		try {
			return arrayOrganizado2.get(0);
		} catch (Exception e) {
			return "Nenhum";
		}
	}
}
