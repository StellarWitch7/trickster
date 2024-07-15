package dev.enjarai.trickster.entity;

import dev.enjarai.trickster.Trickster;
import dev.enjarai.trickster.entity.projectile.ItemProjectileEntity;
import dev.enjarai.trickster.spell.tricks.Trick;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ModEntities {
    public static final EntityType<ItemProjectileEntity> ITEM_PROJECTILE_ENTITY_TYPE = register("item_projectile_entity_type",
            EntityType.Builder.create(ItemProjectileEntity::createType, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .eyeHeight(0.13f)
                    .maxTrackingRange(4)
                    .trackingTickInterval(20));

    public static final TagKey<EntityType<?>> MANA_DEVOID = TagKey.of(RegistryKeys.ENTITY_TYPE, Trickster.id("mana_devoid"));

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        return Registry.register(Registries.ENTITY_TYPE, Trickster.id(name), builder.build(name));
    }

    public static void register() {

    }
}
