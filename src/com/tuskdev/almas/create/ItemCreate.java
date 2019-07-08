package com.tuskdev.almas.create;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.md_5.bungee.api.ChatColor;

public class ItemCreate {

	private ItemStack is;

	public ItemCreate(Material m) {
		this(m, 1, (short) 0);
	}
	
	public ItemCreate(int id) {
		this(id, 1, (short) 0);
	}

	public ItemCreate(ItemStack is) {
		this.is = is.clone();
	}

	public ItemCreate(Material m, int amount, short data) {
		is = new ItemStack(m, amount, data);
	}

	@SuppressWarnings("deprecation")
	public ItemCreate(int id, int amount, short data) {
		is = new ItemStack(id, amount, data);
	}
	
	public ItemCreate clone() {
		return new ItemCreate(is);
	}

	public ItemCreate durability(int dur) {
		is.setDurability((short) dur);
		return this;
	}

	public ItemCreate name(String name) {
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		is.setItemMeta(im);
		return this;
	}

	public ItemCreate unsafeEnchantment(Enchantment ench, int level) {
		is.addUnsafeEnchantment(ench, level);
		return this;
	}

	public ItemCreate enchant(Enchantment ench, int level) {
		ItemMeta im = is.getItemMeta();
		im.addEnchant(ench, level, true);
		is.setItemMeta(im);
		return this;
	}

	public ItemCreate removeEnchantment(Enchantment ench) {
		is.removeEnchantment(ench);
		return this;
	}

	public ItemCreate owner(String owner) {
		try {
			SkullMeta im = (SkullMeta) is.getItemMeta();
			im.setOwner(owner);
			is.setItemMeta(im);
		} catch (ClassCastException expected) {
		}
		return this;
	}

	public ItemCreate infinityDurabilty() {
		is.setDurability(Short.MAX_VALUE);
		return this;
	}

	public ItemCreate lore(String... lore) {
		ItemMeta im = is.getItemMeta();
		List<String> out = im.getLore() == null ? new ArrayList<>() : im.getLore();
		for (String string : lore)
			out.add(ChatColor.translateAlternateColorCodes('&', string));
		im.setLore(out);
		is.setItemMeta(im);
		return this;
	}

	public ItemCreate listlore(List<String> lore) {
		ItemMeta im = is.getItemMeta();
		im.setLore(lore);
		is.setItemMeta(im);
		return this;
	}

	@SuppressWarnings("deprecation")
	public ItemCreate woolColor(DyeColor color) {
		if (!is.getType().equals(Material.WOOL))
			return this;
		is.setDurability(color.getDyeData());
		return this;
	}

	public ItemCreate amount(int amount) {
		if (amount > 64)
			amount = 64;
		is.setAmount(amount);
		return this;
	}

	public ItemCreate removeAttributes() {
		ItemMeta meta = is.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		is.setItemMeta(meta);
		return this;
	}

	public ItemStack build() {
		return is;
	}

	public ItemCreate color(Color color) {
		if (!is.getType().name().contains("LEATHER_"))
			return this;
		LeatherArmorMeta meta = (LeatherArmorMeta) is.getItemMeta();
		meta.setColor(color);
		is.setItemMeta(meta);
		return this;
	}
	
	public ItemCreate head(String texture) {
//		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
//		GameProfile profile = (texture, UUID.randomUUID());
//		ItemMeta headMeta = head.getItemMeta();
//	    Class<?> headMetaClass = headMeta.getClass();
//		RefSet(headMetaClass, headMeta, "profile", profile);
		return this;
	}

	
	public static boolean RefSet(Class<?> sourceClass, Object instance, String fieldName, Object value) {
      try {
         Field field = sourceClass.getDeclaredField(fieldName);
         Field modifiersField = Field.class.getDeclaredField("modifiers");
         int modifiers = modifiersField.getModifiers();
         if (!field.isAccessible()) {
            field.setAccessible(true);
         }

         if ((modifiers & 16) == 16) {
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, modifiers & -17);
         }

         try {
            field.set(instance, value);
         } finally {
            if ((modifiers & 16) == 16) {
               modifiersField.setInt(field, modifiers | 16);
            }

            if (!field.isAccessible()) {
               field.setAccessible(false);
            }

         }

         return true;
      } catch (Exception var11) {
         Bukkit.getLogger().log(Level.WARNING, "Unable to inject Gameprofile", var11);
         return false;
      }
   }
	
}