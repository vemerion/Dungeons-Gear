package com.infamous.dungeons_gear.items.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.infamous.dungeons_gear.DungeonsGear;
import com.infamous.dungeons_gear.items.armor.models.old_models.MercenaryArmorModel;
import com.infamous.dungeons_gear.items.armor.models.old_models.RenegadeArmorModel;
import com.infamous.dungeons_gear.items.ItemRegistry;
import com.infamous.dungeons_gear.items.interfaces.IArmor;
import com.infamous.dungeons_gear.utilties.DescriptionHelper;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.UUID;

public class MercenaryArmorItem extends ArmorItem implements IArmor {

    private static final UUID[] ARMOR_MODIFIERS = new UUID[]{
            UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"),
            UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
            UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
            UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};

    private final boolean unique;
    private final int damageReduceAmount;
    private final float toughness;
    private final Multimap<Attribute, AttributeModifier> attributeModifiers;

    public MercenaryArmorItem(IArmorMaterial armorMaterial, EquipmentSlotType slotType, Properties properties, boolean unique) {
        super(armorMaterial, slotType, properties);
        this.unique = unique;

        this.damageReduceAmount = armorMaterial.getDamageReductionAmount(slot);
        this.toughness = armorMaterial.getToughness();

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIERS[slot.getIndex()];
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", (double)this.damageReduceAmount, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", (double)this.toughness, AttributeModifier.Operation.ADDITION));
        if (this.knockbackResistance > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", (double)this.knockbackResistance, AttributeModifier.Operation.ADDITION));
        }
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "Armor attack damage boost", 0.20D * 0.5D, AttributeModifier.Operation.MULTIPLY_BASE));
        if(this.unique) {
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "Armor attack speed boost", 0.25D * 0.5D, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        this.attributeModifiers = builder.build();
    }


    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        if(stack.getItem() == ItemRegistry.MERCENARY_ARMOR.get() || stack.getItem() == ItemRegistry.MERCENARY_ARMOR_HELMET.get()){
            return DungeonsGear.MODID + ":textures/models/armor/mercenary_armor.png";
        }
        else if(stack.getItem() == ItemRegistry.RENEGADE_ARMOR.get() || stack.getItem() == ItemRegistry.RENEGADE_ARMOR_HELMET.get()){
            return DungeonsGear.MODID + ":textures/models/armor/renegade_armor.png";
        }
        else return "";
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @OnlyIn(Dist.CLIENT)
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack stack, EquipmentSlotType armorSlot, A _default) {
        if(stack.getItem() == ItemRegistry.MERCENARY_ARMOR.get() || stack.getItem() == ItemRegistry.MERCENARY_ARMOR_HELMET.get()){
            return (A) new MercenaryArmorModel<>(1.0F, slot, entityLiving);
        }
        else if(stack.getItem() == ItemRegistry.RENEGADE_ARMOR.get() || stack.getItem() == ItemRegistry.RENEGADE_ARMOR_HELMET.get()){
            return (A) new RenegadeArmorModel<>(1.0F, slot, entityLiving);
        }
        return null;
    }


    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        return equipmentSlot == this.slot ? this.attributeModifiers : super.getAttributeModifiers(equipmentSlot);
    }

    @Override
    public Rarity getRarity(ItemStack itemStack){
        if(this.unique) return Rarity.RARE;
        return Rarity.UNCOMMON;
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
        super.addInformation(stack, world, list, flag);
        DescriptionHelper.addLoreDescription(list, stack);
    }

    @Override
    public double getRangedDamage() {
        return 10;
    }
}
