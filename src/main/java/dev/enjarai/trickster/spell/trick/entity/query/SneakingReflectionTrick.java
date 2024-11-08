package dev.enjarai.trickster.spell.trick.entity.query;

import dev.enjarai.trickster.spell.Fragment;
import dev.enjarai.trickster.spell.Pattern;
import dev.enjarai.trickster.spell.SpellContext;
import dev.enjarai.trickster.spell.fragment.BooleanFragment;
import dev.enjarai.trickster.spell.blunder.BlunderException;

import java.util.List;

public class SneakingReflectionTrick extends AbstractLivingEntityQueryTrick {
    public SneakingReflectionTrick() {
        super(Pattern.of(2, 4, 7));
    }

    @Override
    public Fragment activate(SpellContext ctx, List<Fragment> fragments) throws BlunderException {
        return BooleanFragment.of(getLivingEntity(ctx, fragments, 0).isSneaking());
    }
}
