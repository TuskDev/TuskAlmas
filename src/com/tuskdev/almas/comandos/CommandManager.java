package com.tuskdev.almas.comandos;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.SimplePluginManager;

public class CommandManager
{
    public static void registerCommands(final Class<?>... classes) {
        try {
            CommandMap map = null;
            final Class<?> mappingReflection = SimplePluginManager.class;
            final Field field = mappingReflection.getDeclaredField("commandMap");
            field.setAccessible(true);
            map = (CommandMap)field.get(Bukkit.getPluginManager());
            for (final Class<?> clazz : classes) {
                Method[] declaredMethods;
                for (int length2 = (declaredMethods = clazz.getDeclaredMethods()).length, j = 0; j < length2; ++j) {
                    final Method method = declaredMethods[j];
                    if (method.isAnnotationPresent(Command.class)) {
                        final Command command = method.<Command>getAnnotation(Command.class);
                        final BukkitCommand commandRegister = new BukkitCommand(command.aliases()[0], command.description(), command.usage(), Arrays.<String>asList(command.aliases())) {
                            public boolean execute(final CommandSender sender, final String label, final String[] args) {
                                try {
                                    method.invoke(clazz.newInstance(), sender, args);
                                }
                                catch (Exception ex) {}
                                return false;
                            }
                        };
                        commandRegister.setPermission(command.permission());
                        map.register(command.aliases()[0], (org.bukkit.command.Command)commandRegister);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
