package dev.enjarai.trickster.entity.projectile;

import dev.enjarai.trickster.entity.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class ItemProjectileEntity extends ProjectileEntity {
    private static final TrackedData<ItemStack> STACK = DataTracker.registerData(ItemProjectileEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);

    public ItemProjectileEntity(EntityType<? extends ItemProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public ItemProjectileEntity(ItemEntity item, World world) {
        this(ModEntities.ITEM_PROJECTILE_ENTITY_TYPE, world);
        this.setPosition(item.getX(), item.getY(), item.getZ());
        this.dataTracker.set(STACK, item.getStack().copy());
        item.setDespawnImmediately();
    }

    public static ItemProjectileEntity createType(EntityType<? extends ItemProjectileEntity> entityType, World world) {
        return new ItemProjectileEntity(entityType, world);
    }

    public ItemStack getStack() {
        return dataTracker.get(STACK);
    }

    public Item getItem() {
        return dataTracker.get(STACK).getItem();
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(STACK, ItemStack.EMPTY);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (getWorld().isClient())
            return;

        dropStack(dataTracker.get(STACK));
        super.onEntityHit(entityHitResult);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        var world = getWorld();

        if (!world.isClient() && getItem() instanceof BlockItem blockItem) {
            var pos = blockHitResult.getBlockPos();
            var block = blockItem.getBlock();
            var newState = block.getDefaultState();

            if (newState.canPlaceAt(world, pos)) {
                world.setBlockState(pos, newState);
            } else {
                dropStack(dataTracker.get(STACK));
            }
        }

        super.onBlockHit(blockHitResult);
    }
}
