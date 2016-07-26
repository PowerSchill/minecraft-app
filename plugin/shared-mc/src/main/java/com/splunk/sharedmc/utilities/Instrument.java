package com.splunk.sharedmc.utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giy4 on 7/23/16.
 */
public class Instrument {

    private String item;
    private List enchantments;


    public Instrument(String item) {

        this.enchantments = new ArrayList();
        this.item = item;
    }


    public void addEnchantment(String item, int level) {
        Enchantment enchantment = new Enchantment(item, level);


        addEnchantment(enchantment);
    }

    public void addEnchantment(Enchantment enchantment) {


        enchantments.add(enchantment);

    }


}
