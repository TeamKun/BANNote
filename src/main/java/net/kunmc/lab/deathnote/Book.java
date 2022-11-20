package net.kunmc.lab.deathnote;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class Book {
    public static ItemStack deathNote(){
        ItemStack itemStack = new ItemStack(Material.WRITABLE_BOOK);
        BookMeta bookmeta = (BookMeta) itemStack.getItemMeta();
        List<String> pages = new ArrayList<String>();
        pages.add("-----BanNote-----\n\n" +
                "1.BANしたい人物の正確なMCIDを、署名⇒本のタイトル欄に記入するとBANできます。\n\n" +
                "2.名前をもし間違って記入するとあなたがBANされます。\n\n" +
                "3.このノートを一度でも使った人間はその代償として、天国にも地獄にも行けない。");
        bookmeta.setPages(pages);
        bookmeta.setDisplayName(ChatColor.DARK_RED+"BanNote");
        bookmeta.setGeneration(BookMeta.Generation.COPY_OF_COPY);
        itemStack.setItemMeta(bookmeta);
        return itemStack;
    }
}
