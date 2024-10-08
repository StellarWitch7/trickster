package dev.enjarai.trickster.item;

import dev.enjarai.trickster.cca.ModEntityComponents;
import dev.enjarai.trickster.item.component.ModComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class WandItem extends Item {
    public WandItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var stack = user.getStackInHand(hand);

        if (!world.isClient()) {
            var spell = stack.get(ModComponents.SPELL);
            if (spell != null) {
                ModEntityComponents.CASTER.get(user).queueSpell(spell.spell(), List.of());
            }
        }

        return TypedActionResult.success(stack);
    }
}
