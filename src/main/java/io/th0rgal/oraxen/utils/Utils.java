package io.th0rgal.oraxen.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;

public class Utils {

    private Utils() {
    }

    public static List<String> toLowercaseList(final String... values) {
        final ArrayList<String> list = new ArrayList<>();
        for (final String value : values)
            list.add(value.toLowerCase(Locale.ENGLISH));
        return list;
    }

    public static String[] toLowercase(final String... values) {
        for (int index = 0; index < values.length; index++)
            values[index] = values[index].toLowerCase();
        return values;
    }

    public static long getVersion(final String format) {
        return Long.parseLong(OffsetDateTime.now().format(DateTimeFormatter.ofPattern(format)));
    }

    public static String removeExtension(String s) {

        String separator = System.getProperty("file.separator");
        String filename;

        // Remove the path upto the filename.
        int lastSeparatorIndex = s.lastIndexOf(separator);
        if (lastSeparatorIndex == -1) filename = s;
        else filename = s.substring(lastSeparatorIndex + 1);

        // Remove the extension.
        int extensionIndex = filename.lastIndexOf(".");
        if (extensionIndex == -1)
            return filename;

        return filename.substring(0, extensionIndex);
    }

    public static void writeStringToFile(final File file, final String content) {
        file.getParentFile().mkdirs();
        try (final FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static int firstEmpty(Map<String, Integer> map, int min) {
        while (map.containsValue(min))
            min++;
        return min;
    }

    public static void swingHand(Player player, EquipmentSlot hand) {
        if (hand == EquipmentSlot.HAND) player.swingMainHand();
        else player.swingOffHand();
    }

    /**
     * @param itemStack The ItemStack to edit the ItemMeta of
     * @param function  The function-block to edit the ItemMeta in
     * @return The original ItemStack with the new ItemMeta
     */
    public static ItemStack editItemMeta(ItemStack itemStack, Consumer<ItemMeta> function) {
        ItemMeta meta = itemStack.getItemMeta();
        function.accept(meta);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

}
