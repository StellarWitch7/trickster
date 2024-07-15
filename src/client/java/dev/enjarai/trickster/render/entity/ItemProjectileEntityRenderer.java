package dev.enjarai.trickster.render.entity;

import dev.enjarai.trickster.entity.projectile.ItemProjectileEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ItemProjectileEntityRenderer extends EntityRenderer<ItemProjectileEntity> {
    private final ItemRenderer itemRenderer;

    public ItemProjectileEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(ItemProjectileEntity itemProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.multiply(this.dispatcher.getRotation());

        this.itemRenderer.renderItem(itemProjectileEntity.getStack(), ModelTransformationMode.GROUND, i, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumerProvider, itemProjectileEntity.getWorld(), itemProjectileEntity.getId());
        matrixStack.pop();
        super.render(itemProjectileEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(ItemProjectileEntity entity) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
    }
}
