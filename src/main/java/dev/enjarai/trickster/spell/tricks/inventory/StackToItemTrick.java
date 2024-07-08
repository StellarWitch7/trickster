package dev.enjarai.trickster.spell.tricks.inventory;

import dev.enjarai.trickster.spell.Fragment;
import dev.enjarai.trickster.spell.Pattern;
import dev.enjarai.trickster.spell.SpellContext;
import dev.enjarai.trickster.spell.fragment.FragmentType;
import dev.enjarai.trickster.spell.fragment.ItemTypeFragment;
import dev.enjarai.trickster.spell.tricks.Trick;
import dev.enjarai.trickster.spell.tricks.blunder.BlunderException;

import java.util.List;

public class StackToItemTrick extends Trick {
    public StackToItemTrick() {
        super(Pattern.of(1, 4, 7, 8, 5, 2, 4, 6, 3, 4));
    }

    @Override
    public Fragment activate(SpellContext ctx, List<Fragment> fragments) throws BlunderException {
        var stack = expectInput(fragments, FragmentType.ITEM_STACK, 0).stack();
        return new ItemTypeFragment(stack.getItem());
    }
}