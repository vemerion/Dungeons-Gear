package com.infamous.dungeons_gear.items.melee;

import com.infamous.dungeons_gear.items.ItemRegistry;
import com.infamous.dungeons_gear.items.interfaces.IComboWeapon;
import com.infamous.dungeons_gear.items.interfaces.IMeleeWeapon;
import com.infamous.dungeons_gear.utilties.DescriptionHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.List;

public class RapierItem extends SwordItem implements IMeleeWeapon, IComboWeapon {
    private final boolean unique;

    public RapierItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder, boolean isUnique) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
        this.unique = isUnique;
    }

    @Override
    public int getComboLength(ItemStack stack, LivingEntity attacker) {
        return 14;
    }

    @Override
    public boolean hasFastThrusts(ItemStack stack) {
        return stack.getItem() instanceof RapierItem;
    }

    @Override
    public boolean hasBusyBeeBuiltIn(ItemStack stack) {
        return stack.getItem() == ItemRegistry.BEE_STINGER.get();
    }

    @Override
    public boolean hasFreezingBuiltIn(ItemStack stack) {
        return stack.getItem() == ItemRegistry.FREEZING_FOIL.get();
    }

    public Rarity getRarity(ItemStack itemStack) {

        if (this.unique) {
            return Rarity.RARE;
        }
        return Rarity.UNCOMMON;
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
        super.addInformation(stack, world, list, flag);
        DescriptionHelper.addFullDescription(list, stack);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.hurtResistantTime = 0;
        return super.hitEntity(stack, target, attacker);
    }
}
