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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.List;

public class DungeonsSwordItem extends net.minecraft.item.SwordItem implements IMeleeWeapon, IComboWeapon {
    @Override
    public int getComboLength(ItemStack stack, LivingEntity attacker) {
        return 3;
    }

    private final boolean unique;

    public DungeonsSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder, boolean isUnique) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
        this.unique = isUnique;
    }

    @Override
    public boolean hasSharpnessBuiltIn(ItemStack stack) {
        return stack.getItem() == ItemRegistry.DIAMOND_SWORD.get();
    }

    @Override
    public boolean hasCriticalHitBuiltIn(ItemStack stack) {
        return stack.getItem() == ItemRegistry.HAWKBRAND.get() ||
                stack.getItem() == ItemRegistry.SINISTER_SWORD.get();
    }

    public Rarity getRarity(ItemStack itemStack){

        if(this.unique){
            return Rarity.RARE;
        }
        return Rarity.UNCOMMON;
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
    {
        super.addInformation(stack, world, list, flag);
        DescriptionHelper.addFullDescription(list, stack);
    }
}
