package com.infamous.dungeons_gear.groups;

import com.infamous.dungeons_gear.items.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class MeleeWeaponGroup extends ItemGroup
{
    public MeleeWeaponGroup()
    {
        super("melee_weapons");
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(ItemRegistry.BROADSWORD.get());
    }
}
